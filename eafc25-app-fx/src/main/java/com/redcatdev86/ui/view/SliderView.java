package com.redcatdev86.ui.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class SliderView extends VBox {

    public SliderView() {
        getStyleClass().add("page");
        setPadding(new Insets(30));
        setSpacing(12);
        setAlignment(Pos.TOP_CENTER);

        Label t = new Label("SLIDER");
        t.getStyleClass().add("page-title");

        Label d = new Label("Qui mettiamo la tabella slider (prossimo step).");
        d.getStyleClass().add("home-text");

        getChildren().addAll(t, d);
    }
}