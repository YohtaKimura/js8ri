package ch03.ex15;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.File;

public class ImageTest extends Application {

    @Override
    public void start(Stage stage) {
        Image image = new Image(new File("./src/main/java/ch03/ex15/test.jpg").toURI().toString());
        Converter converter = Converter.of(image);
        converter.transform(Color::brighter);
        converter.transform(Color::grayscale);
        Image converted = converter.toImage();

        stage.setScene(new Scene(new HBox(
                new ImageView(image),
                new ImageView(converted)
        )));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
