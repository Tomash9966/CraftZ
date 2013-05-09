package io.github.tomash9966.CraftZ;

import org.bukkit.Bukkit;

public class CraftZScheduler {

	CraftZPlugin plugin;

	public CraftZScheduler(CraftZPlugin plugin){

		this.plugin = plugin;

		Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable(){

			public void run(){



			}

		}, 20L, 20L);

	}

}
