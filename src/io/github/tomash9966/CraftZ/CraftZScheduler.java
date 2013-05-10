package io.github.tomash9966.CraftZ;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.configuration.ConfigurationSection;

public class CraftZScheduler {

	CraftZPlugin plugin;

	public CraftZScheduler(final CraftZPlugin plugin){

		this.plugin = plugin;

		Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable(){

			public void run(){

				if(plugin.chestdata.chestdata.isConfigurationSection("chestdata")){

				ConfigurationSection chests = plugin.chestdata.chestdata.getConfigurationSection("chestdata");

					for(String cheststring : chests.getKeys(false)){

						String[] chestconfig = cheststring.trim().split(":");

						World world = Bukkit.getWorld(chestconfig[0]);

						int x = Integer.valueOf(chestconfig[1]);
						int y = Integer.valueOf(chestconfig[2]);
						int z = Integer.valueOf(chestconfig[3]);

						Location chestloc = new Location(world, x, y, z);

						Block block = chestloc.getBlock();

						Long systemmilis = System.currentTimeMillis();

						if(block.getType().equals(Material.AIR) && plugin.chestdata.chestdata.isLong("chestdata." + cheststring + ".lastdestroy")){

							if(systemmilis - plugin.chestdata.getChestLastDestroy(block) >= 5000){

								plugin.chestdata.chestItem(block);


							}

						}

					}

				}

			}

		}, 20L, 20L);

	}

}
