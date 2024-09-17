package me.Mini.miniEssentials.banMenu;

import me.Mini.miniEssentials.MiniEssentials;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.Objects;
import java.util.UUID;

public class unmuteCommand implements CommandExecutor {
    private final MiniEssentials plugin;
    private final muteManager muteManager;

    public unmuteCommand(MiniEssentials plugin, muteManager muteManager) {
        this.plugin = plugin;
        this.muteManager = muteManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (args.length == 0) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("unmute.incorrect-usage"))));
            return true;
        }

        String targetName = args[0];
        UUID targetUUID = Bukkit.getOfflinePlayer(targetName).getUniqueId();

        if (muteManager.isPlayerMuted(targetUUID)) {
            muteManager.unmutePlayer(targetUUID);
            Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("unmute.unban-broadcast")).replace("%target%", targetName)));
        } else {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("unmute.non-muted-target"))));
        }

        return true;
    }
}
