package ch04.dc;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.application.Platform;
import javafx.scene.text.Text;
import javafx.scene.text.Font;

import java.util.Calendar;

public class SimpleDc extends Application {
    int hour;
    int minute;
    int second;
    String aHour = "";
    String bMinute = "";
    String cSecond = "";
    private final Text text = new Text();
    private Font clockFont = new Font("SANS_SERIF", 50);
    private Color clockColor = new Color(0, 0, 0, 1.0);

    private void updateTime() {
        text.setText(getTime());
    }

    private String getTime() {
        Calendar rite = Calendar.getInstance();
        hour = rite.get(Calendar.HOUR_OF_DAY);
        minute = rite.get(Calendar.MINUTE);
        second = rite.get(Calendar.SECOND);
        if (hour < 10) {
            this.aHour = "0";
        }
        if (hour >= 10) {
            this.aHour = "";
        }
        if (minute < 10) {
            this.bMinute = "0";
        }
        if (minute >= 10) {
            this.bMinute = "";
        }
        if (second < 10) {
            this.cSecond = "0";
        }
        if (second >= 10) {
            this.cSecond = "";
        }
        return aHour + hour + ":" + bMinute + minute + ":" + cSecond + second;
    }

    @Override
    public void start(Stage primaryStage) {
        text.setFill(clockColor);
        text.setFont(clockFont);
        StackPane root = new StackPane();
        root.getChildren().add(text);

        Scene scene = new Scene(root, 400, 300);

        // longrunning operation runs on different thread
        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                Runnable updater = new Runnable() {

                    @Override
                    public void run() {
                        updateTime();
                    }
                };

                while (true) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                    }

                    // UI update is run on the Application thread
                    Platform.runLater(updater);
                }
            }

        });
        // don't let thread prevent JVM shutdown
        thread.setDaemon(true);
        thread.start();

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
