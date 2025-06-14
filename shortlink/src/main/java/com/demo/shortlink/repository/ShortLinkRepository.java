package com.demo.shortlink.repository;

import com.demo.shortlink.model.ShortLink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShortLinkRepository extends JpaRepository<ShortLink, Long> {
    Optional<ShortLink> findByOriginalUrl(String originalUrl);

    Optional<ShortLink> findByShortCode(String shortCode);
}
