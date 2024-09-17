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
import java.util.UUID;

public class muteCommand implements CommandExecutor {
    private final MiniEssentials plugin;
    private final muteManager muteManager;

    public muteCommand(MiniEssentials plugin, muteManager muteManager) {
        this.plugin = plugin;
        this.muteManager = muteManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (args.length < 3) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("mute.incorrect-usage"))));
            return true;
        }

        Player target = Bukkit.getPlayer(args[0]);
        if (target == null) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("mute.player-not-found"))));
            return true;
        }

        String reason = String.join(" ", Arrays.copyOfRange(args, 2, args.length));
        long muteDuration;
        try {
            String timeStr = args[1];
            int timeValue = Integer.parseInt(timeStr.substring(0, timeStr.length() - 1));
            switch (timeStr.charAt(timeStr.length() - 1)) {
                case 's':
                    muteDuration = timeValue;
                    break;
                case 'm':
                    muteDuration = timeValue * 60L;
                    break;
                case 'h':
                    muteDuration = timeValue * 3600L;
                    break;
                case 'd':
                    muteDuration = timeValue * 86400L;
                    break;
                case 'w':
                    muteDuration = timeValue * 604800L;
                    break;
                case 'y':
                    muteDuration = timeValue * 31556926L;
                    break;
                default:
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("mute.invalid-time"))));
                    return true;
            }
        } catch (NumberFormatException e) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("mute.invalid-time"))));
            return true;
        }

        UUID targetUUID = target.getUniqueId();
        muteManager.mutePlayer(targetUUID, System.currentTimeMillis() + muteDuration * 1000);
        target.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("mute.mute-message-for-player")).replace("%reason%", reason)));
        Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("mute.mute-broadcast")).replace("%target%", target.getName()).replace("%reason%", reason).replace("%time%", args[1])));

        return true;
    }
}
