package ch03.ex20;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class ListMapper {
    public static void main(String args[]) {
        List<String> l1 = new ArrayList<>();
        l1.add("1");
        l1.add("2");
        l1.add("3");
        System.out.println(Arrays.toString(l1.toArray()));
        l1.stream().forEach(e -> {
            System.out.println(e.getClass());
            System.out.println(e);
        });
        Function f = (s) -> {
            return Integer.valueOf(s.toString());
        };
        List l2 = map(l1, f);
        l2.stream().forEach(e -> {
            System.out.println(e.getClass());
            System.out.println(e);
        });
    }

	@SuppressWarnings("unchecked")
	public static <T, U> List<U> map(List<T> l, Function<T, U> f) {
		List<U> result;
		try {
			result = l.getClass().newInstance();
			for (T element : l) {
				result.add(f.apply(element));
			}
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
