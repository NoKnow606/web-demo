package com.example.demo.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.List;

@JsonIgnoreProperties(value = {"handle"})
public class GroupDetail implements Serializable {
    private String themeName;
    private long themeId;
    private List<GroupInfoDetail> groupInfoDetails;

    public void setThemeId(long themeId) {
        this.themeId = themeId;
    }

    public void setGroupInfoDetails(List<GroupInfoDetail> groupInfoDetails) {
        this.groupInfoDetails = groupInfoDetails;
    }

    public void setThemeName(String themeName) {
        this.themeName = themeName;
    }

    public long getThemeId() {
        return themeId;
    }

    public List<GroupInfoDetail> getGroupInfoDetails() {
        return groupInfoDetails;
    }

    public String getThemeName() {
        return themeName;
    }
}
