package ch01.ex05;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

class SimpleDigitalClock extends JPanel {
    String stringTime;
    int hour;
    int minute;
    int second;
    String aHour = "";
    String bMinute = "";
    String cSecond = "";
    private Font clockFont = new Font(Font.SANS_SERIF, Font.BOLD, 50);
    private Color clockColor = new Color(0, 0, 0);

    public void setStringTime(String abc) {
        this.stringTime = abc;
    }

    public int getSmallerOne(int a, int b) {
        return (a <= b) ? a : b;
    }

    SimpleDigitalClock() {
        Timer t = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                repaint();
            }
        });
        t.start();
    }

    @Override
    public void paintComponent(Graphics v) {
        super.paintComponent(v);
        setStringTime(getTime());
        v.setColor(this.clockColor);
        int length = getSmallerOne(this.getWidth(), this.getHeight());
//        Font Font1 = new Font("SansSerif", Font.PLAIN, length / 5);

        v.setFont(this.clockFont);
        v.drawString(stringTime, (int) length / 6, length / 2);
    }

    String getTime() {
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

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(300, 300);
    }

    void setClockFont(Font font) {
        clockFont = font;
        // Simulate preferred size
        Label tempLabel = new Label(getTime());
        tempLabel.setFont(font);
        Frame frame = new Frame();
        frame.add(tempLabel);
        frame.pack();
    }

    Font getClockFont() {
        return clockFont;
    }

    void setClockColor(Color c) {
        clockColor = c;
    }
}
