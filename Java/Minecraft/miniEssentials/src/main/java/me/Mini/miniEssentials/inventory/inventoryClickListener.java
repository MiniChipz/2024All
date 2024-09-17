package me.Mini.miniEssentials.inventory;

import me.Mini.miniEssentials.MiniEssentials;
import me.Mini.miniEssentials.specialBow.specialBowUtil;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;

public class inventoryClickListener implements Listener {

    private final MiniEssentials plugin;
    private final specialBowUtil bowUtil;

    public inventoryClickListener(MiniEssentials plugin) {
        this.plugin = plugin;
        this.bowUtil = new specialBowUtil(plugin);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getView().getTitle().equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("specialbow.inventory-name").replace("%player%", event.getWhoClicked().getName()))))) {
            event.setCancelled(true);
            event.getWhoClicked().setItemOnCursor(new ItemStack(Material.AIR));
            switch (event.getSlot()) {
                case 9 -> {
                    event.getWhoClicked().getInventory().addItem(bowUtil.createSpecialBow("teleportbow"),new ItemStack(Material.ARROW, 1));
                    event.getWhoClicked().sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("specialbow.teleportbow-recieve-message"))));
                    event.getWhoClicked().closeInventory();
                }
                case 13 -> {
                    event.getWhoClicked().getInventory().addItem(bowUtil.createSpecialBow("explosivebow"),new ItemStack(Material.ARROW, 1));
                    event.getWhoClicked().sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("specialbow.explosivebow-recieve-message"))));
                    event.getWhoClicked().closeInventory();
                }
                case 17 -> {
                    event.getWhoClicked().getInventory().addItem(bowUtil.createSpecialBow("smitebow"),new ItemStack(Material.ARROW, 1));
                    event.getWhoClicked().sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("specialbow.smitebow-recieve-message"))));
                    event.getWhoClicked().closeInventory();
                }
            }
        }
    }
}
