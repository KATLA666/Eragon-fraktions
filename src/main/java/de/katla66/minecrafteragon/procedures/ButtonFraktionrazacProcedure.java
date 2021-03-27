package de.katla66.minecrafteragon.procedures;

import net.minecraft.util.text.StringTextComponent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.Entity;

import java.util.Map;

import de.katla66.minecrafteragon.MinecraftEragonFraktionsModElements;
import de.katla66.minecrafteragon.MinecraftEragonFraktionsMod;

@MinecraftEragonFraktionsModElements.ModElement.Tag
public class ButtonFraktionrazacProcedure extends MinecraftEragonFraktionsModElements.ModElement {
	public ButtonFraktionrazacProcedure(MinecraftEragonFraktionsModElements instance) {
		super(instance, 23);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				MinecraftEragonFraktionsMod.LOGGER.warn("Failed to load dependency entity for procedure ButtonFraktionrazac!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if (((entity.getPersistentData().getDouble("clicked")) == 0)) {
			entity.getPersistentData().putDouble("ra'zac", 1);
			entity.getPersistentData().putDouble("clicked", 1);
			if (entity instanceof PlayerEntity && !entity.world.isRemote()) {
				((PlayerEntity) entity).sendStatusMessage(new StringTextComponent("\u00EF\u00BF\u00BDaYou have joined the Ra'zac faction"), (false));
			}
		} else {
			if (entity instanceof PlayerEntity && !entity.world.isRemote()) {
				((PlayerEntity) entity).sendStatusMessage(new StringTextComponent("\u00EF\u00BF\u00BDcYou are already in a faction"), (false));
			}
		}
	}
}
