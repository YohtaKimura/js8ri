package ch06.ex01;

import java.util.concurrent.atomic.AtomicReference;

public final class LongString {
    private final AtomicReference<String> longestString= new AtomicReference<>("");
    public final void setIfLongest(String s) {
        longestString.accumulateAndGet(s, (s1, s2) -> {
            return s1.length() > s2.length()? s1 : s2;
        });
    }
    public final String getLongest() {
        return longestString.get();
    }
}
