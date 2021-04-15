package de.katla66.minecrafteragon.procedures;

import net.minecraft.util.text.StringTextComponent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.Entity;

import java.util.Map;

import de.katla66.minecrafteragon.MinecraftEragonFraktionsModVariables;
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
			{
				double _setval = (double) 1;
				entity.getCapability(MinecraftEragonFraktionsModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.razac = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
			entity.getPersistentData().putDouble("clicked", 1);
			{
				Entity _ent = entity;
				if (!_ent.world.isRemote && _ent.world.getServer() != null) {
					_ent.world.getServer().getCommandManager().handleCommand(_ent.getCommandSource().withFeedbackDisabled().withPermissionLevel(4),
							"tag @s add razac");
				}
			}
			if (entity instanceof PlayerEntity && !entity.world.isRemote()) {
				((PlayerEntity) entity).sendStatusMessage(new StringTextComponent("\u00A7aYou have joined the Ra'zac faction"), (false));
			}
		} else {
			if (entity instanceof PlayerEntity && !entity.world.isRemote()) {
				((PlayerEntity) entity).sendStatusMessage(new StringTextComponent("\u00A7cYou are already in a faction"), (false));
			}
		}
	}
}
