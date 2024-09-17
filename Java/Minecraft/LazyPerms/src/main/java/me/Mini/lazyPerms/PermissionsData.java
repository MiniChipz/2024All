package me.Mini.lazyPerms;

import me.Mini.lazyPerms.LazyPerms;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class PermissionsData {
    private final FileConfiguration config;
    private final File configFile;

    public PermissionsData(LazyPerms plugin) {
        this.configFile = new File(plugin.getDataFolder(), "permissions.yml");
        this.config = YamlConfiguration.loadConfiguration(configFile);
    }

    public void loadData() {
        if (!configFile.exists()) {
            saveData();
        }
    }

    public void saveData() {
        try {
            config.save(configFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isGroup(String target) {
        return config.isConfigurationSection("groups." + target);
    }

    public void addGroupPermission(String group, String permission) {
        List<String> permissions = config.getStringList("groups." + group + ".permissions");
        permissions.add(permission);
        config.set("groups." + group + ".permissions", permissions);
        saveData();
    }

    public void removeGroupPermission(String group, String permission) {
        List<String> permissions = config.getStringList("groups." + group + ".permissions");
        permissions.remove(permission);
        config.set("groups." + group + ".permissions", permissions);
        saveData();
    }

    public void addPlayerPermission(String player, String permission) {
        List<String> permissions = config.getStringList("players." + player + ".permissions");
        permissions.add(permission);
        config.set("players." + player + ".permissions", permissions);
        saveData();
    }

    public void removePlayerPermission(String player, String permission) {
        List<String> permissions = config.getStringList("players." + player + ".permissions");
        permissions.remove(permission);
        config.set("players." + player + ".permissions", permissions);
        saveData();
    }

    public void addPlayerPrefix(String player, String prefix) {
        config.set("players." + player + ".prefix", prefix);
        saveData();
    }

    public void removePlayerPrefix(String player) {
        config.set("players." + player + ".prefix", null);
        saveData();
    }

    public void addPlayerSuffix(String player, String suffix) {
        config.set("players." + player + ".suffix", suffix);
        saveData();
    }

    public void removePlayerSuffix(String player) {
        config.set("players." + player + ".suffix", null);
        saveData();
    }

    public Set<String> getPlayerPermissions(String player) {
        return new HashSet<>(config.getStringList("players." + player + ".permissions"));
    }

    public Set<String> getGroupPermissions(String group) {
        return new HashSet<>(config.getStringList("groups." + group + ".permissions"));
    }

    public Set<String> getPlayerGroups(String player) {
        return new HashSet<>(config.getStringList("players." + player + ".groups"));
    }
}
