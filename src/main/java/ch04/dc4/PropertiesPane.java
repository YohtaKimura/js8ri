package ch04.dc4;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.List;

public class PropertiesPane extends GridPane {
    private  ComboBox<String> fontsCmb;
    private ComboBox<String> sizesCmb;
    private ComboBox<DisplayColor> fontColorCmb;
    private ComboBox<DisplayColor> backgroundColorCmb;
    private Stage parent;
    private final SimpleDc dc;

    PropertiesPane(SimpleDc dc, Stage stage) {
        this.parent = stage;
        this.dc = dc;
        setAlignment(Pos.CENTER);
        setHgap(10);
        setVgap(12);

        ColumnConstraints column1 = new ColumnConstraints();
        column1.setHalignment(HPos.RIGHT);
        getColumnConstraints().add(column1);

        ColumnConstraints column2 = new ColumnConstraints();
        column2.setHalignment(HPos.LEFT);
        getColumnConstraints().add(column2);

        Label fontLabel = new Label("Font:");
        fontsCmb = new ComboBox<>();
        List<String> fonts = Font.getFamilies();
        for (String font: fonts) {
            fontsCmb.getItems().add(font);
        }
        add(fontLabel, 0, 0);
        add(fontsCmb, 1, 0);

        Label fontSizeLabel = new Label("Font size:");
        sizesCmb = new ComboBox<>();
        for (int i = 10; i <= 250; i++) {
	      if (i % 5 != 0)
		  continue;
	      sizesCmb.getItems().add(Integer.toString(i));
	    }
        add(fontSizeLabel, 0, 1);
        add(sizesCmb, 1, 1);

        Label fontColorLabel = new Label("Font color:");
        fontColorCmb = new ComboBox<>();
        for (DisplayColor color: DisplayColor.values()) {
            fontColorCmb.getItems().add(color);
        }
        add(fontColorLabel, 0, 2);
        add(fontColorCmb, 1, 2);

        Label backgroundColorLabel = new Label("Background color:");
        backgroundColorCmb = new ComboBox<>();
        for (DisplayColor color: DisplayColor.values()) {
            backgroundColorCmb.getItems().add(color);
        }
        add(backgroundColorLabel, 0, 3);
        add(backgroundColorCmb, 1, 3);

        Button btnCancel = new Button("Cancel");
        btnCancel.setOnAction(new EventHandler<ActionEvent>() {
	          @Override
              public void handle(ActionEvent event) {
	              stage.close();
	          }});

        Button btnOK = new Button("OK");
        btnOK.setOnAction(new EventHandler<ActionEvent>() {
	          @Override
              public void handle(ActionEvent event) {
	              Font newFont = new Font(fontsCmb.getValue(), dc.getFontSize());
	              dc.setClockFont(newFont);
	              dc.setFontSize(Integer.valueOf(sizesCmb.getValue()));
	              DisplayColor fontCol = fontColorCmb.getValue();
	              dc.setFontColor(fontCol.getValue());
	              DisplayColor backGroundCol = backgroundColorCmb.getValue();
	              dc.setBackGroundColor(backGroundCol.getValue());
	              ConfigurationPreferences conf = dc.getConfiguration();
	              conf.update(dc.getLocation(), dc.getWidth(), dc.getHeight(),
                        newFont, fontCol,
                        backGroundCol);
	              stage.close();
	          }});

        HBox hbButtons = new HBox();
        hbButtons.getChildren().addAll(btnCancel, btnOK);
        add(hbButtons, 1, 4, 2, 1);
    }
}
