package me.Mini.miniEssentials.other;

import me.Mini.miniEssentials.MiniEssentials;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.Objects;

public class joinListener implements Listener {
    private final MiniEssentials plugin;

    public joinListener(MiniEssentials plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        String message = Objects.requireNonNull(plugin.getConfig().getString("messages.player-join")).replace("%player%", player.getName());
        event.setJoinMessage(ChatColor.translateAlternateColorCodes('&', message));
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        String message = Objects.requireNonNull(plugin.getConfig().getString("messages.player-quit")).replace("%player%", player.getName());
        event.setQuitMessage(ChatColor.translateAlternateColorCodes('&', message));
    }
}
