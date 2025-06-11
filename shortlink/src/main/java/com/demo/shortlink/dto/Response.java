package com.demo.shortlink.dto;

import lombok.Data;

@Data
public class Response {
    private int code;
    private String msg;
    private ShortLinkResponse shortLinkResponse;
}
