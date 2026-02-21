package com.esliceu.Myforum.dto;

import com.esliceu.Myforum.model.Topic;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TopicDetailDTO {

    @JsonProperty("_id")
    private Long id;

    private String title;
    private String content;
    private int views;

    private CategoryDTO category;
    private UserDTO user;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @JsonProperty("__v")
    private int version = 0;

    private List<ReplyDTO> replies = new ArrayList<>();
    private Object numberOfReplies;

    public TopicDetailDTO(Topic topic) {
        this.id = topic.getId();
        this.title = topic.getTitle();
        this.content = topic.getContent();
        this.views = topic.getViews();
        this.category = new CategoryDTO(topic.getCategory());
        this.user = new UserDTO(topic.getUser());
        this.createdAt = topic.getCreatedAt();
        this.updatedAt = topic.getUpdatedAt();
        this.replies = topic.getReplies()
                .stream()
                .map(ReplyDTO::new)
                .toList();

        this.numberOfReplies = this.replies.size();
    }

    public Long getId() { return id; }
    public String getTitle() { return title; }
    public String getContent() { return content; }
    public int getViews() { return views; }
    public CategoryDTO getCategory() { return category; }
    public UserDTO getUser() { return user; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public int getVersion() { return version; }
    public Object getNumberOfReplies() { return numberOfReplies; }

    public List<ReplyDTO> getReplies() {
        return replies;
    }

}
