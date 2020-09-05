package ch01.ex06;

public class Uncheck {
    public static Runnable uncheck(RunnableEx runner){
        return () -> {
            try {
                runner.run();
            } catch (Exception e) {
                e.printStackTrace();
            }
        };
    }
}
