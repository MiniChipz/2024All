package me.Mini.miniEssentials.specialBow;

import me.Mini.miniEssentials.MiniEssentials;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;
import java.util.Objects;

public class specialBowCommand implements CommandExecutor {
    private final MiniEssentials plugin;
    private final specialBowUtil bowUtil;

    public specialBowCommand(MiniEssentials plugin) {
        this.plugin = plugin;
        this.bowUtil = new specialBowUtil(plugin);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("You must be a player!");
            return true;
        }

        Player player = (Player) sender;
        if (args.length > 0) {
            player = Bukkit.getPlayer(args[0]);
            if (player == null) {
                sender.sendMessage("Player not found!");
                return true;
            }
        }
        Player senderPlayer = (Player) sender;

        Inventory inv = Bukkit.createInventory(player, 27, ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("specialbow.inventory-name")).replace("%player%", player.getName())));

        inv.setItem(9, bowUtil.createSpecialBow("teleportbow"));
        inv.setItem(13, bowUtil.createSpecialBow("explosivebow"));
        inv.setItem(17, bowUtil.createSpecialBow("smitebow"));

        ItemStack greyGlassPane = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
        ItemMeta greymeta = greyGlassPane.getItemMeta();
            greymeta.setDisplayName(" ");
            greymeta.setLore(null);
            greyGlassPane.setItemMeta(greymeta);
        int[] slots = {0, 1, 2, 3, 4, 5, 6, 7, 8, 18, 19, 20, 21, 22, 23, 24, 25, 26};
        for (int slot : slots) {
            inv.setItem(slot, greyGlassPane);
        }

        senderPlayer.openInventory(inv);
        senderPlayer.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1f, 1f);
        return true;
    }
}
