package de.offermann.minecrafteragonpersonallib.procedures;

import java.util.Map;

import de.offermann.minecrafteragonpersonallib.MinecraftEragonPersonallibModElements;

@MinecraftEragonPersonallibModElements.ModElement.Tag
public class RageDisplayOverlayIngameProcedure extends MinecraftEragonPersonallibModElements.ModElement {
	public RageDisplayOverlayIngameProcedure(MinecraftEragonPersonallibModElements instance) {
		super(instance, 30);
	}

	public static boolean executeProcedure(Map<String, Object> dependencies) {
		return (true);
	}
}
