package me.Mini.miniEssentials.give;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class iGiveCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage(ChatColor.RED + "You must be a player to use this command!");
        }
        Player player = (Player) commandSender;
        if (args.length < 1) {
            player.sendMessage(ChatColor.RED + "/i <item> <amount>");
        }
        Material material = Material.getMaterial(args[0].toUpperCase());
        if (material == null) {
            player.sendMessage(ChatColor.RED + "Ugyldigt item: " + args[0]);
        }
        int amount;
        try {
            if (args.length < 2) {
                amount = 1;
            }
            amount = Integer.parseInt(args[1]);
            if (amount <= 0) {
                player.sendMessage(ChatColor.RED + "Du kan ikke skrive 0 eller mindre!");
                return true;
            }
        } catch (NumberFormatException e) {
            player.sendMessage(ChatColor.RED + "Du skal skrive et tal!");
            return true;
        }

        ItemStack item = new ItemStack(material, amount);
        player.getInventory().addItem(item);
        player.sendMessage("Du har fået " + ChatColor.YELLOW + amount + " " + args[0] + "s");
        return true;
    }
}
