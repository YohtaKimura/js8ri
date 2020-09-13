package ch04.dc4;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;

import java.util.List;

public class PopupMenu extends ContextMenu {
    private static Menu changeFontMenu;
    private static Menu changeFontSizeMenu;
    private static Menu changeFontColorMenu;
    private static Menu changeBackgroundColorMenu;
    private SimpleDc dc;

    PopupMenu(SimpleDc dc) {
        this.dc = dc;
        final Menu viewMenu = new Menu("View");
        changeFontMenu = new Menu("Change font...");
        List<String> fonts = Font.getFamilies();
	    for (String f : fonts) {
	        MenuItem item = new MenuItem(f);
	        item.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                dc.setClockFont(new Font(f, dc.getFontSize()));
            }});
	        changeFontMenu.getItems().add(item);
	    }

        changeFontSizeMenu = new Menu("Change font size...");
        for (int i = 10; i <= 250; i++) {
	      if (i % 5 != 0)
		  continue;
	      final int finalei = i;
	      MenuItem item = new MenuItem(Integer.toString(i));

	      item.setOnAction(new EventHandler<ActionEvent>() {
	          @Override
              public void handle(ActionEvent event) {
	              dc.setFontSize(finalei);
	          }});
	      changeFontSizeMenu.getItems().add(item);
	    }

        changeFontColorMenu = new Menu("Change font color...");
        for (DisplayColor c : DisplayColor.values()) {
	      MenuItem item = new MenuItem(c.toString());
	      item.setOnAction(new EventHandler<ActionEvent>() {
	          @Override
              public void handle(ActionEvent event) {
	              dc.setFontColor(c.getValue());
	          }});
	      changeFontColorMenu.getItems().add(item);
	    }

        changeBackgroundColorMenu = new Menu("Change background color...");
        for (DisplayColor c : DisplayColor.values()) {
	      MenuItem item = new MenuItem(c.toString());
	      item.setOnAction(new EventHandler<ActionEvent>() {
	          @Override
              public void handle(ActionEvent event) {
	              dc.setBackGroundColor(c.getValue());
	          }});
	      changeBackgroundColorMenu.getItems().add(item);
	    }

        changeBackgroundColorMenu.setOnAction(DcMenuBar::menuAction);
        viewMenu.getItems().addAll(changeFontMenu, changeFontSizeMenu, changeBackgroundColorMenu, changeFontColorMenu);
        getItems().addAll(changeFontMenu, changeFontSizeMenu, changeFontColorMenu, changeBackgroundColorMenu);
        TextField textField = new TextField("Type Something"); // we will add a popup menu to this text field
        textField.setContextMenu(this);
    }
}
