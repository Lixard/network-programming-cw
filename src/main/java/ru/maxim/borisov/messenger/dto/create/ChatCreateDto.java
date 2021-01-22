package ru.maxim.borisov.messenger.dto.create;

import java.util.List;

/**
 * Dto для создания чата.
 */
public class ChatCreateDto {
    private String name;
    private Long createdBy;
    private List<Long> participationIds;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public List<Long> getParticipationIds() {
        return participationIds;
    }

    public void setParticipationIds(List<Long> participationIds) {
        this.participationIds = participationIds;
    }
}
