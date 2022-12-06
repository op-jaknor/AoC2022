package main;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Runner {

    private static final int NUM_CHARACTERS_PT_1 = 4;
    private static final int NUM_CHARACTERS_PT_2 = 14;

    public void calculate() {
        final String input = input().get(0);

        for (int i = 0; i < input.length(); i++) {
            Set<Character> characters = new HashSet<>();
            for (int j = 0; j < NUM_CHARACTERS_PT_2; j++) {
                characters.add(input.charAt(i+j));
            }
            if (characters.size() == NUM_CHARACTERS_PT_2) {
                System.out.println(i + NUM_CHARACTERS_PT_2);
                break;
            }
        }

    }

    private List<String> input() {
        final List<String> strings;
        try {
            strings = Files.readAllLines(Path.of("src/main/input.txt"));
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return strings;
    }
}
