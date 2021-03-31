package de.katla66.minecrafteragon.procedures;

import net.minecraft.entity.Entity;

import java.util.Map;

import de.katla66.minecrafteragon.MinecraftEragonFraktionsModElements;
import de.katla66.minecrafteragon.MinecraftEragonFraktionsMod;

@MinecraftEragonFraktionsModElements.ModElement.Tag
public class SummonSoldierProcedure extends MinecraftEragonFraktionsModElements.ModElement {
	public SummonSoldierProcedure(MinecraftEragonFraktionsModElements instance) {
		super(instance, 44);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				MinecraftEragonFraktionsMod.LOGGER.warn("Failed to load dependency entity for procedure SummonSoldier!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		{
			Entity _ent = entity;
			if (!_ent.world.isRemote && _ent.world.getServer() != null) {
				_ent.world.getServer().getCommandManager().handleCommand(_ent.getCommandSource().withFeedbackDisabled().withPermissionLevel(4),
						(("summon minecraft_eragon_fraktions:empire_soldier ~ ~ ~ {Tags:[\"") + "" + ((((entity.getDisplayName().getString())) + ""
								+ ((("\"],Owner:") + "" + ((((entity.getDisplayName().getString())) + "" + ("}")))))))));
			}
		}
	}
}
