package io.github.tomash9966.CraftZ;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

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

    public int addItem(Chest chest, String item){

		String world = chest.getWorld().getName();

		int x = chest.getX();
		int y = chest.getY();
		int z = chest.getZ();

		if(this.chestdata.isList("chestdata." + world + ":" + String.valueOf(x) + ":" + String.valueOf(y) + ":" + String.valueOf(z) + ".items")){

			List<String> items = this.chestdata.getStringList("chestdata." + world + ":" + String.valueOf(x) + ":" + String.valueOf(y) + ":" + String.valueOf(z) + ".items");

			items.add(item);

			this.chestdata.set("chestdata." + world + ":" + String.valueOf(x) + ":" + String.valueOf(y) + ":" + String.valueOf(z) + ".items", items);

			saveData();

			return items.size();

		}
		else
		{

			List<String> items = new ArrayList<String>();

			items.add(item);

			this.chestdata.set("chestdata." + world + ":" + String.valueOf(x) + ":" + String.valueOf(y) + ":" + String.valueOf(z) + ".items", items);

			saveData();

			return items.size();

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

	public void setChestLastDestroy(Chest chest){

		String world = chest.getWorld().getName();

		int x = chest.getX();
		int y = chest.getY();
		int z = chest.getZ();

		long systemmilis = System.currentTimeMillis();

		this.chestdata.set("chestdata." + world + ":" + String.valueOf(x) + ":" + String.valueOf(y) + ":" + String.valueOf(z), systemmilis);

		saveData();

	}

	public long getChestLastDestroy(Chest chest){

		String world = chest.getWorld().getName();

		int x = chest.getX();
		int y = chest.getY();
		int z = chest.getZ();

		long lastdestroy = this.chestdata.getLong("chestdata." + world + ":" + String.valueOf(x) + ":" + String.valueOf(y) + ":" + String.valueOf(z));

		return lastdestroy;

	}

}
