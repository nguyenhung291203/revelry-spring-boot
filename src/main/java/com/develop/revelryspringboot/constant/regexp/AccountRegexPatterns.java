package com.develop.revelryspringboot.constant.regexp;

public class AccountRegexPatterns {

    public static final String USERNAME_PATTERN = "^[a-zA-Z][a-zA-Z0-9_]{4,19}$";

    public static final String PASSWORD_PATTERN = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d).{8,}$";

    public static final String EMAIL_PATTERN = "^[\\w-.]+@[\\w-]+\\.[a-zA-Z]{2,}$";
}
