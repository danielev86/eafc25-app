package com.redcatdev86.ui.view;

import com.redcatdev86.service.IssueService;
import com.redcatdev86.service.RandomIssueService;
import com.redcatdev86.ui.bean.IssueBean;

import java.sql.SQLException;
import java.util.Optional;

public class PlayerIssueView extends IssuePageBase {

    private final RandomIssueService random;

    public PlayerIssueView(IssueService issueService, RandomIssueService random) {
        super("PLAYER ISSUE");
        this.random = random;

        bindGenerate(() -> {
            try {
                Optional<IssueBean> opt = random.randomPlayerIssueLikeElectron();
                int playerNumber = random.randomPlayerNumber();

                // Extra sempre sotto
                if (opt.isPresent()) {
                    resultExtra.setText(buildExtra(opt.get()) + " • PLAYER #" + playerNumber);
                } else {
                    resultExtra.setText("PLAYER #" + playerNumber);
                }
                return opt;

            } catch (SQLException ex) {
                throw new RuntimeException("Errore DB: " + ex.getMessage(), ex);
            }
        });
    }
}