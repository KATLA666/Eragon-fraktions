package de.offermann.minecrafteragonpersonallib.procedures;

import net.minecraft.util.text.StringTextComponent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.Entity;

import java.util.Map;

import de.offermann.minecrafteragonpersonallib.MinecraftEragonPersonallibModElements;

@MinecraftEragonPersonallibModElements.ModElement.Tag
public class ButtonFraktionUrgalProcedure extends MinecraftEragonPersonallibModElements.ModElement {
	public ButtonFraktionUrgalProcedure(MinecraftEragonPersonallibModElements instance) {
		super(instance, 24);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				System.err.println("Failed to load dependency entity for procedure ButtonFraktionUrgal!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if (((entity.getPersistentData().getDouble("clicked")) == 0)) {
			entity.getPersistentData().putDouble("urgal", 1);
			entity.getPersistentData().putDouble("clicked", 1);
			if (entity instanceof PlayerEntity && !entity.world.isRemote) {
				((PlayerEntity) entity).sendStatusMessage(new StringTextComponent("\uFFFDaYou have joined the Urgal faction"), (false));
			}
		} else {
			if (entity instanceof PlayerEntity && !entity.world.isRemote) {
				((PlayerEntity) entity).sendStatusMessage(new StringTextComponent("\uFFFDcYou are already in a faction"), (false));
			}
		}
	}
}
