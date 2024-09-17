package me.Mini.miniEssentials.tabCompleters;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class materialNameArg0 implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> suggestions = new ArrayList<>();
        if (args.length == 1) {
            String partialMaterial = args[0].toUpperCase();
            for (Material material : Material.values()) {
                if (material.name().startsWith(partialMaterial)) {
                    suggestions.add(material.name().toLowerCase());
                }
            }
        }

        return suggestions;
    }
}
