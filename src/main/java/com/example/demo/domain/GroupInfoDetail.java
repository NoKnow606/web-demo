package com.example.demo.domain;

public class GroupInfoDetail {
    private String groupName;
    private int count;
    private String [] groupMember;

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setGroupMember(String[] groupMember) {
        this.groupMember = groupMember;
    }

    public String getGroupName() {
        return groupName;
    }

    public int getCount() {
        return count;
    }

    public String[] getGroupMember() {
        return groupMember;
    }
}

