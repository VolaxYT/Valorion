package fr.volax.valorionplugin.listeners;

import fr.volax.valorionplugin.Main;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityPortalEnterEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.world.PortalCreateEvent;

public class PortalsEvent implements Listener {
    @EventHandler
    public void onPortal(EntityPortalEnterEvent event){
        Player p = (Player) event.getEntity();
        if(!p.isOp()){
            if(!Main.getMain().getConfig().getBoolean("portals.enter-on-portail")){
                Player player = (Player) event.getEntity();
                player.performCommand("kick " + player.getName() + " §4§lAuto kick reason : Want to bypass nether / end portal");
                player.teleport(new Location(player.getWorld(), player.getLocation().getX() - 4, player.getLocation().getY(), player.getLocation().getZ() - 4));
            }
        }else if(p.isOp() && !Main.getMain().getConfig().getBoolean("portals.op-can-bypass")){
            if(!Main.getMain().getConfig().getBoolean("portals.enter-on-portail")){
                Player player = (Player) event.getEntity();
                player.performCommand("kick " + player.getName() + " §4§lAuto kick reason : Want to bypass nether / end portal");
                player.teleport(new Location(player.getWorld(), player.getLocation().getX() - 4, player.getLocation().getY(), player.getLocation().getZ() - 4));
            }
        }
    }

    @EventHandler
    public void onPortalCreate(PortalCreateEvent event){
        if(!Main.getMain().getConfig().getBoolean("portals.nether")){
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onRightClick(PlayerInteractEvent event){
        Player p = event.getPlayer();
        /**
         * AETHER ADDON - When player tries to right click with a water bucket on block of glowstone
         * if in config the option : 'portals.create-aether' is set to false -> the event is cancelled
         */
        if(event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            if(p.getItemInHand().getType() == Material.WATER_BUCKET){
                if(event.getClickedBlock().getType() == Material.GLOWSTONE){
                    if(!Main.getMain().getConfig().getBoolean("portals.create-aether")) {
                        System.err.println("[PortalsCanceller]" + p.getName() + " tried to place water on glowstone at (Player Location)");
                        System.err.println("[PortalsCanceller]X: " +p.getLocation().getX() + " Y: " +p.getLocation().getY() + " Z: " +p.getLocation().getZ());
                        event.setCancelled(true);
                    }
                }
                /**
                 * When player tries to right click with a eye of ender on ender portal frame
                 * if in config the option: 'portals.create-end' is set to false -> the event is cancelled
                 */
            }else if(p.getItemInHand().getType() == Material.EYE_OF_ENDER){
                if(event.getClickedBlock().getType() == Material.ENDER_PORTAL_FRAME){
                    if(!Main.getMain().getConfig().getBoolean("portals.create-end")) {
                        event.setCancelled(true);
                        System.err.println("[PortalsCanceller]" + p.getName() + " tried to create an End portal at (Player Location)");
                        System.err.println("[PortalsCanceller]X: " +p.getLocation().getX() + " Y: " +p.getLocation().getY() + " Z: " +p.getLocation().getZ());
                    }
                }
                /**
                 * When player tries to right click with a flint and steel on an obsidian block
                 * if in config the option: 'portals.create-nether' is set to false -> the event is cancelled
                 */
            }else if(p.getItemInHand().getType() == Material.FLINT_AND_STEEL) {
                if (event.getClickedBlock().getType() == Material.OBSIDIAN) {
                    if (!Main.getMain().getConfig().getBoolean("portals.create-nether")) {
                        event.setCancelled(true);
                        System.err.println("[PortalsCanceller]" + p.getName() + " tried to place fire on obsidian at (Player Location)");
                        System.err.println("[PortalsCanceller]X: " +p.getLocation().getX() + " Y: " +p.getLocation().getY() + " Z: " +p.getLocation().getZ());
                        return;
                    }
                }
            }
        }
    }

}
