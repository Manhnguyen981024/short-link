package com.demo.shortlink.service.impl;

import com.demo.shortlink.dto.Response;
import com.demo.shortlink.dto.ShortLinkRequest;
import com.demo.shortlink.dto.ShortLinkResponse;
import com.demo.shortlink.exception.ShortLinkNotFoundException;
import com.demo.shortlink.model.ShortLink;
import com.demo.shortlink.repository.ShortLinkRepository;
import com.demo.shortlink.service.ShortenService;
import com.demo.shortlink.util.ShortUrlUtil;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class ShortenServiceImpl implements ShortenService {
    private final ShortLinkRepository shortLinkRepository;
    private final ShortUrlUtil shortUrlUtil;

    @Override
    @Cacheable(cacheNames = "short-links" , key = "#originalLink.fullLinkUrl")
    public ShortLinkResponse shorten(ShortLinkRequest originalLink) {
        String fullUrl = originalLink.getFullLinkUrl();

        ShortLink existingShortlink = shortLinkRepository.findByOriginalUrl(fullUrl).orElse(null);
        if (existingShortlink != null) {
            return ShortLinkResponse.builder().shortLink(existingShortlink.getShortCode()).build();
        } else {
            String newKey = shortUrlUtil.generateUniqueKey();
            ShortLink shortLink = ShortLink.builder()
                    .shortCode(newKey)
                    .originalUrl(fullUrl)
                    .createdAt(LocalDateTime.now())
                    .expirationAt(LocalDateTime.now().plusDays(7L))
                    .hitCount(0L)
                    .build();

            shortLinkRepository.save(shortLink);
            return ShortLinkResponse.builder().shortLink(shortLink.getShortCode()).build();
        }
    }

    @Override
    public Long stats(String shortlink) {
        ShortLink shortLink = shortLinkRepository.findByShortCode(shortlink).orElse(null);
        if (shortLink == null)
            throw new ShortLinkNotFoundException(shortlink);
        return shortLink.getHitCount();
    }

    @Override
    @CacheEvict(cacheNames = "short-links" , key = "#shortlink")
    public void delete(String shortlink) {
        ShortLink shortLink = shortLinkRepository.findByShortCode(shortlink).orElse(null);
        if (shortLink == null)
            throw new ShortLinkNotFoundException(shortlink);
        this.shortLinkRepository.delete(shortLink);
    }

    @Cacheable(cacheNames = "short-links" , key = "#shortlink")
    public String getFullUrl(String shortlink) {
        ShortLink shortLink = shortLinkRepository.findByShortCode(shortlink).orElse(null);
        if (shortLink == null)
            throw new RuntimeException("<UNK>");

        shortLink.setHitCount(shortLink.getHitCount() + 1);
        shortLinkRepository.save(shortLink);
        return shortLink.getOriginalUrl();
    }
}
