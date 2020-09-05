package ch01.ex05;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

@SuppressWarnings("serial")
public class ChangeFontColorDialog extends JDialog implements ActionListener,
        AdjustmentListener {

    private SimpleDigitalClock panel;
    private JScrollBar redBar, greenBar, blueBar;
    private Label redLabel, greenLabel, blueLabel;
    private Color color;
    private Panel colorPanel;

    ChangeFontColorDialog(JFrame owner, SimpleDigitalClock panel) {
        super(owner);
        this.panel = panel;
        this.color = panel.getForeground();

        setLayout(new BorderLayout());
        setTitle("Change font color");

        colorPanel = new Panel();
        colorPanel.setBackground(color);
        colorPanel.setPreferredSize(new Dimension(300, 110));
        add(colorPanel, BorderLayout.NORTH);

        Panel barPanel = new Panel(new GridLayout(3, 1));
        redBar = new JScrollBar(JScrollBar.HORIZONTAL);
        redBar.setMinimum(0);
        redBar.setMaximum(265);
        redBar.setValue(color.getRed());
        redBar.addAdjustmentListener(this);
        barPanel.add(redBar);
        greenBar = new JScrollBar(JScrollBar.HORIZONTAL);
        greenBar.setMinimum(0);
        greenBar.setMaximum(265);
        greenBar.setValue(color.getGreen());
        greenBar.addAdjustmentListener(this);
        barPanel.add(greenBar);
        blueBar = new JScrollBar(JScrollBar.HORIZONTAL);
        blueBar.setMinimum(0);
        blueBar.setMaximum(265);
        blueBar.setValue(color.getBlue());
        blueBar.addAdjustmentListener(this);
        barPanel.add(blueBar);
        barPanel.setPreferredSize(new Dimension(270, 56));
        add(barPanel, BorderLayout.CENTER);

        Panel valuePanel = new Panel(new GridLayout(3, 1));
        redLabel = new Label(Integer.toString(color.getRed()));
        redLabel.setAlignment(Label.RIGHT);
        valuePanel.add(redLabel);
        greenLabel = new Label(Integer.toString(color.getGreen()));
        greenLabel.setAlignment(Label.RIGHT);
        valuePanel.add(greenLabel);
        blueLabel = new Label(Integer.toString(color.getBlue()));
        blueLabel.setAlignment(Label.RIGHT);
        valuePanel.add(blueLabel);
        valuePanel.setPreferredSize(new Dimension(30, 56));
        add(valuePanel, BorderLayout.EAST);

        Button okButton = new Button("OK");
        okButton.addActionListener(this);
        add(okButton, BorderLayout.SOUTH);
        pack();
        setLocationRelativeTo(null);
        addWindowListener(new DialogCloseAdapter(this));
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        panel.setClockColor(color);
        setVisible(false);
    }

    @Override
    public void adjustmentValueChanged(AdjustmentEvent e) {
        int r = redBar.getValue();
        int g = greenBar.getValue();
        int b = blueBar.getValue();
        redLabel.setText(Integer.toString(r));
        greenLabel.setText(Integer.toString(g));
        blueLabel.setText(Integer.toString(b));
        // colorchip
        colorPanel.setBackground(color);
        color = new Color(r, g, b);
    }
}