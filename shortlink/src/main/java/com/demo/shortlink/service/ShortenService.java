package com.demo.shortlink.service;


import com.demo.shortlink.dto.Response;
import com.demo.shortlink.dto.ShortLinkRequest;
import com.demo.shortlink.dto.ShortLinkResponse;
import org.springframework.web.servlet.view.RedirectView;

public interface ShortenService {
    ShortLinkResponse shorten(ShortLinkRequest originalLink);
    Long stats(String shortlink);
    void delete(String shortlink);
    String getFullUrl(String shortlink);
}
