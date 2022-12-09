package main;

import java.util.HashSet;
import java.util.Set;

public class Tail {
    private Position position;
    private Set<Position> visited;
    private Tail follower;

    public Tail(final Position startingPosition) {
        this(startingPosition, null);
    }

    public Tail(final Position startingPosition, final Tail follower) {
        this.position = startingPosition;
        this.follower = follower;
        this.visited = new HashSet<>();
        visited.add(startingPosition);
    }

    public Position follow(final Head head) {
        if (!(head.distance(position) < 2)) {
            this.position = new Position(moveCloserAlongAxis(position.x, head.position().x), moveCloserAlongAxis(position().y, head.position().y));
            visited.add(position());
        }
        if (follower != null) {
            follower.follow(this);
        }

        return position();
    }

    public Position follow(final Tail head) {
        if (!(head.distance(position) < 2)) {
            this.position = new Position(moveCloserAlongAxis(position.x, head.position().x), moveCloserAlongAxis(position().y, head.position().y));
            visited.add(position());
        }
        if (follower != null) {
            follower.follow(this);
        }

        return position();
    }

    public double distance(final Position position) {
        return this.position.distance(position);
    }

    public int visitedPositionsOfEnd() {
        return follower == null ? visited.size() : follower.visitedPositionsOfEnd();
    }

    private Position position() {
        return new Position(position.x, position.y);
    }

    private Integer moveCloserAlongAxis(final int current, final int target) {
        if (current == target) {
            return current;
        } else if (current < target) {
            return current+1;
        } else {
            return current-1;
        }

    }
}
