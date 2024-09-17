package me.mini.starterplugin;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class firstCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (sender instanceof Player) {
            if (command.getName().equalsIgnoreCase("testcommand")) {
                if (args.length > 1) {
                    Player player = (Player) sender;
                    ItemStack item = new Itemstack(Material.valueOf(args[0].toUpperCase()), args[1]);
                    player.getInventory().addItem(item);
                    player.sendMessage("Du har fået " + item);
                } else {
                        sender.sendMessage(ChatColor.RED + "Skriv alle argumenter!");
                        sender.sendMessage(ChatColor.GRAY + "/testcommand <item> <amount>");
//                    Player player = Bukkit.getPlayer(args[0]);
//                    ItemStack item = new ItemStack(Material.EMERALD, 20);
//                    player.getInventory().addItem(item);
//                    player.sendMessage("Du har fået " + item + "Af spilleren: " + sender);
                }
    }
}
        return false;

    }
}
