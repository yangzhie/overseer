package org.yangzhie.overseer;

import org.bukkit.plugin.java.JavaPlugin;
import org.yangzhie.overseer.listeners.PlayerLogging;

public final class Overseer extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(new PlayerLogging(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
