package ch09.ex05;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class ReverseContents {

    public static void main(String[] args) throws IOException {
        byte[] bytes = Files.readAllBytes(Paths.get("ch09/ex05/Alice.txt"));
        bytes = reverse(bytes);
        Files.write(Paths.get("ch09/ex05/out.txt"), bytes, StandardOpenOption.CREATE);
    }

    private static byte[] reverse(byte[] bytes) {
        int length = bytes.length;
        byte[] result = new byte[length];


        for (int i = 0; i < length; i++)
            result[i] = bytes[length - 1 - i];

        return result;
    }

}
