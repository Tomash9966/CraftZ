package io.github.tomash9966.CraftZ.listeners;

import java.util.HashMap;

import io.github.tomash9966.CraftZ.CraftZPlugin;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class PlayerInteractListener implements Listener{

	public HashMap<String, Boolean> loot = new HashMap<String, Boolean>();
	public HashMap<String, String> item = new HashMap<String, String>();

	CraftZPlugin plugin;

	public PlayerInteractListener(CraftZPlugin plugin) {

		this.plugin = plugin;

	}

	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event){

		Player player = event.getPlayer();

		Block block = event.getClickedBlock();

		if(block.getType().equals(Material.CHEST)){

			Chest chest = (Chest) block.getState();

			Action action = event.getAction();

			if(action.equals(Action.LEFT_CLICK_BLOCK)){

				block.setType(Material.AIR);

			}

			if(action.equals(Action.RIGHT_CLICK_BLOCK)){

				if(this.item.containsKey(player.getName())){

					event.setCancelled(true);

					int number = this.plugin.chestdata.addItem(chest, this.item.get(player.getName()));

					player.sendMessage(ChatColor.GREEN + "Chest with item no. " + String.valueOf(number) + " has been set!");

				}

				if(this.loot.containsKey(player.getName())){

					event.setCancelled(true);

					this.plugin.chestdata.addChest(chest);

					player.sendMessage(ChatColor.GREEN + "Chest with loot has been set!");

					this.loot.remove(player.getName());

				}

			}

		}

	}

}
