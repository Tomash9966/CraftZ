package io.github.tomash9966.CraftZ.listeners;

import io.github.tomash9966.CraftZ.CraftZPlugin;

import org.bukkit.event.Listener;

public class PlayerInteractListener implements Listener{

	CraftZPlugin plugin;

	public PlayerInteractListener(PlayerInteractListener playerdeath, CraftZPlugin plugin) {

		this.plugin = plugin;

	}

}
