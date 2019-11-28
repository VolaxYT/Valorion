package fr.volax.bungeeswitcher.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerJoinListener implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){
        final Player player = event.getPlayer();
        World w = Bukkit.getServer().getWorld("world");
        double x = -9;
        double y = 122;
        double z = 52;
        Location loc = new Location (w, x, y, z);
        player.teleport(loc);
        player.setBedSpawnLocation(loc);
        player.getInventory().clear();
        player.getInventory().setItem(4, new ItemStack(5822));
    }
}
