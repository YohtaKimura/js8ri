package ch04.dc4;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuBar;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
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
    private Color clockColor = Color.rgb(0, 0, 0);
    private Scene scene;
    private Color backGroundColor = Color.rgb(255, 255, 255);
    private BorderPane root;
    private Stage primaryStage;
    private static MenuBar menuBar;

    static ConfigurationPreferences getConfiguration() {
	    return new ConfigurationPreferences();
	}

    private void updateTime() {
        root.setBackground(new Background(new BackgroundFill(backGroundColor, new CornerRadii(0), Insets.EMPTY)));
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

    Font getClockFont() {
        return  this.clockFont;
    }

    void setFontSize(int size) {
        this.clockFont = new Font(clockFont.getName(), size);
    }

    double getFontSize(){
        return clockFont.getSize();
    }

    DisplayColor getFontColor() {
        return DisplayColor.valueOf(this.clockColor);
    }

    void setFontColor(Color color) {
        this.clockColor = color;
    }

    DisplayColor getBackGroundColor() {
        return DisplayColor.valueOf(this.backGroundColor);
    }

    void setBackGroundColor(Color color) {
        this.backGroundColor = color;
    }

    int getHeight() {
        return (int)primaryStage.getHeight();
    }

    int getWidth() {
        return (int)primaryStage.getWidth();
    }

    Point2D getLocation() {
        return new Point2D(primaryStage.getX(), primaryStage.getY());
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        ConfigurationPreferences conf = this.getConfiguration();
        setFontColor(conf.getForeground().getValue());
        setBackGroundColor(conf.getBackground().getValue());
        clockFont = new Font(conf.getFontName(), conf.getFontSize());

        setClockFont(clockFont);
        text.setFill(clockColor);
        text.setFont(clockFont);
        root = new BorderPane();
        root.setCenter(text);
        menuBar = new DcMenuBar(this);
        root.setTop(menuBar);

        scene = new Scene(root, 400, 300);

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
        if (conf.getLocation() != null) {
            primaryStage.setX(conf.getLocation().getX());
            primaryStage.setY(conf.getLocation().getY());
        }
        primaryStage.setWidth(conf.getWidth());
        primaryStage.setHeight(conf.getHeight());

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
