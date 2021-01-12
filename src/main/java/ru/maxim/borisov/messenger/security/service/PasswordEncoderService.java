package ru.maxim.borisov.messenger.security.service;

public interface PasswordEncoderService {

    String encode(final String password);

    boolean matches(CharSequence raw, String encoded);
}
