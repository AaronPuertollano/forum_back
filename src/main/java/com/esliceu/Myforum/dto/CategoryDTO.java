package com.esliceu.Myforum.dto;

import com.esliceu.Myforum.model.Category;

import java.util.List;

public class CategoryDTO {

    private String _id;
    private String title;
    private String description;
    private String slug;
    private String color;
    private List<String> moderators;
    private int __v = 0;

    public CategoryDTO(Category category) {
        this._id = category.getId().toString();
        this.title = category.getTitle();
        this.description = category.getDescription();
        this.slug = category.getSlug();
        this.color = category.getColor();
        this.moderators = category.getModerators();
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public List<String> getModerators() {
        return moderators;
    }

    public void setModerators(List<String> moderators) {
        this.moderators = moderators;
    }

    public int get__v() {
        return __v;
    }

    public void set__v(int __v) {
        this.__v = __v;
    }
}
