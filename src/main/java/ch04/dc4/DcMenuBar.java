package ch04.dc4;

import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.text.Font;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class DcMenuBar extends MenuBar {
    private static MenuItem changeFontMenu;
    private static MenuItem changeFontSizeMenu;
    private static MenuItem changeFontColorMenu;
    private static MenuItem changeBackgroundColorMenu;
    private static SimpleDc dc;

    DcMenuBar(SimpleDc dc) {
        this.dc = dc;
        final Menu viewMenu = new Menu("View");
        final MenuItem view = new MenuItem("View");
        changeFontMenu = new MenuItem("Change font...");
        changeFontMenu.setOnAction(DcMenuBar::menuAction);
        changeFontSizeMenu = new MenuItem("Change font size...");
        changeFontSizeMenu.setOnAction(DcMenuBar::menuAction);
        changeFontColorMenu = new MenuItem("Change font color...");
        changeFontColorMenu.setOnAction(DcMenuBar::menuAction);
        changeBackgroundColorMenu = new MenuItem("Change background color...");
        changeBackgroundColorMenu.setOnAction(DcMenuBar::menuAction);
        viewMenu.getItems().addAll(changeFontMenu, changeFontSizeMenu, changeBackgroundColorMenu, changeFontColorMenu);
        getMenus().addAll(viewMenu);
    }

    public static void menuAction(ActionEvent e) {
        Object source = e.getSource();
        if (source.equals(changeFontMenu)) {
            System.out.println("Font");

            List<String> fonts = Font.getFamilies();
            ChoiceDialog<String> dialog = new ChoiceDialog<>( fonts.get( 0 ) , fonts );
            Optional<String> result = dialog.showAndWait();
            System.out.println(result.get());
            dc.setClockFont(new Font(result.get(), dc.getFontSize()));
        } else if (source.equals(changeFontSizeMenu)) {
            TextInputDialog dialog  = new TextInputDialog( "font size" );
            Optional<String> result = dialog.showAndWait();
            dc.setFontSize(Integer.valueOf(result.get()));
            System.out.println("FontSize");
        } else if (source.equals(changeFontColorMenu)) {
            List<DisplayColor> colors = Arrays.asList(DisplayColor.values());
            ChoiceDialog<DisplayColor> dialog = new ChoiceDialog<>( colors.get(0), colors );
            Optional<DisplayColor> result = dialog.showAndWait();
            System.out.println(result.get());
            dc.setFontColor(result.get().getValue());
            System.out.println("FontColor");
        } else if (source.equals(changeBackgroundColorMenu)) {
            List<DisplayColor> colors = Arrays.asList(DisplayColor.values());
            ChoiceDialog<DisplayColor> dialog = new ChoiceDialog<>( colors.get(0), colors );
            Optional<DisplayColor> result = dialog.showAndWait();
            System.out.println(result.get());
            dc.setBackGroundColor(result.get().getValue());
            System.out.println("BackgroundColor");
        }
    }
}
