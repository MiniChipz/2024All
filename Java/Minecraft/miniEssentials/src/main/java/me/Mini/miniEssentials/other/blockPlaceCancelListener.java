package me.Mini.miniEssentials.other;

import me.Mini.miniEssentials.MiniEssentials;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class blockPlaceCancelListener implements Listener {
    private final MiniEssentials plugin;

    public blockPlaceCancelListener(MiniEssentials plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
            if (!(event.getPlayer().hasPermission("miniessentials.blockplace"))) {
                event.setCancelled(true);
                String message;
                message = plugin.getConfig().getString("messages.block-place-cancel-message");
                if (message == null) {
                    message = "&cDet må du ik!.";
                }
                event.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', message));
        }
    }
}
