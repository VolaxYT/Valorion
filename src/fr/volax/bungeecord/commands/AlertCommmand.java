package fr.volax.bungeecord.commands;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Command;

public class AlertCommmand extends Command {
    public AlertCommmand(){
        super("galert");
    }
    @Override
    public void execute(CommandSender commandSender, String[] args){
            if(args.length==0){
                commandSender.sendMessage("§cArgument invalide !");
            }else {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < args.length; i++) {
                    sb.append(args[i]).append(" ");
                }
                String message = sb.toString().trim();
                ProxyServer.getInstance().broadcast("§f[§aValorion§f]§r" + ChatColor.translateAlternateColorCodes('&', message));
            }
    }
}
