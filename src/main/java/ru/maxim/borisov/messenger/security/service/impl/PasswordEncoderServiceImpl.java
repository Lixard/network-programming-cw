package ru.maxim.borisov.messenger.security.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.maxim.borisov.messenger.security.service.PasswordEncoderService;

/**
 * {@inheritDoc}
 */
@Service
public class PasswordEncoderServiceImpl implements PasswordEncoderService {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public PasswordEncoderServiceImpl(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String encode(String password) {
        return passwordEncoder.encode(password);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean matches(CharSequence raw, String encoded) {
        return passwordEncoder.matches(raw, encoded);
    }
}
