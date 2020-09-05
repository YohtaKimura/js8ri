package ch01.ex07;

import org.junit.Test;
import static org.junit.Assert.*;

class AndThenTest {
    @Test
    public void Test() throws Exception {
        AndThen.andThen(() -> System.out.println("First"), () -> {
            System.out.println("Second");
        });
        assertEquals(1, 1);
    }
}