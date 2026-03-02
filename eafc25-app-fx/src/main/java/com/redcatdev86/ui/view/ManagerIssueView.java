package com.redcatdev86.ui.view;

import com.redcatdev86.backend.model.IssueTable;
import com.redcatdev86.service.IssueService;
import com.redcatdev86.service.RandomIssueService;
import com.redcatdev86.ui.bean.IssueBean;

import java.sql.SQLException;
import java.util.Optional;

public class ManagerIssueView extends IssuePageBase {

    public ManagerIssueView(IssueService issueService, RandomIssueService random) {
        super("MANAGER ISSUE");

        bindGenerate(() -> {
            try {
                return random.randomIssue(IssueTable.MANAGER_ISSUES);
            } catch (SQLException ex) {
                throw new RuntimeException("Errore DB: " + ex.getMessage(), ex);
            }
        });
    }
}