package fr.valorion.asc;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.UUID;

public class AscMain extends JavaPlugin implements Listener {

    public static AscMain main;
    private HashMap<UUID, Long> cooldown;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        cooldown = new HashMap<>();
        main = this;
        Bukkit.getPluginManager().registerEvents(this,this);
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e){
        if(e.getPlayer().hasPermission(this.getConfig().getString("permission-bypass"))){ return; }
        UUID uuid = e.getPlayer().getUniqueId();
        if(cooldown.containsKey(uuid)){
            float time = (System.currentTimeMillis() - cooldown.get(uuid)) / 1000;
            if(time < this.getConfig().getInt("time")){
                e.setCancelled(true);
                e.getPlayer().sendMessage(this.getConfig().getString("message").replaceAll("&","§"));
            }else { cooldown.put(uuid, System.currentTimeMillis()); }
        } else{ cooldown.put(uuid, System.currentTimeMillis()); }
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent e){
        cooldown.remove(e.getPlayer().getUniqueId());
    }
}