package fr.volax.valorionrtp;

import fr.volax.valorionrtp.command.CommandRTP;
import fr.volax.valorionrtp.listeners.RTPEvent;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.UUID;
/**
 * RandomTP for Valorion Server
 * @author Volax
 * @company Valorion
 */
public class RandomTPMain extends JavaPlugin {
    public static RandomTPMain main;
    public HashMap<UUID, Long> cooldown;
    public String prefix = "§6Valorion »";

    @Override
    public void onEnable() {
        registers();
    }

    private void registers(){
        saveDefaultConfig();
        registerEvents();
        registerCommands();
        main = this;
        cooldown = new HashMap();
    }

    private void registerCommands() {
        getCommand("rtp").setExecutor(new CommandRTP());
    }

    private void registerEvents() {
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new RTPEvent(), this);
    }

    public static RandomTPMain getMain(){
        return main;
    }
}
