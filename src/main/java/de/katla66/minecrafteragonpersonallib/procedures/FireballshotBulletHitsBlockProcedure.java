package de.katla66.minecrafteragonpersonallib.procedures;

import net.minecraft.world.World;
import net.minecraft.world.IWorld;
import net.minecraft.world.Explosion;

import java.util.Map;

import de.katla66.minecrafteragonpersonallib.MinecraftEragonFraktionsModElements;
import de.katla66.minecrafteragonpersonallib.MinecraftEragonFraktionsMod;

@MinecraftEragonFraktionsModElements.ModElement.Tag
public class FireballshotBulletHitsBlockProcedure extends MinecraftEragonFraktionsModElements.ModElement {
	public FireballshotBulletHitsBlockProcedure(MinecraftEragonFraktionsModElements instance) {
		super(instance, 43);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				MinecraftEragonFraktionsMod.LOGGER.warn("Failed to load dependency x for procedure FireballshotBulletHitsBlock!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				MinecraftEragonFraktionsMod.LOGGER.warn("Failed to load dependency y for procedure FireballshotBulletHitsBlock!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				MinecraftEragonFraktionsMod.LOGGER.warn("Failed to load dependency z for procedure FireballshotBulletHitsBlock!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				MinecraftEragonFraktionsMod.LOGGER.warn("Failed to load dependency world for procedure FireballshotBulletHitsBlock!");
			return;
		}
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		if (world instanceof World && !((World) world).isRemote) {
			((World) world).createExplosion(null, (int) x, (int) y, (int) z, (float) 5, Explosion.Mode.DESTROY);
		}
	}
}
