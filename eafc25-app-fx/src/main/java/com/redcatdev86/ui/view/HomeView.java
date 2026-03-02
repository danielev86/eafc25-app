package com.redcatdev86.ui.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

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

        getChildren().addAll(title, lead);
    }
}