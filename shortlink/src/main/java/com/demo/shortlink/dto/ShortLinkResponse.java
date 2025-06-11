package com.demo.shortlink.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class ShortLinkResponse implements Serializable {
    private String shortLink;
}
