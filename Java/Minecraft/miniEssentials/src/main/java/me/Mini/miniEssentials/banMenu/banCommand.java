package me.Mini.miniEssentials.banMenu;

import me.Mini.miniEssentials.MiniEssentials;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.Date;
import java.util.Objects;

public class banCommand implements CommandExecutor {
    private final MiniEssentials plugin;

    public banCommand(MiniEssentials plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Player player = (Player) sender;

        if (args.length < 3) {
            player.sendMessage(Objects.requireNonNull(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("ban.incorrect-usage")))));
            return true;
        }

        Player target = Bukkit.getPlayer(args[0]);
        if (target == null) {
            player.sendMessage(Objects.requireNonNull(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("ban.player-not-found")))));
            return true;
        }

        if (target.isOp()) {
            player.sendMessage(Objects.requireNonNull(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("ban.cannot-ban-op")))));
            return true;
        }

        String reason = String.join(" ", Arrays.copyOfRange(args, 2, args.length));

        long tid;
        try {
            String tidStr = args[1];
            int calc = Integer.parseInt(tidStr.substring(0, tidStr.length() - 1));
            switch (tidStr.charAt(tidStr.length() - 1)) {
                case 's' -> tid = calc;
                case 'm' -> tid = calc * 60L;
                case 'h' -> tid = calc * 3600L;
                case 'd' -> tid = calc * 86400L;
                case 'w' -> tid = calc * 604800L;
                case 'y' -> tid = calc * 31556926L;
                default -> {
                    player.sendMessage(Objects.requireNonNull(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("ban.invalid-time")))));
                    return true;
                }
            }
        } catch (NumberFormatException e) {
            player.sendMessage(Objects.requireNonNull(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("ban.invalid-time")))));
            return true;
        }

        Date unbanDate = new Date(System.currentTimeMillis() + tid * 1000);

        Bukkit.getBanList(org.bukkit.BanList.Type.NAME).addBan(target.getName(), reason, unbanDate, player.getName());
        target.kickPlayer(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(Objects.requireNonNull(plugin.getConfig().getString("ban.ban-message-for-player"))).replace("%reason%", reason)));
        Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(Objects.requireNonNull(plugin.getConfig().getString("ban.ban-broadcast"))).replace("%target%", target.getName()).replace("%reason%", reason).replace("%time%", args[1])));

        // Schedule unban task
//        plugin.getServer().getScheduler().runTaskLater(plugin, () -> {
//            Bukkit.getBanList(org.bukkit.BanList.Type.NAME).pardon(target.getName());
//            Bukkit.broadcastMessage(ChatColor.GREEN + "Player " + target.getName() + " has been unbanned.");
//        }, tid * 20L);

        return true;
    }
}
