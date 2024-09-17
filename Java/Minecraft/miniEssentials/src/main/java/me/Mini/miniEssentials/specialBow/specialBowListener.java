package me.Mini.miniEssentials.specialBow;

import me.Mini.miniEssentials.MiniEssentials;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;

import java.util.Objects;

public class specialBowListener implements Listener {
    private final MiniEssentials plugin;
    private final specialBowUtil bowUtil;

    public specialBowListener(MiniEssentials plugin) {
        this.plugin = plugin;
        this.bowUtil = new specialBowUtil(plugin);
    }

    @EventHandler
    public void onProjectileHit(ProjectileHitEvent event) {
        if (event.getEntity().getShooter() instanceof Player player) {

            String bowType = bowUtil.getSpecialBowType(player.getInventory().getItemInMainHand());
            if (bowType != null) {
                switch (bowType) {
                    case "teleportbow":
                        Location hitLocation = event.getEntity().getLocation();
                        float pitch = player.getLocation().getPitch();
                        float yaw = player.getLocation().getYaw();
                        hitLocation.setPitch(pitch);
                        hitLocation.setYaw(yaw);
                        player.teleport(hitLocation);

                        MiniEssentials.sendActionBar(player, ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("specialbow.teleportbow-use-message"))));
                        event.getEntity().remove();
                        break;
                    case "explosivebow":
                        float explosiveRange = (float) plugin.getConfig().getDouble("specialbow.explosivebow-size");
                        Objects.requireNonNull(event.getEntity().getLocation().getWorld()).createExplosion(event.getEntity().getLocation(), explosiveRange);
                        event.getEntity().remove();
                        MiniEssentials.sendActionBar(player, ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("specialbow.explosivebow-use-message"))));
                        break;
                    case "smitebow":
                        Objects.requireNonNull(event.getEntity().getLocation().getWorld()).strikeLightning(event.getEntity().getLocation());
                        MiniEssentials.sendActionBar(player, ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("specialbow.smitebow-use-message"))));
                        event.getEntity().remove();
                        break;
                }
            }
        }
    }
}
