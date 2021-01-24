package de.offermann.minecrafteragonpersonallib.procedures;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.common.MinecraftForge;

import net.minecraft.world.World;
import net.minecraft.entity.Entity;

import java.util.Map;
import java.util.HashMap;

import de.offermann.minecrafteragonpersonallib.MinecraftEragonPersonallibModElements;

@MinecraftEragonPersonallibModElements.ModElement.Tag
public class ClockProcedure extends MinecraftEragonPersonallibModElements.ModElement {
	public ClockProcedure(MinecraftEragonPersonallibModElements instance) {
		super(instance, 20);
		MinecraftForge.EVENT_BUS.register(this);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				System.err.println("Failed to load dependency entity for procedure Clock!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		entity.getPersistentData().putDouble("clock", ((entity.getPersistentData().getDouble("clock")) + 1));
		if (((entity.getPersistentData().getDouble("clock")) >= 1000)) {
			entity.getPersistentData().putDouble("clockready", 1);
			entity.getPersistentData().putDouble("clock", 0);
		} else if (((entity.getPersistentData().getDouble("clock")) >= 6000)) {
			entity.getPersistentData().putDouble("clockreadystrong", 1);
			entity.getPersistentData().putDouble("clock", 0);
		}
	}

	@SubscribeEvent
	public void onPlayerTick(TickEvent.PlayerTickEvent event) {
		if (event.phase == TickEvent.Phase.END) {
			Entity entity = event.player;
			World world = entity.world;
			double i = entity.posX;
			double j = entity.posY;
			double k = entity.posZ;
			Map<String, Object> dependencies = new HashMap<>();
			dependencies.put("x", i);
			dependencies.put("y", j);
			dependencies.put("z", k);
			dependencies.put("world", world);
			dependencies.put("entity", entity);
			dependencies.put("event", event);
			this.executeProcedure(dependencies);
		}
	}
}
