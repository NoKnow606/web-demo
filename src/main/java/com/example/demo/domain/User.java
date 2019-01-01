package com.example.demo.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User {
    @Id
    private Long id;

    @Column (nullable = false)
    private String name;

    @Column (nullable = false)
    private Integer age;

    public User(){

    }

    public User(String name,Integer age){
        this.name = name;
        this.age = age;
    }

    public Long getId(){
        return this.id;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setId (Long id){
        this.id = id;
    }


    public String getName(){
        return this.name;
    }

    public void setAge(Integer age){
        this.age = age;
    }

    public Integer getAge(){
        return this.age;
    }

    public String toString(){
        return "id:"+this.id+"name:"+this.name+"age:"+this.age;
    }
}
