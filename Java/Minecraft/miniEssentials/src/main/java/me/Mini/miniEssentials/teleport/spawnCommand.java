package me.Mini.miniEssentials.teleport;

import me.Mini.miniEssentials.MiniEssentials;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Objects;


public class spawnCommand implements CommandExecutor {

    private final MiniEssentials plugin;

    public spawnCommand(MiniEssentials plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
    if (!(commandSender instanceof Player)) {
        commandSender.sendMessage("You must be a player to use this command.");
    }
    Player player = (Player) commandSender;

    Location loc = plugin.getConfig().getLocation("spawn");

    String spawnMessage = Objects.requireNonNull(plugin.getConfig().getString("messages.spawn-message")).replace("%player%", player.getName());

    String noSpawnMessage = plugin.getConfig().getString("messages.no-spawn-message");

    if (loc != null) {
        player.teleport(loc);

        player.sendMessage(ChatColor.translateAlternateColorCodes('&', spawnMessage));

    } else {
        assert noSpawnMessage != null;
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', noSpawnMessage));
    }


        return true;
    }
}