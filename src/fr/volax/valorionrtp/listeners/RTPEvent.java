package fr.volax.valorionrtp.listeners;

import fr.volax.valorionrtp.utils.CooldownHandler;
import fr.volax.valorionrtp.RandomTPMain;
import fr.volax.valorionrtp.utils.TeleportHandler;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class RTPEvent implements Listener {
    RandomTPMain plugin;

    public RTPEvent(){
        this.plugin = plugin;
    }

    @EventHandler
    public void OnSignCreate(SignChangeEvent e){
        Block b = e.getBlock();
        Material blockType = b.getType();
        Sign sign = (Sign) b.getState();

        if((blockType.equals(Material.SIGN)) || (blockType.equals(Material.SIGN_POST)) || (blockType.equals(Material.WALL_SIGN))){
            if(e.getLine(0).equalsIgnoreCase("[ValorionRTP]")){
                if(e.getPlayer().hasPermission(RandomTPMain.getMain().getConfig().getString("permissions.rtp-sign-create"))){
                    if((e.getLine(1) == null || e.getLine(1).equals(" "))){
                        e.getPlayer().sendMessage(RandomTPMain.getMain().prefix + "§cSetup une pancarte: \n" + RandomTPMain.getMain().prefix + "§cLigne 1 : [ValorionRTP]\n" + RandomTPMain.getMain().prefix + "§cLigne 2: Le nom du monde(ou default pour le monde par défaut dans la config)");
                        e.setCancelled(true);
                        b.breakNaturally();
                        return;
                    }else if((Bukkit.getWorld(e.getLine(1)) == null) && (!e.getLine(1).equalsIgnoreCase("default"))){
                        e.getPlayer().sendMessage(RandomTPMain.getMain().prefix + "§cSetup une pancarte: \n" + RandomTPMain.getMain().prefix + "§cLigne 1 : [ValorionRTP]\n" + RandomTPMain.getMain().prefix + "§cLigne 2: Le nom du monde(ou default pour le monde par défaut dans la config)");
                        e.setCancelled(true);
                        b.breakNaturally();
                        return;
                    }
                    e.getPlayer().sendMessage(RandomTPMain.getMain().prefix + "§aVous avez créer avec succès une pancarte RandomTP dans le monde: §2" + e.getLine(1));
                    e.setLine(0, "§b=-=-=-=-=-=-=-=-");
                    e.setLine(2, "§a" + e.getLine(1));
                    e.setLine(1, "§a§lValorionRTP");
                    e.setLine(3, "§b=-=-=-=-=-=-=-=-");
                }else{
                    e.setCancelled(true);
                    b.breakNaturally();
                    e.getPlayer().sendMessage(RandomTPMain.getMain().prefix + RandomTPMain.getMain().getConfig().getString("messages.rtp.noperm").replaceAll("&","§"));
                    return;
                }

            }else{
                return;
            }
        }
    }

    @EventHandler
    public void onInteractEvent(PlayerInteractEvent e){
        if ((!e.isCancelled()) && (e.getAction().equals(Action.RIGHT_CLICK_BLOCK))) {
            Player p = e.getPlayer();
            Block block = e.getClickedBlock();
            Material blockType = block.getType();
            if ((blockType.equals(Material.SIGN)) || (blockType.equals(Material.SIGN_POST)) || (blockType.equals(Material.WALL_SIGN))) {
                Sign sign = (Sign)block.getState();
                String line = sign.getLine(1);
                if (line.equalsIgnoreCase("§a§lValorionRTP")) {
                    if(e.getPlayer().hasPermission(RandomTPMain.getMain().getConfig().getString("permissions.rtp-sign-create"))){
                        if(RandomTPMain.getMain().getConfig().getBoolean("rtp.cooldown-activer") && (!CooldownHandler.areTherePlayersInTheMap())){
                            CooldownHandler cooldown = CooldownHandler.getCooldown(p);
                            if(!cooldown.check(p) && (cooldown.getTimeLeft(p) * -1L >= 1L)){
                                p.sendMessage(RandomTPMain.getMain().prefix + RandomTPMain.getMain().getConfig().getString("messages.rtp.error").replaceAll("&","§").replaceAll("%cooldown%", String.valueOf(cooldown.getTimeLeft(p) * -1L)));
                                return;
                            }else if(cooldown.getTimeLeft(p) * -1L == 0L){
                                p.sendMessage(RandomTPMain.getMain().prefix + RandomTPMain.getMain().getConfig().getString("messages.rtp.canteleport"));
                                cooldown.finalize();
                            }else{
                                p.sendMessage(RandomTPMain.getMain().prefix + RandomTPMain.getMain().getConfig().getString("messages.rtp.canteleport"));
                                cooldown.finalize();
                            }
                        }
                        World w = e.getClickedBlock().getWorld();
                        if (sign.getLine(2).equalsIgnoreCase("default")) {
                            w = Bukkit.getWorld(RandomTPMain.getMain().getConfig().getString("rtp.world"));
                        }else if (((sign.getLine(2) != null) || (sign.getLine(2) == "")) && (!sign.getLine(2).equalsIgnoreCase("default")) && (!sign.getLine(2).equalsIgnoreCase("sign"))) {
                            w = Bukkit.getWorld(sign.getLine(2));
                        }else if ((sign.getLine(2) == null) || (sign.getLine(2) == "")) {
                            w = p.getWorld();
                        }
                        TeleportHandler tp = new TeleportHandler(p, w, RandomTPMain.getMain().getConfig().getInt("rtp.x"), RandomTPMain.getMain().getConfig().getInt("rtp.z"));
                        tp.teleport();
                        p.sendMessage(tp.getMessage());
                        if(RandomTPMain.getMain().getConfig().getBoolean("rtp.cooldown-activer")){
                            CooldownHandler cooldown = new CooldownHandler(this.plugin, p,  RandomTPMain.getMain().getConfig().getInt("rtp.cooldown-temps"));
                            cooldown.start();
                        }
                    }else{
                        e.getPlayer().sendMessage(RandomTPMain.getMain().prefix + RandomTPMain.getMain().getConfig().getString("messages.rtp.noperm2").replaceAll("&","§"));
                    }
                }
            }
        }
    }
}
