package io.github.tomash9966.CraftZ;

import io.github.tomash9966.CraftZ.listeners.PlayerDeathListener;
import io.github.tomash9966.CraftZ.listeners.PlayerInteractListener;

import java.util.logging.Level;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class CraftZPlugin extends JavaPlugin{

	public CraftZPlayerTeleport teleport;
	public CraftZData config;
	public CraftZChestData chestdata;
	public CraftZScheduler scheduler;

	PlayerDeathListener playerdeath;
	PlayerInteractListener playerinteract;

	public void onEnable(){

		this.teleport = new CraftZPlayerTeleport(this);
		this.config = new CraftZData(this);
		this.chestdata = new CraftZChestData(this);
		this.scheduler = new CraftZScheduler(this);

		this.playerdeath = new PlayerDeathListener(this);
		this.playerinteract = new PlayerInteractListener(this);

		getServer().getPluginManager().registerEvents(this.playerdeath, this);
		getServer().getPluginManager().registerEvents(this.playerinteract, this);

		getLogger().log(Level.INFO, "CraftZ has been enabled!");

	}

	public void onDisable(){

		getLogger().log(Level.INFO, "CraftZ has been disabled!");

	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){

		if(cmd.getName().equalsIgnoreCase("craftz") || cmd.getName().equalsIgnoreCase("z")){

			if(sender instanceof Player){

				Player player = (Player) sender;

				if(sender.hasPermission("craftz.manage")){

					if(args.length > 0){

						if(args[0].equalsIgnoreCase("help")){

							sender.sendMessage(ChatColor.GRAY + "##### " + ChatColor.DARK_GRAY + ChatColor.BOLD + "Craft" + ChatColor.DARK_RED + ChatColor.BOLD + "Z" + ChatColor.GRAY + " #####");
							sender.sendMessage(ChatColor.GOLD + "/craftz help" + ChatColor.BLUE + " - displays help.");
							sender.sendMessage(ChatColor.GOLD + "/craftz setlobby" + ChatColor.BLUE + " - sets lobby.");
							sender.sendMessage(ChatColor.GOLD + "/craftz lobby" + ChatColor.BLUE + " - teleports to lobby.");
							sender.sendMessage(ChatColor.GOLD + "/craftz addspawn [number]" + ChatColor.BLUE + " - adds game spawn location.");
							sender.sendMessage(ChatColor.GOLD + "/craftz delspawn <number>" + ChatColor.BLUE + " - deletes spawn location.");
							sender.sendMessage(ChatColor.GOLD + "/craftz spawn" + ChatColor.BLUE + " - starts the game.");
							sender.sendMessage(ChatColor.GOLD + "/craftz addchest" + ChatColor.BLUE + " - adds chest with random loot.");
							sender.sendMessage(ChatColor.GOLD + "/craftz additem" + ChatColor.BLUE + " - adds item to chest with loot.");
							sender.sendMessage(ChatColor.GRAY + "##################");

						}

						if(args[0].equalsIgnoreCase("addchest")){

							this.playerinteract.addloot.put(player.getName(), true);

							player.sendMessage(ChatColor.GOLD + "Click chest in which you want to be loot.");

						}

						if(args[0].equalsIgnoreCase("delchest")){

							this.playerinteract.delloot.put(player.getName(), true);

							player.sendMessage(ChatColor.GOLD + "Click chest which do you want to remove.");

						}

						if(args[0].equalsIgnoreCase("additem")){

							if(args.length > 3){

								String item;

								if(args.length > 4){

									item = args[1] + ":" + args[2] + ":" + args[3] + ":" + args[4];

								}
								else
								{

									item = args[1] + ":" + args[2] + ":" + args[3];

								}

								this.playerinteract.additem.put(player.getName(), item);

								player.sendMessage(ChatColor.GOLD + "Click chest in which you want to be item.");

							}
							else
							{

								player.sendMessage(ChatColor.RED + "Too few arguments! Usage:" + ChatColor.GOLD + " /craftz additem <id> <min quantity> <max quantity> [data]");

							}

						}

						if(args[0].equalsIgnoreCase("setlobby")){

							this.config.setLobby(player);

							int x = player.getLocation().getBlockX();
							int y = player.getLocation().getBlockY();
							int z = player.getLocation().getBlockZ();

							player.getWorld().setSpawnLocation(x, y, z);

							player.sendMessage(ChatColor.GREEN + "You have set lobby!");

						}

						if(args[0].equalsIgnoreCase("spawn")){

							this.teleport.randomSpawn(player);

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

								Location location = player.getLocation();

								int i = this.config.addSpawn(location, Integer.valueOf(args[1]) - 1);

								player.sendMessage(ChatColor.GREEN + "You have set spawn no. " + String.valueOf(i));

							}

						}

						if(args[0].equalsIgnoreCase("delspawn")){

							if(args.length > 1){

								int i = Integer.valueOf(args[1]);

								if(this.config.delSpawn(i - 1)){

									player.sendMessage(ChatColor.GREEN + "Spawn no. " + String.valueOf(i) + " has been deleted.");

								}
								else
								{

									player.sendMessage(ChatColor.RED + "There's no spawn no. " + String.valueOf(i) + "!");

								}

							}
							else
							{

								player.sendMessage(ChatColor.RED + "Invalid arguments! " + ChatColor.GOLD + "Example: /craftz delspawn <number>");

							}

						}

					}
					else
					{

						player.sendMessage(ChatColor.BLUE + "Available arguments: " + ChatColor.GOLD + "help, setlobby, lobby, addspawn, delspawn, addchest, additem");

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
