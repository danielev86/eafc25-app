package com.redcatdev86.service;


import com.redcatdev86.backend.IssueRepository;
import com.redcatdev86.backend.model.IssueEntity;
import com.redcatdev86.backend.model.IssueType;
import com.redcatdev86.ui.bean.IssueBean;

import java.sql.SQLException;
import java.util.Locale;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

public class IssueService {

    private final IssueRepository repo;

    public IssueService(IssueRepository repo) {
        this.repo = repo;
    }

    public int getMaxId(IssueType table) throws SQLException {
        return repo.getMaxId(table);
    }

    public Optional<IssueBean> getIssueById(IssueType table, int id) throws SQLException {
        return repo.findById(table, id).map(this::toUiBean);
    }

    public int addIssue(IssueType table, String issueType, String issueDescription) throws SQLException {
        return repo.insertIssueWithNextId(table, issueType, issueDescription);
    }

    public int addIssues(IssueType table, String issueType, String issueDescription, int quantity) throws SQLException {
        return repo.insertManyIssuesWithNextIds(table, issueType, issueDescription, quantity);
    }

    public Optional<IssueBean> randomIssue(IssueType table) throws SQLException {
        int max = getMaxId(table);
        if (max <= 0) return Optional.empty();
        int id = RandomIssueService.generateRandomInt(max);
        return getIssueById(table, id);
    }

    /** Replica la logica Electron solo per player: se id random è pari => nessuna issue */
    public Optional<IssueBean> randomPlayerIssue() throws SQLException {
        int max = getMaxId(IssueType.PLAYER_ISSUES);
        if (max <= 0) return Optional.empty();

        int id = RandomIssueService.generateRandomInt(max);
        if (id % 2 == 0) return Optional.empty();

        return getIssueById(IssueType.PLAYER_ISSUES, id);
    }


    private IssueBean toUiBean(IssueEntity e) {
        String category = e.getTable().name().replace("_ISSUES", "").toUpperCase(Locale.ROOT);
        return new IssueBean(category, e.getId(), e.getIssueType(), e.getIssueDescription());
    }
}