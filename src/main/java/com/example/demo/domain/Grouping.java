package com.example.demo.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Grouping {

    @GeneratedValue
    @Id
    private Long id;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private String theme;

    @Column(nullable = false)
    private String status;

    @Column(nullable = false)
    private Integer participatorCount;

    @Column(nullable = false)
    private Integer groupCount;

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setParticipatorCount(Integer participatorCount) {
        this.participatorCount = participatorCount;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public void setGroupCount(Integer groupCount) {
        this.groupCount = groupCount;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public Long getId() {
        return id;
    }

    public String getTheme() {
        return theme;
    }

    public Integer getGroupCount() {
        return groupCount;
    }

    public Integer getParticipatorCount() {
        return participatorCount;
    }

    public Long getUserId() {
        return userId;
    }

    public void genGrouping(String themeName,int groupCount,int participatorCount,long userId){
        this.setStatus("valid");
        this.setUserId(userId);
        this.setGroupCount(groupCount);
        this.setTheme(themeName);
        this.setParticipatorCount(participatorCount);
    }
}
