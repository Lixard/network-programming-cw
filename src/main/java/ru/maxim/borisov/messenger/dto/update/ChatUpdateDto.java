package ru.maxim.borisov.messenger.dto.update;

import java.util.List;

public class ChatUpdateDto {

    private Long id;
    private String name;
    private List<Long> participationIds;

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

    public List<Long> getParticipationIds() {
        return participationIds;
    }

    public void setParticipationIds(List<Long> participationIds) {
        this.participationIds = participationIds;
    }
}
