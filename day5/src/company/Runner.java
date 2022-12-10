package company;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.stream.Collectors;

public class Runner {

    public void calculate() {
        final List<String> input = input();
        final int inputDividerRow = input.indexOf("");
        List<Deque<Character>> stacks = new ArrayList<>();

        final List<Deque<Character>> currentPosition = getStartingPosition(input, inputDividerRow, stacks);

        for (int i = inputDividerRow + 1; i < input.size(); i++) {
            Instruction instruction = parseInstruction(input.get(i));
            updateStatePart2(currentPosition, instruction);
        }
        System.out.println(currentPosition.stream().map(deque -> deque.peek() + "").collect(Collectors.joining()));
    }

    private void updateStatePart2(final List<Deque<Character>> currentPosition, final Instruction instruction) {
        Deque<Character> deque = new ArrayDeque<>();
        for (int i = 0; i < instruction.amount; i++) {
            deque.push(currentPosition.get(instruction.source).pop());
        }

        deque.forEach(c -> currentPosition.get(instruction.target).push(c));
    }

    private void updateStatePart1(final List<Deque<Character>> currentPosition, final Instruction instruction) {
        for (int i = 0; i < instruction.amount; i++) {
            currentPosition.get(instruction.target).push(currentPosition.get(instruction.source).pop());
        }
    }

    private Instruction parseInstruction(final String s) {
        final String[] split = s.split(" ");
        return new Instruction(Integer.parseInt(split[3]) - 1, Integer.parseInt(split[5]) - 1, Integer.parseInt(split[1]));
    }

    private List<Deque<Character>> getStartingPosition(final List<String> input, final int inputDividerRow, final List<Deque<Character>> stacks) {
        int columns = parseNumberOfColumns(input.get(inputDividerRow-1));
        for (int i = 1; i < columns * 4; i += 4) {
            final ArrayDeque<Character> column = new ArrayDeque<>();
            for (int j = 0; j < inputDividerRow; j++) {
                if (input.get(j).length() > i && !(input.get(j).charAt(i) + "").isBlank()) {
                    column.addLast(input.get(j).charAt(i));
                }
            }
            stacks.add(column);
        }
        return stacks;
    }

    private int parseNumberOfColumns(final String s) {
        final String[] split = s.split(" ");
        return Integer.parseInt(split[split.length - 1]);
    }

    private List<String> input() {
        final List<String> strings;
        try {
            strings = Files.readAllLines(Path.of("src/company/input.txt"));
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return strings;
    }
}