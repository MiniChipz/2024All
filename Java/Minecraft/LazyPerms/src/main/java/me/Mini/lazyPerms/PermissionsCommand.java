package me.Mini.lazyPerms;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.Arrays;

public class PermissionsCommand implements CommandExecutor {
    private final PermissionsManager permissionsManager;

    public PermissionsCommand(PermissionsManager permissionsManager) {
        this.permissionsManager = permissionsManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length < 2) {
            sender.sendMessage("Usage: /lp <group/g|player/p|create> <add|remove|addplayer|removeplayer> <permission|prefix|suffix> <value>");
            return false;
        }

        String targetType = args[0];
        String action = args[1];
        String type = args.length > 3 ? args[2] : null;
        String target = args.length > 3 ? args[3] : null;
        String value = args.length > 4 ? String.join(" ", Arrays.copyOfRange(args, 4, args.length)) : null;

        if (targetType.equalsIgnoreCase("create") && args.length == 2) {
            permissionsManager.createGroup(action);
            sender.sendMessage("Group " + action + " created.");
            return true;
        }

        if (args.length < 4) {
            switch (args.length) {
                case 2:
                    sender.sendMessage("Usage: /lp <group/g|player/p> <add|remove|addplayer|removeplayer> <permission|prefix|suffix> <value>");
                    break;
                case 3:
                    sender.sendMessage("Usage: /lp " + args[0] + " " + args[1] + " <permission|prefix|suffix> <value>");
                    break;
                case 4:
                    sender.sendMessage("Usage: /lp " + args[0] + " " + args[1] + " " + args[2] + " <value>");
                    break;
                default:
                    sender.sendMessage("Usage: /lp <group/g|player/p|create> <add|remove|addplayer|removeplayer> <permission|prefix|suffix> <value>");
            }
            return false;
        }

        switch (targetType.toLowerCase()) {
            case "group":
            case "g":
                handleGroupAction(sender, action, type, target, value);
                break;
            case "player":
            case "p":
                handlePlayerAction(sender, action, type, target, value);
                break;
            default:
                sender.sendMessage("Invalid target type. Use group/g, player/p, or create.");
                return false;
        }

        return true;
    }

    private void handleGroupAction(CommandSender sender, String action, String type, String group, String value) {
        switch (action.toLowerCase()) {
            case "add":
                if (type.equalsIgnoreCase("permission")) {
                    permissionsManager.addGroupPermission(group, value);
                    sender.sendMessage("Permission added to group.");
                } else {
                    sender.sendMessage("Invalid type for group. Use permission.");
                }
                break;
            case "remove":
                if (type.equalsIgnoreCase("permission")) {
                    permissionsManager.removeGroupPermission(group, value);
                    sender.sendMessage("Permission removed from group.");
                } else {
                    sender.sendMessage("Invalid type for group. Use permission.");
                }
                break;
            case "addplayer":
                permissionsManager.addPlayerToGroup(value, group);
                sender.sendMessage("Player added to group.");
                break;
            case "removeplayer":
                permissionsManager.addPlayerToGroup(value, null); // or implement a method to remove a player from a group
                sender.sendMessage("Player removed from group.");
                break;
            default:
                sender.sendMessage("Invalid action. Use add/remove/addplayer/removeplayer.");
        }
    }

    private void handlePlayerAction(CommandSender sender, String action, String type, String player, String value) {
        switch (action.toLowerCase()) {
            case "add":
                if (type.equalsIgnoreCase("permission")) {
                    permissionsManager.addPlayerPermission(player, value);
                    sender.sendMessage("Permission added to player.");
                } else if (type.equalsIgnoreCase("prefix")) {
                    permissionsManager.addPlayerPrefix(player, value);
                    sender.sendMessage("Prefix added to player.");
                } else if (type.equalsIgnoreCase("suffix")) {
                    permissionsManager.addPlayerSuffix(player, value);
                    sender.sendMessage("Suffix added to player.");
                } else {
                    sender.sendMessage("Invalid type for player. Use permission/prefix/suffix.");
                }
                break;
            case "remove":
                if (type.equalsIgnoreCase("permission")) {
                    permissionsManager.removePlayerPermission(player, value);
                    sender.sendMessage("Permission removed from player.");
                } else if (type.equalsIgnoreCase("prefix")) {
                    permissionsManager.removePlayerPrefix(player);
                    sender.sendMessage("Prefix removed from player.");
                } else if (type.equalsIgnoreCase("suffix")) {
                    permissionsManager.removePlayerSuffix(player);
                    sender.sendMessage("Suffix removed from player.");
                } else {
                    sender.sendMessage("Invalid type for player. Use permission/prefix/suffix.");
                }
                break;
            default:
                sender.sendMessage("Invalid action. Use add/remove.");
        }
    }
}
