package io.github.tomash9966.CraftZ;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class CraftZConfig {

	CraftZPlayerTeleport teleport;
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

    public Location getLobby(){

    	ConfigurationSection lobby = this.data.getConfigurationSection("data.lobby");

    	World world = Bukkit.getWorld(lobby.getString("w"));

    	double x = lobby.getDouble("x");
    	double y = lobby.getDouble("y");
    	double z = lobby.getDouble("z");
    	float p = (float) lobby.getDouble("p");
    	float a = (float) lobby.getDouble("a");

    	Location lobbyloc = new Location(world, x, y, z);
    	lobbyloc.setPitch(p);
    	lobbyloc.setYaw(a);

    	return lobbyloc;

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

	public boolean delSpawn(int i){

		ConfigurationSection spawn = this.data.getConfigurationSection("data");

		if(this.data.isList("data.spawn")){

			List<String> spawnlist = spawn.getStringList("spawn");

			if(i + 1 <= spawnlist.size()){

				spawnlist.remove(i);

				spawn.set("spawn", spawnlist);

				saveData();

				return true;

			}
			else
			{

				return false;

			}

		}
		else
		{

			return false;

		}

	}

	public Location randomSpawn(Player player){

		ConfigurationSection spawn = this.data.getConfigurationSection("data");

		if(this.data.isList("data.spawn")){

			List<String> spawnlist = spawn.getStringList("spawn");

			int random = (int)(Math.round(Math.random() * spawnlist.size()));

			String[] spawnstring = spawnlist.get(random).trim().split(":");

			World world = Bukkit.getWorld(spawnstring[0]);
			double x = Double.parseDouble(spawnstring[1]);
			double y = Double.parseDouble(spawnstring[2]);
			double z = Double.parseDouble(spawnstring[3]);
			float p = (float) Double.parseDouble(spawnstring[4]);
			float a = (float) Double.parseDouble(spawnstring[5]);

			Location randomspawn = new Location(world, x, y, z);
			randomspawn.setPitch(p);
			randomspawn.setYaw(a);

			return randomspawn;

		}
		else
		{

			Bukkit.getConsoleSender().sendMessage(ChatColor.DARK_RED + "There's no spawn! Please add them!");

			return null;

		}

	}

	public int addSpawn(Location location, int i){

		ConfigurationSection spawn = this.data.getConfigurationSection("data");

		String world = location.getWorld().getName();

		double x = location.getX();
		double y = location.getY();
		double z = location.getZ();

		float p = location.getPitch();
		float a = location.getYaw();

		if(this.data.isList("data.spawn")){

			List<String> spawnlist = spawn.getStringList("spawn");

			if(i >= spawnlist.size() - 1){

				spawnlist.add(world + ":" + String.valueOf(x) + ":" + String.valueOf(y) + ":" + String.valueOf(z) + ":" + String.valueOf(p) + ":" + String.valueOf(a));

				spawn.set("spawn", spawnlist);

				saveData();

				return spawnlist.size();

			}
			else
			{

				spawnlist.set(i, world + ":" + String.valueOf(x) + ":" + String.valueOf(y) + ":" + String.valueOf(z) + ":" + String.valueOf(p) + ":" + String.valueOf(a));

				spawn.set("spawn", spawnlist);

				saveData();

				return spawnlist.size();

			}

		}
		else
		{

			List<String> spawnlist = new ArrayList<String>();

			spawnlist.set(i, world + ":" + String.valueOf(x) + ":" + String.valueOf(y) + ":" + String.valueOf(z) + ":" + String.valueOf(p) + ":" + String.valueOf(a));

			spawn.set("spawn", spawnlist);

			saveData();

			return spawnlist.size();

		}

	}

	public int addSpawn(Location location){

		ConfigurationSection spawn = this.data.getConfigurationSection("data");

		String world = location.getWorld().getName();

		double x = location.getX();
		double y = location.getY();
		double z = location.getZ();

		float p = location.getPitch();
		float a = location.getYaw();

		if(this.data.isList("data.spawn")){

			List<String> spawnlist = spawn.getStringList("spawn");

			spawnlist.add(world + ":" + String.valueOf(x) + ":" + String.valueOf(y) + ":" + String.valueOf(z) + ":" + String.valueOf(p) + ":" + String.valueOf(a));

			spawn.set("spawn", spawnlist);

			saveData();

			return spawnlist.size();

		}
		else
		{

			List<String> spawnlist = new ArrayList<String>();

			spawnlist.add(world + ":" + String.valueOf(x) + ":" + String.valueOf(y) + ":" + String.valueOf(z) + ":" + String.valueOf(p) + ":" + String.valueOf(a));

			spawn.set("spawn", spawnlist);

			saveData();

			return spawnlist.size();

		}

	}

}
