package fr.volax.valorionrtp.utils;

import fr.volax.valorionrtp.RandomTPMain;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class CooldownHandler {
    RandomTPMain plugin;
    Player player = null;
    int coolDownTime = -1;
    Map<String, Long> map = new HashMap();
    private static Map<String, CooldownHandler> cooldownMap = new HashMap();

    public CooldownHandler(RandomTPMain plugin, Player player, int coolDownTime){
        this.plugin = plugin;
        this.player = player;
        this.coolDownTime = coolDownTime;
    }

    public void start(){
        this.map.put(this.player.getUniqueId().toString(), System.currentTimeMillis());
        cooldownMap.put(this.player.getUniqueId().toString(), this);
    }

    public void finalize(){
        this.map.remove(this.player.getUniqueId().toString());
        cooldownMap.remove(this.player.getUniqueId().toString());
    }

    public long getTimeLeft(Player player) {
        long startTime = (System.currentTimeMillis() - (Long) this.map.get(player.getUniqueId().toString())) / 1000L;
        return startTime - this.coolDownTime;
    }

    public boolean check(Player player) {
        if (((Long) this.map.get(player.getUniqueId().toString()) - System.currentTimeMillis()) / 1000L < this.coolDownTime) {
            return false;
        }
        return true;
    }

    public static CooldownHandler getCooldown(Player player) {
        return (CooldownHandler)cooldownMap.get(player.getUniqueId().toString());
    }

    public static boolean areTherePlayersInTheMap() {
        return cooldownMap.isEmpty();
    }
}
