package ch02.ex04;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamOfInt {
    public static void main(String[] args) {
		int[] values = { 1, 4, 9, 16 };
		Stream<int[]> stream = Stream.of(values);
		IntStream intstream = Arrays.stream(values);
		// https://docs.oracle.com/javase/jp/8/docs/api/java/util/stream/IntStream.html
		Stream<Integer> streamOfInt = Arrays.stream(values).boxed();
		for (int i : stream.collect(Collectors.toList()).get(0)) {
            System.out.print(i + " ");
        }
		System.out.println();
		System.out.println(streamOfInt.collect(Collectors.toList()));
	}
}
