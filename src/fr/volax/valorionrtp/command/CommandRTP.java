package fr.volax.valorionrtp.command;

import fr.volax.valorionrtp.utils.CooldownHandler;
import fr.volax.valorionrtp.RandomTPMain;
import fr.volax.valorionrtp.utils.TeleportHandler;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandRTP implements CommandExecutor {
    RandomTPMain plugin;
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
        if(sender instanceof Player){
            Player player = (Player)sender;
            if(player.hasPermission(RandomTPMain.getMain().getConfig().getString("permissions.rtp-teleport"))){
                if(args.length > 0){
                    if(args.length == 1){
                        if(args[0].equalsIgnoreCase("reload")){
                            if(player.hasPermission(RandomTPMain.getMain().getConfig().getString("permissions.rtp-reloadconfig"))){
                                RandomTPMain.getMain().reloadConfig();
                                sender.sendMessage(RandomTPMain.getMain().prefix +  RandomTPMain.getMain().getConfig().getString("messages.reload").replaceAll("&","§"));
                                return true;
                            }else{
                                player.sendMessage(RandomTPMain.getMain().prefix + RandomTPMain.getMain().getConfig().getString("messages.rtp.help").replaceAll("&","§"));
                            }
                        }
                    }else {
                        player.sendMessage(RandomTPMain.getMain().prefix + RandomTPMain.getMain().getConfig().getString("messages.rtp.help").replaceAll("&","§"));
                    }
                }else if(RandomTPMain.getMain().getConfig().getBoolean("rtp.cooldown-activer") && (!CooldownHandler.areTherePlayersInTheMap())){
                    CooldownHandler cooldown = CooldownHandler.getCooldown(player);
                    if(!cooldown.check(player) && (cooldown.getTimeLeft(player) * -1L >= 1L)){
                        player.sendMessage(RandomTPMain.getMain().prefix + RandomTPMain.getMain().getConfig().getString("messages.rtp.error").replaceAll("&","§").replaceAll("%cooldown%", String.valueOf(cooldown.getTimeLeft(player) * -1L)));
                        return true;
                    }else if(cooldown.getTimeLeft(player) * -1L == 0L){
                        player.sendMessage(RandomTPMain.getMain().prefix + RandomTPMain.getMain().getConfig().getString("messages.rtp.canteleport"));
                        cooldown.finalize();
                    }else{
                        player.sendMessage(RandomTPMain.getMain().prefix + RandomTPMain.getMain().getConfig().getString("messages.rtp.canteleport"));
                        cooldown.finalize();
                    }
                }
                TeleportHandler tp = new TeleportHandler(player, Bukkit.getWorld(RandomTPMain.getMain().getConfig().getString("rtp.world")), RandomTPMain.getMain().getConfig().getInt("rtp.x"), RandomTPMain.getMain().getConfig().getInt("rtp.z"));
                tp.teleport();
                player.sendMessage(tp.getMessage());
                if(RandomTPMain.getMain().getConfig().getBoolean("rtp.cooldown-activer")){
                    CooldownHandler cooldown = new CooldownHandler(this.plugin, player,  RandomTPMain.getMain().getConfig().getInt("rtp.cooldown-temps"));
                    cooldown.start();
                }
            }
        }else{
            sender.sendMessage(RandomTPMain.getMain().getConfig().getString("messages.noplayer").replaceAll("&","§"));
            return false;
        }
        return false;
    }
}
