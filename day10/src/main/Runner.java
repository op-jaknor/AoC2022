package main;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Runner {

    public void part2(List<String> input) {
        int spritePosition = 1;
        int pixel = 0;

        for (final String instruction : input) {
            pixel = printCharacter(spritePosition, pixel);
            if (instruction.startsWith("addx")) {
                final String[] split = instruction.split(" ");
                final int registerUpdateValue = Integer.parseInt(split[1]);

                pixel = printCharacter(spritePosition, pixel);

                spritePosition += registerUpdateValue;
            }
        }
    }

    private int printCharacter(final int spritePosition, int pixel) {
        if (pixel <= spritePosition + 1 && pixel >= spritePosition - 1) {
            System.out.print("#");
        } else {
            System.out.print(".");
        }
        if (++pixel == 40){
            System.out.println();
            return 0;
        } else {
            return pixel;
        }
    }

    public void part1(List<String> input) {
        int x = 1;
        int cycle = 1;
        List<Integer> valueEvery20Cycles = new ArrayList<>();
        for (final String instruction : input) {
            if (cycle % 40 == 20) {
                valueEvery20Cycles.add(x * cycle);
            }

            if (instruction.startsWith("noop")) {
                cycle++;
            } else if (instruction.startsWith("addx")) {
                final String[] split = instruction.split(" ");
                final int registerUpdateValue = Integer.parseInt(split[1]);
                cycle++;
                if (cycle % 40 == 20) {
                    valueEvery20Cycles.add(x * cycle);
                }

                x += registerUpdateValue;
                cycle++;
            }
        }
        System.out.println(valueEvery20Cycles.stream().reduce(Integer::sum).get());
    }


    public void calculate() {
        part2(input());
    }

    public void test() {
        part2(testInput());
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
