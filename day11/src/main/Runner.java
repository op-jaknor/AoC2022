package main;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

import static java.util.Collections.*;

public class Runner {

    public void part2(List<String> input) {

    }

    public void part1(List<String> input) {
        List<Monkey> monkeys = new ArrayList<>();

        for (int i = 0; i < input.size(); i += 7) {
            monkeys.add(parseNextMonkey(input.subList(i, i + 6)));
        }

        for (Monkey monkey : monkeys) {
            monkey.setFalseMonkey(monkeys.get(monkey.falseMonkeyNumber));
            monkey.setTrueMonkey(monkeys.get(monkey.trueMonkeyNumber));
        }
        for (int i = 0; i < 20; i++) {
            monkeys.forEach(Monkey::inspectItems);
        }

        final List<Long> itemsInspected = monkeys.stream().map(Monkey::getItemsInspected).sorted().toList();
        System.out.println(itemsInspected.get(itemsInspected.size()-1)*itemsInspected.get(itemsInspected.size()-2));

    }

    public Monkey parseNextMonkey(final List<String> input) {
        return new Monkey(
                parseItems(input.get(1)),
                parseWorryChangeOperation(input.get(2)),
                parseThrowingDecisionFunciton(input.get(3)),
                Integer.parseInt(input.get(4).substring(input.get(4).length()-2).trim()),
                Integer.parseInt(input.get(5).substring(input.get(5).length()-2).trim())
        );

    }

    private Function<Long, Boolean> parseThrowingDecisionFunciton(final String s) {
        //Test: divisible by 23
        String val = getValue(s);
        return i -> i % Integer.parseInt(s.split("by ")[1]) == 0;
    }

    private String getValue(final String s) {
        return s.split(":")[1].trim();
    }

    private Function<Long, Long> parseWorryChangeOperation(final String s) {
        //  Operation: new = old * 19
        final String[] operation = s.split(":")[1].trim().split(" ");

        if ("*".equals(operation[3])) {
            return i -> i * ("old".equals(operation[4]) ? i : Integer.parseInt(operation[4]));
        } else if ("+".equals(operation[3])) {
            return i -> i + Integer.parseInt(operation[4]);
        } else {
            throw new IllegalStateException();
        }
    }

    private List<Long> parseItems(final String items) {
        final String[] split = items.split(":");
        return Arrays.stream(split[1].trim().split(",")).map(String::trim).map(Long::parseLong).toList();
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
