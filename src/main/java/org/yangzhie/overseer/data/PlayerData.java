package org.yangzhie.overseer.data;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.UUID;

import org.bukkit.entity.Player;

public class PlayerData {
  public UUID id;
  public String playerName;
  public String eventType; // in/out/session
  public LocalDateTime time;
  public Duration session;

  public PlayerData(Player player, LocalDateTime time, String eventType) {
    this.id = player.getUniqueId();
    this.playerName = player.getName();
    this.time = time;
    this.eventType = eventType;
    this.session = null;
  }

  public PlayerData(Player player, LocalDateTime time, String eventType, Duration session) {
    this.id = player.getUniqueId();
    this.playerName = player.getName();
    this.time = time;
    this.eventType = eventType;
    this.session = session;
  }
}
