package com.redcatdev86.ui.view;

import com.redcatdev86.config.AppConfig;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.StringConverter;

public class HomeView extends VBox {

    public HomeView() {
        getStyleClass().add("page");
        setAlignment(Pos.CENTER);
        setSpacing(14);
        setPadding(new Insets(30));

        Label title = new Label("WELCOME");
        title.getStyleClass().add("page-title");

        Label lead = new Label("Genera imprevisti random per la tua carriera.");
        lead.getStyleClass().add("home-lead");

        Label lbl = new Label("Max bench players:");
        lbl.setStyle("-fx-text-fill: white;");
        ComboBox<Integer> cmb = new ComboBox<>(FXCollections.observableArrayList(5, 7, 9,10,11,12));
        cmb.setPrefWidth(120);
        cmb.setValue(AppConfig.get().getMaxBenchPlayers());

// combo chiusa
        cmb.setStyle("-fx-background-color: white; -fx-text-fill: black;");

// celle del dropdown
        cmb.setCellFactory(list -> new ListCell<>() {
            @Override
            protected void updateItem(Integer item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.toString());
                    setStyle(
                            "-fx-background-color: white;" +
                                    "-fx-text-fill: black;"
                    );
                }
            }
        });

// cella visualizzata quando la combo è chiusa
        cmb.setButtonCell(new ListCell<>() {
            @Override
            protected void updateItem(Integer item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.toString());
                    setStyle(
                            "-fx-background-color: white;" +
                                    "-fx-text-fill: black;"
                    );
                }
            }
        });

        // se config cambia da qualche altra parte -> aggiorna combo
        AppConfig.get().maxBenchPlayersProperty().addListener((obs, oldV, newV) -> {
            if (newV != null && !newV.equals(cmb.getValue())) {
                cmb.setValue(newV.intValue());
            }
        });

        HBox row = new HBox(10, lbl, cmb);
        row.setAlignment(Pos.CENTER);
        row.setPadding(new Insets(8, 0, 0, 0));

        getChildren().addAll(title, lead, row);
    }
}