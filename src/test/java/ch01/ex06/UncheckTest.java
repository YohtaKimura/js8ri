package ch01.ex06;

import org.junit.Test;

import static ch01.ex06.Uncheck.uncheck;

public class UncheckTest {
    @Test
    public void test() throws Exception {
        new Thread(uncheck(() -> {
            System.out.println("zzz");
            Thread.sleep(1000);
        })).start();
    }
}