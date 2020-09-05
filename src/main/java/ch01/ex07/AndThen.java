package ch01.ex07;

public class AndThen {
    public static void main(String args[]) {
        Thread t =new Thread(AndThen.andThen(() -> System.out.println("First"), () -> {
            System.out.println("Second"); }));
        t.start();
    }

    public static Runnable andThen(Runnable first, Runnable second) {
        return () -> {
            first.run();
            second.run();
        };
    }
}
