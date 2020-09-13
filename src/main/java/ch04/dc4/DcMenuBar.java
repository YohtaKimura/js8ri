package ch04.dc4;

import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class DcMenuBar extends MenuBar {
    private static MenuItem changeViewMenu;
    private static SimpleDc dc;

    DcMenuBar(SimpleDc dc) {
        this.dc = dc;
        final Menu menu = new Menu("Menu");
        changeViewMenu = new MenuItem("View");
        changeViewMenu.setOnAction(this::menuAction);
        menu.getItems().addAll(changeViewMenu);
        getMenus().addAll(menu);
    }

    public void menuAction(ActionEvent e) {
        Object source = e.getSource();
        if (source.equals(changeViewMenu)) {
            Stage stage = new Stage();
            stage.setTitle("My New Stage Title");
            GridPane gridPane = new PropertiesPane(this.dc, stage);
            stage.setScene(new Scene(gridPane, 400, 300));
            stage.show();
        } else if (source.equals(null)) {
            TextInputDialog dialog = new TextInputDialog("font size");
            Optional<String> result = dialog.showAndWait();
            dc.setFontSize(Integer.valueOf(result.get()));
            System.out.println("FontSize");
        }
    }
}
