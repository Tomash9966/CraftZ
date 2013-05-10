package io.github.tomash9966.CraftZ.listeners;

import io.github.tomash9966.CraftZ.CraftZPlugin;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class CreatureSpawnListener implements Listener{

	CraftZPlugin plugin;

	public CreatureSpawnListener(CraftZPlugin plugin) {

		this.plugin = plugin;

	}

	@EventHandler
	public void onCreatureSpawnEvent(CreatureSpawnEvent event){

		EntityType entitytype = event.getEntityType();

		if(entitytype.equals(EntityType.ZOMBIE)){

			final LivingEntity entity = event.getEntity();

			Bukkit.getScheduler().scheduleSyncRepeatingTask(this.plugin, new Runnable(){

				@Override
				public void run() {

					entity.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 1200, 2));

					entity.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 1200, 2));

				}



			},0L, 1000L);

			entity.setMaxHealth(50);

			entity.setHealth(50);

			entity.getEquipment().setHelmet(new ItemStack(Material.GOLD_HELMET, 1));

			entity.getEquipment().setHelmetDropChance(0);

		}
		else
		{

			event.setCancelled(true);

		}

	}

}
