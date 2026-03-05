package com.redcatdev86.ui.view;

import com.redcatdev86.backend.model.IssueType;
import com.redcatdev86.service.IssueService;
import com.redcatdev86.service.RandomIssueService;
import com.redcatdev86.ui.bean.IssueBean;

import java.sql.SQLException;
import java.util.Optional;

public class PlayerIssueView extends IssuePageBase {

    public PlayerIssueView(IssueService issueService) {
        super("PLAYER ISSUE");

        bindGenerate(() -> {
            try {
                Optional<IssueBean> opt = issueService.randomPlayerIssue();
                return opt;

            } catch (SQLException ex) {
                throw new RuntimeException("Errore DB: " + ex.getMessage(), ex);
            }
        }, IssueType.PLAYER_ISSUES);
    }
}