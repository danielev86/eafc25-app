package com.redcatdev86.ui.view;

import com.redcatdev86.backend.model.IssueType;
import com.redcatdev86.service.IssueService;
import com.redcatdev86.service.RandomIssueService;

import java.sql.SQLException;

public class MarketIssueView extends IssuePageBase {

    public MarketIssueView(IssueService issueService) {
        super("MARKET ISSUE");

        IssueType marketIssue = IssueType.MARKET_ISSUES;
        bindGenerate(() -> {
            try {
                return issueService.randomIssue(marketIssue);
            } catch (SQLException ex) {
                throw new RuntimeException("Errore DB: " + ex.getMessage(), ex);
            }
        }, marketIssue);
    }
}