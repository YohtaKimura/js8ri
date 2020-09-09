package ch04.ex01;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class HelloWorld extends Application {
    private static final String INITIAL_MESSAGE = "Hello, Java!";
    @Override
    public void start(Stage primaryStage) {
        TextArea input = new TextArea(INITIAL_MESSAGE);
        Label message = new Label(INITIAL_MESSAGE);
        message.setFont(new Font(100));
        message.textProperty().bindBidirectional(input.textProperty());
        VBox root = new VBox();
        root.getChildren().addAll(message, input);

        Scene scene = new Scene(root);

        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
