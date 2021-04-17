package de.katla66.minecrafteragon.procedures;

import net.minecraft.entity.Entity;

import java.util.Map;

import de.katla66.minecrafteragon.MinecraftEragonFraktionsModElements;
import de.katla66.minecrafteragon.MinecraftEragonFraktionsMod;

@MinecraftEragonFraktionsModElements.ModElement.Tag
public class NoFallOnPotionActiveTickProcedure extends MinecraftEragonFraktionsModElements.ModElement {
	public NoFallOnPotionActiveTickProcedure(MinecraftEragonFraktionsModElements instance) {
		super(instance, 52);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				MinecraftEragonFraktionsMod.LOGGER.warn("Failed to load dependency entity for procedure NoFallOnPotionActiveTick!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		entity.fallDistance = (float) (0);
	}
}
