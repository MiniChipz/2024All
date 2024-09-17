package me.Mini.miniEssentials.godMode;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class godModeCommand implements CommandExecutor {

    private final Set<UUID> godModePlayers = new HashSet<>();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("This command can only be executed by a player!");
            return true;
        }

        Player player;
        UUID playerUUID;
        if (args.length == 0) {
            player = (Player) sender;
        } else {
            player = Bukkit.getPlayer(args[0]);
            if (player == null) {
                sender.sendMessage(ChatColor.RED + "Player not found!");
                return true;
            }
        }

        playerUUID = player.getUniqueId();
        Player senderPlayer = (Player) sender;

        if (godModePlayers.contains(playerUUID)) {
            godModePlayers.remove(playerUUID);
            String message = "God mode for " + ChatColor.YELLOW + player.getName() + ChatColor.RED + " disabled.";
            if (senderPlayer.equals(player)) {
                player.sendMessage(message);
            } else {
                player.sendMessage(message);
                senderPlayer.sendMessage(message);
            }
        } else {
            godModePlayers.add(playerUUID);
            String message = "God mode for " + ChatColor.YELLOW + player.getName() + ChatColor.GREEN + " enabled.";
            if (senderPlayer.equals(player)) {
                player.sendMessage(message);
            } else {
                player.sendMessage(message);
                senderPlayer.sendMessage(message);
            }
        }

        return true;
    }

    public Set<UUID> getGodModePlayers() {
        return godModePlayers;
    }
}
