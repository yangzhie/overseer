package org.yangzhie.overseer;

import java.sql.SQLException;

import org.bukkit.plugin.java.JavaPlugin;
import org.yangzhie.overseer.data.Database;
import org.yangzhie.overseer.listeners.PlayerLogging;

import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;

public final class Overseer extends JavaPlugin {
    public static Database db;
    public static LuckPerms luckAPI;

    @Override
    public void onEnable() {
        // Register plugin
        getServer().getPluginManager().registerEvents(new PlayerLogging(), this);
        // Register LP
        try {
            luckAPI = LuckPermsProvider.get();
        } catch (IllegalStateException ex) {
            getLogger().severe("LuckPerms not hooked: " + ex.getMessage());
        }

        // Create directory
        if (!getDataFolder().exists()) {
            getDataFolder().mkdirs();
        }

        // Establish DB
        db = new Database();
        try {
            db.connect(getDataFolder() + "/playerdata.db");
            db.createTable();
        } catch (SQLException ex) {
            getLogger().severe("Failed to connect to database: " + ex.getMessage());
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
