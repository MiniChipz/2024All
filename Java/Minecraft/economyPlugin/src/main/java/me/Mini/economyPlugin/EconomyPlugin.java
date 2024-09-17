package me.Mini.economyPlugin;

import lombok.Getter;
import me.Mini.economyPlugin.commands.balanceCommand;
import me.Mini.economyPlugin.commands.ecoCommand;
import me.Mini.economyPlugin.commands.payCommand;
import me.Mini.economyPlugin.storage.dbManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public class EconomyPlugin extends JavaPlugin {

    @Getter
    private static EconomyPlugin instance;
    private dbManager database;

    @Override
    public void onEnable() {
        instance = this;
        database = new dbManager();

        getConfig().options().copyDefaults(true);
        saveDefaultConfig();

        // Register commands
        Objects.requireNonNull(this.getCommand("eco")).setExecutor(new ecoCommand());
        Objects.requireNonNull(this.getCommand("balance")).setExecutor(new balanceCommand(this));
        Objects.requireNonNull(this.getCommand("pay")).setExecutor(new payCommand(this));

        // Other plugin initialization code
    }

    @Override
    public void onDisable() {
        if (database != null) {
            database.close();
        }
    }

}