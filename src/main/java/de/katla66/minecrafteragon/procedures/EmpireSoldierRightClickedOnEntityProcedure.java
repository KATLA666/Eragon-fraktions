package de.katla66.minecrafteragon.procedures;

import net.minecraft.util.text.StringTextComponent;
import net.minecraft.entity.Entity;

import java.util.Map;

import de.katla66.minecrafteragon.MinecraftEragonFraktionsModElements;
import de.katla66.minecrafteragon.MinecraftEragonFraktionsMod;

@MinecraftEragonFraktionsModElements.ModElement.Tag
public class EmpireSoldierRightClickedOnEntityProcedure extends MinecraftEragonFraktionsModElements.ModElement {
	public EmpireSoldierRightClickedOnEntityProcedure(MinecraftEragonFraktionsModElements instance) {
		super(instance, 45);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				MinecraftEragonFraktionsMod.LOGGER.warn("Failed to load dependency entity for procedure EmpireSoldierRightClickedOnEntity!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if ((((entity.getDisplayName().getString())).equals("Empiresoldier"))) {
			entity.setCustomName(new StringTextComponent("Empiresoldier (staying)"));
			entity.getPersistentData().putBoolean("staying", (false));
		} else if ((((entity.getDisplayName().getString())).equals("Empiresoldier (staying)"))) {
			entity.setCustomName(new StringTextComponent("Empiresoldier"));
			entity.getPersistentData().putBoolean("staying", (true));
		}
	}
}
