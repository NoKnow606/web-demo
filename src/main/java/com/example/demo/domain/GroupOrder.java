package com.example.demo.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class GroupOrder {

    @GeneratedValue
    @Id
    private Long id;

    @Column(nullable = false)
    private Long themeId;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private Long groupId;

    @Column(nullable = false)
    private String status;

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setThemeId(Long themeId) {
        this.themeId = themeId;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public Long getGroupId() {
        return groupId;
    }

    public Long getThemeId() {
        return themeId;
    }

    public void genGroupOrder (long userId, long groupId, long themeId){
        this.setUserId(userId);
        this.setGroupId(groupId);
        this.setThemeId(themeId);
        this.setStatus("valid");
    }
}
