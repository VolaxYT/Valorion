package fr.volax.valorionplugin;

import fr.volax.valorionplugin.commands.*;
import fr.volax.valorionplugin.listeners.*;
import org.bukkit.Bukkit;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    public static Main main;
    public String prefix = "§6Valorion »";

    @Override
    public void onEnable() {
        saveDefaultConfig();
        registerEvents();
        registerCommands();
        main = this;
    }

    public void registerEvents(){
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new JoinQuitMessages(), this);
        pm.registerEvents(new XPBottleClic(), this);
        pm.registerEvents(new PortalsEvent(), this);
    }

    public void registerCommands(){
        getCommand("sendplayerchat").setExecutor(new CommandSendPlayerChat());
        getCommand("valorionpl").setExecutor(new CommandValorion());
        getCommand("getmat").setExecutor(new CommandGetMat());
        getCommand("xpbottle").setExecutor(new CommandXPBottle());
        PluginCommand cmdec = Bukkit.getServer().getPluginCommand("ec");
        if(cmdec.getPlugin().getDescription().getName().equals("CustomEnderChest"))
            cmdec.setExecutor(new CommandEc());

    }

    public static Main getMain(){
        return main;
    }

    public boolean isInt(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
