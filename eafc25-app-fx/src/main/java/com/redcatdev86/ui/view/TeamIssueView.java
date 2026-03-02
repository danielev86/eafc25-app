package com.redcatdev86.ui.view;

import com.redcatdev86.backend.model.IssueTable;
import com.redcatdev86.service.IssueService;
import com.redcatdev86.service.RandomIssueService;

import java.sql.SQLException;

public class TeamIssueView extends IssuePageBase {

    public TeamIssueView(IssueService issueService, RandomIssueService random) {
        super("TEAM ISSUE");

        bindGenerate(() -> {
            try {
                return random.randomIssue(IssueTable.TEAM_ISSUES);
            } catch (SQLException ex) {
                throw new RuntimeException("Errore DB: " + ex.getMessage(), ex);
            }
        });
    }
}