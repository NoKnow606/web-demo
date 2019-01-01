package com.example.demo.domain;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class GroupInfo {

    @GeneratedValue
    @Id
    private long id;

    @Column(nullable = false)
    private String groupName;

    @Column(nullable = false)
    private int count;

    @Column(nullable = false)
    private long themeId;

    public void setCount(int count) {
        this.count = count;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public void setThemeId(long themeId) {
        this.themeId = themeId;
    }

    public int getCount() {
        return count;
    }

    public long getId() {
        return id;
    }

    public long getThemeId() {
        return themeId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void genGroupInfo(int count, String groupName, long themeId){
        this.setCount(count);
        this.setThemeId(themeId);
        this.setGroupName(groupName);
    }
}
