package com.example.demo.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class BlogProperties {
    @Value("${com.demo.blog.name}")
    private String name;
    @Value("${com.demo.blog.title}")
    private String title;
}
