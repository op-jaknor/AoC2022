package main;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Runner {

    public void part2(List<String> input) {
        final Position start = new Position(1000,1000);
        final Head head = new Head(start);
        final Tail tail = new Tail(start,
                new Tail(start,
                        new Tail(start,
                                new Tail(start,
                                        new Tail(start,
                                                new Tail(start,
                                                        new Tail(start,
                                                                new Tail(start,
                                                                        new Tail(start)))))))));

        input.forEach(instruction -> handleInstruction(new Instruction(instruction), head, tail));

        System.out.println(tail.visitedPositionsOfEnd());
    }

    public void part1(List<String> input) {
        final Position start = new Position(1000,1000);
        final Head head = new Head(start);
        final Tail tail = new Tail(start);

        final Set<Position> set = input.stream().map(instruction -> handleInstruction(new Instruction(instruction), head, tail)).flatMap(Collection::stream).collect(Collectors.toUnmodifiableSet());

        System.out.println(set.size());
    }

    private Set<Position> handleInstruction(final Instruction instruction, final Head head, final Tail tail) {
        return instruction.execute(head, tail);
    }

    public void calculate() {
        part2(input());
    }

    public void test() {
        part2(testInput());
    }

    private List<String> testInput() {
        try {
            return Files.readAllLines(Path.of("src/main/testInput2.txt"));
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
