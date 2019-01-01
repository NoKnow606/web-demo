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

        private Grouping grouping;
    private List<GroupOrder> groupOrders;
    private List<GroupInfo> groupInfos;

    public GroupDetail(){}

    public GroupDetail(Grouping grouping, List<GroupInfo> groupInfos){
        this.grouping = grouping;
        this.groupInfos = groupInfos;
    }

    public GroupDetail(Grouping grouping, List<GroupOrder> groupOrders,List<GroupInfo> groupInfos){
        this.grouping = grouping;
        this.groupInfos = groupInfos;
        this.groupOrders = groupOrders;
    }

    public void setGroupInfos(List<GroupInfo> groupInfos) {
        this.groupInfos = groupInfos;
    }

    public void setGrouping(Grouping grouping) {
        this.grouping = grouping;
    }

    public void setGroupOrders(List<GroupOrder> groupOrders) {
        this.groupOrders = groupOrders;
    }

    public Grouping getGrouping() {
        return grouping;
    }

    public List<GroupInfo> getGroupInfos() {
        return groupInfos;
    }

    public List<GroupOrder> getGroupOrders() {
        return groupOrders;
    }

    public void genGroupDetail(Grouping grouping, List<GroupOrder> groupOrders, List<GroupInfo> groupInfos){
        this.setGrouping(grouping);
        this.setGroupInfos(groupInfos);
        this.setGroupOrders(groupOrders);
    }
}
