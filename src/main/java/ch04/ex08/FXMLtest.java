package ch04.ex08;

import java.io.File;
import java.net.URL;
import java.nio.file.Paths;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import javax.tools.JavaFileManager;
import javax.xml.stream.Location;

public class FXMLtest extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        // フォント色がおかしくなることへの対処
        System.setProperty( "prism.lcdtext" , "false" );

        // FXMLファイルの読込
        String path = new File(".").getAbsoluteFile().getParent();
        URL location = new File(Paths.get(path + "\\src\\main\\java\\ch04\\ex08\\FXMLtest.fxml").toString()).toURI().toURL();
        FXMLLoader fxmlLoader = new FXMLLoader( location );

        // シーングラフの作成
        Pane root = (Pane) fxmlLoader.load();

        // シーンの作成
        Scene scene = new Scene( root , 200 , 50 );

        // ウィンドウ表示
        primaryStage.setScene( scene );
        primaryStage.show();

    }

}