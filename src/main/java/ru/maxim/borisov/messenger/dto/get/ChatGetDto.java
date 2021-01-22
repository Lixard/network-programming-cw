package ru.maxim.borisov.messenger.dto.get;

import java.util.List;

/**
 * Dto для получения чата со списком его участников.
 */
public class ChatGetDto {

    private Long id;
    private String name;
    private List<UserGetDto> participations;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<UserGetDto> getParticipations() {
        return participations;
    }

    public void setParticipations(List<UserGetDto> participations) {
        this.participations = participations;
    }
}
