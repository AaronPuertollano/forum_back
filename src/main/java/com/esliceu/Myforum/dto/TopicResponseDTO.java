package com.esliceu.Myforum.dto;

import com.esliceu.Myforum.model.Topic;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public class TopicResponseDTO {

    @JsonProperty("_id")
    private Long id;

    private String title;
    private String content;
    private Long category;
    private Long user;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Integer views;
    private Object replies = null;
    private Object numberOfReplies = null;
    private Integer __v = 0;

    public TopicResponseDTO(Topic topic) {
        this.id = topic.getId();
        this.title = topic.getTitle();
        this.content = topic.getContent();
        this.category = topic.getCategory().getId();
        this.user = topic.getUser().getId();
        this.createdAt = topic.getCreatedAt();
        this.updatedAt = topic.getUpdatedAt();
        this.views = topic.getViews();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getCategory() {
        return category;
    }

    public void setCategory(Long category) {
        this.category = category;
    }

    public Long getUser() {
        return user;
    }

    public void setUser(Long user) {
        this.user = user;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public Object getReplies() {
        return replies;
    }

    public void setReplies(Object replies) {
        this.replies = replies;
    }

    public Object getNumberOfReplies() {
        return numberOfReplies;
    }

    public void setNumberOfReplies(Object numberOfReplies) {
        this.numberOfReplies = numberOfReplies;
    }

    public Integer get__v() {
        return __v;
    }

    public void set__v(Integer __v) {
        this.__v = __v;
    }
}


