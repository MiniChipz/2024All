package me.Mini.miniEssentials.other;

import me.Mini.miniEssentials.MiniEssentials;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.event.world.TimeSkipEvent;

public class extraListeners implements Listener {

    private final MiniEssentials plugin;

    public extraListeners(MiniEssentials plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onTimeSkip(TimeSkipEvent event) {
        if (plugin.getConfig().getBoolean("extra.time-change-cancel")) {
            event.setCancelled(true);
        }
    }
    @EventHandler
    public void onWheaterChange(WeatherChangeEvent event) {
        if (event.toWeatherState()) {
            if (plugin.getConfig().getBoolean("extra.wheater-change-cancel")) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onHunger(FoodLevelChangeEvent event) {
        if (plugin.getConfig().getBoolean("extra.hunger-change-cancel")) {
            event.setCancelled(true);
        }
    }
}
