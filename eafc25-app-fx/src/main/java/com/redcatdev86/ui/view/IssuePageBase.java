package com.redcatdev86.ui.view;

import com.redcatdev86.backend.model.IssueType;
import com.redcatdev86.config.AppConfig;
import com.redcatdev86.service.RandomIssueService;
import com.redcatdev86.ui.ResettableView;
import com.redcatdev86.ui.bean.IssueBean;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.util.Optional;
import java.util.function.Supplier;

public abstract class IssuePageBase extends VBox implements ResettableView {

    protected final Label pageTitle = new Label();
    protected final Button generate = new Button("GENERATE");

    protected final Label resultType = new Label("");
    protected final Label resultDesc = new Label("");
    protected final Label resultExtra = new Label("");

    protected IssuePageBase(String title) {
        getStyleClass().add("page");

        setAlignment(Pos.TOP_CENTER);
        setSpacing(14);
        setPadding(new Insets(18, 10, 10, 10));

        pageTitle.setText(title);
        pageTitle.getStyleClass().add("page-title");

        generate.getStyleClass().add("cta-btn");

        resultType.getStyleClass().add("result-type");
        resultDesc.getStyleClass().add("result-desc");
        resultExtra.getStyleClass().add("result-extra");

        resultType.setMaxWidth(Double.MAX_VALUE);
        resultDesc.setMaxWidth(720);
        resultExtra.setMaxWidth(Double.MAX_VALUE);

        resultType.setAlignment(Pos.CENTER);
        resultDesc.setAlignment(Pos.CENTER);
        resultExtra.setAlignment(Pos.CENTER);

        resultDesc.setWrapText(true);

        getChildren().addAll(pageTitle, generate, resultType, resultDesc, resultExtra);

        resetView(); // stato iniziale
    }

    @Override
    public void resetView() {
        resultType.setText("");
        resultDesc.setText("");
        resultExtra.setText("");
    }

    protected void bindGenerate(Supplier<Optional<IssueBean>> supplier, IssueType issueType) {
        generate.setOnAction(e -> {
            try {
                Optional<IssueBean> opt = supplier.get();
                if (opt.isEmpty()) {
                    resultType.setText("NESSUN IMPREVISTO");
                    resultDesc.setText("");
                    resultExtra.setText("");
                    return;
                }

                IssueBean bean = opt.get();
                resultType.setText(bean.getType() == null ? "" : bean.getType().toUpperCase());
                resultDesc.setText(bean.getDescription() == null ? "" : bean.getDescription());
                String extraIssueText = "";
                if (IssueType.PLAYER_ISSUES.equals(issueType)){
                    extraIssueText = buildExtra(bean);
                }
                resultExtra.setText(extraIssueText);

            } catch (Exception ex) {
                resultType.setText("ERRORE");
                resultDesc.setText(ex.getMessage() == null ? "Errore sconosciuto" : ex.getMessage());
                resultExtra.setText("");
            }
        });
    }

    protected String buildExtra(IssueBean bean) {
        RandomIssueService randomIssueService = new RandomIssueService();
        return  bean.getCategory() + ": " + randomIssueService.generateRandomInt(11 + AppConfig.get().getMaxBenchPlayers());
    }
}