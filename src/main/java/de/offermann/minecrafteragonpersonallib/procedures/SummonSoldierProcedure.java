package de.offermann.minecrafteragonpersonallib.procedures;

import net.minecraft.entity.Entity;

import java.util.Map;

import de.offermann.minecrafteragonpersonallib.MinecraftEragonPersonallibModElements;
import de.offermann.minecrafteragonpersonallib.MinecraftEragonPersonallibMod;

@MinecraftEragonPersonallibModElements.ModElement.Tag
public class SummonSoldierProcedure extends MinecraftEragonPersonallibModElements.ModElement {
	public SummonSoldierProcedure(MinecraftEragonPersonallibModElements instance) {
		super(instance, 44);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				MinecraftEragonPersonallibMod.LOGGER.warn("Failed to load dependency entity for procedure SummonSoldier!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		{
			Entity _ent = entity;
			if (!_ent.world.isRemote && _ent.world.getServer() != null) {
				_ent.world.getServer().getCommandManager().handleCommand(_ent.getCommandSource().withFeedbackDisabled().withPermissionLevel(4),
						(("summon minecraft_eragon__personallib:empire_soldier ~ ~ ~ {Owner:") + ""
								+ ((((entity.getDisplayName().getString())) + "" + ("}")))));
			}
		}
		{
			Entity _ent = entity;
			if (!_ent.world.isRemote && _ent.world.getServer() != null) {
				_ent.world.getServer().getCommandManager().handleCommand(_ent.getCommandSource().withFeedbackDisabled().withPermissionLevel(4),
						(("summon minecraft_eragon__personallib:empire_soldier ~ ~ ~ {Owner:") + ""
								+ ((((entity.getDisplayName().getString())) + "" + ("}")))));
			}
		}
		{
			Entity _ent = entity;
			if (!_ent.world.isRemote && _ent.world.getServer() != null) {
				_ent.world.getServer().getCommandManager().handleCommand(_ent.getCommandSource().withFeedbackDisabled().withPermissionLevel(4),
						(("summon minecraft_eragon__personallib:empire_soldier ~ ~ ~ {Owner:") + ""
								+ ((((entity.getDisplayName().getString())) + "" + ("}")))));
			}
		}
		{
			Entity _ent = entity;
			if (!_ent.world.isRemote && _ent.world.getServer() != null) {
				_ent.world.getServer().getCommandManager().handleCommand(_ent.getCommandSource().withFeedbackDisabled().withPermissionLevel(4),
						(("summon minecraft_eragon__personallib:empire_soldier ~ ~ ~ {Owner:") + ""
								+ ((((entity.getDisplayName().getString())) + "" + ("}")))));
			}
		}
		entity.getPersistentData().putDouble("clock", 0);
		entity.getPersistentData().putDouble("clockreadystrong", 0);
	}
}
