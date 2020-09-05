package ch02.ex08;

import java.util.Iterator;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.List;

public class Zip {
    public static <T> Stream<T> zip(Stream<T> first, Stream<T> second) {
        Stream.Builder<T> result = Stream.builder();
        Iterator<T> fI = first.iterator();
        Iterator<T> sI = second.iterator();
        while(fI.hasNext() && sI.hasNext()) {
            T fEle = fI.next();
            result.add(fEle);
            T sEle = sI.next();
            result.add(sEle);
        }

		return result.build();
	}

	public static void main(String[] args) {
		Stream<String> f = Stream.of("a", "b", "c");
		Stream<String> s = Stream.of("d", "e", "f", "g");
		System.out.println(zip(f, s).collect(Collectors.toList()));
	}
}
