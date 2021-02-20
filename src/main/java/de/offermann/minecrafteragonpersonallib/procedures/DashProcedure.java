package de.offermann.minecrafteragonpersonallib.procedures;

import net.minecraft.entity.Entity;

import java.util.Map;

import de.offermann.minecrafteragonpersonallib.MinecraftEragonPersonallibModElements;
import de.offermann.minecrafteragonpersonallib.MinecraftEragonPersonallibMod;

@MinecraftEragonPersonallibModElements.ModElement.Tag
public class DashProcedure extends MinecraftEragonPersonallibModElements.ModElement {
	public DashProcedure(MinecraftEragonPersonallibModElements instance) {
		super(instance, 34);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				MinecraftEragonPersonallibMod.LOGGER.warn("Failed to load dependency entity for procedure Dash!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		entity.setMotion((Math.sin(Math.toRadians(((entity.rotationYaw) + 180))) * 3), Math.sin(Math.toRadians((0 - (entity.rotationPitch)))),
				(Math.cos(Math.toRadians((entity.rotationYaw))) * 3));
	}
}
