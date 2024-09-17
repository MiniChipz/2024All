package me.Mini.miniEssentials;

import me.Mini.miniEssentials.banMenu.*;
import me.Mini.miniEssentials.entities.armorstandCommand;
import me.Mini.miniEssentials.gamemodes.gamemodeCommand;
import me.Mini.miniEssentials.give.giveCommand;
import me.Mini.miniEssentials.give.iGiveCommand;
import me.Mini.miniEssentials.godMode.godModeCommand;
import me.Mini.miniEssentials.godMode.godModeListener;
import me.Mini.miniEssentials.heal.healCommand;
import me.Mini.miniEssentials.inventory.inventoryClickListener;
import me.Mini.miniEssentials.other.*;
import me.Mini.miniEssentials.specialBow.specialBowCommand;
import me.Mini.miniEssentials.specialBow.specialBowListener;
import me.Mini.miniEssentials.tabCompleters.materialNameArg0;
import me.Mini.miniEssentials.msg.msgCommand;
import me.Mini.miniEssentials.tabCompleters.materialNameArg1;
import me.Mini.miniEssentials.tabCompleters.playerNameArg0;
import me.Mini.miniEssentials.tabCompleters.playerNameArg1;
import me.Mini.miniEssentials.msg.replyCommand;
import me.Mini.miniEssentials.teleport.setSpawnCommand;
import me.Mini.miniEssentials.teleport.spawnCommand;
import me.Mini.miniEssentials.teleport.spawnEventListener;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class MiniEssentials extends JavaPlugin {

    private muteManager muteManager;
    private godModeCommand godModeCommandExecutor;

    @Override
    public void onEnable() {
        //---------------------------------------------Config-----------------------------------------------------------
        getConfig().options().copyDefaults(true);
        saveDefaultConfig();

        //----------------------------------------Event Listeners-------------------------------------------------------
        getServer().getPluginManager().registerEvents(new spawnEventListener(this), this);
        getServer().getPluginManager().registerEvents(new godModeListener(this), this);
        if (getConfig().getBoolean("allows.allow-block-break") || getConfig().getBoolean("allows.allow-block-place")) {
            getServer().getPluginManager().registerEvents(new blockBreakCancelListener(this), this);
            getServer().getPluginManager().registerEvents(new blockPlaceCancelListener(this), this);
        }
        getServer().getPluginManager().registerEvents(new joinListener(this), this);
        if (getConfig().getBoolean("extra.time-change-cancel") || getConfig().getBoolean("extra.wheater-change-cancel") || getConfig().getBoolean("extra.hunger-change-cancel")) {
            getServer().getPluginManager().registerEvents(new extraListeners(this), this);
        }
        getServer().getPluginManager().registerEvents(new killListener(this), this);
        getServer().getPluginManager().registerEvents(new inventoryClickListener(this), this);
        getServer().getPluginManager().registerEvents(new specialBowListener(this), this);

        //----------------------------------------Spawn commands--------------------------------------------------------
        Objects.requireNonNull(getCommand("setspawn")).setExecutor(new setSpawnCommand(this));
        Objects.requireNonNull(getCommand("spawn")).setExecutor(new spawnCommand(this));

        //-----------------------------------------Special bows---------------------------------------------------------
        Objects.requireNonNull(getCommand("specialbow")).setExecutor(new specialBowCommand(this));

        //-------------------------------------------ArmorStands--------------------------------------------------------
        Objects.requireNonNull(getCommand("armorstand")).setExecutor(new armorstandCommand());

        //-----------------------------------------Give commands--------------------------------------------------------
        Objects.requireNonNull(this.getCommand("give")).setExecutor(new giveCommand());
        Objects.requireNonNull(this.getCommand("give")).setTabCompleter(new materialNameArg1());
        Objects.requireNonNull(this.getCommand("i")).setExecutor(new iGiveCommand());
        Objects.requireNonNull(this.getCommand("i")).setTabCompleter(new materialNameArg0());

        //-----------------------------------------Msg commands---------------------------------------------------------
        msgCommand msgCmd = new msgCommand();
        Objects.requireNonNull(this.getCommand("msg")).setExecutor(msgCmd);
        Objects.requireNonNull(this.getCommand("msg")).setTabCompleter(new playerNameArg0());
        Objects.requireNonNull(this.getCommand("reply")).setExecutor(new replyCommand(msgCmd));

        //-------------------------------------Gamemode commands--------------------------------------------------------
        Objects.requireNonNull(this.getCommand("gamemode")).setExecutor(new gamemodeCommand());
        Objects.requireNonNull(this.getCommand("gamemode")).setTabCompleter(new playerNameArg1());
        Objects.requireNonNull(this.getCommand("gmc")).setTabCompleter(new playerNameArg0());
        Objects.requireNonNull(this.getCommand("gms")).setTabCompleter(new playerNameArg0());
        Objects.requireNonNull(this.getCommand("gmsp")).setTabCompleter(new playerNameArg0());
        Objects.requireNonNull(this.getCommand("gma")).setTabCompleter(new playerNameArg0());

        //----------------------------------------Other commands--------------------------------------------------------
        Objects.requireNonNull(this.getCommand("broadcast")).setExecutor(new broadcastCommand());
        Objects.requireNonNull(this.getCommand("fly")).setExecutor(new flyCommand());
        Objects.requireNonNull(this.getCommand("fly")).setTabCompleter(new playerNameArg1());
        godModeCommandExecutor = new godModeCommand();
        Objects.requireNonNull(this.getCommand("god")).setExecutor(godModeCommandExecutor);
        Objects.requireNonNull(this.getCommand("god")).setTabCompleter(new playerNameArg0());
        Objects.requireNonNull(getCommand("clearchat")).setExecutor(new clearchatCommand(this));
        Objects.requireNonNull(getCommand("heal")).setExecutor(new healCommand(this));
        Objects.requireNonNull(getCommand("heal")).setTabCompleter(new playerNameArg0());

        //----------------------------------------Ban Commands----------------------------------------------------------
        Objects.requireNonNull(getCommand("ban")).setExecutor(new banCommand(this));
        Objects.requireNonNull(getCommand("ban")).setTabCompleter(new playerNameArg0());
        Objects.requireNonNull(getCommand("kick")).setExecutor(new kickCommand(this));
        Objects.requireNonNull(getCommand("kick")).setTabCompleter(new playerNameArg0());
        Objects.requireNonNull(getCommand("unban")).setExecutor(new unbanCommand(this));
        Objects.requireNonNull(getCommand("unban")).setTabCompleter(new unbanTabCompleter());

        muteManager = new muteManager();
        Objects.requireNonNull(getCommand("mute")).setExecutor(new muteCommand(this, muteManager));
        Objects.requireNonNull(getCommand("mute")).setTabCompleter(new playerNameArg0());
        Objects.requireNonNull(getCommand("unmute")).setExecutor(new unmuteCommand(this, muteManager));
        Objects.requireNonNull(getCommand("unmute")).setTabCompleter(new unmuteTabCompleter(muteManager));
        getServer().getPluginManager().registerEvents(new muteListener(muteManager), this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public godModeCommand getGodModeCommandExecutor() {
        return godModeCommandExecutor;
    }

    public muteManager getMuteManager() {
        return muteManager;
    }

    public static void sendActionBar(Player player, String message) {
        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(message));
    }
}
