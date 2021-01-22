package ru.maxim.borisov.messenger.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.maxim.borisov.messenger.dto.get.FileDownloadGetDto;
import ru.maxim.borisov.messenger.dto.get.FileGetDto;
import ru.maxim.borisov.messenger.mapper.MessageFileMapper;
import ru.maxim.borisov.messenger.repository.MessageFileRepository;
import ru.maxim.borisov.messenger.repository.MessageRepository;
import ru.maxim.borisov.messenger.service.MessageFileService;

import java.util.List;

/**
 * {@inheritDoc}
 */
@Service
public class MessageFileServiceImpl implements MessageFileService {

    private final MessageRepository messageRepository;
    private final MessageFileRepository messageFileRepository;
    private final MessageFileMapper messageFileMapper;

    @Autowired
    public MessageFileServiceImpl(MessageRepository messageRepository,
                                  MessageFileRepository messageFileRepository,
                                  MessageFileMapper messageFileMapper) {
        this.messageRepository = messageRepository;
        this.messageFileRepository = messageFileRepository;
        this.messageFileMapper = messageFileMapper;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<FileGetDto> getAllMessageFiles(Long messageId) {
        final var message = messageRepository.findById(messageId).orElseThrow();
        return messageFileMapper.toGetDto(message.getMessageFiles());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FileDownloadGetDto downloadMessageFile(Long fileId) {
        final var file = messageFileRepository.findById(fileId).orElseThrow();
        return messageFileMapper.toDownloadGetDto(file);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FileGetDto saveMessageFile(Long messageId, MultipartFile file) {
        final var messageFile = messageFileMapper.toEntity(messageId, file);
        final var saved = messageFileRepository.save(messageFile);
        return messageFileMapper.toGetDto(saved);
    }
}
