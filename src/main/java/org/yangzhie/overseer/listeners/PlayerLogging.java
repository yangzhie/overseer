package org.yangzhie.overseer.listeners;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.UUID;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.entity.Player;

public class PlayerLogging implements Listener {
  static HashMap<UUID, PlayerData> players = new HashMap<>();

  /*
    Logs player log in
  */
  @EventHandler
  public void PlayerLogIn(PlayerJoinEvent event) {
    // Get player of event
    Player player = event.getPlayer();

    // Get UUID of player
    UUID id = player.getUniqueId();

    // Get log in time
    LocalDateTime playerLogInTime;

    // Create player data object
    PlayerData data = new PlayerData(player, playerLogInTime);

    // Log info into HashMap
    players.put(id, data);
  }

  /*
    Logs player log out
  */
  @EventHandler
  public void PlayerLogOut(PlayerQuitEvent event) {
    // Get player
    Player player = event.getPlayer();

    // Get player UUID
    UUID id = player.getUniqueId();

    // Get log out time
    LocalDateTime playerLogOutTime;

    // Create player data object and fill with info
    PlayerData data = new PlayerData(player, playerLogOutTime);

    // Log info into HashMap
    players.put(id, data);
  }
}
