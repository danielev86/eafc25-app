package com.redcatdev86.ui;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

import java.util.EnumMap;
import java.util.Map;
import java.util.function.Consumer;

public class NavBar extends HBox {

    private final Map<Views, Button> buttons = new EnumMap<>(Views.class);

    public NavBar(Consumer<Views> onNavigate) {
        getStyleClass().add("nav");
        setSpacing(10);
        setPadding(new Insets(10, 20, 14, 20));

        addNavButton("HOME", Views.HOME, onNavigate);
        addNavButton("PLAYER", Views.PLAYER, onNavigate);
        addNavButton("TEAM", Views.TEAM, onNavigate);
        addNavButton("MARKET", Views.MARKET, onNavigate);
        addNavButton("MANAGER", Views.MANAGER, onNavigate);
        addNavButton("FORMATION", Views.FORMATION, onNavigate);
        addNavButton("FIFA 16 LEAGUE", Views.FIFA16LEAGUE, onNavigate);
        addNavButton("ADD ISSUE", Views.ADD_ISSUE, onNavigate);
        setActive(Views.HOME);
    }

    private void addNavButton(String text, Views view, Consumer<Views> onNavigate) {
        Button b = new Button(text);
        b.getStyleClass().addAll("nav-btn");
        b.setOnAction(e -> onNavigate.accept(view));
        buttons.put(view, b);
        getChildren().add(b);
    }

    public void setActive(Views view) {
        buttons.forEach((k, btn) -> {
            btn.getStyleClass().remove("active");
            if (k == view) btn.getStyleClass().add("active");
        });
    }
}