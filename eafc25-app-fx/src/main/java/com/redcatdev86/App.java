package com.redcatdev86;


import com.redcatdev86.backend.Database;
import com.redcatdev86.backend.IssueRepository;
import com.redcatdev86.service.IssueService;
import com.redcatdev86.service.RandomIssueService;
import com.redcatdev86.ui.MainView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage) {
        // Backend bootstrap
        var db = Database.fromInstalledDb();
        var repo = new IssueRepository(db);
        var issueService = new IssueService(repo);
        var randomService = new RandomIssueService(issueService);

        var root = new MainView(issueService, randomService);

        var scene = new Scene(root, 1100, 900);
        scene.getStylesheets().add(getClass().getResource("/ui/app.css").toExternalForm());

        stage.setTitle("EAFC25 Issues");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}