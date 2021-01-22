package ru.maxim.borisov.messenger.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.web.multipart.MultipartFile;
import ru.maxim.borisov.messenger.dto.get.FileDownloadGetDto;
import ru.maxim.borisov.messenger.dto.get.FileGetDto;
import ru.maxim.borisov.messenger.entity.MessageFile;

import java.io.IOException;
import java.util.List;

/**
 * Преобразователь моделей, относящихся к сущности файлов сообщений.
 */
@Mapper(componentModel = "spring")
public interface MessageFileMapper {

    /**
     * Преобразование файла в сущность базы данных.
     *
     * @param messageId идентификатор сообщения
     * @param file      файл
     * @return сущность файла
     */
    @Mapping(target = "message", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "data", source = "file", qualifiedByName = "getData")
    @Mapping(target = "message.id", source = "messageId")
    @Mapping(target = "mimeType", expression = "java(file.getContentType())")
    @Mapping(target = "name", expression = "java(file.getOriginalFilename())")
    MessageFile toEntity(Long messageId, MultipartFile file);

    /**
     * Преобразование сущность в dto с мета-информацией о файле.
     *
     * @param file сущность
     * @return dto
     */
    @Mapping(target = "size", expression = "java((long) file.getData().length)")
    @Mapping(target = "messageId", source = "message.id")
    FileGetDto toGetDto(MessageFile file);

    /**
     * Преобразование сущности в dto с файлом.
     *
     * @param file сущность
     * @return dto
     */
    FileDownloadGetDto toDownloadGetDto(MessageFile file);

    /**
     * Преобразование списка сущностей в список dto с мета-иноформацией о файлах.
     *
     * @param files список сущностей
     * @return список dto
     */
    List<FileGetDto> toGetDto(List<MessageFile> files);

    /**
     * Метод для для получания байтов из MultipartFile в массив байтов сущности файла.
     * Используется mapstruct'ом.
     *
     * @param file multipart file
     * @return массив байт
     * @throws IOException ошибка
     */
    @Named("getData")
    default byte[] getData(MultipartFile file) throws IOException {
        return file.getBytes();
    }
}
