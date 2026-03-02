package com.redcatdev86.ui.view;

import com.redcatdev86.backend.model.IssueTable;
import com.redcatdev86.service.IssueService;
import com.redcatdev86.service.RandomIssueService;

import java.sql.SQLException;
import java.util.Optional;

public class MarketIssueView extends IssuePageBase {

    public MarketIssueView(IssueService issueService, RandomIssueService random) {
        super("MARKET ISSUE");

        bindGenerate(() -> {
            try {
                return random.randomIssue(IssueTable.MARKET_ISSUES);
            } catch (SQLException ex) {
                throw new RuntimeException("Errore DB: " + ex.getMessage(), ex);
            }
        });
    }
}