package com.example.demo.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.List;

@JsonIgnoreProperties
public class UserGroupLogOrder implements Serializable {

    private  String themeName;
    private  long themeId;
    private  String type;

    public void setThemeId(long themeId) {
        this.themeId = themeId;
    }

    public void setThemeName(String themeName) {
        this.themeName = themeName;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getThemeId() {
        return themeId;
    }

    public String getThemeName() {
        return themeName;
    }

    public String getType() {
        return type;
    }
}
