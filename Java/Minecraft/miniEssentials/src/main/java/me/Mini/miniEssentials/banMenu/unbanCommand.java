package me.Mini.miniEssentials.banMenu;

import me.Mini.miniEssentials.MiniEssentials;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.Objects;

public class unbanCommand implements CommandExecutor {
    private final MiniEssentials plugin;

    public unbanCommand(MiniEssentials plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (args.length == 0) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("unban.incorrect-usage"))));
            return true;
        }

        String targetName = args[0];

        if (Bukkit.getBanList(org.bukkit.BanList.Type.NAME).isBanned(targetName)) {
            Bukkit.getBanList(org.bukkit.BanList.Type.NAME).pardon(targetName);
            Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("unban.unban-broadcast")).replace("%target%", targetName)));
        } else {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("unban.non-banned-target"))));
        }

        return true;
    }
}
