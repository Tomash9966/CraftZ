package io.github.tomash9966.CraftZ;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class CraftZChestData {

	CraftZPlugin plugin;

	YamlConfiguration chestdata;

	public CraftZChestData(CraftZPlugin plugin){

		this.plugin = plugin;
		this.chestdata = YamlConfiguration.loadConfiguration(new File(this.plugin.getDataFolder(), "chest.yml"));

	}

    public void saveData(){
    	try{

    		this.chestdata.save(new File(this.plugin.getDataFolder(), "chest.yml"));

    	}
    	catch (Exception e){

    		System.out.println("Error while trying to save data!");

    	}

	}

    public void addItem(Chest chest, String item, Player player){

		String world = chest.getWorld().getName();

		int x = chest.getX();
		int y = chest.getY();
		int z = chest.getZ();

		if(this.chestdata.isConfigurationSection("chestdata." + world + ":" + String.valueOf(x) + ":" + String.valueOf(y) + ":" + String.valueOf(z))){

			if(this.chestdata.isList("chestdata." + world + ":" + String.valueOf(x) + ":" + String.valueOf(y) + ":" + String.valueOf(z) + ".items")){

				List<String> items = this.chestdata.getStringList("chestdata." + world + ":" + String.valueOf(x) + ":" + String.valueOf(y) + ":" + String.valueOf(z) + ".items");

				items.add(item);

				this.chestdata.set("chestdata." + world + ":" + String.valueOf(x) + ":" + String.valueOf(y) + ":" + String.valueOf(z) + ".items", items);

				saveData();

				int number = items.size();

				player.sendMessage(ChatColor.GREEN + "Chest with item no. " + String.valueOf(number) + " has been set!");

				this.plugin.playerinteract.additem.remove(player.getName());

			}
			else
			{

				List<String> items = new ArrayList<String>();

				items.add(item);

				this.chestdata.set("chestdata." + world + ":" + String.valueOf(x) + ":" + String.valueOf(y) + ":" + String.valueOf(z) + ".items", items);

				saveData();

				int number = items.size();

				player.sendMessage(ChatColor.GREEN + "Chest with item no. " + String.valueOf(number) + " has been set!");

				this.plugin.playerinteract.additem.remove(player.getName());

			}

		}
		else
		{

			player.sendMessage(ChatColor.RED + "This chest hasn't be set!");

			this.plugin.playerinteract.additem.remove(player.getName());

		}

    }

	public void	addChest(Chest chest){

		String world = chest.getWorld().getName();

		int x = chest.getX();
		int y = chest.getY();
		int z = chest.getZ();

		this.chestdata.createSection("chestdata." + world + ":" + String.valueOf(x) + ":" + String.valueOf(y) + ":" + String.valueOf(z));

		setChestLastDestroy(chest, System.currentTimeMillis());

		saveData();

	}

	public void removeChest(Chest chest){

		String world = chest.getWorld().getName();

		int x = chest.getX();
		int y = chest.getY();
		int z = chest.getZ();

		this.chestdata.set("chestdata." + world + ":" + String.valueOf(x) + ":" + String.valueOf(y) + ":" + String.valueOf(z), null);

		saveData();

	}

	public void setChestLastDestroy(Chest chest, Long systemmilis){

		String world = chest.getWorld().getName();

		int x = chest.getX();
		int y = chest.getY();
		int z = chest.getZ();

		this.chestdata.set("chestdata." + world + ":" + String.valueOf(x) + ":" + String.valueOf(y) + ":" + String.valueOf(z) + ".lastdestroy", systemmilis);

		saveData();

	}

	public long getChestLastDestroy(Block block){

		String world = block.getWorld().getName();

		int x = block.getX();
		int y = block.getY();
		int z = block.getZ();

		long lastdestroy = this.chestdata.getLong("chestdata." + world + ":" + String.valueOf(x) + ":" + String.valueOf(y) + ":" + String.valueOf(z) + ".lastdestroy");

		return lastdestroy;

	}

	public void chestItem(Block block){

		block.setType(Material.CHEST);

		final Chest chest = (Chest) block.getState();

		String world = chest.getWorld().getName();

		int x = chest.getX();
		int y = chest.getY();
		int z = chest.getZ();

		List<String> itemlist = this.chestdata.getStringList("chestdata." + world + ":" + String.valueOf(x) + ":" + String.valueOf(y) + ":" + String.valueOf(z) + ".items");

		for(String itemstring : itemlist){

			String[] itemsplit = itemstring.trim().split(":");

			int id = Integer.valueOf(itemsplit[0]);

			int min = Integer.valueOf(itemsplit[1]);
			int max = Integer.valueOf(itemsplit[2]);

			int quantity = (int) Math.round((min + Math.random() * (max - min)));

			final ItemStack itemstack;

			if(itemstring.length() == 3){

				itemstack = new ItemStack(Material.getMaterial(id), quantity);

			}
			else
			{

				int intdata = Integer.valueOf(itemsplit[3]);

				short data = (short) intdata;

				itemstack = new ItemStack(Material.getMaterial(id), quantity, data);

			}

	        Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {

	        	public void run() {

	    			chest.getInventory().addItem(itemstack);

	    			chest.update();

	            }

	        }, 20L);

		}

	}

}
