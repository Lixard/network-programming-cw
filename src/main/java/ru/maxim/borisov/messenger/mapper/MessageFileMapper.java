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

@Mapper(componentModel = "spring")
public interface MessageFileMapper {

    @Mapping(target = "message", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "data", source = "file", qualifiedByName = "getData")
    @Mapping(target = "message.id", source = "messageId")
    @Mapping(target = "mimeType", expression = "java(file.getContentType())")
    @Mapping(target = "name", expression = "java(file.getOriginalFilename())")
    MessageFile toEntity(Long messageId, MultipartFile file);

    @Mapping(target = "size", expression = "java((long) file.getData().length)")
    @Mapping(target = "messageId", source = "message.id")
    FileGetDto toGetDto(MessageFile file);

    FileDownloadGetDto toDownloadGetDto(MessageFile file);

    List<FileGetDto> toGetDto(List<MessageFile> files);

    @Named("getData")
    default byte[] getData(MultipartFile file) throws IOException {
        return file.getBytes();
    }
}
