package main;

import java.util.Objects;

public class Position {
    public final int x;
    public final int y;

    public Position(final int x, final int y) {
        this.x = x;
        this.y = y;
    }

    public double distance(final Position position) {
        return Math.sqrt(Math.pow(this.x - position.x, 2) + Math.pow(this.y - position.y, 2));
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Position position = (Position) o;
        return x == position.x && y == position.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
