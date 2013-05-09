package io.github.tomash9966.CraftZ.listeners;

import io.github.tomash9966.CraftZ.CraftZPlugin;

import org.bukkit.Material;
import org.bukkit.block.Block;
//import org.bukkit.block.Chest;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class PlayerInteractListener implements Listener{

	CraftZPlugin plugin;

	public PlayerInteractListener(CraftZPlugin plugin) {

		this.plugin = plugin;

	}

	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event){

		if(event.getAction().equals(Action.LEFT_CLICK_BLOCK)){

			Block block = event.getClickedBlock();

			if(block.getType().equals(Material.CHEST)){

				//Chest chest = (Chest) block.getState();

				block.setType(Material.AIR);

			}

		}

	}

}
