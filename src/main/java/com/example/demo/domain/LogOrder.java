package com.example.demo.domain;

import net.bytebuddy.pool.TypePool;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class LogOrder {

    @Id
    @GeneratedValue
    private long id;

    @Column
    private String status;

    @Column(nullable = false)
    private String type;

    @Column
    private long parentId = 0;

    @Column
    private long userId;

    public void setStatus(String status) {
        this.status = status;
    }

    public void setParentId(long parentId) {
        this.parentId = parentId;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getParentId() {
        return parentId;
    }

    public String getStatus() {
        return status;
    }

    public long getId() {
        return id;
    }

    public long getUserId() {
        return userId;
    }

    public String getType() {
        return type;
    }

    public void genLogOrder(String type, long parentId, long userId){
        this.setStatus("pending");
        this.setType(type);
        this.setUserId(userId);
        this.setParentId(parentId);
    }

    @Override
    public String toString() {
        return "id:"+this.id+"parentId:"+this.parentId+"type:"+this.type+"userId:"+this.userId+"status:"+status;
    }
}
