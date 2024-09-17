package me.Mini.miniEssentials.other;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class flyCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage("This command can only be executed by a player!");
        }
        Player player;
        if (args.length == 0) {
            player = (Player) commandSender;
        } else {
            try {
                player = Bukkit.getPlayer(args[0]);
            } catch (Exception e) {
                e.printStackTrace();
                assert commandSender instanceof Player;
                player = (Player) commandSender;
            }
        }
        assert player != null;
        if (player.getAllowFlight()) {
            player.setAllowFlight(false);
            player.sendMessage(player.getName() +"'s flight er slået " + ChatColor.RED +"fra");
        } else {
            player.setAllowFlight(true);
            player.sendMessage(player.getName() +"'s flight er slået " + ChatColor.GREEN +"til");
        }



        return true;
    }
}
