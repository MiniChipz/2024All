package me.Mini.miniEssentials.godMode;

import me.Mini.miniEssentials.MiniEssentials;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class godModeListener implements Listener {

    private final MiniEssentials plugin;

    public godModeListener(MiniEssentials plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onDamage(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player victim) {
            godModeCommand godModeCmd = plugin.getGodModeCommandExecutor();

            if (godModeCmd.getGodModePlayers().contains(victim.getUniqueId())) {
                event.setCancelled(true);
            }
        }
    }
}
