package ru.maxim.borisov.messenger.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.maxim.borisov.messenger.dto.create.UserCreateDto;
import ru.maxim.borisov.messenger.dto.get.UserGetDto;
import ru.maxim.borisov.messenger.mapper.UserMapper;
import ru.maxim.borisov.messenger.repository.UserRepository;
import ru.maxim.borisov.messenger.security.service.PasswordEncoderService;
import ru.maxim.borisov.messenger.service.UserService;

import java.util.List;

/**
 * {@inheritDoc}
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoderService encoderService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           UserMapper userMapper,
                           PasswordEncoderService encoderService) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.encoderService = encoderService;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserGetDto create(UserCreateDto userCreateDto) {
        final var user = userMapper.fromCreateDto(userCreateDto);
        user.setPassword(encoderService.encode(user.getPassword()));
        final var userWithEncodedPass = userRepository.save(user);
        return userMapper.toGetDto(userWithEncodedPass);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UserGetDto> getAll() {
        return userMapper.toGetDto(userRepository.findAll());
    }

    @Override
    public UserGetDto getById(long userId) {
        return userRepository.findById(userId)
                .map(userMapper::toGetDto)
                .orElse(null);
    }
}
