package com.esliceu.Myforum.dto;

import com.esliceu.Myforum.model.Category;

import java.util.List;
/*AÑADIR RECORD*/
import com.fasterxml.jackson.annotation.JsonProperty;

public class CategoryDTO {

    @JsonProperty("_id")
    private String id;

    private String title;
    private String description;
    private String slug;
    private String color;
    private List<String> moderators;

    @JsonProperty("__v")
    private int version = 0;

    public CategoryDTO(Category category) {
        this.id = category.getId().toString();
        this.title = category.getTitle();
        this.description = category.getDescription();
        this.slug = category.getSlug();
        this.color = category.getColor();
        this.moderators = category.getModerators();
    }

    public String getId() { return id; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public String getSlug() { return slug; }
    public String getColor() { return color; }
    public List<String> getModerators() { return moderators; }
    public int getVersion() { return version; }
}

