package com.redcatdev86.ui.view;

import com.redcatdev86.backend.model.IssueType;
import com.redcatdev86.service.IssueService;
import com.redcatdev86.service.RandomIssueService;

import java.sql.SQLException;

public class ManagerIssueView extends IssuePageBase {

    public ManagerIssueView(IssueService issueService) {
        super("MANAGER ISSUE");

        IssueType managerIssue = IssueType.MANAGER_ISSUES;
        bindGenerate(() -> {
            try {
                return issueService.randomIssue(managerIssue);
            } catch (SQLException ex) {
                throw new RuntimeException("Errore DB: " + ex.getMessage(), ex);
            }
        }, managerIssue);
    }
}