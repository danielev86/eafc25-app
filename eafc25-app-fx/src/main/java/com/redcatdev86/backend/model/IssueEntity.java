package com.redcatdev86.backend.model;


public class IssueEntity {

    private final IssueType table;
    private final int id;
    private final String issueType;
    private final String issueDescription;

    public IssueEntity(IssueType table, int id, String issueType, String issueDescription) {
        this.table = table;
        this.id = id;
        this.issueType = issueType;
        this.issueDescription = issueDescription;
    }

    public IssueType getTable() {
        return table;
    }

    public int getId() {
        return id;
    }

    public String getIssueType() {
        return issueType;
    }

    public String getIssueDescription() {
        return issueDescription;
    }
}