package me.Mini.miniEssentials.banMenu;

import org.bukkit.BanEntry;
import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class unbanTabCompleter implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length == 1) {
            Set<BanEntry> bannedPlayers = Bukkit.getBanList(BanList.Type.NAME).getBanEntries();
            List<String> suggestions = new ArrayList<>();
            for (BanEntry entry : bannedPlayers) {
                suggestions.add(entry.getTarget());
            }
            return suggestions;
        }
        return null;
    }
}
