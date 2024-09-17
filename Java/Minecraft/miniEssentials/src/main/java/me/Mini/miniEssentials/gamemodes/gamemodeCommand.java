package me.Mini.miniEssentials.gamemodes;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class gamemodeCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {
        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage("This command can only be executed by a player!");
            return true; // Exit early since the command sender is not a player
        }

        Player player = (Player) commandSender;
        if (label.equalsIgnoreCase("gamemode")) {
            if (args.length == 0) {
                player.sendMessage("Usage:" + ChatColor.YELLOW + "/gamemode <survival|creative|adventure|spectator> <player>");
                return true;
            }

            Player targetPlayer = player;
            if (args.length >= 2) {
                targetPlayer = Bukkit.getPlayer(args[1]);
                if (targetPlayer == null) {
                    player.sendMessage("Player not found: " + args[1]);
                    return true;
                }
            }

            String mode = args[0];
            switch (mode.toLowerCase()) {
                case "1":
                case "creative":
                    targetPlayer.setGameMode(GameMode.CREATIVE);
                    targetPlayer.sendMessage(ChatColor.YELLOW + targetPlayer.getName() + "'s" + ChatColor.GRAY + " gamemode has been set to " + ChatColor.GREEN + "creative");
                    break;
                case "2":
                case "adventure":
                    targetPlayer.setGameMode(GameMode.ADVENTURE);
                    targetPlayer.sendMessage(ChatColor.YELLOW + targetPlayer.getName() + "'s" + ChatColor.GRAY + " gamemode has been set to " + ChatColor.GREEN + "adventure");
                    break;
                case "3":
                case "spectator":
                    targetPlayer.setGameMode(GameMode.SPECTATOR);
                    targetPlayer.sendMessage(ChatColor.YELLOW + targetPlayer.getName() + "'s" + ChatColor.GRAY + " gamemode has been set to " + ChatColor.GREEN + "spectator");
                    break;
                case "0":
                case "survival":
                    targetPlayer.setGameMode(GameMode.SURVIVAL);
                    targetPlayer.sendMessage(ChatColor.YELLOW + targetPlayer.getName() + "'s" + ChatColor.GRAY + " gamemode has been set to " + ChatColor.GREEN + "survival");
                    break;
                default:
                    player.sendMessage("Unknown gamemode: " + mode);
                    return true;
            }
        } else {
            Player targetPlayer = player;
            if (args.length >= 1) {
                targetPlayer = Bukkit.getPlayer(args[0]);
                if (targetPlayer == null) {
                    player.sendMessage("Player not found: " + args[0]);
                    return true;
                }
            }
            GameMode gameMode;
            switch (label.toLowerCase()) {
                case "gmc":
                    gameMode = GameMode.CREATIVE;
                    break;
                case "gms":
                    gameMode = GameMode.SURVIVAL;
                    break;
                case "gmsp":
                    gameMode = GameMode.SPECTATOR;
                    break;
                case "gma":
                    gameMode = GameMode.ADVENTURE;
                    break;
                default:
                    player.sendMessage("Unknown command: " + label);
                    return true;
            }
            targetPlayer.setGameMode(gameMode);
            if (player == targetPlayer) {
                targetPlayer.sendMessage("Your gamemode has been set to " + gameMode.name().toLowerCase() + ".");
            } else {
                player.sendMessage("Changed " + targetPlayer.getName() + "'s gamemode to " + gameMode.name().toLowerCase() + ".");
                targetPlayer.sendMessage("Your gamemode has been set to " + gameMode.name().toLowerCase() + ".");
            }
        }
        return true;
    }
}
