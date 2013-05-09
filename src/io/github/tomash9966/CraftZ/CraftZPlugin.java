package io.github.tomash9966.CraftZ;

import io.github.tomash9966.CraftZ.listeners.PlayerJoinListener;

import java.util.logging.Level;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class CraftZPlugin extends JavaPlugin{

	public CraftZPlayerTeleport teleport;
	public CraftZConfig config;
	public PlayerJoinListener playerjoin;

	public void onEnable(){

		this.teleport = new CraftZPlayerTeleport(this, config);
		this.config = new CraftZConfig(this);

		getServer().getPluginManager().registerEvents(playerjoin, this);

		getLogger().log(Level.INFO, "CraftZ has been enabled!");

	}

	public void onDisable(){

		getLogger().log(Level.INFO, "CraftZ has been disabled!");

	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){

		if(cmd.getName().equalsIgnoreCase("craftz")){

			if(sender instanceof Player){

				Player player = (Player) sender;

				if(sender.hasPermission("craftz.manage")){

					if(args.length > 0){

						if(args[0].equalsIgnoreCase("setlobby")){

							this.config.setLobby(player);

							player.sendMessage(ChatColor.GREEN + "You have set lobby!");

						}

						if(args[0].equalsIgnoreCase("lobby")){

							this.teleport.lobbyTeleport(player);

							player.sendMessage(ChatColor.GREEN + "You have been teleported to lobby!");

						}

						if(args[0].equalsIgnoreCase("addspawn")){

							if(args.length == 1){

								Location location = player.getLocation();

								int i = this.config.addSpawn(location);

								player.sendMessage(ChatColor.GREEN + "You have set spawn no. " + String.valueOf(i));

							}
							else if(args.length > 1){

								int i = Integer.valueOf(args[1]);

								Location location = player.getLocation();

								this.config.addSpawn(location, i);

								player.sendMessage(ChatColor.GREEN + "You have set spawn no. " + String.valueOf(i));

							}

						}

					}
					else
					{

						player.sendMessage(ChatColor.BLUE + "Available arguments: " + ChatColor.GOLD + "setlobby, lobby, addspawn, delspawn");

					}

				}
				else
				{

					player.sendMessage(ChatColor.RED + "You don't have permission to perform this command.");

				}

			}
			else
			{

				sender.sendMessage(ChatColor.RED + "This command cannot be performed from console.");

			}

		}

		return true;

	}

}
