package com.github.syr0ws.craftventory.common.transform.dto;

import com.github.syr0ws.craftventory.api.transform.dto.DTO;

public class TitleDto implements DTO {

    private String title;

    public TitleDto(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String getId() {
        return DtoNameEnum.TITLE.name();
    }
}
