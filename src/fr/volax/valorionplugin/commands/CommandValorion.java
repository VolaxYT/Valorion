package fr.volax.valorionplugin.commands;

import fr.volax.valorionplugin.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CommandValorion implements CommandExecutor {
    public void valorionhelp(CommandSender sender){
        sender.sendMessage(Main.getMain().prefix + Main.getMain().getConfig().getString("messages.valorion.helpperms.help").replaceAll("&","§"));
        sender.sendMessage(Main.getMain().prefix + Main.getMain().getConfig().getString("messages.valorion.helpperms.help2").replaceAll("&","§"));
        sender.sendMessage(Main.getMain().prefix + Main.getMain().getConfig().getString("messages.valorion.helpperms.help3").replaceAll("&","§"));
        sender.sendMessage(Main.getMain().prefix + Main.getMain().getConfig().getString("messages.valorion.helpperms.help4").replaceAll("&","§"));
        sender.sendMessage(Main.getMain().prefix + Main.getMain().getConfig().getString("messages.valorion.helpperms.help5").replaceAll("&","§"));
        sender.sendMessage(Main.getMain().prefix + Main.getMain().getConfig().getString("messages.valorion.helpperms.help6").replaceAll("&","§"));
        sender.sendMessage(Main.getMain().prefix + Main.getMain().getConfig().getString("messages.valorion.helpperms.help7").replaceAll("&","§"));
    }

    public void noperm(CommandSender sender){sender.sendMessage(Main.getMain().prefix + Main.getMain().getConfig().getString("permissions.noperm").replaceAll("&","§")); }
    public void help(CommandSender sender){sender.sendMessage(Main.getMain().prefix + Main.getMain().getConfig().getString("messages.valorion.help.help").replaceAll("&","§")); }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
        if(cmd.getName().equalsIgnoreCase("valorionpl")){
            if(sender.hasPermission(Main.getMain().getConfig().getString("permissions.valorion"))){
                if(args.length == 0){
                    help(sender);
                    return false;
                }else if(args.length == 1) {
                    if (args[0].equalsIgnoreCase("help")) {
                        if (sender.hasPermission(Main.getMain().getConfig().getString("permissions.valorion"))) {
                            valorionhelp(sender);
                            return false;
                        } else {
                            noperm(sender);
                            return false;
                        }
                    } else if (args[0].equalsIgnoreCase("reload")) {
                        if (!sender.hasPermission(Main.getMain().getConfig().getString("permissions.valorionreload"))) {
                            noperm(sender);
                            return false;
                        }
                        try {
                            Main.getMain().reloadConfig();
                            sender.sendMessage(Main.getMain().prefix + Main.getMain().getConfig().getString("messages.reload").replaceAll("&", "§"));
                            return false;
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
                    }else{
                        help(sender);
                        return false;
                    }
                }else if(args.length == 2){
                    if(args[0].equalsIgnoreCase("permissions") && args[1].equalsIgnoreCase("help")){
                        if(sender.hasPermission(Main.getMain().getConfig().getString("permissions.valorionhelpperms"))){
                            valorionhelp(sender);
                            return false;
                        }else{
                            noperm(sender);
                            return false;
                        }
                    }else{
                        help(sender);
                        return false;
                    }
                }else if(args.length == 3){
                    return false;
            }else{
        noperm(sender);
        return false;
    }
        return false;
    }
}
