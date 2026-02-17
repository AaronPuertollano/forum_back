package com.esliceu.Myforum.dto;

import com.esliceu.Myforum.model.Topic;
import com.esliceu.Myforum.model.User;

import java.time.LocalDateTime;

public class TopicDTO {

    private Long _id;
    private String title;
    private String content;
    private int views;
    private String category;
    private User user;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Integer numberOfReplies = 0;
    private Object replies = null;
    private int __v = 0;

    public TopicDTO(Topic topic) {
        this._id = topic.getId();
        this.title = topic.getTitle();
        this.content = topic.getContent();
        this.views = topic.getViews();
        this.category = topic.getCategory().getId().toString();
        this.user = topic.getUser();
        this.createdAt = topic.getCreatedAt();
        this.updatedAt = topic.getUpdatedAt();
    }

    public Long get_id() {
        return _id;
    }

    public void set_id(Long _id) {
        this._id = _id;
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

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
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

    public Integer getNumberOfReplies() {
        return numberOfReplies;
    }

    public void setNumberOfReplies(Integer numberOfReplies) {
        this.numberOfReplies = numberOfReplies;
    }

    public Object getReplies() {
        return replies;
    }

    public void setReplies(Object replies) {
        this.replies = replies;
    }

    public int get__v() {
        return __v;
    }

    public void set__v(int __v) {
        this.__v = __v;
    }
}
