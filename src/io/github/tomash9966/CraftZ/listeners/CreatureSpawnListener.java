package io.github.tomash9966.CraftZ.listeners;

import java.lang.reflect.Field;

import io.github.tomash9966.CraftZ.CraftZPlugin;

import net.minecraft.server.v1_5_R3.EntityHuman;
import net.minecraft.server.v1_5_R3.EntityLiving;
import net.minecraft.server.v1_5_R3.EntityVillager;
import net.minecraft.server.v1_5_R3.EntityZombie;
import net.minecraft.server.v1_5_R3.PathfinderGoalBreakDoor;
import net.minecraft.server.v1_5_R3.PathfinderGoalFloat;
import net.minecraft.server.v1_5_R3.PathfinderGoalLookAtPlayer;
import net.minecraft.server.v1_5_R3.PathfinderGoalMeleeAttack;
import net.minecraft.server.v1_5_R3.PathfinderGoalMoveThroughVillage;
import net.minecraft.server.v1_5_R3.PathfinderGoalMoveTowardsRestriction;
import net.minecraft.server.v1_5_R3.PathfinderGoalRandomLookaround;
import net.minecraft.server.v1_5_R3.PathfinderGoalRandomStroll;
import net.minecraft.server.v1_5_R3.PathfinderGoalSelector;

import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_5_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_5_R3.entity.CraftZombie;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.inventory.ItemStack;

public class CreatureSpawnListener implements Listener{

	CraftZPlugin plugin;

	public CreatureSpawnListener(CraftZPlugin plugin) {

		this.plugin = plugin;

	}

	@EventHandler
	public void onCreatureSpawnEvent(CreatureSpawnEvent event){

		EntityType entitytype = event.getEntityType();

		if(entitytype.equals(EntityType.ZOMBIE)){

			EntityZombie zombie = ((CraftZombie) event.getEntity()).getHandle();
	        Field fGoalSelector;
	        //Field fdamage;

			try {
				fGoalSelector = EntityLiving.class.getDeclaredField("goalSelector");
		        fGoalSelector.setAccessible(true);
		        Float speed = 0.5F;

		        PathfinderGoalSelector gs = new PathfinderGoalSelector(

		        	((CraftWorld) event.getEntity().getWorld()).getHandle() != null &&
		        	((CraftWorld) event.getEntity().getWorld()).getHandle().methodProfiler != null ?
		        	((CraftWorld) event.getEntity().getWorld()).getHandle().methodProfiler : null);

		        	gs.a(0, new PathfinderGoalFloat(zombie));
		        	gs.a(1, new PathfinderGoalBreakDoor(zombie));
		        	gs.a(2, new PathfinderGoalMeleeAttack(zombie, EntityHuman.class, speed, false));
		        	gs.a(3, new PathfinderGoalMeleeAttack(zombie, EntityVillager.class, speed, true));
		        	gs.a(4, new PathfinderGoalMoveTowardsRestriction(zombie, speed));
		        	gs.a(5, new PathfinderGoalMoveThroughVillage(zombie, speed, false));
		        	gs.a(6, new PathfinderGoalRandomStroll(zombie, speed));
		        	gs.a(7, new PathfinderGoalLookAtPlayer(zombie, EntityHuman.class, 15.0F));
		        	gs.a(7, new PathfinderGoalRandomLookaround(zombie));
		        	fGoalSelector.set(zombie, gs);

			}
			catch (NumberFormatException e){

				e.printStackTrace();

			}
			catch (SecurityException e) {

				e.printStackTrace();

			}
			catch (NoSuchFieldException e) {

				e.printStackTrace();

			}
			catch (IllegalArgumentException e) {

				e.printStackTrace();

			}catch (IllegalAccessException e) {

				e.printStackTrace();

			}

			LivingEntity entity = event.getEntity();

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
