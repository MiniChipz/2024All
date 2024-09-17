package me.Mini.lazyPerms;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.TabCompleter;
import org.bukkit.configuration.file.FileConfiguration;

public class LazyPerms extends JavaPlugin {
    private PermissionsManager permissionsManager;

    @Override
    public void onEnable() {
        // Load the configuration file
        saveDefaultConfig();
        FileConfiguration config = getConfig();

        // Initialize PermissionsManager with the configuration
        permissionsManager = new PermissionsManager(config);

        // Register commands
        CommandExecutor permissionsCommand = new PermissionsCommand(permissionsManager);
        getCommand("lp").setExecutor(permissionsCommand);

        // Register tab completer
        TabCompleter permissionsTabCompleter = new PermissionsTabCompleter(permissionsManager);
        getCommand("lp").setTabCompleter(permissionsTabCompleter);
    }

    @Override
    public void onDisable() {
        // Any cleanup logic here
    }
}
