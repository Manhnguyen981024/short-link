package com.demo.shortlink.controller;

import com.demo.shortlink.dto.ShortLinkRequest;
import com.demo.shortlink.dto.ShortLinkResponse;
import com.demo.shortlink.service.ShortenService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@AllArgsConstructor
public class ShortLinkController {
    private final ShortenService shortenService;

    @PostMapping("/shorten")
    public ResponseEntity<ShortLinkResponse> shorten(@RequestBody ShortLinkRequest shortLinkRequest) {
        return ResponseEntity.ok(shortenService.shorten(shortLinkRequest));
    }

    @GetMapping("/{shortLink}")
    public RedirectView redirect(@PathVariable String shortLink) {
        RedirectView redirectView = new RedirectView(shortenService.getFullUrl(shortLink));
        redirectView.setStatusCode(HttpStatus.MOVED_PERMANENTLY);
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
}
