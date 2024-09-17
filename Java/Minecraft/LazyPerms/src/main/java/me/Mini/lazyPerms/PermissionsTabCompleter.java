package me.Mini.lazyPerms;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PermissionsTabCompleter implements TabCompleter {

    private final PermissionsManager permissionsManager;

    public PermissionsTabCompleter(PermissionsManager permissionsManager) {
        this.permissionsManager = permissionsManager;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> completions = new ArrayList<>();
        switch (args.length) {
            case 1:
                completions.addAll(Arrays.asList("group", "g", "player", "p", "create"));
                break;
            case 2:
                if (args[0].equalsIgnoreCase("create")) {
                    // No further suggestions for group creation
                    break;
                }
                completions.addAll(Arrays.asList("add", "remove", "addplayer", "removeplayer"));
                break;
            case 3:
                if (args[0].equalsIgnoreCase("group") || args[0].equalsIgnoreCase("g") ||
                        args[0].equalsIgnoreCase("player") || args[0].equalsIgnoreCase("p")) {
                    completions.addAll(Arrays.asList("permission"));
                    if (args[0].equalsIgnoreCase("player") || args[0].equalsIgnoreCase("p")) {
                        completions.addAll(Arrays.asList("prefix", "suffix"));
                    }
                }
                break;
            case 4:
                if (args[0].equalsIgnoreCase("group") || args[0].equalsIgnoreCase("g")) {
                    completions.addAll(permissionsManager.getGroups());
                } else if (args[0].equalsIgnoreCase("player") || args[0].equalsIgnoreCase("p")) {
                    completions.addAll(permissionsManager.getPlayers());
                }
                break;
            case 5:
                if (args[2].equalsIgnoreCase("permission")) {
                    completions.addAll(permissionsManager.getPermissions());
                }
                break;
            default:
                break;
        }
        return completions;
    }
}
