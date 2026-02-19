package com.esliceu.Myforum.dto;

public class UpdateTopicDTO {

    private String title;
    private String content;
    private String category; // slug

    public String getTitle() { return title; }
    public String getContent() { return content; }
    public String getCategory() { return category; }
}
