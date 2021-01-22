package ru.maxim.borisov.messenger.service;

import org.springframework.web.multipart.MultipartFile;
import ru.maxim.borisov.messenger.dto.get.FileDownloadGetDto;
import ru.maxim.borisov.messenger.dto.get.FileGetDto;

import java.util.List;

/**
 * Сервис для взаимодействия с файлами сообщений.
 */
public interface MessageFileService {

    /**
     * Получение всех файлов определенного сообщения
     *
     * @param messageId идентификатор сообщения
     * @return список мета-информации о файлах
     */
    List<FileGetDto> getAllMessageFiles(Long messageId);

    /**
     * Метод для загрузки файла из системы.
     *
     * @param fileId идентификатор файла
     * @return файл с мета-информацией
     */
    FileDownloadGetDto downloadMessageFile(Long fileId);

    /**
     * Метод для сохранения файла в систему.
     *
     * @param messageId идентификатор сообщения
     * @param file      файл
     * @return мета-информация о сохраненном в системе файле
     */
    FileGetDto saveMessageFile(Long messageId, MultipartFile file);
}
