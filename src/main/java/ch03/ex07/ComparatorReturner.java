package ch03.ex07;

import java.util.Arrays;
import java.util.Comparator;

/* ソート方法は２進数で判別 */
public class ComparatorReturner {
	public static Comparator<String> createComparator(boolean isReverse, boolean isIgnoreSize, boolean isIgnoreSpace) {
		return (t, u) -> {
			if (isIgnoreSpace) {
				t = t.replaceAll(" ", "");
				u = u.replaceAll(" ", "");
			}
			if (isIgnoreSize) {
				t = t.toLowerCase();
				u = u.toLowerCase();
			}

			return isReverse ? t.compareTo(u) : u.compareTo(t);
		};
	}

	public static void main(String[] args) {
		String[] strs = { "ab c", "ghi", "bc d", " fg h", "f g h" };
		Arrays.sort(strs, createComparator(true, true, true));
		for (String str : strs)
			System.out.println(str);
		System.out.println();
		Arrays.sort(strs, createComparator(true, true, false));
		for (String str : strs)
			System.out.println(str);
		System.out.println();
		Arrays.sort(strs, createComparator(true, false, true));
		for (String str : strs)
			System.out.println(str);
		System.out.println();
		Arrays.sort(strs, createComparator(true, false, false));
		for (String str : strs)
			System.out.println(str);
		System.out.println();
		Arrays.sort(strs, createComparator(false, true, true));
		for (String str : strs)
			System.out.println(str);
		System.out.println();
		Arrays.sort(strs, createComparator(false, true, false));
		for (String str : strs)
			System.out.println(str);
		System.out.println();
		Arrays.sort(strs, createComparator(false, false, true));
		for (String str : strs)
			System.out.println(str);
		System.out.println();
		Arrays.sort(strs, createComparator(false, false, false));
		for (String str : strs)
			System.out.println(str);
		System.out.println();
	}
}
