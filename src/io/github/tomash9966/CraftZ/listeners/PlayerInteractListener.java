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

	public HashMap<String, Boolean> addloot = new HashMap<String, Boolean>();
	public HashMap<String, String> additem = new HashMap<String, String>();

	public HashMap<String, Boolean> delloot = new HashMap<String, Boolean>();

	CraftZPlugin plugin;

	public PlayerInteractListener(CraftZPlugin plugin) {

		this.plugin = plugin;

	}

	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event){

		Player player = event.getPlayer();

		if(event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.LEFT_CLICK_AIR)){



		}

		else if(event.getClickedBlock().getType().equals(Material.CHEST) && (event.getAction().equals(Action.LEFT_CLICK_BLOCK) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK))){

			Block block = event.getClickedBlock();

			Chest chest = (Chest) block.getState();

			Action action = event.getAction();

			if(action.equals(Action.LEFT_CLICK_BLOCK)){

				if(this.plugin.chestdata.getChestLastDestroy(block) != 0){

					this.plugin.chestdata.setChestLastDestroy(chest, System.currentTimeMillis());

					block.setType(Material.AIR);

				}

			}

			if(action.equals(Action.RIGHT_CLICK_BLOCK)){

				if(this.additem.containsKey(player.getName())){

					event.setCancelled(true);

					this.plugin.chestdata.addItem(chest, this.additem.get(player.getName()), player);

					this.plugin.chestdata.chestItem(block);

				}

				if(this.addloot.containsKey(player.getName())){

					event.setCancelled(true);

					this.plugin.chestdata.addChest(chest);

					player.sendMessage(ChatColor.GREEN + "Chest with loot has been set!");

					this.addloot.remove(player.getName());

				}

				if(this.delloot.containsKey(player.getName())){

					event.setCancelled(true);

					this.plugin.chestdata.removeChest(chest);

					player.sendMessage(ChatColor.GREEN + "Chest with loot has been removed!");

					block.setType(Material.AIR);

					this.delloot.remove(player.getName());

				}

				if(this.plugin.chestdata.getChestLastDestroy(block) != 0){

					event.setCancelled(true);

					this.plugin.chestdata.setChestLastDestroy(chest, System.currentTimeMillis());

					block.setType(Material.AIR);

				}

			}

		}

	}

}
