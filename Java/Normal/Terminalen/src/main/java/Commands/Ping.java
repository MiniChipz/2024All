package Commands;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Ping {
    public static String pinga(String ip) {
        try {
            StringBuilder output = new StringBuilder();
            ProcessBuilder processBuilder = new ProcessBuilder("ping", ip);
            Process process = processBuilder.start();

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line.replace("Reply from", "\nReply from").replace("Ping statistics", "\nPing statistics").replace("Packets:", "\nPackets:"));
            }
            process.waitFor();
            return output.toString();
        } catch (IOException | InterruptedException e) {
            return e.getMessage();
        }
    }
}
