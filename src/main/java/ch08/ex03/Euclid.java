package ch08.ex03;

// https://edu.clipper.co.jp/pg-3-29.html
public class Euclid {
    static int gcd(int a, int b) {
        if (b == 0)
            return positive(a);
        return gcd(b, a % b);
    }

    static int gcdFloorMood(int a, int b) {
        if (b == 0)
            return positive(a);
        return gcdFloorMood(b, Math.floorMod(a, b));
    }

    static int gcdRem(int a, int b) {
        if (b == 0)
            return positive(a);
        return gcdRem(b, rem(a, b));
    }

    static int positive(int a) {
        if (a < 0)
            a = -a;
        return a;
    }

    static int rem(int a, int b) {
        int remainder = a % b;
        if (remainder < 0)
            remainder = -1 * remainder;
        return remainder;
    }

    public static void main(String[] args) {
        long startTimeGcd = System.nanoTime();
        System.out.println(gcd(-18, 12));
        long endTimeGcd = System.nanoTime();
        System.out.println("処理時間(Gcd)：" + (endTimeGcd - startTimeGcd) + " ns");
        long startTimeFloorMood = System.nanoTime();
        System.out.println(gcdFloorMood(-18, 12));
        long endTimeFloorMood = System.nanoTime();
        System.out.println("処理時間(Floor)：" + (endTimeFloorMood - startTimeFloorMood) + " ns");
        long startTimeRem = System.nanoTime();
        System.out.println(gcdRem(-18, 12));
        long endTimeRem = System.nanoTime();
        System.out.println("処理時間(Rem)：" + (endTimeRem - startTimeRem) + " ns");
    }
}