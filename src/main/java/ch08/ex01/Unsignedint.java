package ch08.ex01;

/* 剰余は正の数という定義． divideUnsigned　が必要な理由は分かりませんでした．*/
public class Unsignedint {
    public int add(int x, int y) {
        return (int) (Integer.toUnsignedLong(x) + Integer.toUnsignedLong(y));
    }
    public int subtract(int x, int y) {
        return (int) (Integer.toUnsignedLong(x) - Integer.toUnsignedLong(y));
    }
    public int divide(int x, int y) {
        return (int) (Integer.toUnsignedLong(x) / Integer.toUnsignedLong(y));
    }
    public int compare(int x, int y) {
        long unsignedX = Integer.toUnsignedLong(x);
        long unsignedY = Integer.toUnsignedLong(y);
        return unsignedX == unsignedY ? 0 : 1;
    }
}
