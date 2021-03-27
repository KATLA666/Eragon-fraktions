package de.katla66.minecrafteragonpersonallib.procedures;

import net.minecraft.entity.Entity;

import java.util.Map;

import de.katla66.minecrafteragonpersonallib.MinecraftEragonFraktionsModElements;
import de.katla66.minecrafteragonpersonallib.MinecraftEragonFraktionsMod;

@MinecraftEragonFraktionsModElements.ModElement.Tag
public class ElfischStrenghPotionStartedappliedProcedure extends MinecraftEragonFraktionsModElements.ModElement {
	public ElfischStrenghPotionStartedappliedProcedure(MinecraftEragonFraktionsModElements instance) {
		super(instance, 12);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				MinecraftEragonFraktionsMod.LOGGER.warn("Failed to load dependency entity for procedure ElfischStrenghPotionStartedapplied!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if (((entity.getPersistentData().getDouble("elfischstrengh")) == 0)) {
			{
				Entity _ent = entity;
				if (!_ent.world.isRemote && _ent.world.getServer() != null) {
					_ent.world.getServer().getCommandManager().handleCommand(_ent.getCommandSource().withFeedbackDisabled().withPermissionLevel(4),
							"attribute @p minecraft:generic.attack_speed base set 4.5");
				}
			}
			{
				Entity _ent = entity;
				if (!_ent.world.isRemote && _ent.world.getServer() != null) {
					_ent.world.getServer().getCommandManager().handleCommand(_ent.getCommandSource().withFeedbackDisabled().withPermissionLevel(4),
							"attribute @p minecraft:generic.movement_speed base set 0.125");
				}
			}
			entity.getPersistentData().putDouble("elfischstrengh", 1);
		}
	}
}
