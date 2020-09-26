package ch08.ex05;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class WordCountingPerformance {
    private static final int TRIAL_COUNT = 10;


    public static void main(String[] args) throws Exception {
        String path = new File(".").getAbsoluteFile().getParent();
        List<String> words = readAsWords(Paths.get(path + "\\src\\main\\java\\ch08\\ex05\\Alice.txt").toString());

        withStream(words);
        withFor(words);
        withForEach(words);
    }

    public static void withStream(List<String> words) {
        measurePerformance(() -> {
            words.stream().filter(w -> w.length() > 12).count();
        });
    }

    public static void withFor(List<String> words) {
        Predicate<String> p = w -> w.length() > 12;
        measurePerformance(()-> {
            long count = 0;
            for (String w: words) {
                if (p.test(w))
                    count++;
            }
        });
    }

    public static void withForEach(List<String> words) {
        measurePerformance(() -> {
                        long[] count = new long[1];
            words.forEach(w -> {
                if (w.length() > 12)
                    count[0]++;
            });
        });
    }

    private static void measurePerformance(Runnable r) {
        double total = 0.0;
        for (int i = 0; i < TRIAL_COUNT; i++) {
            long start = System.nanoTime();
            r.run();
            total += System.nanoTime() - start;
        }
        System.out.printf("%f%n", (total / TRIAL_COUNT) / 1000_1000);
    }

    private static List<String> readAsWords(String filepath) {
        if (filepath == null) {
            throw new NullPointerException("filepath is null");
        }

        String contents = null;
        try {
            contents = new String(java.nio.file.Files.readAllBytes(
                    Paths.get(filepath)), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new IllegalArgumentException("Cannot Open " + filepath);
        }

        return Arrays.asList(contents.split("[\\P{L}]+"));
    }

 }
