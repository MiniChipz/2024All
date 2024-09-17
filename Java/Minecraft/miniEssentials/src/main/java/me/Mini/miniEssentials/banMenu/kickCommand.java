package me.Mini.miniEssentials.banMenu;

import me.Mini.miniEssentials.MiniEssentials;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.Objects;

public class kickCommand implements CommandExecutor {
    private final MiniEssentials plugin;

    public kickCommand(MiniEssentials plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Player player = (Player) sender;
        if (args.length < 2) {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("kick.incorrect-usage"))));
        }
        Player target;
        target = Bukkit.getPlayer(args[0]);
        if (target == null) {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("kick.invalid-target"))));
            return true;
        }
        if (target.isOp()) {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("kick.cannot-kick-op"))));
        }
        String reason = String.join(" ", Arrays.copyOfRange(args, 1, args.length));

        target.kickPlayer(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(Objects.requireNonNull(plugin.getConfig().getString("kick.kick-message-for-player"))).replace("%reason%", reason)));
        Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(Objects.requireNonNull(plugin.getConfig().getString("kick.kick-broadcast"))).replace("%target%", target.getName()).replace("%reason%", reason)));

        return true;
    }
}
