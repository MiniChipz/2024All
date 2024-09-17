package me.Mini.economyPlugin.commands;

import me.Mini.economyPlugin.EconomyPlugin;
import me.Mini.economyPlugin.storage.dbManager;
import me.Mini.economyPlugin.storage.objects.Economy;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Objects;
import java.util.UUID;

public class balanceCommand implements CommandExecutor {

    private final EconomyPlugin plugin;

    public balanceCommand(EconomyPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player sender1 = (Player) sender;
        if (args.length == 0 && sender instanceof Player player) {
            // /balance (to check own balance)
            UUID playerUUID = player.getUniqueId();
            double balance = dbManager.getBalance(playerUUID);
            String message;
            message = Objects.requireNonNull(plugin.getConfig().getString("balance.players-own-balance")).replace("%amount%", String.valueOf(balance));
            if (plugin.getConfig().getString("balance.players-own-balance") == null) {
                message = "&7Din balance: &2" + balance + "$";
            }
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
            return true;
        } else if (args.length >= 1) {
            // /balance <player> (to check another player's balance)
            Player target = Bukkit.getPlayer(args[0]);
            Player player = (Player) sender;
            if (target != null) {

                UUID targetUUID = target.getUniqueId();
                double balance = dbManager.getBalance(targetUUID);
                String message;
                message = Objects.requireNonNull(plugin.getConfig().getString("balance.players-balance")).replace("%amount%", String.valueOf(balance)).replace("%player%",target.getName());
                if (plugin.getConfig().getString("balance.players-balance") == null) {
                    message = "&6" + target.getName() + "'s &7balance: &2" + String.valueOf(balance) + "$";
                }
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
                return true;
            } else {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("balance.player-not-found-balance"))));
                return false;
            }
        } else {
            sender1.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("balance.usage-balance"))));
            return false;
        }
    }
}
