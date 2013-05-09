package io.github.tomash9966.CraftZ.listeners;

import io.github.tomash9966.CraftZ.CraftZPlugin;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PlayerDeathListener implements Listener {

	CraftZPlugin plugin;

	public PlayerDeathListener(CraftZPlugin plugin) {

		this.plugin = plugin;

	}

	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent event){

		Player player = event.getEntity();

		this.plugin.teleport.lobbyTeleport(player);

		player.kickPlayer(ChatColor.GOLD + "You have died! Please wait 10 seconds to join.");

	}

}
