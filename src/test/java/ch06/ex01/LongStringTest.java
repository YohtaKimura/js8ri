package ch06.ex01;

import org.junit.Test;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class LongStringTest {
    @Test
    public void parallelSet() throws Exception {
        String path = new File(".").getAbsoluteFile().getParent();
	    String contents = new String(Files.readAllBytes(Paths.get(path + "\\src\\main\\java\\ch02\\ex01\\Alice.txt")),
					StandardCharsets.UTF_8);
// https://stackoverflow.com/questions/36312464/what-does-regex-pattern-pl-mean-in-java
	    List<String> words = Arrays.asList(contents.split("[\\P{L}]+"));
        LongString ls = new LongString();

        words.parallelStream().forEach(ls::setIfLongest);
        System.out.println(ls.getLongest());
        assertEquals(longString(words), ls.getLongest());
    }

    private String longString(List<String> strings) {
        String result = "";
        for (String s: strings) {
            if (s.length() > result.length())
                result = s;
        }
        return result;
    }

}