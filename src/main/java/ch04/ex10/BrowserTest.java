package ch04.ex10;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebHistory;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class BrowserTest extends Application {
    @Override
    public void start(Stage stage) {
        WebView browser = new WebView();
        WebEngine engine = browser.getEngine();
        WebHistory history = engine.getHistory();
        TextField urlField = new TextField();
        urlField.setOnAction(e -> engine.load(urlField.getText()));

        Button backButton = new Button("←");
        backButton.setOnAction(e -> {
            history.go(-1);
            updateURL(urlField, history);
        });
        Button forwardButton = new Button("→");
        forwardButton.setOnAction(e -> {
            history.go(1);
            updateURL(urlField, history);
        });

        VBox box = new VBox();
        FlowPane firstLine = new FlowPane();
        firstLine.getChildren().addAll(backButton, forwardButton, urlField);

        box.getChildren().addAll(firstLine, browser);

        Scene scene = new Scene(box);
        stage.setScene(scene);
        stage.setWidth(500);
        stage.setHeight(500);
        stage.show();
    }

    private void updateURL(TextField urlField, WebHistory history) {
        urlField.setText(history.getEntries().get(history.getCurrentIndex()).getUrl());
    }

    public static void main(String[] args) {
        launch(args);
    }

}
