package io.github.tomash9966.CraftZ;

import io.github.tomash9966.CraftZ.listeners.PlayerJoinListener;

import java.util.logging.Level;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class CraftZPlugin extends JavaPlugin{

	public CraftZConfig config;
	public PlayerJoinListener playerjoin;

	public void onEnable(){

		this.config = new CraftZConfig(this);

		getServer().getPluginManager().registerEvents(playerjoin, this);

		getLogger().log(Level.INFO, "CraftZ has been enabled!");

	}

	public void onDisable(){

		getLogger().log(Level.INFO, "CraftZ has been disabled!");

	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){

		if(cmd.getName().equalsIgnoreCase("craftz")){

			if(sender.hasPermission("craftz.manage")){

				if(sender instanceof Player){

					if(args.length > 0){

						if(args[0].equals("setlobby")){



						}

					}
					else
					{

						sender.sendMessage(ChatColor.DARK_AQUA + "Available arguments: " + ChatColor.BLUE + "setlobby, lobby, addspawn, delspawn");

					}

				}

			}

		}

		return true;

	}

}
