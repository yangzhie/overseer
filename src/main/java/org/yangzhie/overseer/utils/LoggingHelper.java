package org.yangzhie.overseer.utils;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.yangzhie.overseer.Overseer;
import org.yangzhie.overseer.data.PlayerData;

public class LoggingHelper {
  public void logHelper(Player player, String eventType) {
    // Get time
    LocalDateTime time = LocalDateTime.now();

    // Instantiate playerdata object
    PlayerData data = new PlayerData(player, time, "login");

    // Insert data into database
    try {
      Overseer.db.insertLog(data);
    } catch (SQLException ex) {
      Bukkit.getLogger().severe("Failed to insert log: " + ex.getMessage());
    }
  }
}
