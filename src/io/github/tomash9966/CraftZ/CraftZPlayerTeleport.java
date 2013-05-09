package io.github.tomash9966.CraftZ;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
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

	public void randomSpawn(Player player){

		if(this.plugin.config.data.getStringList("data.spawn").size() == 0){

			player.sendMessage(ChatColor.DARK_RED + "There's no spawns. Please inform administrator!");

			Bukkit.getConsoleSender().sendMessage(ChatColor.DARK_RED + "There's no spawns. Please add couple of them.");

		}
		else
		{

			Location spawn = this.plugin.config.randomSpawn(player);

			player.teleport(spawn);

			player.sendMessage(ChatColor.GOLD + "The game has started. Good luck!");

		}

	}

}
