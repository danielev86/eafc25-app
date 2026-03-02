package com.redcatdev86.service;

import com.redcatdev86.backend.model.IssueTable;
import com.redcatdev86.ui.bean.IssueBean;

import java.sql.SQLException;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

public class RandomIssueService {

    private final IssueService issueService;

    public RandomIssueService(IssueService issueService) {
        this.issueService = issueService;
    }

    public Optional<IssueBean> randomIssue(IssueTable table) throws SQLException {
        int max = issueService.getMaxId(table);
        if (max <= 0) return Optional.empty();

        int id = ThreadLocalRandom.current().nextInt(1, max + 1);
        return issueService.getIssueById(table, id);
    }

    /** Replica la logica Electron solo per player: se id random è pari => nessuna issue */
    public Optional<IssueBean> randomPlayerIssueLikeElectron() throws SQLException {
        int max = issueService.getMaxId(IssueTable.PLAYER_ISSUES);
        if (max <= 0) return Optional.empty();

        int id = ThreadLocalRandom.current().nextInt(1, max + 1);
        if (id % 2 == 0) return Optional.empty();

        return issueService.getIssueById(IssueTable.PLAYER_ISSUES, id);
    }

    public int randomPlayerNumber() {
        return ThreadLocalRandom.current().nextInt(1, 19); // 1..18 come Electron
    }
}