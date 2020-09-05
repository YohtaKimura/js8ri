package ch01.ex05;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class ChangeFontDialog extends JDialog {
    private static Frame owner;
    private static Choice fontChoise;
    private static SimpleDigitalClock panel;

    ChangeFontDialog(Frame owner, SimpleDigitalClock panel) {
        super(owner);
        this.panel = panel;
        this.owner = owner;

        setLayout(new FlowLayout());
        setTitle("Change font");

        GraphicsEnvironment ge = GraphicsEnvironment
                .getLocalGraphicsEnvironment();
        Font[] fonts = ge.getAllFonts();
        fontChoise = new Choice();
        for (Font f : fonts) {
            fontChoise.add(f.getName());
        }
        add(fontChoise);

        Button okButton = new Button("OK");
        okButton.addActionListener(ChangeFontDialog::actionPerformed);
        add(okButton);

        pack();
        setLocationRelativeTo(null);
        addWindowListener(new DialogCloseAdapter(this));
        setVisible(true);
    }

    public static void actionPerformed(ActionEvent e) {
        Font currentFont = panel.getClockFont();
        panel.setClockFont(new Font(fontChoise.getSelectedItem(), currentFont
                .getStyle(), currentFont.getSize()));
        owner.pack();
        owner.setLocationRelativeTo(null);
        // setVisible(false);
    }
}