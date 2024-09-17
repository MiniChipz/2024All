package me.Mini.miniEssentials.banMenu;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class unmuteTabCompleter implements TabCompleter {
    private final muteManager muteManager;

    public unmuteTabCompleter(muteManager muteManager) {
        this.muteManager = muteManager;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length == 1) {
            List<String> mutedPlayers = new ArrayList<>();
            for (UUID uuid : muteManager.getMutedPlayers()) {
                OfflinePlayer player = Bukkit.getOfflinePlayer(uuid);
                mutedPlayers.add(player.getName());
            }
            return mutedPlayers;
        }
        return null;
    }
}
