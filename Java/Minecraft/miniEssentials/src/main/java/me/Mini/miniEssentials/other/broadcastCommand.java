package me.Mini.miniEssentials.other;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Collection;

public class broadcastCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage("You must be a player to use this command!");
        }
        if (!(args.length >= 1)) {
            commandSender.sendMessage("Usage: /broadcast <message>");
        }
        String message = "&8[&6Broadcast&8]: &f" + String.join(" ", args);
        Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', message));
        return true;
    }
}
