package me.Mini.miniEssentials.entities;

import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class armorstandCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("This command can only be executed by a player!");
        }
        Player player = (Player) sender;
        ArmorStand armorstand = (ArmorStand) player.getWorld().spawnEntity(player.getLocation(), EntityType.ARMOR_STAND);
        ItemStack helmet = player.getInventory().getHelmet();
        ItemStack chestplate = player.getInventory().getChestplate();
        ItemStack leggings = player.getInventory().getLeggings();
        ItemStack boots = player.getInventory().getBoots();
        ItemStack mainHand = player.getInventory().getItemInMainHand();

        armorstand.setHelmet(helmet);
        armorstand.setChestplate(chestplate);
        armorstand.setLeggings(leggings);
        armorstand.setBoots(boots);
        armorstand.setItemInHand(mainHand);
        armorstand.setArms(true);

        player.getInventory().setHelmet(null);
        player.getInventory().setChestplate(null);
        player.getInventory().setLeggings(null);
        player.getInventory().setBoots(null);
        player.getInventory().setItemInMainHand(null);

        player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BASS, 1, 1);

        return true;
    }
}
