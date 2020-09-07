package ch02.ex10;

import org.junit.Test;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class AvarageDoubleStreamTest {
	@Test
	public void test() {
		Double[] data = { 10.0, 20.0, 30.0, 40.0, 50.0, 60.0, 70.0, 80.0, 90.0 };
		Stream<Double> stream = Stream.of(data);
		double ans = AverageDoubleStream.average(stream);
		double expect = 0;
		for (double d : data)
			expect += d;
		expect /= data.length;
		assertEquals(ans, expect);
	}
}