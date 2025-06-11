package com.demo.shortlink.util;

import com.demo.shortlink.config.ShortLinkConfig;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
@AllArgsConstructor
public class ShortUrlUtil {
    private ShortLinkConfig shortLinkConfig;

    public String generateUniqueKey() {
        int key = shortLinkConfig.getKeyLength();
        String allowedCharacters = shortLinkConfig.getAllowedCharacters();

        StringBuilder keyBuilder = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < key; i++) {
            int randomInd = random.nextInt(allowedCharacters.length());
            keyBuilder.append(allowedCharacters.charAt(randomInd));
        }
        return keyBuilder.toString();
    }
}
