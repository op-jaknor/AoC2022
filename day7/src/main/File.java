package main;

public class File {
    public final String name;
    public final int size;

    public File(final String name, final int size) {
        this.name = name;
        this.size = size;
    }

    public static File getFile(final String input) {
        final String[] split = input.split(" ");
        return new File(split[1], Integer.parseInt(split[0]));
    }

    public static boolean isFile(final String maybeFile) {
        return maybeFile.matches("\\d+ .+");
    }
}
