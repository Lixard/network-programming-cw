package ru.maxim.borisov.messenger.service;

import org.springframework.web.multipart.MultipartFile;
import ru.maxim.borisov.messenger.dto.get.FileDownloadGetDto;
import ru.maxim.borisov.messenger.dto.get.FileGetDto;

import java.util.List;

public interface MessageFileService {

    List<FileGetDto> getAllMessageFiles(Long messageId);

    FileDownloadGetDto downloadMessageFile(Long fileId);

    FileGetDto saveMessageFile(Long messageId, MultipartFile file);
}
