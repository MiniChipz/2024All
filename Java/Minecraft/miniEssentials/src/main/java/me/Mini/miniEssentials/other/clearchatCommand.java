package me.Mini.miniEssentials.other;

import me.Mini.miniEssentials.MiniEssentials;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Objects;

public class clearchatCommand implements CommandExecutor {
    private final MiniEssentials plugin;

    public clearchatCommand(MiniEssentials plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("This command can only be executed by a player!");
        }
        Player player = (Player) sender;
        if (!(player.hasPermission("miniessentials.clearchat"))) {
            player.sendMessage("You don't have permission to use this command!");
        }
        for (int i = 0; i != 100; i++) {
            Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&c  "));
        }
        String message = Objects.requireNonNull(plugin.getConfig().getString("messages.clearchat-message")).replace("%player%", player.getName());
        for (Player p : Bukkit.getOnlinePlayers()) {
            for (int i = 0; i < 100; i++) {
                p.sendMessage(" ");
            }
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
        }
        return true;
    }
}
