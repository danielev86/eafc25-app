package com.redcatdev86.ui;

import com.redcatdev86.service.IssueService;
import com.redcatdev86.service.RandomIssueService;
import com.redcatdev86.ui.view.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.util.EnumMap;
import java.util.Map;
import java.util.function.Supplier;

public class MainView extends BorderPane {

    private final NavBar navBar;
    private final StackPane content = new StackPane();

    // invece di Node cache -> factory che crea una view nuova ogni volta
    private final Map<Views, Supplier<Node>> viewFactories = new EnumMap<>(Views.class);

    public MainView(IssueService issueService) {
        getStyleClass().add("app-root");

        VBox top = new VBox(10);
        top.getStyleClass().add("top-wrap");
        top.setAlignment(Pos.CENTER);
        top.setPadding(new Insets(22, 20, 16, 20));

        Label appTitle = new Label("EA FC Issue Generator");
        appTitle.getStyleClass().add("app-title");

        Label appSubtitle = new Label("Career Mode random events • EA FC style");
        appSubtitle.getStyleClass().add("app-subtitle");

        VBox header = new VBox(6, appTitle, appSubtitle);
        header.setAlignment(Pos.CENTER);
        header.getStyleClass().add("header");

        navBar = new NavBar(this::navigate);
        navBar.setAlignment(Pos.CENTER);
        navBar.getStyleClass().add("nav-centered");

        top.getChildren().addAll(header, navBar);
        setTop(top);

        content.getStyleClass().add("content-wrap");
        content.setPadding(new Insets(18, 20, 30, 20));

        VBox container = new VBox(content);
        container.setAlignment(Pos.TOP_CENTER);
        container.getStyleClass().add("container");

        setCenter(container);

        // factories (NUOVA view ogni volta)
        viewFactories.put(Views.HOME, HomeView::new);
        viewFactories.put(Views.PLAYER, () -> new PlayerIssueView(issueService));
        viewFactories.put(Views.TEAM, () -> new TeamIssueView(issueService));
        viewFactories.put(Views.MARKET, () -> new MarketIssueView(issueService));
        viewFactories.put(Views.MANAGER, () -> new ManagerIssueView(issueService));
        viewFactories.put(Views.FORMATION, () -> new FormationView());
        viewFactories.put(Views.ADD_ISSUE, () -> new AddIssueView(issueService));

        navigate(Views.HOME);
    }

    private void navigate(Views view) {
        navBar.setActive(view);

        Supplier<Node> factory = viewFactories.get(view);
        if (factory == null) return;

        try {
            Node freshView = factory.get(); // view nuova => stato iniziale garantito
            content.getChildren().setAll(freshView);
        } catch (Throwable ex) {
            ex.printStackTrace(); // IMPORTANTISSIMO: guarda la console di IntelliJ

            Label err = new Label("ERROR OPENING " + view + ":\n" +
                    ex.getClass().getSimpleName() + ": " + (ex.getMessage() == null ? "" : ex.getMessage()));
            err.setWrapText(true);
            err.setMaxWidth(900);
            err.getStyleClass().add("result-desc");

            content.getChildren().setAll(err);
        }
    }
}