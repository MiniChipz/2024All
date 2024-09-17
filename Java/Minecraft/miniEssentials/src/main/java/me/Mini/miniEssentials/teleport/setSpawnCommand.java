package me.Mini.miniEssentials.teleport;

import me.Mini.miniEssentials.MiniEssentials;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Objects;

public class setSpawnCommand implements CommandExecutor {

    private final MiniEssentials plugin;

    public setSpawnCommand(MiniEssentials plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage("You must be a player to use this command!");
        }
        Player player = (Player) commandSender;
        Location loc = player.getLocation();

        plugin.getConfig().set("spawn", loc);
        plugin.saveConfig();

        double x = loc.getX();
        String locx = String.format("%.2f", x);

        double y = loc.getY();
        String locy = String.format("%.2f", y);
        double z = loc.getZ();
        String locz = String.format("%.2f", z);

        String fullMessage = Objects.requireNonNull(plugin.getConfig().getString("messages.set-spawn")).replace("%loc.x%", locx).replace("%loc.y%", locy).replace("%loc.z%", locz).replace("%world%", player.getWorld().getName());

        player.sendMessage(ChatColor.translateAlternateColorCodes('&', fullMessage));


        return true;
    }
}
