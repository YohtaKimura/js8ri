package ch09.ex09;

import java.util.Objects;

public class LabeledPoint {

    private final String label;
    private final int x;
    private final int y;

    public LabeledPoint(String label, int x, int y) {
        this.label = label;
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (!(other instanceof LabeledPoint)) {
            return false;
        }

        LabeledPoint o = (LabeledPoint) other;

        return Objects.equals(label, o.label)
                && x == o.x
                && y == o.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(label, x, y);
    }

}
