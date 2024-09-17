package me.Mini.miniEssentials.give;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class giveCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "This command can only be used by players.");
            return true;
        }
            if (args.length < 2) {
                sender.sendMessage(ChatColor.RED + "/give <item> <amount>");
                return true;
            }

            Player player;
            try {
                player = Bukkit.getPlayer(args[0]);
                if (player == null) {
                    sender.sendMessage(ChatColor.RED + "Player not found.");
                    return true;
                }
            } catch (Exception e) {
                e.printStackTrace();
                return true;
            }
            int amount;

            try {
                amount = Integer.parseInt(args[2]);
                if (args.length <= 2) {
                    amount = 1;
                }
                if (amount <= 0) {
                    player.sendMessage(ChatColor.RED + "Du kan ikke skrive 0 eller mindre!");
                    return true;
                }
            } catch (NumberFormatException e) {
                player.sendMessage(ChatColor.RED + "Du skal skrive et tal!");
                return true;
            }

            Material material = Material.getMaterial(args[1].toUpperCase());
            if (material == null) {
                player.sendMessage(ChatColor.RED + "Ugyldigt item: " + args[1]);
                return true;
            }

            ItemStack item = new ItemStack(material, amount);
            player.getInventory().addItem(item);
            player.sendMessage("Du har fået " + ChatColor.YELLOW + amount + " " + args[1] + "s");

        return true;
    }
}
