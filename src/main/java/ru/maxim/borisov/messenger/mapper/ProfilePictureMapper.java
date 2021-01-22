package ru.maxim.borisov.messenger.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.web.multipart.MultipartFile;
import ru.maxim.borisov.messenger.dto.get.ProfilePictureDownloadGetDto;
import ru.maxim.borisov.messenger.dto.get.ProfilePictureGetDto;
import ru.maxim.borisov.messenger.entity.ProfilePicture;

import java.io.IOException;

/**
 * Преобразователь моделей, относящихся к сущности аватарок пользователя.
 */
@Mapper(componentModel = "spring")
public interface ProfilePictureMapper {

    /**
     * Создание сущности базы данных из MultipartFile.
     *
     * @param userId идентификатор пользователя
     * @param file   MultipartFile
     * @return сущность базы данных
     */
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user.id", source = "userId")
    @Mapping(target = "data", source = "file", qualifiedByName = "getData")
    @Mapping(target = "name", expression = "java(file.getOriginalFilename())")
    ProfilePicture toEntity(Long userId, MultipartFile file);

    /**
     * Преобразование сущности в dto c мета-информацией о файле.
     *
     * @param file сущность
     * @return dto
     */
    @Mapping(target = "size", expression = "java(file.getData().length)")
    ProfilePictureGetDto toGetDto(ProfilePicture file);

    /**
     * Преобразование сущности dto с файлом.
     *
     * @param file сущность
     * @return dto
     */
    ProfilePictureDownloadGetDto toDownloadGetDto(ProfilePicture file);

    /**
     * Метод для получения массива байтов из MultipartFile.
     * Используется mapstruct'ом.
     *
     * @param file multipart file
     * @return массив байтов
     * @throws IOException ошибка ввода/вывода
     */
    @Named("getData")
    default byte[] getData(MultipartFile file) throws IOException {
        return file.getBytes();
    }
}
