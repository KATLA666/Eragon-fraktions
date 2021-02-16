package de.offermann.minecrafteragonpersonallib.procedures;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.common.MinecraftForge;

import net.minecraft.world.World;
import net.minecraft.potion.Effects;
import net.minecraft.potion.EffectInstance;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;

import java.util.Map;
import java.util.HashMap;

import de.offermann.minecrafteragonpersonallib.MinecraftEragonPersonallibModElements;

@MinecraftEragonPersonallibModElements.ModElement.Tag
public class FraktionSurdaProcedure extends MinecraftEragonPersonallibModElements.ModElement {
	public FraktionSurdaProcedure(MinecraftEragonPersonallibModElements instance) {
		super(instance, 19);
		MinecraftForge.EVENT_BUS.register(this);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				System.err.println("Failed to load dependency entity for procedure FraktionSurda!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if (((entity.getPersistentData().getDouble("surda")) == 1)) {
			if ((entity.isPassenger())) {
				if (entity instanceof LivingEntity)
					((LivingEntity) entity).addPotionEffect(new EffectInstance(Effects.STRENGTH, (int) 20, (int) 0, (false), (false)));
				if (entity instanceof LivingEntity)
					((LivingEntity) entity).addPotionEffect(new EffectInstance(Effects.RESISTANCE, (int) 20, (int) 0, (false), (false)));
				if ((entity.getRidingEntity()) instanceof LivingEntity)
					((LivingEntity) (entity.getRidingEntity()))
							.addPotionEffect(new EffectInstance(Effects.SPEED, (int) 20, (int) 1, (false), (false)));
				if ((entity.getRidingEntity()) instanceof LivingEntity)
					((LivingEntity) (entity.getRidingEntity()))
							.addPotionEffect(new EffectInstance(Effects.RESISTANCE, (int) 20, (int) 0, (false), (false)));
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
