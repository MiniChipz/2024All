package me.Mini.miniEssentials.other;

import me.Mini.miniEssentials.MiniEssentials;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.util.Objects;

public class killListener implements Listener {
    private final MiniEssentials plugin;

    public killListener(MiniEssentials plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player victim = event.getEntity();
        Player attacker = event.getEntity().getKiller();
        if (attacker != null) {
            String message = Objects.requireNonNull(plugin.getConfig().getString("messages.death-message")).replace("%victim%", victim.getName()).replace("%attacker%", attacker.getName());
            event.setDeathMessage(ChatColor.translateAlternateColorCodes('&', message));
        }
    }
}
