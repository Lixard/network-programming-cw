package ru.maxim.borisov.messenger.security.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.maxim.borisov.messenger.repository.UserRepository;
import ru.maxim.borisov.messenger.security.model.UserDetailsImpl;

/**
 * Имплементация сервиса создания UserDetails бина.
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Метод, позволяющий создавать UserDetails при поиске по имени пользователя. ВАЖНО чтобы в системе имя
     * пользователя было уникальным.
     *
     * @param username УНИКАЛЬНОЕ имя пользователя
     * @return UserDetails bean
     */
    @Override
    public UserDetails loadUserByUsername(String username) {
        return new UserDetailsImpl(userRepository.findOneByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found")));
    }
}
