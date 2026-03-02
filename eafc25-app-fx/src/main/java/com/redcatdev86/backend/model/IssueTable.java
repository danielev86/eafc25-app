package com.redcatdev86.backend.model;

public enum IssueTable {
    PLAYER_ISSUES("player_issues"),
    TEAM_ISSUES("team_issues"),
    MANAGER_ISSUES("manager_issues"),
    MARKET_ISSUES("market_issues");

    private final String tableName;

    IssueTable(String tableName) {
        this.tableName = tableName;
    }

    public String tableName() {
        return tableName;
    }
}