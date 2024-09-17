package me.mini.starterplugin;

import org.bukkit.plugin.java.JavaPlugin;
import java.util.Objects;

public final class StarterPlugin extends JavaPlugin {
    @Override
    public void onEnable() {
        // Plugin startup logic
        Objects.requireNonNull(this.getCommand("testcommand")).setExecutor(new firstCommand());

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
