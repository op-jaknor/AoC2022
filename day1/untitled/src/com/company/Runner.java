package com.company;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Runner {

    public void calculate() {
        final List<String> input = input();
        int max = 0;
        int current = 0;
        List<Integer> stuff = new ArrayList<>();
        for (int i = 0; i < input.size(); i++) {
            if (!input.get(i).isBlank()) {
                current += Integer.parseInt(input.get(i));
            } else {
                stuff.add(current);
                current = 0;
            }
        }
        stuff.sort(Integer::compare);
        System.out.println(stuff.get(stuff.size()-1) + stuff.get(stuff.size() -2)+ stuff.get(stuff.size() -3));
    }

    private List<String> input() {
        final List<String> strings;
        try {
            strings = Files.readAllLines(Path.of("src/com/company/input.txt"));
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return strings;
    }
}
