package fr.volax.bungeeserver;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class CommandHub extends Command {

    public CommandHub(String name) {
        super(name, "hub.teleport", "lobby", "cc");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if(sender instanceof ProxiedPlayer) {
            ProxiedPlayer player = (ProxiedPlayer) sender;
            if ( player.getServer().getInfo() == ProxyServer.getInstance().getServerInfo("lobby")){
                player.sendMessage(new TextComponent("§cVous êtes déjà connecté au serveur !"));
                return;
            }else {
                player.sendMessage(new TextComponent("§cTéléportation au lobby..."));
                player.connect(ProxyServer.getInstance().getServerInfo("lobby"));
                return;
            }
        }

    }

}

