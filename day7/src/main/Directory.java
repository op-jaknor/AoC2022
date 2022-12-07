package main;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Directory {
    public final String name;
    private final List<File> files;
    private final Map<String, Directory> subDirectories;
    public final Directory parentDir;

    public Directory(final String name, final Directory parentDir) {
        this.name = name;
        this.parentDir = parentDir;
        this.files = new ArrayList<>();
        this.subDirectories = new HashMap<>();
    }

    public int getTotalSize() {
        return files.stream().map(file -> file.size).reduce(0, Integer::sum) + subDirectories.values().stream().map(Directory::getTotalSize).reduce(0, Integer::sum);
    }

    public void addFile(final File file) {
        files.add(file);
    }

    public void addDirectory(final Directory dir) {
        subDirectories.put(dir.name, dir);
    }

    public Directory getSubDirectory(final String dirName) {
        return subDirectories.get(dirName);
    }

    public void printTree(final int offset) {
        System.out.println(" ".repeat(offset) + "- " + name + " (dir)");
        subDirectories.values().forEach(d -> d.printTree(offset + 2));
        files.forEach(f -> System.out.println(" ".repeat(offset + 2) + "- " + f.name + " " + f.size));
    }

    public Collection<Directory> getSubDirectoriesSmallerThan(final int size) {
        Collection<Directory> collection = new ArrayList<>();
        smallerSubDirectoriesFromSubdirectories(size, collection);
        smallerDirs(size, collection);
        return collection;
    }

    private void smallerDirs(final int size, final Collection<Directory> collection) {
        collection.addAll(subDirectories.values()
                .stream()
                .filter(dir -> dir.getTotalSize() < size).toList());
    }

    private void smallerSubDirectoriesFromSubdirectories(final int size, final Collection<Directory> collection) {
        collection.addAll(subDirectories.values()
                .stream()
                .flatMap(d -> d.getSubDirectoriesSmallerThan(size).stream()).toList());
    }
}
