package fr.volax.valorionplugin.commands;

import fr.volax.valorionplugin.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandSendPlayerChat implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
        if(cmd.getName().equalsIgnoreCase("sendplayerchat")){
            if(args.length == 0){
                sender.sendMessage(Main.getMain().prefix + Main.getMain().getConfig().getString("messages.sendplayerchat.help").replaceAll("&","§"));
                return false;

            }
            if(args.length == 1){
                sender.sendMessage(Main.getMain().prefix + Main.getMain().getConfig().getString("messages.sendplayerchat.help").replaceAll("&","§"));
                return false;
            }else{
                Player receive = Bukkit.getPlayer(args[0]);
                if(receive != null){
                        StringBuilder bc = new StringBuilder();
                        for(int i = 1; i < args.length; i++){
                            bc.append(args[i] + " ");
                        }
                        receive.sendMessage(bc.toString().replaceAll("&","§"));
                        return false;
                }else{
                    sender.sendMessage(Main.getMain().prefix + Main.getMain().getConfig().getString("messages.valorion.noonline").replaceAll("&","§").replaceAll("%player%",args[0]));
                }
            }
        }
        return false;
    }
}
