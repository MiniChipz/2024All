package me.Mini.economyPlugin.commands;

import me.Mini.economyPlugin.storage.dbManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class ecoCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length < 3) {
            sender.sendMessage(ChatColor.RED + "Usage: /eco <set|give|remove> <player> <amount>");
            return false;
        }

        String action = args[0];
        Player target = Bukkit.getPlayer(args[1]);
        if (target == null) {
            sender.sendMessage(ChatColor.RED + "Player not found.");
            return false;
        }

        UUID targetUUID = target.getUniqueId();
        double amount;
        try {
            amount = Double.parseDouble(args[2]);
        } catch (NumberFormatException e) {
            sender.sendMessage(ChatColor.RED + "Amount must be a number.");
            return false;
        }

        switch (action.toLowerCase()) {
            case "set":
                dbManager.setBalance(targetUUID, amount);
                sender.sendMessage(ChatColor.GREEN + "Set balance of " + target.getName() + " to " + amount);
                break;
            case "give":
                dbManager.giveBalance(targetUUID, amount);
                sender.sendMessage(ChatColor.GREEN + "Gave " + amount + " to " + target.getName());
                break;
            case "remove":
                dbManager.removeBalance(targetUUID, amount);
                sender.sendMessage(ChatColor.GREEN + "Removed " + amount + " from " + target.getName());
                break;
            default:
                sender.sendMessage(ChatColor.RED + "Invalid action. Use set, give, or remove.");
                return false;
        }

        return true;
    }
}
