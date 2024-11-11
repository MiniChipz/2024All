import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {
    public static File currentDir = new File(System.getProperty("user.home"));

    public static void main(String[] arggers) throws Exception {
        Scanner scanner = new Scanner(System.in);
        clear();
        while (true) {
            System.out.print(currentDir + ">");
            String input = scanner.nextLine().strip();
            String[] args = input.split(" ", 2);

            switch (args[0].toLowerCase()) {
                case "ls" -> GetChildItem.ls();

                case "cls" -> clear();

                case "echo", "write-host" -> System.out.println(input.replace("echo ", ""));

                case "mkdir" -> Mkdir.main(args[1]);

                case "cd" -> ChangeDirectory.cd(args[1]);

                case "prgm" -> ChangeDirectory.cd("C:\\Users\\Malte\\Documents\\Programmering");

                case "rm", "goon" -> RemoveFile.remove(args[1]);

                case "cat" -> Cat.readFile(args[1]);

                case "greg", "touch" -> MakeFile.greg(args[1]);

                case "ping" -> Ping.pinga(args[1]);

                case "find" -> FindFile.find(currentDir, args[1]);

                case "rename" -> Rename.newName(args[1]);

                case "date" -> System.out.println(getDate());

                case "help" ,"?" -> help();

                case "exit" -> System.exit(0);

                default -> System.out.println("\u001B[31mInvalid command!\u001B[0m");
            }
        }
    }

    static void clear() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
    static void help() {
        System.out.println("All commands:");
        System.out.println("ls -> Show files and directories in current directory\ncls -> Clears terminal\necho -> Writes <argument> in terminal \nmkdir -> Creates a directory in current directory or in specified directory \ncd -> Changes directory \nrm -> deletes a directory \ncat -> reads a file and displays in terminal \nexit -> exits the terminal \nhelp -> lists all commands");
    }
    static String getDate() {
        DateTimeFormatter date = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        return date.format(LocalDateTime.now());
    }
}