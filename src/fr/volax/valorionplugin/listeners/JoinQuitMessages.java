package fr.volax.valorionplugin.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class JoinQuitMessages implements Listener {

    @EventHandler
    public void OnJoinMessage(PlayerJoinEvent event){
        event.setJoinMessage(null);
    }

    @EventHandler
    public void onQuitMessage(PlayerQuitEvent event){
        event.setQuitMessage(null);
    }
}
