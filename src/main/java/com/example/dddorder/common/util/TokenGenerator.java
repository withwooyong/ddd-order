package com.example.dddorder.common.util;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.UUID;

public class TokenGenerator {
    private static final int TOKEN_LENGTH = 32;

    public static String randomCharacter(int length) {
        return RandomStringUtils.randomAlphanumeric(length);
    }

    public static String randomCharacterWithPrefix(String prefix) {
        return prefix + randomCharacter(TOKEN_LENGTH - prefix.length());
    }

    // UUID 32자리
    public static String getUUID() {
        return UUID.randomUUID().toString();
    }

    // 자릿수 줄여서 개발하면 중복발생 가능성 높아짐. prefix + 32자리
    public static String getUUID(String prefix) {
        String id = UUID.randomUUID().toString();
        return prefix + id;
    }
}