package com.redcatdev86.ui.view;

import com.redcatdev86.backend.model.IssueType;
import com.redcatdev86.service.IssueService;

public class MatchTimeView extends IssuePageBase {

    public MatchTimeView(IssueService issueService) {
        super("MATCHTIME");

        IssueType managerIssue = IssueType.MATCH_TIME_ISSUES;
        bindGenerate(() -> {
            return issueService.randomMatchTimeIssue();
        }, managerIssue);
    }
}