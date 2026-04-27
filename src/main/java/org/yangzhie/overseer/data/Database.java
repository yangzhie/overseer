package org.yangzhie.overseer.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.UUID;

import org.bukkit.Bukkit;

public class Database {
  private Connection conn;
  
  // Establish SQL connection
  public void connect(String path) throws SQLException {
    conn = DriverManager.getConnection("jdbc:sqlite:" + path);
  }

  // Create SQL table
  public void createTable() throws SQLException {
    String sqlString = """
        CREATE TABLE IF NOT EXISTS player_logs (
          id INTEGER PRIMARY KEY AUTOINCREMENT,
          uuid TEXT,
          player_name TEXT,
          event_type TEXT,
          time TEXT,
          session_seconds INTEGER,
          player_group TEXT
        )
        """;

    // Create SQL statement
    conn.createStatement().execute(sqlString);
  }

  public void insertLog(PlayerData data) throws SQLException {
    String sqlString = """
        INSERT INTO player_logs (uuid, player_name, event_type, time, session_seconds, player_group) VALUES (?, ?, ?, ?, ?, ?)
        """;
    
    var statement = conn.prepareStatement(sqlString);
    statement.setString(1, data.id.toString());
    statement.setString(2, data.playerName);
    statement.setString(3, data.eventType);
    statement.setString(4, data.time.toString());
    statement.setLong(5, data.session != null ? data.session.toSeconds() : 0);
    statement.setString(6, data.playerGroup);
    statement.executeUpdate();
  }

  public LocalDateTime getLastLogin(UUID uuid) {
    String sqlString = """
        SELECT time FROM player_logs WHERE uuid = ? AND event_type = 'login' ORDER BY time DESC LIMIT 1
        """;
    try {
      var statement = conn.prepareStatement(sqlString);
      statement.setString(1, uuid.toString());
      ResultSet rs = statement.executeQuery();
      if (rs.next()) {
        return LocalDateTime.parse(rs.getString("time"));
      }
    } catch (SQLException ex) {
      Bukkit.getLogger().severe("Failed to get last login: " + ex.getMessage());
    }

    return null;
  }
}
