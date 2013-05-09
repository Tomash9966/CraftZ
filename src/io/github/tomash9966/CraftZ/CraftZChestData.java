package io.github.tomash9966.CraftZ;

import java.io.File;

import org.bukkit.block.Chest;
import org.bukkit.configuration.file.YamlConfiguration;

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

	public void	addChest(Chest chest){

		String world = chest.getWorld().getName();

		int x = chest.getX();
		int y = chest.getY();
		int z = chest.getZ();

		this.chestdata.createSection("chestdata." + world + ":" + String.valueOf(x) + ":" + String.valueOf(y) + ":" + String.valueOf(z));

		saveData();

	}

}
