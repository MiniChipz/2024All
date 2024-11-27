package Commands;

import java.io.File;

public class FindFile {
    public static String find(File directory, String fileName) {
        File[] files = directory.listFiles();
        StringBuilder output = new StringBuilder("File couldn't be found");
        if (files != null) {
            long start = System.nanoTime();
            for (File file : files) {
                if (file.isDirectory()) {
                    String result = FindFile.find(file, fileName);
                    if (!result.equals("File couldn't be found")) {
                        return result;
                    }
                } else if (file.getName().contains(fileName)) {
                    long done = System.nanoTime();
                    long elapsedTime = done - start;
                    output = new StringBuilder();

                    String formattedTime = formatElapsedTime(elapsedTime);
                    output.append("Possible file: ").append(file.getAbsolutePath()).append("\n");
                    output.append("It took: ").append(formattedTime);
                    return output.toString();
                }
            }
        }
        return output.toString();
    }

    private static String formatElapsedTime(long nanoTime) {
        long microseconds = nanoTime / 1_000;
        long milliseconds = microseconds / 1_000;
        long seconds = milliseconds / 1_000;
        long minutes = seconds / 60;

        if (minutes > 0) {
            return String.format("%d minutes, %d seconds", minutes, seconds % 60);
        } else if (seconds > 0) {
            return String.format("%d seconds, %d milliseconds", seconds, milliseconds % 1_000);
        } else if (milliseconds > 0) {
            return String.format("%d milliseconds, %d microseconds", milliseconds, microseconds % 1_000);
        } else {
            return String.format("%d microseconds", microseconds);
        }
    }
}
