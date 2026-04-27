package org.yangzhie.overseer.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.yangzhie.overseer.utils.LoggingHelper;
import org.yangzhie.overseer.utils.LuckpermsHelper;

public class PlayerLogging implements Listener {
  LoggingHelper helper = new LoggingHelper();
  LuckpermsHelper lpHelper = new LuckpermsHelper();

  /*
    Logs player log in
  */
  @EventHandler
  public void playerLogIn(PlayerJoinEvent event) {
    helper.logHelper(event.getPlayer(), "login");
  }

  /*
    Logs player log out
  */
  @EventHandler
  public void playerLogOut(PlayerQuitEvent event) {
    helper.logHelper(event.getPlayer(), "logout");
  }
}
