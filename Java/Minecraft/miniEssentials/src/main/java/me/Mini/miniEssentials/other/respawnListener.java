package me.Mini.miniEssentials.other;

import me.Mini.miniEssentials.MiniEssentials;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.util.Objects;

public class respawnListener implements Listener {
    private final MiniEssentials plugin;

    public respawnListener(MiniEssentials plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onInteract(PlayerDeathEvent event) {
        Player player = event.getEntity();
        Bukkit.getScheduler().runTask(plugin, () -> player.spigot().respawn());
        MiniEssentials.sendActionBar(player, ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("messages.respawn-message"))));
    }
}
