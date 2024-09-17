package me.Mini.miniEssentials.msg;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class replyCommand implements CommandExecutor {

    private final msgCommand msgCommand;

    public replyCommand(msgCommand msgCommand) {
        this.msgCommand = msgCommand;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player senderPlayer)) {
            sender.sendMessage("This command can only be executed by a player!");
            return true;
        }

        Player lastMessaged = msgCommand.getLastMessaged(senderPlayer);

        if (lastMessaged == null) {
            senderPlayer.sendMessage(ChatColor.RED + "You have no one to reply to.");
            return true;
        }

        if (args.length < 1) {
            senderPlayer.sendMessage(ChatColor.RED + "Usage: /reply <message>");
            return true;
        }

        String message = String.join(" ", args);
        String senderMessage = ChatColor.translateAlternateColorCodes('&', "&8[&6Me &a-> &e" + lastMessaged.getName() + "&8]: &f" + message);
        String receiverMessage = ChatColor.translateAlternateColorCodes('&', "&8[&6" + senderPlayer.getName() + " &a-> &eMe&8]: &f" + message);

        lastMessaged.sendMessage(receiverMessage);
        senderPlayer.sendMessage(senderMessage);

        // Update the last messaged player for the recipient
        msgCommand.getLastMessagedMap().put(senderPlayer, lastMessaged);
        msgCommand.getLastMessagedMap().put(lastMessaged, senderPlayer);

        return true;
    }
}
