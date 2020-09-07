package ch03.ex18;

import java.util.function.Function;

public class UncheckedFunctionClass {
	public static <T, U> Function<T, U> unchecked(UncheckFunction<T, U> f){
		return (t) -> {
			try{
				return f.apply(t);
			}
			catch(Throwable e){
				throw new RuntimeException(e);
			}
		};
	}
	@FunctionalInterface
	public interface UncheckFunction<T, U>{
		U apply(T t) throws Throwable;
	}
}
