package ch04.dc2;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Calendar;

public class SimpleDc extends Application {
    int hour;
    int minute;
    int second;
    String aHour = "";
    String bMinute = "";
    String cSecond = "";
    private final Text text = new Text();
    private int fontSize = 50;
    private Font clockFont = new Font("SANS_SERIF", fontSize);
    private Color clockColor = new Color(0, 0, 0, 1.0);
    private static MenuBar menuBar;

    private void updateTime() {
        text.setFill(clockColor);
        text.setFont(clockFont);
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

    void setClockFont(Font font) {
        clockFont = font;
    }

    void setFontSize(int size) {
        this.clockFont = new Font(clockFont.getName(), size);
    }

    double getFontSize(){
        return clockFont.getSize();
    }

    void setFontColor(Color color) {
        this.clockColor = color;
    }

    @Override
    public void start(Stage primaryStage) {
        text.setFill(clockColor);
        text.setFont(clockFont);
        BorderPane root = new BorderPane();
        root.setCenter(text);
        menuBar = new DcMenuBar(this);
        root.setTop(menuBar);

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
