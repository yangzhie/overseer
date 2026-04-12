package org.yangzhie.overseer.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

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
          time TEXT
        )
        """;

    // Create SQL statement
    conn.createStatement().execute(sqlString);
  }

  public void insertLog(PlayerData data) throws SQLException {
    String sqlString = """
        INSERT INTO player_logs (uuid, player_name, event_type, time) VALUES (?, ?, ?, ?)
        """;
    
    var statement = conn.prepareStatement(sqlString);
    statement.setString(1, data.id.toString());
    statement.setString(2, data.playerName);
    statement.setString(3, data.eventType);
    statement.setString(4, data.time.toString());
    statement.executeUpdate();
  }
}
