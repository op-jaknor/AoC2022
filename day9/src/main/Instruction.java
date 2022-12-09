package main;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Instruction {
    private final Direction direction;
    private final int steps;

    public Instruction(final String input) {
        final String[] split = input.split(" ");
        this.direction = Direction.fromStr(split[0]);
        this.steps = Integer.parseInt(split[1]);
    }

    public Set<Position> execute(final Head head, final Tail tail) {
        return IntStream.range(0,steps).mapToObj(__ -> move(head, tail)).collect(Collectors.toUnmodifiableSet());
    }

    private Position move(final Head head, final Tail tail) {
        direction.move(head);
        return tail.follow(head);
    }

    private enum Direction {
        RIGHT("R"),
        LEFT("L"),
        UP("U"),
        DOWN("D");

        public final String direction;

        public void move(final Head head) {
            switch (this) {
                case UP ->  head.setPosition(new Position(head.position().x, head.position().y + 1));
                case DOWN -> head.setPosition(new Position(head.position().x, head.position().y -1));
                case LEFT -> head.setPosition(new Position(head.position().x -1, head.position().y));
                case RIGHT -> head.setPosition(new Position(head.position().x + 1, head.position().y));
            };
        }

        public static Direction fromStr(final String input) {
            return switch (input) {
                case "D" -> DOWN;
                case "U" -> UP;
                case "R" -> RIGHT;
                case "L" -> LEFT;
                default -> throw new RuntimeException();
            };
        }

        Direction(final String r) {
            direction = r;
        }
    }
}
