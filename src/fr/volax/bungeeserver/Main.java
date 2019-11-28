package fr.volax.bungeeserver;

import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.api.plugin.PluginManager;

public class Main extends Plugin {
    @Override
    public void onEnable() {
        PluginManager pm = getProxy().getPluginManager();
        pm.registerCommand(this, new CommandHub("hub"));
    }

    @Override
    public void onDisable() {}

}
