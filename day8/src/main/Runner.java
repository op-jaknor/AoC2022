package main;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Runner {

    public void part2(List<String> input) {
        int visible = input.get(0).length() * 2 + (input.size() * 2) - 4;
        int maxScore = 0;
        for (int i = 1; i < input.size() -1; i++) {
            for (int j = 1; j < input.get(i).length() -1; j++) {
                int currentScore = 1;
                final char c = input.get(i).charAt(j);
                for (int k = j -1; k > -1; k--) {
                    if (c <= input.get(i).charAt(k) || k == 0) {
                        currentScore *= (j-k);
                        break;
                    }
                }

                for (int k = j+1; k < input.get(i).length(); k++) {
                    if (c <= input.get(i).charAt(k) || k == input.get(i).length()-1) {
                        currentScore *= k - (j);
                        break;
                    }
                }

                for (int k = i+1; k < input.size(); k++) {
                    if (c <= input.get(k).charAt(j) || k == input.size()-1) {
                        currentScore *= k - (i);
                        break;
                    }
                }

                for (int k = i-1; k > -1; k--) {
                    if (c <= input.get(k).charAt(j) || k ==0 ) {
                        currentScore *= (i - k);
                        break;
                    }
                }
                maxScore = Integer.max(maxScore, currentScore);
            }
        }
        System.out.println(maxScore);
    }

    public void part1(List<String> input) {
        int visible = input.get(0).length() * 2 + (input.size() * 2) - 4;
        for (int i = 1; i < input.size() -1; i++) {
            for (int j = 1; j < input.get(i).length() -1; j++) {
                boolean isVisible = false;
                final char c = input.get(i).charAt(j);
                for (int k = 0; k < j; k++) {
                    if (c <= input.get(i).charAt(k)) {
                        isVisible = false;
                        break;
                    }
                    isVisible = true;
                }
                if (isVisible) {
                    visible++;
                    continue;
                }
                for (int k = j+1; k < input.get(i).length(); k++) {
                    if (c <= input.get(i).charAt(k)) {
                        isVisible = false;
                        break;
                    }
                    isVisible = true;
                }
                if (isVisible) {
                    visible++;
                    continue;
                }
                for (int k = i+1; k < input.size(); k++) {
                    if (c <= input.get(k).charAt(j)) {
                        isVisible = false;
                        break;
                    }
                    isVisible = true;
                }
                if (isVisible) {
                    visible++;
                    continue;
                }
                for (int k = 0; k < i; k++) {
                    if (c <= input.get(k).charAt(j)) {
                        isVisible = false;
                        break;
                    }
                    isVisible = true;
                }
                if (isVisible) {
                    visible++;
                }
            }
        }
        System.out.println(visible);
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
