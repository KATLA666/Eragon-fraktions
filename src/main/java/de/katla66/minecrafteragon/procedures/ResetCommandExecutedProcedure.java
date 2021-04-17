package de.katla66.minecrafteragon.procedures;

import net.minecraft.util.text.StringTextComponent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.Entity;

import java.util.Map;

import de.katla66.minecrafteragon.MinecraftEragonFraktionsModElements;
import de.katla66.minecrafteragon.MinecraftEragonFraktionsMod;

@MinecraftEragonFraktionsModElements.ModElement.Tag
public class ResetCommandExecutedProcedure extends MinecraftEragonFraktionsModElements.ModElement {
	public ResetCommandExecutedProcedure(MinecraftEragonFraktionsModElements instance) {
		super(instance, 4);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				MinecraftEragonFraktionsMod.LOGGER.warn("Failed to load dependency entity for procedure ResetCommandExecuted!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if (entity instanceof PlayerEntity && !entity.world.isRemote()) {
			((PlayerEntity) entity)
					.sendStatusMessage(new StringTextComponent((((("Clock: ") + ""
							+ ((((entity.getPersistentData().getDouble("clock"))) + ""
									+ (((" Clockready: ") + "" + ((((entity.getPersistentData().getDouble("clockready"))) + ""
											+ (((" clockreadystrong ") + "" + ((entity.getPersistentData().getDouble("clockreadystrong")))))))))))))
							+ "" + (null))), (false));
		}
	}
}
