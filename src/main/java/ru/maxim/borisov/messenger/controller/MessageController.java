package ru.maxim.borisov.messenger.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import ru.maxim.borisov.messenger.dto.create.MessageCreateDto;
import ru.maxim.borisov.messenger.dto.get.FileGetDto;
import ru.maxim.borisov.messenger.dto.get.MessageGetDto;
import ru.maxim.borisov.messenger.security.model.CurrentUser;
import ru.maxim.borisov.messenger.service.MessageFileService;
import ru.maxim.borisov.messenger.service.MessageService;

import java.util.List;


/**
 * Контроллер для взаимодействия с сообщениями.
 */
@RestController
@RequestMapping(
        value = "/messages",
        produces = MediaType.APPLICATION_JSON_VALUE
)
public class MessageController {

    private final CurrentUser currentUser;
    private final MessageService messageService;
    private final SimpMessagingTemplate simpMessagingTemplate;
    private final MessageFileService messageFileService;

    @Autowired
    public MessageController(CurrentUser currentUser,
                             MessageService messageService,
                             SimpMessagingTemplate simpMessagingTemplate,
                             MessageFileService messageFileService) {
        this.currentUser = currentUser;
        this.messageService = messageService;
        this.simpMessagingTemplate = simpMessagingTemplate;
        this.messageFileService = messageFileService;
    }

    /**
     * Регистрация нового сообщения в системе. При получении нового сообщения отправляет только что полученное
     * сообщение через websocket всем активным пользователям чата, чтобы поддерживать live статус.
     *
     * @param message сообщение
     * @return зарегистрированное в системе сообщение
     */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public MessageGetDto processMessages(@RequestBody MessageCreateDto message) {
        message.setSenderId(currentUser.getId());
        final var messageGetDto = messageService.sendMessage(message);
        simpMessagingTemplate.convertAndSend("/topic/chats/" + message.getChatId(), messageGetDto);
        return messageGetDto;
    }

    /**
     * Загрузка файла сообщения в систему.
     *
     * @param messageId идентификатор сообщения
     * @param file      файл
     * @return зарегистрированный в системе файл
     */
    @PostMapping(
            path = "/{messageId}/files",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public FileGetDto saveMessageFile(@PathVariable Long messageId, @RequestBody MultipartFile file) {
        return messageFileService.saveMessageFile(messageId, file);
    }

    /**
     * Получение мета-данных о всех файлах определенного сообщения.
     *
     * @param messageId идентификатор сообщения.
     * @return список мета-данных о файлах
     */
    @GetMapping(path = "/{messageId}/files")
    public List<FileGetDto> getAllMessageFiles(@PathVariable Long messageId) {
        return messageFileService.getAllMessageFiles(messageId);
    }

    /**
     * Загрузка файла из системы.
     *
     * @param messageId идентификатор сообщения (в прекрасном будущем должен использоваться для валидации и проверки
     *                  на права доступа)
     * @param fileId    идентификатор файла
     * @return массив байт, представляющих собой сам файл
     */
    @GetMapping(path = "/{messageId}/files/{fileId}")
    public ResponseEntity<byte[]> downloadMessageFile(@PathVariable Long messageId, @PathVariable Long fileId) {
        final var file = messageFileService.downloadMessageFile(fileId);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getName() + "\"")
                .body(file.getData());
    }

}
