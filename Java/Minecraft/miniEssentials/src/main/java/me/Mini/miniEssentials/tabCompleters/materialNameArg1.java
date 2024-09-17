package me.Mini.miniEssentials.tabCompleters;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class materialNameArg1 implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> suggestions = new ArrayList<>();
        if (args.length == 2) {
            String partialMaterial = args[1].toUpperCase();
            for (Material material : Material.values()) {
                if (material.name().startsWith(partialMaterial)) {
                    suggestions.add(material.name().toLowerCase());
                }
            }
        } else if (args.length == 1) {
                List<String> playerNames = new ArrayList<>();
                String currentInput = args[0].toLowerCase(); // Get current input

                for (Player player : Bukkit.getOnlinePlayers()) {
                    String playerName = player.getName();
                    if (playerName.toLowerCase().startsWith(currentInput)) {
                        playerNames.add(playerName);
                    }
                }
                return playerNames;
            }
        return suggestions;
    }
}
