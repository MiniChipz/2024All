package me.Mini.miniEssentials.banMenu;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.UUID;

public class muteListener implements Listener {
    private final muteManager muteManager;

    public muteListener(muteManager muteManager) {
        this.muteManager = muteManager;
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        UUID playerUUID = event.getPlayer().getUniqueId();
        if (muteManager.isPlayerMuted(playerUUID)) {
            event.getPlayer().sendMessage("You are muted and cannot send messages.");
            event.setCancelled(true);
        }
    }
}
