package ch02.ex13;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ParallelCount {
    public static void main(String args[]) throws IOException {
        String path = new File(".").getAbsoluteFile().getParent();
	    String contents = new String(Files.readAllBytes(Paths.get(path + "\\src\\main\\java\\ch02\\ex13\\Alice.txt")),
					StandardCharsets.UTF_8);
// https://stackoverflow.com/questions/36312464/what-does-regex-pattern-pl-mean-in-java
	    Stream<String> words = Stream.of(contents.split("[\\P{L}]+"));
        int[] count = countShortWords(words, 4);
        System.out.println(Arrays.toString(count));
    }

    public static int[] countShortWords(Stream<String> words, int length) {
        Map<Integer, Long> shortWordCounts = words.filter(
                w -> !w.isEmpty() && w.length() < length).collect(
                        Collectors.groupingBy(w -> w.length(), Collectors.counting()));

        int[] result = new int[length];
        for (int i = 0; i < length; i++) {
            Long counts = shortWordCounts.get(i);
            result[i] = (counts == null) ? 0 : counts.intValue();
        }
        return result;
    }
}
