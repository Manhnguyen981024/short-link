package com.demo.shortlink.controller;

import com.demo.shortlink.dto.ShortLinkRequest;
import com.demo.shortlink.dto.ShortLinkResponse;
import com.demo.shortlink.model.LinkAccessLog;
import com.demo.shortlink.model.ShortLink;
import com.demo.shortlink.repository.LinkAccessLogRepository;
import com.demo.shortlink.repository.ShortLinkRepository;
import com.demo.shortlink.service.GeoIpService;
import com.demo.shortlink.service.LinkAccessLogService;
import com.demo.shortlink.service.ShortenService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@AllArgsConstructor
public class ShortLinkController {
    private final ShortenService shortenService;
    private final ShortLinkRepository shortLinkRepository;
    private final GeoIpService geoIpService;
    private final LinkAccessLogService linkAccessLogService;


    @PostMapping("/shorten")
    public ResponseEntity<ShortLinkResponse> shorten(@RequestBody ShortLinkRequest shortLinkRequest) {
        return ResponseEntity.ok(shortenService.shorten(shortLinkRequest));
    }

    @GetMapping("/{shortLink}")
    public RedirectView redirect(@PathVariable String shortLink, HttpServletRequest request) {
        RedirectView redirectView = new RedirectView(shortenService.getFullUrl(shortLink));
        redirectView.setStatusCode(HttpStatus.MOVED_PERMANENTLY);

        String ip = extractClientIP(request);
        String userAgent = request.getHeader("User-Agent");
        String location = geoIpService.getLocationByIP(ip);

        ShortLink link = shortLinkRepository.findByShortCode(shortLink).orElse(null);
        LinkAccessLog linkAccessLog = LinkAccessLog.builder()
                .shortLink(link)
                .location(location)
                .userAgent(userAgent)
                .ipAddress(ip)
                .build();
        linkAccessLogService.save(linkAccessLog);

        return redirectView;
    }

    @GetMapping("stats/{shortLink}")
    public ResponseEntity<Long> stats(@PathVariable String shortLink) {
        return ResponseEntity.ok(shortenService.stats(shortLink));
    }

    @DeleteMapping("/shorten/{shortLink}")
    public ResponseEntity<Void> deleteShortLink(@PathVariable String shortLink) {
        shortenService.delete(shortLink);
        return ResponseEntity.noContent().build();
    }

    public String extractClientIP(HttpServletRequest request) {
        String header = request.getHeader("X-Forwarded-For");
        if (header != null && !header.isEmpty()) {
            return header.split(",")[0]; // lấy IP đầu tiên
        }
        return request.getRemoteAddr();
    }
}
