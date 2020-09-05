package ch01.ex05;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class ChangeFontSizeDialog extends Dialog implements ActionListener {

    private Frame owner;
    private TextField sizeTextField;
    private SimpleDigitalClock panel;

    ChangeFontSizeDialog(Frame owner, SimpleDigitalClock panel) {
        super(owner);
        this.panel = panel;
        this.owner = owner;

        setLayout(new FlowLayout());
        setTitle("Change font size");

        Label sizeLabel = new Label("Font size (px): ");
        add(sizeLabel);

        sizeTextField = new TextField("", 3);
        sizeTextField.addActionListener(this);
        add(sizeTextField);

        Button okButton = new Button("OK");
        okButton.addActionListener(this);
        add(okButton);

        pack();
        setLocationRelativeTo(null);
        addWindowListener(new DialogCloseAdapter(this));
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Font currentFont = panel.getClockFont();
        int size = currentFont.getSize();
        try {
            size = Integer.parseInt(sizeTextField.getText());
        } catch (NumberFormatException nfe) {
            new IllegalInputDialog(this, "数字を入れてね☆");
            return;
        }
        if (size < 10) {
            new IllegalInputDialog(this, "数字は 10 以上で入れてね☆");
            return;
        } else if (size > 500) {
            new IllegalInputDialog(this, "数字がでかすぎるよ☆");
            return;
        }
        panel.setClockFont(new Font(currentFont.getFamily(), currentFont
                .getStyle(), size));
        owner.pack();
        owner.setLocationRelativeTo(null);
        setVisible(false);
    }
}

@SuppressWarnings("serial")
class IllegalInputDialog extends Dialog {

    public IllegalInputDialog(Dialog owner, String message) {
        super(owner);
        setLayout(new FlowLayout());
        setTitle("Change font size");

        add(new Label(message));

        Button okButton = new Button("OK");
        okButton.addActionListener((e) -> setVisible(false));
        add(okButton);

        pack();
        setLocationRelativeTo(null);
        addWindowListener(new DialogCloseAdapter(this));
        setVisible(true);
    }
}