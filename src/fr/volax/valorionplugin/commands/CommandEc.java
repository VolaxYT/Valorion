package fr.volax.valorionplugin.commands;

import fr.volax.valorionplugin.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandEc implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
        if(sender instanceof Player){
            if(cmd.getName().equalsIgnoreCase("ec")){
                Player p = (Player)sender;
                if(args.length == 0){
                    p.performCommand("customec open");
            }else if(args.length == 1){
                    Player receive = Bukkit.getPlayer(args[0]);
                    if(receive == null){
                        return false;
                    }else{p.performCommand("customec open " + receive.getName()); }
                }
            }
        }else { sender.sendMessage(Main.getMain().getConfig().getString("messages.noplayer").replaceAll("&","§")); return false;}
        return false;
    }
}
