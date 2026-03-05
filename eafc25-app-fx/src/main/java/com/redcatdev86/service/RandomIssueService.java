package com.redcatdev86.service;

import com.redcatdev86.backend.model.IssueType;
import com.redcatdev86.ui.bean.IssueBean;

import java.sql.SQLException;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

public class RandomIssueService {

    private IssueService issueService;

    public RandomIssueService() {
    }

    public RandomIssueService(IssueService issueService) {
        this.issueService = issueService;
    }

    public static int generateRandomInt(int val){
        return ThreadLocalRandom.current().nextInt(1, val + 1);
    }

}