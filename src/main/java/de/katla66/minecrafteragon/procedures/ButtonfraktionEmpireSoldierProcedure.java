package de.katla66.minecrafteragon.procedures;

import net.minecraft.util.text.StringTextComponent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.Entity;

import java.util.Map;

import de.katla66.minecrafteragon.MinecraftEragonFraktionsModElements;
import de.katla66.minecrafteragon.MinecraftEragonFraktionsMod;

@MinecraftEragonFraktionsModElements.ModElement.Tag
public class ButtonfraktionEmpireSoldierProcedure extends MinecraftEragonFraktionsModElements.ModElement {
	public ButtonfraktionEmpireSoldierProcedure(MinecraftEragonFraktionsModElements instance) {
		super(instance, 21);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				MinecraftEragonFraktionsMod.LOGGER.warn("Failed to load dependency entity for procedure ButtonfraktionEmpireSoldier!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if (((entity.getPersistentData().getDouble("clicked")) == 0)) {
			entity.getPersistentData().putDouble("empire_soldier", 1);
			entity.getPersistentData().putDouble("clicked", 1);
			if (entity instanceof PlayerEntity && !entity.world.isRemote()) {
				((PlayerEntity) entity).sendStatusMessage(new StringTextComponent("\u00EF\u00BF\u00BDaYou have joined the Empire Soldier faction"),
						(false));
			}
		} else {
			if (entity instanceof PlayerEntity && !entity.world.isRemote()) {
				((PlayerEntity) entity).sendStatusMessage(new StringTextComponent("\u00EF\u00BF\u00BDcYou are already in a faction"), (false));
			}
		}
	}
}
