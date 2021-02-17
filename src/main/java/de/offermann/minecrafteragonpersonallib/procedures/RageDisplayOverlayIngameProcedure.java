package de.offermann.minecrafteragonpersonallib.procedures;

import net.minecraft.potion.EffectInstance;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;

import java.util.Map;
import java.util.Collection;

import de.offermann.minecrafteragonpersonallib.potion.LastManStandingPotion;
import de.offermann.minecrafteragonpersonallib.MinecraftEragonPersonallibModElements;
import de.offermann.minecrafteragonpersonallib.MinecraftEragonPersonallibMod;

@MinecraftEragonPersonallibModElements.ModElement.Tag
public class RageDisplayOverlayIngameProcedure extends MinecraftEragonPersonallibModElements.ModElement {
	public RageDisplayOverlayIngameProcedure(MinecraftEragonPersonallibModElements instance) {
		super(instance, 30);
	}

	public static boolean executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				MinecraftEragonPersonallibMod.LOGGER.warn("Failed to load dependency entity for procedure RageDisplayOverlayIngame!");
			return false;
		}
		Entity entity = (Entity) dependencies.get("entity");
		return (new Object() {
			boolean check(Entity _entity) {
				if (_entity instanceof LivingEntity) {
					Collection<EffectInstance> effects = ((LivingEntity) _entity).getActivePotionEffects();
					for (EffectInstance effect : effects) {
						if (effect.getPotion() == LastManStandingPotion.potion)
							return true;
					}
				}
				return false;
			}
		}.check(entity));
	}
}
