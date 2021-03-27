package de.katla66.minecrafteragon.procedures;

import net.minecraft.entity.Entity;

import java.util.Map;

import de.katla66.minecrafteragon.MinecraftEragonFraktionsModElements;
import de.katla66.minecrafteragon.MinecraftEragonFraktionsMod;

@MinecraftEragonFraktionsModElements.ModElement.Tag
public class DashProcedure extends MinecraftEragonFraktionsModElements.ModElement {
	public DashProcedure(MinecraftEragonFraktionsModElements instance) {
		super(instance, 34);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				MinecraftEragonFraktionsMod.LOGGER.warn("Failed to load dependency entity for procedure Dash!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		entity.setMotion((Math.sin(Math.toRadians(((entity.rotationYaw) + 180))) * 3), Math.sin(Math.toRadians((0 - (entity.rotationPitch)))),
				(Math.cos(Math.toRadians((entity.rotationYaw))) * 3));
	}
}
