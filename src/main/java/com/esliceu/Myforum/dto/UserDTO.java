package com.esliceu.Myforum.dto;

import com.esliceu.Myforum.model.User;

import java.util.List;
import java.util.Map;

public class UserDTO {
    private String id;
    private String name;
    private String email;
    private String role;
    private String avatarUrl;
    private Map<String, Object> permissions;

    public UserDTO(User user) {
        this.id = user.getId().toString();
        this.name = user.getName();
        this.email = user.getEmail();
        this.role = user.getRole();
        this.avatarUrl = "";
        this.permissions = Map.of(
                "root", List.of(
                        "own_topics:write",
                        "own_topics:delete",
                        "own_replies:write",
                        "own_replies:delete"
                ),
                "categories", List.of()
        );
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public Map<String, Object> getPermissions() {
        return permissions;
    }

    public void setPermissions(Map<String, Object> permissions) {
        this.permissions = permissions;
    }
}

