package io.github.tomash9966.CraftZ;

import java.io.File;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class CraftZConfig {

	CraftZPlugin plugin;

	YamlConfiguration data;

	public CraftZConfig(CraftZPlugin plugin){

		this.plugin = plugin;
		this.data = YamlConfiguration.loadConfiguration(new File(this.plugin.getDataFolder(), "data.yml"));

	}

    public void saveData(){
    	try{

    		this.data.save(new File(this.plugin.getDataFolder(), "data.yml"));

    	}
    	catch (Exception e){

    		System.out.println("Error while trying to save data!");

    	}

	}

	public void setLobby(Player player){

		String world = player.getWorld().getName();
		double x = player.getLocation().getX();
		double y = player.getLocation().getY();
		double z = player.getLocation().getZ();
		float p = player.getLocation().getPitch();
		float a = player.getLocation().getYaw();

		this.data.createSection("data.lobby");

		ConfigurationSection lobby = this.data.getConfigurationSection("data.lobby");

		lobby.set("w", world);
		lobby.set("x", x);
		lobby.set("y", y);
		lobby.set("z", z);
		lobby.set("p", p);
		lobby.set("a", a);

		saveData();

	}

}
