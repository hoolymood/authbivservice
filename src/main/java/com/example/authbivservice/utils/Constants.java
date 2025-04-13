package com.example.authbivservice.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Constants {
    public static final String REGEX_PHONE_NUMBER = "^8[0-9]{10}$";
    public static final String REGEX_CODE = "^[1-9]{1}[0-9]{5}$";

}
