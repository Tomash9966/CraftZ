package io.github.tomash9966.CraftZ;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public class CraftZPlayerTeleport {

	CraftZPlugin plugin;

	public CraftZPlayerTeleport(CraftZPlugin plugin) {

		this.plugin = plugin;

	}
	public void lobbyTeleport(Player player){

		Location lobbyloc = this.plugin.config.getLobby();

		player.teleport(lobbyloc);

	}

}
