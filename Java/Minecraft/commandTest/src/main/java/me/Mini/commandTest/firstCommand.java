package me.Mini.commandTest;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class firstCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            ItemStack item = new ItemStack(Material.EMERALD, 20);
            player.getInventory().addItem(item);
            player.sendMessage("Du har fået " + item);

        }
        return false;

    }
}
