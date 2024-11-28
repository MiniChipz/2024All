package mainPackage;

import Commands.*;
import GUI.Terminal;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Main {
    public static File currentDir = new File(System.getProperty("user.home"));

    public static void main(String[] args) throws Exception {
        Terminal.main(new String[]{""});
    }

    public static String commandHandler(String input) throws Exception {
            String[] args = input.split(" ", 2);
            String output = "No work";
            switch (args[0].toLowerCase()) {
                case "ls" -> output = GetChildItem.ls();

                case "echo", "write-host" -> output = input.replace("echo ", "");

                case "mkdir" -> output = Mkdir.main(args[1]);

                case "cd" -> output = ChangeDirectory.cd(args[1]);

                case "prgm" -> output = prgm();

                case "rm", "goon" -> output = RemoveFile.remove(args[1]);

                case "cat" -> output = Cat.readFile(args[1]);

                case "greg", "touch" -> output = MakeFile.greg(args[1]);

                case "ping" -> output = Ping.pinga(args[1]);

                case "find" -> output = FindFile.find(currentDir, args[1]);

                case "rename" -> output = Rename.newName(args[1]);

                case "date" -> output = getDate();

                case "help" ,"?" -> output = help();

                case "exit" -> System.exit(0);

                default -> output = "Invalid command";
            }
            if (input.equals("clear") || input.equals("cls")) {
                output = "";
            }
            return output;
    }

    static String prgm() {
        ChangeDirectory.cd("C:\\Users\\Malte\\Documents\\Programmering");
        return "";
    }
    static String help() {
        return "All commands:\nls -> Show files and directories in current directory\ncls -> Clears terminal\necho -> Writes <argument> in terminal \nmkdir -> Creates a directory in current directory or in specified directory \ncd -> Changes directory \nrm -> deletes a directory \ncat -> reads a file and displays in terminal \nexit -> exits the terminal \nhelp -> lists all commands";
    }
    static String getDate() {
        DateTimeFormatter date = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        return date.format(LocalDateTime.now());
    }
}