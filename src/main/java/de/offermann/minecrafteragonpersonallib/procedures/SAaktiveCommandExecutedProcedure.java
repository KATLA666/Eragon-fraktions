package de.offermann.minecrafteragonpersonallib.procedures;

import net.minecraft.util.text.StringTextComponent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.Entity;

import java.util.Map;

import de.offermann.minecrafteragonpersonallib.MinecraftEragonPersonallibModElements;
import de.offermann.minecrafteragonpersonallib.MinecraftEragonPersonallibMod;

@MinecraftEragonPersonallibModElements.ModElement.Tag
public class SAaktiveCommandExecutedProcedure extends MinecraftEragonPersonallibModElements.ModElement {
	public SAaktiveCommandExecutedProcedure(MinecraftEragonPersonallibModElements instance) {
		super(instance, 25);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				MinecraftEragonPersonallibMod.LOGGER.warn("Failed to load dependency entity for procedure SAaktiveCommandExecuted!");
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
