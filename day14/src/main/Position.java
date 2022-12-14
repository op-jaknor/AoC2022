package main;

import java.util.Objects;

public class Position {
    public final int x;
    public final int y;

    public Position(final int x, final int y) {
        this.x = x;
        this.y = y;
    }

    public Position(final String input) {
        final String[] split = input.split(",");
        this.x = Integer.parseInt(split[0]);
        this.y = Integer.parseInt(split[1]);
    }

    public Position copyOf() {
        return new Position(x,y);
    }

    public Position getPositionUnder() {
        return new Position(x, y+1);
    }

    public Position getPositionDownLeft() {
        return new Position(x-1, y+1);
    }

    public Position getPositionDownRight() {
        return new Position(x+1, y+1);
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
