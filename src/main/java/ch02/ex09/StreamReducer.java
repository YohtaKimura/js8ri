package ch02.ex09;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Stream;

public class StreamReducer {
    public static <T> ArrayList<T> reduceStream(Stream<ArrayList<T>> stream) {
		Optional<ArrayList<T>> resultOpt = stream.reduce((x, y) -> {
			x.addAll(y);
			return x;
		});
		ArrayList<T> result = new ArrayList<T>();
		resultOpt.ifPresent(result::addAll);
		return result;
	}

    public static <T> ArrayList<T> reduceStream2(Stream<ArrayList<T>> stream) {
		ArrayList<T> result = stream.reduce(new ArrayList<T>(), (x, y) -> {
			x.addAll(y);
			return x;
		});
		return result;
	}

}
