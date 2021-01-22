package ru.maxim.borisov.messenger.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.web.multipart.MultipartFile;
import ru.maxim.borisov.messenger.dto.get.ProfilePictureDownloadGetDto;
import ru.maxim.borisov.messenger.dto.get.ProfilePictureGetDto;
import ru.maxim.borisov.messenger.entity.ProfilePicture;

import java.io.IOException;

@Mapper(componentModel = "spring")
public interface ProfilePictureMapper {

    @Mapping(target = "user", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user.id", source = "userId")
    @Mapping(target = "data", source = "file", qualifiedByName = "getData")
    @Mapping(target = "name", expression = "java(file.getOriginalFilename())")
    ProfilePicture toEntity(Long userId, MultipartFile file);

    @Mapping(target = "size", expression = "java(file.getData().length)")
    ProfilePictureGetDto toGetDto(ProfilePicture file);

    ProfilePictureDownloadGetDto toDownloadGetDto(ProfilePicture file);

    @Named("getData")
    default byte[] getData(MultipartFile file) throws IOException {
        return file.getBytes();
    }
}
