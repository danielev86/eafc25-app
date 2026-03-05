package com.redcatdev86.ui.view;

import com.redcatdev86.backend.model.IssueType;
import com.redcatdev86.service.IssueService;
import com.redcatdev86.service.RandomIssueService;

import java.sql.SQLException;

public class TeamIssueView extends IssuePageBase {

    public TeamIssueView(IssueService issueService) {
        super("TEAM ISSUE");

        IssueType teamIssue = IssueType.TEAM_ISSUES;
        bindGenerate(() -> {
            try {
                return issueService.randomIssue(teamIssue);
            } catch (SQLException ex) {
                throw new RuntimeException("Errore DB: " + ex.getMessage(), ex);
            }
        }, teamIssue);
    }
}