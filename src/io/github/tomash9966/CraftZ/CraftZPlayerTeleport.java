package io.github.tomash9966.CraftZ;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

public class CraftZPlayerTeleport {

	CraftZPlugin plugin;
	public CraftZConfig config;

	public CraftZPlayerTeleport(CraftZPlugin plugin, CraftZConfig config) {

		this.plugin = plugin;
		this.config = config;

	}

	public void lobbyTeleport(Player player){

		ConfigurationSection lobby = this.config.data.getConfigurationSection("data.lobby");

		World world = Bukkit.getWorld(lobby.getString("world"));
		double x = lobby.getDouble("x");
		double y = lobby.getDouble("y");
		double z = lobby.getDouble("z");
		float p = (float) lobby.getDouble("p");
		float a = (float) lobby.getDouble("a");

		Location lobbyloc = new Location(world, x, y, z);

		lobbyloc.setPitch(p);
		lobbyloc.setYaw(a);

		player.teleport(lobbyloc);

	}

}
