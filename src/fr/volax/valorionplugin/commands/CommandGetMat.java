package fr.volax.valorionplugin.commands;

import fr.volax.valorionplugin.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class CommandGetMat implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
        if(!(sender instanceof Player)){ sender.sendMessage(Main.getMain().getConfig().getString("messages.noplayer").replaceAll("&","§")); return false;}
            Player p = (Player)sender;
            if (cmd.getName().equalsIgnoreCase("getmat")) {
                if(p.hasPermission(Main.getMain().getConfig().getString("permissions.getmat"))){
                    ItemStack stack = p.getItemInHand();
                    p.sendMessage(Main.getMain().prefix + "§r" +stack.getType().name());
            }else{
                    sender.sendMessage(Main.getMain().prefix + Main.getMain().getConfig().getString("permissions.noperm").replaceAll("&","§"));
                    return false;
                }
        }
        return false;
    }
}
