package de.katla66.minecrafteragon.procedures;

import net.minecraft.util.text.StringTextComponent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.Entity;

import java.util.Map;

import de.katla66.minecrafteragon.MinecraftEragonFraktionsModVariables;
import de.katla66.minecrafteragon.MinecraftEragonFraktionsModElements;
import de.katla66.minecrafteragon.MinecraftEragonFraktionsMod;

@MinecraftEragonFraktionsModElements.ModElement.Tag
public class SpecialAbillityShadeFireballOnKeyPressedProcedure extends MinecraftEragonFraktionsModElements.ModElement {
	public SpecialAbillityShadeFireballOnKeyPressedProcedure(MinecraftEragonFraktionsModElements instance) {
		super(instance, 49);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				MinecraftEragonFraktionsMod.LOGGER.warn("Failed to load dependency entity for procedure SpecialAbillityShadeFireballOnKeyPressed!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if ((((entity.getCapability(MinecraftEragonFraktionsModVariables.PLAYER_VARIABLES_CAPABILITY, null)
				.orElse(new MinecraftEragonFraktionsModVariables.PlayerVariables())).shade) == 1)) {
			if (entity instanceof PlayerEntity && !entity.world.isRemote()) {
				((PlayerEntity) entity).sendStatusMessage(new StringTextComponent("\u00A7cUnder Construction"), (false));
			}
		}
	}
}
