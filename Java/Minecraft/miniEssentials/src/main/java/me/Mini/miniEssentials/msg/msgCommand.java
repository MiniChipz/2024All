package me.Mini.miniEssentials.msg;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class msgCommand implements CommandExecutor {

    private static final Map<Player, Player> lastMessaged = new HashMap<>();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("This command can only be executed by a player!");
            return true;
        }

        if (args.length < 2) {
            sender.sendMessage(ChatColor.RED + "Usage: /msg <player> <message>");
            return true;
        }

        Player senderPlayer = (Player) sender;
        Player receiver = Bukkit.getPlayer(args[0]);

        if (receiver == null) {
            sender.sendMessage(ChatColor.RED + "Player not found.");
            return true;
        }

        StringBuilder message = new StringBuilder();
        for (int i = 1; i < args.length; i++) {
            if (i > 1) {
                message.append(" ");
            }
            message.append(args[i]);
        }

        String senderMessage = ChatColor.translateAlternateColorCodes('&', "&8[&6Me &a-> &e" + receiver.getName() + "&8]: &f" + message.toString());
        String receiverMessage = ChatColor.translateAlternateColorCodes('&', "&8[&6" + senderPlayer.getName() + " &a-> &eMe&8]: &f" + message.toString());

        receiver.sendMessage(receiverMessage);
        senderPlayer.sendMessage(senderMessage);

        lastMessaged.put(senderPlayer, receiver);
        lastMessaged.put(receiver, senderPlayer);

        return true;
    }

    public Player getLastMessaged(Player player) {
        return lastMessaged.get(player);
    }

    public static Map<Player, Player> getLastMessagedMap() {
        return lastMessaged;
    }
}
