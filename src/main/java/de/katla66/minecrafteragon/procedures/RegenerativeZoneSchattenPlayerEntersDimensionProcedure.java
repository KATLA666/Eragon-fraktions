package de.katla66.minecrafteragon.procedures;

import net.minecraft.entity.Entity;

import java.util.Map;

import de.katla66.minecrafteragon.MinecraftEragonFraktionsModElements;
import de.katla66.minecrafteragon.MinecraftEragonFraktionsMod;

@MinecraftEragonFraktionsModElements.ModElement.Tag
public class RegenerativeZoneSchattenPlayerEntersDimensionProcedure extends MinecraftEragonFraktionsModElements.ModElement {
	public RegenerativeZoneSchattenPlayerEntersDimensionProcedure(MinecraftEragonFraktionsModElements instance) {
		super(instance, 79);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				MinecraftEragonFraktionsMod.LOGGER
						.warn("Failed to load dependency entity for procedure RegenerativeZoneSchattenPlayerEntersDimension!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		{
			Entity _ent = entity;
			if (!_ent.world.isRemote && _ent.world.getServer() != null) {
				_ent.world.getServer().getCommandManager().handleCommand(_ent.getCommandSource().withFeedbackDisabled().withPermissionLevel(4),
						"fill ~-8 ~-8 ~-8 ~8 ~8 ~8 air replace minecraft_eragon_fraktions:portalblock");
			}
		}
	}
}
