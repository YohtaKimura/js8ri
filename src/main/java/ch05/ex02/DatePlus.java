package ch05.ex02;

import java.time.LocalDate;

public class DatePlus {
    public static void main(String[] args) {
        // Results:
        // 2000.2.29 = 2000-02-29
        // 2000.2.29  + one year= 2001-02-28
        // 2000.2.29  + four year= 2004-02-29
        // 2000.2.29  + one year * 4 = 2004-02-28

        LocalDate date1 = LocalDate.of(2000, 2, 29);
        System.out.println("2000.2.29 = " + date1);
        System.out.println("2000.2.29  + one year= " + date1.plusYears(1));
        System.out.println("2000.2.29  + four year= " + date1.plusYears(4));
        System.out.println("2000.2.29  + one year * 4 = "
                + date1.plusYears(1).plusYears(1).plusYears(1).plusYears(1));
    }

}
