package main;

public class Head {
    private Position position;

    public Head(final Position position) {
        this.position = position;
    }

    public void setPosition(final Position position) {
        this.position = position;
    }

    public Position position() {
        return new Position(position.x, position.y);
    }

    public double distance(final Position position) {
        return this.position.distance(position);
    }
}
