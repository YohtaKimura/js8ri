package ch03.ex16;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.Callable;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class InOrderAsync {
	// テキストの実装
	public static <T> void doInOrderAsync(Supplier<T> first, Consumer<T> second, Consumer<Throwable> handler) {
		Thread t = new Thread() {
			public void run() {
				try {
					T result = first.get();
					second.accept(result);
				} catch (Throwable t) {
					handler.accept(t);
				}
			}
		};
		t.start();
	}

	// 練習問題１６
	public static <T> void doInOrderAsync(Supplier<T> first, BiConsumer<T, Throwable> second) {
		Thread t = new Thread() {
			public void run() {
				Throwable t = null;
				T result = null;
				try {
					result = first.get();
				} catch (Throwable tf) {
					t = tf;
				}
				second.accept(result, t);
			}
		};
		t.start();
	}

	public static String readFile(String filename) throws Exception {
		String out = "";
		File file = new File(filename);
		BufferedReader br = new BufferedReader(new FileReader(file));
		String str;
		while ((str = br.readLine()) != null) {
			out += str;
		}
		br.close();
		return out;
	}

	public static <T> Supplier<T> unchecked(Callable<T> f){
		return () -> {
			try{
				return f.call();
			}
			catch(Exception e){
				throw new RuntimeException(e);
			}
			catch(Throwable t){
				throw t;
			}
		};
	}

	@FunctionalInterface
	public interface CallableSupplier<T> {
		T get() throws Throwable;
	}

	public static void main(String[] args) {
	    String path = new File(".").getAbsoluteFile().getParent();
		CallableSupplier<String> supplier = () -> {
            return readFile(path + "\\src\\main\\java\\ch03\\ex16\\Alice.txt");
		};

		BiConsumer<String, Throwable> consumer = (s, t) -> {
			if (t != null)
				t.printStackTrace();
			else {
				try {
					System.out.println(s);
				} catch (Throwable ts) {
					ts.printStackTrace();
				}
			}
		};
		doInOrderAsync(unchecked(()->readFile(path + "\\src\\main\\java\\ch03\\ex16\\Alice.txt")), consumer);
	}
}
