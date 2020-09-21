package ch05.ex05;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class DaysFromBirth {
    public static void main(String[] args) {
        int year = 1988;
        int month = 6;
        int day = 19;

        LocalDate birthday = LocalDate.of(year, month, day);
        LocalDate now = LocalDate.now();
        System.out.printf("%d days%n",
                birthday.until(now, ChronoUnit.DAYS));

    }
}
