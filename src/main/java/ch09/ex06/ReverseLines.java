package ch09.ex06;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Collections;
import java.util.List;

public class ReverseLines {
    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("ch09/ex06/Alice.txt"));
        Collections.reverse(lines);
        Files.write(Paths.get("ch09/ex06/out.txt"), lines, StandardOpenOption.CREATE);
    }
}
