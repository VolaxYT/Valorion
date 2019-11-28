package fr.volax.valorionplugin.commands;

import fr.volax.valorionplugin.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class CommandXPBottle implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
        if(sender instanceof Player){
            Player p = (Player)sender;
            if(cmd.getName().equalsIgnoreCase("xpbottle")){
                if(p.hasPermission(Main.getMain().getConfig().getString("permissions.xpbottle"))){
                    int xplevel = p.getLevel();
                    Inventory inv = p.getInventory();
                    if(args.length == 0){
                        if(xplevel <= 0){
                            p.sendMessage(Main.getMain().prefix + Main.getMain().getConfig().getString("messages.xpbottle.nolevel").replaceAll("&","§"));
                            return false;
                        }else {
                            ItemStack xpbottle = new ItemStack(Material.EXP_BOTTLE, xplevel);
                            ItemMeta xpbottleM = xpbottle.getItemMeta();
                            xpbottleM.setDisplayName(Main.getMain().getConfig().getString("messages.xpbottle.displayname").replaceAll("&","§"));
                            xpbottleM.setLore(Arrays.asList(Main.getMain().getConfig().getString("messages.xpbottle.lore").replaceAll("&","§")));
                            xpbottle.setItemMeta(xpbottleM);
                            inv.addItem(xpbottle);
                            p.setLevel(0);
                            p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 10, 1);
                        }
                    }else if (args.length == 1){
                        if(Main.getMain().isInt(args[0])){
                            int xpd = Integer.parseInt(args[0]);
                            if(xpd > xplevel){
                                p.sendMessage(Main.getMain().prefix + Main.getMain().getConfig().getString("messages.xpbottle.nolevel").replaceAll("&","§"));
                            }else{
                                ItemStack xpbottle = new ItemStack(Material.EXP_BOTTLE, xpd);
                                ItemMeta xpbottleM = xpbottle.getItemMeta();
                                xpbottleM.setDisplayName(Main.getMain().getConfig().getString("messages.xpbottle.displayname").replaceAll("&","§"));
                                xpbottleM.setLore(Arrays.asList(Main.getMain().getConfig().getString("messages.xpbottle.lore").replaceAll("&","§")));
                                xpbottle.setItemMeta(xpbottleM);
                                inv.addItem(xpbottle);
                                p.setLevel(xplevel - xpd);
                                p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 10, 1);
                            }
                        }else if(args[0].equalsIgnoreCase("give")){
                            if(p.hasPermission(Main.getMain().getConfig().getString("permissions.xpbottlegive").replaceAll("&","§"))) {
                                p.sendMessage(Main.getMain().prefix + Main.getMain().getConfig().getString("messages.xpbottle.helpgive").replaceAll("&","§"));
                            } else {
                                sender.sendMessage(Main.getMain().prefix + Main.getMain().getConfig().getString("permissions.noperm").replaceAll("&","§"));
                            }
                            }else {
                                p.sendMessage(Main.getMain().prefix + Main.getMain().getConfig().getString("messages.xpbottle.nonumber").replaceAll("&","§"));
                        }
                    }else if(args[0].equalsIgnoreCase("give") && args.length == 2){
                        if(Main.getMain().isInt(args[1])){
                            ItemStack xpbottle = new ItemStack(Material.EXP_BOTTLE, Integer.parseInt(args[1]));
                            ItemMeta xpbottleM = xpbottle.getItemMeta();
                            xpbottleM.setDisplayName(Main.getMain().getConfig().getString("messages.xpbottle.displayname").replaceAll("&","§"));
                            xpbottleM.setLore(Arrays.asList(Main.getMain().getConfig().getString("messages.xpbottle.lore").replaceAll("&","§")));
                            xpbottle.setItemMeta(xpbottleM);
                            inv.addItem(xpbottle);
                            p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 10, 1);
                        }else{
                            p.sendMessage(Main.getMain().prefix + Main.getMain().getConfig().getString("messages.xpbottle.nonumber").replaceAll("&","§"));
                        }

                    }else if(args[0].equalsIgnoreCase("give") && args.length == 3){
                        if(Main.getMain().isInt(args[1])) {
                            Player receive = Bukkit.getPlayer(args[2]);
                            if(receive != null){
                                ItemStack xpbottle = new ItemStack(Material.EXP_BOTTLE, Integer.parseInt(args[1]));
                                ItemMeta xpbottleM = xpbottle.getItemMeta();
                                xpbottleM.setDisplayName(Main.getMain().getConfig().getString("messages.xpbottle.displayname").replaceAll("&", "§"));
                                xpbottleM.setLore(Arrays.asList(Main.getMain().getConfig().getString("messages.xpbottle.lore").replaceAll("&", "§")));
                                xpbottle.setItemMeta(xpbottleM);
                                receive.getInventory().addItem(xpbottle);
                                p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 10, 1);
                            }else{
                                sender.sendMessage(Main.getMain().prefix + Main.getMain().getConfig().getString("messages.valorion.noonline").replaceAll("&","§").replaceAll("%player%",args[2]));
                            }
                        }else{
                            p.sendMessage(Main.getMain().prefix + Main.getMain().getConfig().getString("messages.xpbottle.nonumber").replaceAll("&","§"));
                        }
                    }else{
                        p.sendMessage(Main.getMain().prefix + Main.getMain().getConfig().getString("messages.xpbottle.help").replaceAll("&","§"));
                    }
                }else{
                    sender.sendMessage(Main.getMain().prefix + Main.getMain().getConfig().getString("permissions.noperm").replaceAll("&","§"));
                    return false;
                }
            }
        }else{
            sender.sendMessage(Main.getMain().getConfig().getString("messages.noplayer").replaceAll("&","§"));
        }
        return false;
    }
}
