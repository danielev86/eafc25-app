package com.redcatdev86.ui.bean;

public class IssueBean {

    private final String category; // PLAYER / TEAM / MARKET / MANAGER
    private final int id;
    private final String type;
    private final String description;

    public IssueBean(String category, int id, String type, String description) {
        this.category = category;
        this.id = id;
        this.type = type;
        this.description = description;
    }

    public String getCategory() { return category; }
    public int getId() { return id; }
    public String getType() { return type; }
    public String getDescription() { return description; }
}