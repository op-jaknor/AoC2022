package main;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Runner {

    public void part2(List<String> input) {

    }

    public void part1(List<String> input) {
        final GameMap gameMap = new GameMap(new Position(500, 0), parseInput(input));
        System.out.println(gameMap.calculateSandsDropped());
    }

    private Set<Position> parseInput(List<String> strings) {
        return strings.stream().flatMap(this::parseSingleLine).collect(Collectors.toUnmodifiableSet());
    }

    private Stream<Position> parseSingleLine(final String s) {
        final List<Position> positions = Arrays.stream(s.split(" -> ")).map(Position::new).toList();
        final Set<Position> parsedPositions = new HashSet<>();

        for (int i = 0; i < positions.size() - 1; i++) {
            parsedPositions.addAll(allPositionsBetween(positions.get(i), positions.get(i+1)));
        }

        return parsedPositions.stream();
    }

    private Collection<Position> allPositionsBetween(final Position p1, final Position p2) {
        if (p1.x > p2.x) {
            return IntStream.range(p2.x, p1.x +1).mapToObj(x -> new Position(x, p1.y)).toList();
        } else if (p1.x < p2.x) {
            return IntStream.range(p1.x, p2.x + 1).mapToObj(x -> new Position(x, p1.y)).toList();
        } else if (p1.y < p2.y) {
            return IntStream.range(p1.y, p2.y + 1).mapToObj(y -> new Position(p1.x, y)).toList();
        } else if(p1.y > p2.y) {
            return IntStream.range(p2.y, p1.y + 1).mapToObj(y -> new Position(p1.x, y)).toList();
        }
        throw new IllegalStateException();
    }


    public void calculate() {
        part1(input());
    }

    public void test() {
        part1(testInput());
    }

    private List<String> testInput() {
        try {
            return Files.readAllLines(Path.of("src/main/testInput.txt"));
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private List<String> input() {
        try {
            return Files.readAllLines(Path.of("src/main/input.txt"));
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }


}
