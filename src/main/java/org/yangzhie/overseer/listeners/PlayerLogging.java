package org.yangzhie.overseer.listeners;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.yangzhie.overseer.data.PlayerData;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class PlayerLogging implements Listener {
  static HashMap<UUID, List<PlayerData>> players = new HashMap<>();

  /*
    Logs player log in
  */
  @EventHandler
  public void PlayerLogIn(PlayerJoinEvent event) {
    // Get player + UUID + time
    Player player = event.getPlayer();
    UUID id = player.getUniqueId();
    LocalDateTime time = LocalDateTime.now();

    // Instantiate playerdata object
    PlayerData data = new PlayerData(player, time, "login");

    // Case: no UUID recorded
    if (!players.containsKey(id)) {
      players.put(id, new ArrayList<>());
    }

    // Get player's list out of the map via UUID
    // e.g. history = [LOGIN 9am, LOGOUT 10am]
    List<PlayerData> history = players.get(id);

    // Add new event to player's list
    // e.g. history = [LOGIN 9am, LOGOUT 10am, LOGIN 12pm]
    history.add(data);

    // Bukkit.getLogger().info(player.getName() + " " + "history: ");
    // for(PlayerData entry : history) {
    //   Bukkit.getLogger().info(entry.eventType + " at " + entry.time);
    // }
  }

  /*
    Logs player log out
  */
  @EventHandler
  public void PlayerLogOut(PlayerQuitEvent event) {
    // Get player + UUID + time
    Player player = event.getPlayer();
    UUID id = player.getUniqueId();
    LocalDateTime time = LocalDateTime.now();

    // Instantiate playerdata object
    PlayerData data = new PlayerData(player, time, "logout");

    // Case: no UUID recorded
    if (!players.containsKey(id)) {
      players.put(id, new ArrayList<>());
    }

    // Get player's list out of the map via UUID
    // e.g. history = [LOGIN 9am, LOGOUT 10am]
    List<PlayerData> history = players.get(id);

    // Add new event to player's list
    // e.g. history = [LOGIN 9am, LOGOUT 10am, LOGIN 12pm]
    history.add(data);
  }
}
