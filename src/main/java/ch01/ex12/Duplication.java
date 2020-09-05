package ch01.ex12;

import java.util.ArrayList;
import java.util.stream.Stream;

// どれだけ安全かということに関しては名前被りが無い限りは安全……？
public class Duplication<E> extends ArrayList<E> {
    // public void stream() { 名前が同じで返り値が異なる stream() はエラー
	public Stream stream() {
        System.out.println("hello");
        return null;
    }

	public static void main (String[] args){
		Duplication<String> test = new Duplication<>();
		test.stream();
	}
}
