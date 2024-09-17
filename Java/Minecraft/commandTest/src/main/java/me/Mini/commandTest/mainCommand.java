package me.Mini.commandTest;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class mainCommand extends JavaPlugin {

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
