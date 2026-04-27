package org.yangzhie.overseer.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.yangzhie.overseer.Overseer;

import net.luckperms.api.model.group.Group;
import net.luckperms.api.model.user.User;

public class LuckpermsHelper {
  // Get all revlevant LP groups
  public List<String> getAllGroups() {
    // Get raw groups
    Set<Group> groups = Overseer.luckAPI.getGroupManager().getLoadedGroups();

    // Obtain individual string groups
    List<String> groupNames = new ArrayList<>();
    for(Group group : groups) {
      groupNames.add(group.getName());
    }

    return groupNames;
  }

  // Get the primary LP group of user
  public String getUserPrimaryGroup(UUID uuid) {
    User user = Overseer.luckAPI.getUserManager().getUser(uuid);
    String group = user.getPrimaryGroup();
    return group;
  } 
}
