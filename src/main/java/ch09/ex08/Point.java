package ch09.ex08;

public class Point implements Comparable<Point> {
    public final int x;
    public final int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int compareTo(Point other) {
        if (x < other.x) {
            return -1;
        }

        if (x > other.x) {
            return 1;
        }

        if (y < other.y) {
            return -1;
        }
        if (y > other.y) {
            return 1;
        }

        return 0;
    }

}
