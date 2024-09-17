package me.Mini.miniEssentials.tabCompleters;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class playerNameArg1 implements TabCompleter {

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length == 2) {
            List<String> playerNames = new ArrayList<>();
            String currentInput = args[1].toLowerCase(); // Get current input

            for (Player player : Bukkit.getOnlinePlayers()) {
                String playerName = player.getName();
                if (playerName.toLowerCase().startsWith(currentInput)) {
                    playerNames.add(playerName);
                }
            }

            return playerNames;
        }

        return new ArrayList<>();
    }
}
