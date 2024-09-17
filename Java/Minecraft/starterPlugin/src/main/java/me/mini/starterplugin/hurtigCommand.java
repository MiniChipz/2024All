//package me.mini.starterplugin;
//
//import org.bukkit.Bukkit;
//import org.bukkit.command.Command;
//import org.bukkit.command.CommandExecutor;
//import org.bukkit.command.CommandSender;
//import org.bukkit.command.TabCompleter;
//import org.bukkit.entity.Player;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class hurtigCommand implements CommandExecutor, TabCompleter {
//    @Override
//    public boolean onCommand(CommandSender sender, Command theCommand, String label, String[] args) {
//        if (!(sender instanceof Player)) return false;
//        if (!(args[0] == null)) return false;
//        Player player = (Player) sender;
//        Player argen = (Player) args[0]
//        player.sendMessage("Du skrev en command" + player.getName());
//        return true;
//    }
//
//    @Override
//    public List<String> onTabComplete(CommandSender sender, Command theCommand, String string, String[] args) {
//        if (theCommand.getName().equalsIgnoreCase("test")) {
//            List<String> list = new ArrayList<String>();
//            for (Player p : Bukkit.getOnlinePlayers()) {
//                list.add(p.getName());
//            }
//            return list;
//        }
//        return null;
//    }
//}