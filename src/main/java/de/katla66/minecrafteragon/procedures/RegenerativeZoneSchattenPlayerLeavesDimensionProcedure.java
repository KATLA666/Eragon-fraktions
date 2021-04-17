package de.katla66.minecrafteragon.procedures;

import net.minecraft.potion.Effects;
import net.minecraft.potion.EffectInstance;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;

import java.util.Map;

import de.katla66.minecrafteragon.potion.NoFallPotion;
import de.katla66.minecrafteragon.MinecraftEragonFraktionsModElements;
import de.katla66.minecrafteragon.MinecraftEragonFraktionsMod;

@MinecraftEragonFraktionsModElements.ModElement.Tag
public class RegenerativeZoneSchattenPlayerLeavesDimensionProcedure extends MinecraftEragonFraktionsModElements.ModElement {
	public RegenerativeZoneSchattenPlayerLeavesDimensionProcedure(MinecraftEragonFraktionsModElements instance) {
		super(instance, 51);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				MinecraftEragonFraktionsMod.LOGGER
						.warn("Failed to load dependency entity for procedure RegenerativeZoneSchattenPlayerLeavesDimension!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if (entity instanceof LivingEntity)
			((LivingEntity) entity).addPotionEffect(new EffectInstance(Effects.FIRE_RESISTANCE, (int) 2400, (int) 0, (false), (false)));
		if (entity instanceof LivingEntity)
			((LivingEntity) entity).addPotionEffect(new EffectInstance(Effects.STRENGTH, (int) 200, (int) 0, (false), (false)));
		if (entity instanceof LivingEntity)
			((LivingEntity) entity).addPotionEffect(new EffectInstance(NoFallPotion.potion, (int) 200, (int) 0, (false), (false)));
	}
}
