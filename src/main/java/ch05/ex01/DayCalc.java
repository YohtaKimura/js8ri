package ch05.ex01;

import java.time.LocalDate;
import java.time.Period;

public class DayCalc {
    public static void main(String[] args) {
        LocalDate programmersDay = LocalDate.of(2020, 9, 20).plusDays(255);
        System.out.println(programmersDay);
        programmersDay = LocalDate.of(2020, 9, 20).plus(Period.ofDays(255));
        System.out.println(programmersDay);
    }
}
