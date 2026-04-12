package org.yangzhie.overseer.utils;

import java.sql.SQLException;
import java.time.Duration;
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

    if(eventType.equals("login")) {
      // Instantiate playerdata object
      PlayerData loginData = new PlayerData(player, time, "login");

      // Insert data into database
      try {
        Overseer.db.insertLog(loginData);
      } catch (SQLException ex) {
        Bukkit.getLogger().severe("Failed to insert log: " + ex.getMessage());
      }
    } else if (eventType.equals("logout")) {
      // Obtain session + logout data
      LocalDateTime lastLogin = Overseer.db.getLastLogin(player.getUniqueId());
      Duration session = Duration.between(lastLogin, time);
      PlayerData logoutData = new PlayerData(player, time, "logout", session);

      try {
        // Insert logoutData into DB
        Overseer.db.insertLog(logoutData);
      } catch (SQLException ex) {
        Bukkit.getLogger().severe("Failed to insert log: " + ex.getMessage());
      }

    }

  }
}
