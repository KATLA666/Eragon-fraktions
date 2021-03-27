package de.katla66.minecrafteragonpersonallib.procedures;

import net.minecraft.util.text.StringTextComponent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.Entity;

import java.util.Map;

import de.katla66.minecrafteragonpersonallib.MinecraftEragonFraktionsModElements;
import de.katla66.minecrafteragonpersonallib.MinecraftEragonFraktionsMod;

@MinecraftEragonFraktionsModElements.ModElement.Tag
public class SAaktiveCommandExecutedProcedure extends MinecraftEragonFraktionsModElements.ModElement {
	public SAaktiveCommandExecutedProcedure(MinecraftEragonFraktionsModElements instance) {
		super(instance, 25);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				MinecraftEragonFraktionsMod.LOGGER.warn("Failed to load dependency entity for procedure SAaktiveCommandExecuted!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		entity.getPersistentData().putDouble("clockready", 1);
		entity.getPersistentData().putDouble("clockreadystrong", 1);
		entity.getPersistentData().putDouble("clock", 6000);
		if (entity instanceof PlayerEntity && !entity.world.isRemote()) {
			((PlayerEntity) entity).sendStatusMessage(new StringTextComponent("Aktive"), (false));
		}
	}
}
