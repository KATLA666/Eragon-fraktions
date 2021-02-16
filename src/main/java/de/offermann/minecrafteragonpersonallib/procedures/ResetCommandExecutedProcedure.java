package de.offermann.minecrafteragonpersonallib.procedures;

import net.minecraft.util.text.StringTextComponent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.Entity;

import java.util.Map;

import de.offermann.minecrafteragonpersonallib.MinecraftEragonPersonallibModElements;

@MinecraftEragonPersonallibModElements.ModElement.Tag
public class ResetCommandExecutedProcedure extends MinecraftEragonPersonallibModElements.ModElement {
	public ResetCommandExecutedProcedure(MinecraftEragonPersonallibModElements instance) {
		super(instance, 4);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				System.err.println("Failed to load dependency entity for procedure ResetCommandExecuted!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		entity.getPersistentData().putDouble("clicked", 0);
		entity.getPersistentData().putDouble("varden", 0);
		entity.getPersistentData().putDouble("elfen", 0);
		entity.getPersistentData().putDouble("zwergen", 0);
		entity.getPersistentData().putDouble("surda", 0);
		entity.getPersistentData().putDouble("empire_soldier", 0);
		entity.getPersistentData().putDouble("shade", 0);
		entity.getPersistentData().putDouble("ra'zac", 0);
		entity.getPersistentData().putDouble("urgal", 0);
		entity.getPersistentData().putDouble("clock", 0);
		entity.getPersistentData().putDouble("clockready", 0);
		entity.getPersistentData().putDouble("clockreadystrong", 0);
		entity.getPersistentData().putBoolean("rage", (false));
		if (entity instanceof PlayerEntity && !entity.world.isRemote) {
			((PlayerEntity) entity).sendStatusMessage(new StringTextComponent("\u00A7creseted!"), (false));
		}
	}
}
