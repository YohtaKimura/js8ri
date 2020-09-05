package ch01.ex05;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.function.Function;

@SuppressWarnings("serial")
public class DigitalClockFrame extends JFrame {
    private SimpleDigitalClock panel;
    private JMenuItem changeFontMenu;
    private JMenuItem changeFontSizeMenu;
    private JMenuItem changeFontColorMenu;
    private JMenuItem changeBackgroundColorMenu;
    private JMenuItem aboutMenu;

    public DigitalClockFrame(String title, SimpleDigitalClock panel) {
        super(title);
        this.panel = panel;

        // Frame configuration
        setLayout(new BorderLayout());
        addWindowListener(new ApplicationCloseAdapter());
        setResizable(false);
        //setIgnoreRepaint(true);
        getContentPane().add(panel);


        // Menu configuration
        JMenuBar menuBar = new JMenuBar();
        // [View]
        JMenu viewMenu = new JMenu("View");
//        viewMenu.setShortcut(new MenuShortcut('V'));
        JMenuItem view = new JMenuItem("View");
        viewMenu.add(view);
        menuBar.add(viewMenu);
        // [View] -> [Change font...]
        // 複数行ある処理をラムダにすると可読性が落ちる
        ActionListener actionListener = (ActionEvent e) -> {
            Object source = e.getSource();
        if (source.equals(changeFontMenu)) {
            new ChangeFontDialog(this, panel);
        } else if (source.equals(changeFontSizeMenu)) {
            new ChangeFontSizeDialog(this, panel);
        } else if (source.equals(changeFontColorMenu)) {
            new ChangeFontColorDialog(this, panel);
        } else if (source.equals(changeBackgroundColorMenu)) {
            new ChangeBackgroundColorDialog(this, panel);
        } };

        changeFontMenu = new JMenuItem("Change font...");
        changeFontMenu.addActionListener(actionListener);
        viewMenu.add(changeFontMenu);
        // [View] -> [Change font size...]
        changeFontSizeMenu = new JMenuItem("Change font size...");
        changeFontSizeMenu.addActionListener(actionListener);
        viewMenu.add(changeFontSizeMenu);
        // [View] -> [Change font color...]
        changeFontColorMenu = new JMenuItem("Change font color...");
        changeFontColorMenu.addActionListener(actionListener);
        viewMenu.add(changeFontColorMenu);
        // [View] -> [Change background color...]
        changeBackgroundColorMenu = new JMenuItem("Change background color...");
        changeBackgroundColorMenu.addActionListener(actionListener);
        viewMenu.add(changeBackgroundColorMenu);
        // [Help]
        JMenu helpMenu = new JMenu("Help");
//        helpMenu.setShortcut(new MenuShortcut('H'));
        menuBar.add(helpMenu);

        setJMenuBar(menuBar);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    void resize() {
        pack();
    }
}

class ApplicationCloseAdapter extends WindowAdapter {
    @Override
    public void windowClosing(WindowEvent e) {
        System.exit(0);
    }
}

/**
 * Window adapter for the dialog closing.
 */
class DialogCloseAdapter extends WindowAdapter {
    Dialog dialog;

    public DialogCloseAdapter(Dialog dialog) {
        this.dialog = dialog;
    }

    @Override
    public void windowClosing(WindowEvent e) {
        dialog.setVisible(false);
    }
}