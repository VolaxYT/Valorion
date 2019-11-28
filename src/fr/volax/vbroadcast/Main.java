package fr.volax.vbroadcast;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    @Override
    public void onEnable() {
        this.getCommand("vbroadcast").setExecutor(new CommandVBroadcast());
    }
}
