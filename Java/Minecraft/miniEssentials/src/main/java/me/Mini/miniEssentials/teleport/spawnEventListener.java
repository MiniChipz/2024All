package me.Mini.miniEssentials.teleport;

import me.Mini.miniEssentials.MiniEssentials;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

public class spawnEventListener implements Listener {

    private final MiniEssentials plugin;

    public spawnEventListener(MiniEssentials plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        if (!(event.getPlayer().hasPlayedBefore())) {
            Player player = event.getPlayer();

            Location loc = plugin.getConfig().getLocation("spawn");

            if (loc != null) {
                player.teleport(loc);

                player.sendMessage("Velkommen til serveren!");
            }

        } else {

        }
    }

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent event) {
        Location loc = plugin.getConfig().getLocation("spawn");

        if (loc != null) {
            event.setRespawnLocation(loc);

        }
    }
}

