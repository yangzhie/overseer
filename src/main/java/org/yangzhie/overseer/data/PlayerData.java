package org.yangzhie.overseer.data;

import java.time.LocalDateTime;
import java.util.UUID;

import org.bukkit.entity.Player;

public class PlayerData {
  UUID id;
  String playerName;
  LocalDateTime time;
  String eventType; // in/out

  public PlayerData(Player player, LocalDateTime time, String eventType) {
    this.id = player.getUniqueId();
    this.playerName = player.getName();
    this.time = time;
    this.eventType = eventType;
  }
}
