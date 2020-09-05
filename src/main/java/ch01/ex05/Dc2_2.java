package ch01.ex05;

import javax.swing.*;

public class Dc2_2 {
    public static void main(String[] args) {
        //JFrame frm = new JFrame();
        SimpleDigitalClock clock1 = new SimpleDigitalClock();
        DigitalClockFrame frm = new DigitalClockFrame("dc", clock1);
        frm.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//        frm.add(clock1);
        frm.pack();
        frm.setVisible(true);
    }
}