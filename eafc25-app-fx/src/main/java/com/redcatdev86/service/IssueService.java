package com.redcatdev86.service;


import com.redcatdev86.backend.IssueRepository;
import com.redcatdev86.backend.model.IssueEntity;
import com.redcatdev86.backend.model.IssueTable;
import com.redcatdev86.ui.bean.IssueBean;

import java.sql.SQLException;
import java.util.Locale;
import java.util.Optional;

public class IssueService {

    private final IssueRepository repo;

    public IssueService(IssueRepository repo) {
        this.repo = repo;
    }

    public int getMaxId(IssueTable table) throws SQLException {
        return repo.getMaxId(table);
    }

    public Optional<IssueBean> getIssueById(IssueTable table, int id) throws SQLException {
        return repo.findById(table, id).map(this::toUiBean);
    }

    public int addIssue(IssueTable table, String issueType, String issueDescription) throws SQLException {
        return repo.insertIssueWithNextId(table, issueType, issueDescription);
    }

    public int addIssues(IssueTable table, String issueType, String issueDescription, int quantity) throws SQLException {
        return repo.insertManyIssuesWithNextIds(table, issueType, issueDescription, quantity);
    }

    private IssueBean toUiBean(IssueEntity e) {
        String category = e.getTable().name().replace("_ISSUES", "").toUpperCase(Locale.ROOT);
        return new IssueBean(category, e.getId(), e.getIssueType(), e.getIssueDescription());
    }
}