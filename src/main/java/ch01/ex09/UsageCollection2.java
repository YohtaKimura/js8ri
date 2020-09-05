package ch01.ex09;

import java.util.ArrayList;
import java.util.Collections;

public class UsageCollection2 {
    public static void main(String[] args) {

		class Collection2Impl<T> extends ArrayList<T> implements Collection2<T> {

		}
		String[] strs = { "hello tanaka", "yamada", "hello takahashi", "nobody", "satoh" };
		Collection2<String> list = new Collection2Impl<>();

		Collections.addAll(list, strs);
		System.out.println("forEachの出力");
		list.forEach(item -> System.out.println(item));
		System.out.println("forEachIfの出力");
		list.voidforEachIf(i -> System.out.println(i), i -> i.contains("hello"));

	}

}
