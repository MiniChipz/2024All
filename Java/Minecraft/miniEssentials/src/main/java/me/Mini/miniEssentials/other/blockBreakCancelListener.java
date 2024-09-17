package me.Mini.miniEssentials.other;

import me.Mini.miniEssentials.MiniEssentials;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class blockBreakCancelListener implements Listener {

    private final MiniEssentials plugin;

    public blockBreakCancelListener(MiniEssentials plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
            Player player = event.getPlayer();
            if (!(player.hasPermission("miniessentials.blockbreak"))) {
                event.setCancelled(true);
                String message;
                    message = plugin.getConfig().getString("messages.block-break-cancel-message");
                    if (message == null) {
                        message = "&cDet må du ik!.";
                    }
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
        }
    }
}
