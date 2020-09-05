package ch02.ex05;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LinerCongruentialGenerator {

	public static void main(String[] args) {
		Stream<Long> randomStream = linearRandom(25214903917l, 11, (long) Math.pow(2, 48), 0);
		for (long l : randomStream.limit(100).collect(Collectors.toList()))
			System.out.println((long) l);
	}

	public static Stream<Long> linearRandom(long a, long c, long m, long seed) {
		return Stream.iterate(seed, n -> {
			return (a * n + c) % m;
		});
	}
}
