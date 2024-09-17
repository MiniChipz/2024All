package me.Mini.miniEssentials.specialBow;

import me.Mini.miniEssentials.MiniEssentials;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class specialBowUtil {
    private final MiniEssentials plugin;

    public specialBowUtil(MiniEssentials plugin) {
        this.plugin = plugin;
    }

    public ItemStack createSpecialBow(String bow) {
        ItemStack theBow = null;
        switch (bow) {
            case "teleportbow" -> {
                ItemStack teleportbow = new ItemStack(Material.BOW, 1);

                ItemMeta teleportbowItemMeta = teleportbow.getItemMeta();
                teleportbowItemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("specialbow.teleportbow-name"))));

                List<String> teleportBowLore = plugin.getConfig().getStringList("specialbow.teleportbow-lore");
                teleportBowLore.replaceAll(textToTranslate -> ChatColor.translateAlternateColorCodes('&', textToTranslate));
                teleportbowItemMeta.setLore(teleportBowLore);
                teleportbowItemMeta.addEnchant(Enchantment.ARROW_INFINITE, 1, true);
                teleportbowItemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                teleportbowItemMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
                teleportbowItemMeta.setUnbreakable(true);
                teleportbow.setItemMeta(teleportbowItemMeta);
                theBow = teleportbow;
            }
            case "explosivebow" -> {
                ItemStack explosivebow = new ItemStack(Material.BOW, 1);

                ItemMeta explosivebowItemMeta = explosivebow.getItemMeta();
                explosivebowItemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("specialbow.explosivebow-name"))));

                List<String> explosivebowLore = plugin.getConfig().getStringList("specialbow.explosivebow-lore");
                explosivebowLore.replaceAll(textToTranslate -> ChatColor.translateAlternateColorCodes('&', textToTranslate));
                explosivebowItemMeta.setLore(explosivebowLore);
                explosivebowItemMeta.addEnchant(Enchantment.ARROW_INFINITE, 1, false);
                explosivebowItemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                explosivebowItemMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
                explosivebowItemMeta.setUnbreakable(true);
                explosivebow.setItemMeta(explosivebowItemMeta);
                theBow = explosivebow;
            }
            case "smitebow" -> {
                ItemStack smitebow = new ItemStack(Material.BOW, 1);

                ItemMeta smitebowItemMeta = smitebow.getItemMeta();
                smitebowItemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("specialbow.smitebow-name"))));

                List<String> smitebowLore = plugin.getConfig().getStringList("specialbow.smitebow-lore");
                smitebowLore.replaceAll(textToTranslate -> ChatColor.translateAlternateColorCodes('&', textToTranslate));
                smitebowItemMeta.setLore(smitebowLore);
                smitebowItemMeta.addEnchant(Enchantment.ARROW_INFINITE, 1, false);
                smitebowItemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                smitebowItemMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
                smitebowItemMeta.setUnbreakable(true);
                smitebow.setItemMeta(smitebowItemMeta);
                theBow = smitebow;
            }
        }
        return theBow;
    }
    public String getSpecialBowType(ItemStack item) {
        List<String> bowTypes = Arrays.asList("teleportbow", "explosivebow", "smitebow");
        for (String bowType : bowTypes) {
            if (item.equals(createSpecialBow(bowType))) {
                return bowType;
            }
        }
        return null;
    }
}
