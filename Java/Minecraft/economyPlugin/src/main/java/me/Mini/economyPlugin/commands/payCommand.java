package me.Mini.economyPlugin.commands;

import com.sun.tools.javac.Main;
import me.Mini.economyPlugin.EconomyPlugin;
import me.Mini.economyPlugin.storage.dbManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Objects;
import java.util.Random;
import java.util.UUID;

public class payCommand implements CommandExecutor {
    private final EconomyPlugin plugin;

    public payCommand(EconomyPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (sender instanceof Player) {
            if (args.length < 1) {

                sender.sendMessage(Objects.requireNonNull(plugin.getConfig().getString("pay.no-args")));
            }
            Player reciever = Bukkit.getPlayer(args[0]);
            if (reciever == null) {
                sender.sendMessage(Objects.requireNonNull(plugin.getConfig().getString("pay.no-args")));
            }

            if (reciever.getName() == sender.getName()) {
//                String[] bandeord = {"dit fjols", "din omvendte nettopose", "din spade", "din idiot", "din skovl"};
//                Random rand = new Random();
//                int ordet = rand.nextInt(bandeord.length);
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("pay.player-pay-self"))));
            } else {

                double amount;
                try {
                    amount = Double.parseDouble(args[1]);
                } catch (NumberFormatException e) {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("pay.invalid-amount"))));
                    return true;
                }
                UUID senderUuid = Objects.requireNonNull(Bukkit.getPlayer(sender.getName())).getUniqueId();
                double balance = dbManager.getBalance(senderUuid);
                if (balance >= amount) {
                    dbManager.removeBalance(senderUuid, amount);
                    UUID recieverUuid = Objects.requireNonNull(Bukkit.getPlayer(reciever.getName())).getUniqueId();
                    dbManager.giveBalance(recieverUuid, amount);
                    String recieverMessage = plugin.getConfig().getString("pay.pay-recieve-message").replace("%amount%", String.valueOf(amount)).replace("%sender%", sender.getName());
                    String senderMessage = plugin.getConfig().getString("pay.pay-sender-message").replace("%amount%", String.valueOf(amount)).replace("%reciever%", reciever.getName());
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', recieverMessage));
                    reciever.sendMessage(ChatColor.translateAlternateColorCodes('&', recieverMessage));

                } else {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("pay.cannot-afford"))));
                }


            }
        }
        return false;
    }
}
