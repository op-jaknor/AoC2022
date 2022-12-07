package main;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.List;

public class Runner {
    public static final int MAX_SIZE_PT_1 = 100000;
    public static final int TARGET_UNUUSED = 30000000;
    public static final int TOTAL_DISK_SPACE = 70000000;


    public void part2(List<String> input) {
        Directory rootDir = parseFileSystem(input);
        final int usedSize = rootDir.getTotalSize();
        final int neededFree = TARGET_UNUUSED - (TOTAL_DISK_SPACE - usedSize);
        final List<Directory> directories = rootDir.getSubDirectoriesSmallerThan(TOTAL_DISK_SPACE).stream()
                .filter(d -> d.getTotalSize() > neededFree)
                .sorted(Comparator.comparingInt(Directory::getTotalSize)).toList();
        System.out.println(directories.get(0).getTotalSize());
    }

    public void part1(List<String> input) {
        Directory rootDir = parseFileSystem(input);
        System.out.println(rootDir.getSubDirectoriesSmallerThan(MAX_SIZE_PT_1).stream().map(Directory::getTotalSize).reduce(0, Integer::sum));
    }

    private Directory parseFileSystem(final List<String> input) {
        final Directory rootDir = new Directory("/", null);
        Directory currentDir = rootDir;
        for (int i = 1; i < input.size(); i++) {
            final String line = input.get(i);
            if (line.startsWith("$")) {
                currentDir = handleCommand(currentDir, line);
            } else {
                if (line.startsWith("dir")) {
                } else {
                    currentDir.addFile(File.getFile(line));
                }
            }
        }
        return rootDir;
    }

    private Directory handleCommand(final Directory currentDir, final String line) {
        final String[] split = line.split(" ");
        if (split[1].equals("cd")) {
            if (split[2].equals("..")) {
                return currentDir.parentDir;
            } else {
                final Directory directory = new Directory(split[2], currentDir);
                currentDir.addDirectory(directory);
                return directory;
            }
        } else {
            return currentDir;
        }
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
