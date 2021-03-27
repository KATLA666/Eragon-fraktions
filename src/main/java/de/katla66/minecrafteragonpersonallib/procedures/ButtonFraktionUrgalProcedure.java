package de.katla66.minecrafteragonpersonallib.procedures;

import net.minecraft.util.text.StringTextComponent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.Entity;

import java.util.Map;

import de.katla66.minecrafteragonpersonallib.MinecraftEragonFraktionsModElements;
import de.katla66.minecrafteragonpersonallib.MinecraftEragonFraktionsMod;

@MinecraftEragonFraktionsModElements.ModElement.Tag
public class ButtonFraktionUrgalProcedure extends MinecraftEragonFraktionsModElements.ModElement {
	public ButtonFraktionUrgalProcedure(MinecraftEragonFraktionsModElements instance) {
		super(instance, 24);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				MinecraftEragonFraktionsMod.LOGGER.warn("Failed to load dependency entity for procedure ButtonFraktionUrgal!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if (((entity.getPersistentData().getDouble("clicked")) == 0)) {
			entity.getPersistentData().putDouble("urgal", 1);
			entity.getPersistentData().putDouble("clicked", 1);
			if (entity instanceof PlayerEntity && !entity.world.isRemote()) {
				((PlayerEntity) entity).sendStatusMessage(new StringTextComponent("\u00EF\u00BF\u00BDaYou have joined the Urgal faction"), (false));
			}
		} else {
			if (entity instanceof PlayerEntity && !entity.world.isRemote()) {
				((PlayerEntity) entity).sendStatusMessage(new StringTextComponent("\u00EF\u00BF\u00BDcYou are already in a faction"), (false));
			}
		}
	}
}
