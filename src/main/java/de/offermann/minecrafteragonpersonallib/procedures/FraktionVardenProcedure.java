package de.offermann.minecrafteragonpersonallib.procedures;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.common.MinecraftForge;

import net.minecraft.world.World;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;

import java.util.Map;
import java.util.HashMap;

import de.offermann.minecrafteragonpersonallib.MinecraftEragonPersonallibModElements;

@MinecraftEragonPersonallibModElements.ModElement.Tag
public class FraktionVardenProcedure extends MinecraftEragonPersonallibModElements.ModElement {
	public FraktionVardenProcedure(MinecraftEragonPersonallibModElements instance) {
		super(instance, 17);
		MinecraftForge.EVENT_BUS.register(this);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				System.err.println("Failed to load dependency entity for procedure FraktionVarden!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if (((entity.getPersistentData().getDouble("varden")) == 1)) {
			if ((((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHealth() : -1) <= 6)) {
			}
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
