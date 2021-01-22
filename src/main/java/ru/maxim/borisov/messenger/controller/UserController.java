package ru.maxim.borisov.messenger.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import ru.maxim.borisov.messenger.dto.create.UserCreateDto;
import ru.maxim.borisov.messenger.dto.get.ProfilePictureGetDto;
import ru.maxim.borisov.messenger.dto.get.UserGetDto;
import ru.maxim.borisov.messenger.service.ProfilePictureService;
import ru.maxim.borisov.messenger.service.UserService;

import java.util.List;


@RestController
@RequestMapping(
        path = "/users",
        produces = MediaType.APPLICATION_JSON_VALUE
)
public class UserController {

    private final UserService userService;
    private final ProfilePictureService profilePictureService;

    @Autowired
    public UserController(UserService userService,
                          ProfilePictureService profilePictureService) {
        this.userService = userService;
        this.profilePictureService = profilePictureService;
    }

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public UserGetDto create(@RequestBody UserCreateDto user) {
        return userService.create(user);
    }

    @GetMapping
    public List<UserGetDto> getAll() {
        return userService.getAll();
    }

    @PutMapping(
            path = "/{userId}/avatar",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public ProfilePictureGetDto updateProfilePicture(@PathVariable Long userId, @RequestBody MultipartFile file) {
        return profilePictureService.setPictureToUserProfile(userId, file);
    }

    @GetMapping(path = "/{userId}/avatar")
    public ResponseEntity<byte[]> getProfilePicture(@PathVariable Long userId) {
        final var picture = profilePictureService.downloadProfilePicture(userId);
        if (picture == null) {
            return ResponseEntity.ok().body(null);
        }
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + picture.getName() + "\"")
                .body(picture.getData());
    }
}
