package ch02.ex03;


import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class CountWords extends Thread {
	String word;
	static int count;

	public CountWords(String word) {
		this.word = word;
	}

	public static void setCount(int recount) {
		count = recount;
	}

	public static int getCount() {
		return count;
	}

	@Override
	public void run() {
		if (word.length() > 7) {
			counter();
		}
	}

	synchronized public int counter() {
		return count++;
	}

	public static void main(String[] args) throws IOException, InterruptedException {
	    String path = new File(".").getAbsoluteFile().getParent();
	    String contents = new String(Files.readAllBytes(Paths.get(path + "\\src\\main\\java\\ch02\\ex02\\Alice.txt")),
					StandardCharsets.UTF_8);
// https://stackoverflow.com/questions/36312464/what-does-regex-pattern-pl-mean-in-java
	    List<String> words = Arrays.asList(contents.split("[\\P{L}]+"));
	    long start = System.nanoTime();
		List<String> longWords = words.stream().filter(w -> w.length() > 8).collect(Collectors.toList());
		long end = System.nanoTime();
		long streamSec = end - start;
		System.out.println(longWords);
		start = System.nanoTime();
		longWords = words.parallelStream().filter(w -> w.length() > 8).collect(Collectors.toList());
		end = System.nanoTime();
		long paraStreamSec = end - start;
		System.out.println(longWords);
		System.out.println(streamSec);
		System.out.println(paraStreamSec);
		System.out.println("処理速度" + ((float) (streamSec) / paraStreamSec) + "倍");
	}
}

