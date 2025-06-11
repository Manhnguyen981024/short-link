package com.demo.shortlink.exception;

import java.io.Serial;

public class ShortLinkNotFoundException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;

    public ShortLinkNotFoundException(String shortLink) {
        super(" The short code: "  + shortLink + " is not found.");
    }
}
