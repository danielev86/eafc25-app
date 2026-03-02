package com.redcatdev86.ui.view;

import com.redcatdev86.backend.model.IssueTable;
import com.redcatdev86.service.IssueService;
import com.redcatdev86.ui.ResettableView;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

public class AddIssueView extends VBox implements ResettableView {

    private final IssueService issueService;

    private final ComboBox<IssueTable> tableCombo = new ComboBox<>();
    private final TextField typeField = new TextField();
    private final TextArea descArea = new TextArea();
    private final Spinner<Integer> qtySpinner = new Spinner<>(1, 200, 1);

    private final Button saveBtn = new Button("SAVE");
    private final Label status = new Label("");

    public AddIssueView(IssueService issueService) {
        this.issueService = issueService;

        getStyleClass().add("page");
        setAlignment(Pos.TOP_CENTER);
        setSpacing(14);
        setPadding(new Insets(18, 10, 10, 10));

        Label title = new Label("ADD ISSUE");
        title.getStyleClass().add("page-title");

        tableCombo.getItems().addAll(
                IssueTable.PLAYER_ISSUES,
                IssueTable.TEAM_ISSUES,
                IssueTable.MARKET_ISSUES,
                IssueTable.MANAGER_ISSUES
        );

        typeField.setPromptText("issue_type (es. Infortunio improvviso)");
        typeField.setMaxWidth(720);

        descArea.setPromptText("issue_description...");
        descArea.setWrapText(true);
        descArea.setMaxWidth(720);
        descArea.setPrefRowCount(5);

        qtySpinner.setEditable(true);

        saveBtn.getStyleClass().add("cta-btn");

        status.getStyleClass().add("result-extra");
        status.setMaxWidth(Double.MAX_VALUE);
        status.setAlignment(Pos.CENTER);

        VBox form = new VBox(10,
                labeled("Tabella", tableCombo),
                labeled("Tipo (issue_type)", typeField),
                labeled("Descrizione (issue_description)", descArea),
                labeled("Quantità da aggiungere", qtySpinner),
                saveBtn,
                status
        );
        form.setAlignment(Pos.CENTER);
        form.setPadding(new Insets(8, 0, 0, 0));

        getChildren().addAll(title, form);

        saveBtn.setOnAction(e -> onSave());

        resetView();
    }

    private VBox labeled(String label, Control control) {
        Label l = new Label(label);
        l.getStyleClass().add("home-muted"); // riuso stile leggibile
        VBox box = new VBox(6, l, control);
        box.setAlignment(Pos.CENTER_LEFT);
        box.setMaxWidth(720);
        return box;
    }

    private void onSave() {
        IssueTable table = tableCombo.getValue();
        String type = typeField.getText() == null ? "" : typeField.getText().trim();
        String desc = descArea.getText() == null ? "" : descArea.getText().trim();
        int qty = qtySpinner.getValue();

        if (table == null) {
            status.setText("Seleziona una tabella.");
            return;
        }
        if (type.isEmpty()) {
            status.setText("Compila issue_type.");
            return;
        }
        if (desc.isEmpty()) {
            status.setText("Compila issue_description.");
            return;
        }

        try {
            int lastId = issueService.addIssues(table, type, desc, qty);
            int firstId = lastId - qty + 1;
            status.setText("Salvate " + qty + " issue in " + table.tableName() +
                    " • ID " + firstId + " → " + lastId);
        } catch (Exception ex) {
            status.setText("Errore salvataggio: " + ex.getMessage());
        }
    }

    @Override
    public void resetView() {
        tableCombo.getSelectionModel().select(IssueTable.PLAYER_ISSUES);
        typeField.setText("");
        descArea.setText("");
        qtySpinner.getValueFactory().setValue(1);
        status.setText("");
    }
}