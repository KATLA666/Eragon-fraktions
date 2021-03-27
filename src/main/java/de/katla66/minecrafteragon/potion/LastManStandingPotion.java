
package de.katla66.minecrafteragon.potion;

import net.minecraftforge.registries.ObjectHolder;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.RegistryEvent;

import net.minecraft.util.ResourceLocation;
import net.minecraft.potion.Potion;
import net.minecraft.potion.EffectType;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effect;

import de.katla66.minecrafteragon.MinecraftEragonFraktionsModElements;

@MinecraftEragonFraktionsModElements.ModElement.Tag
public class LastManStandingPotion extends MinecraftEragonFraktionsModElements.ModElement {
	@ObjectHolder("minecraft_eragon_fraktions:last_man_standing")
	public static final Effect potion = null;
	@ObjectHolder("minecraft_eragon_fraktions:last_man_standing")
	public static final Potion potionType = null;
	public LastManStandingPotion(MinecraftEragonFraktionsModElements instance) {
		super(instance, 32);
		FMLJavaModLoadingContext.get().getModEventBus().register(this);
	}

	@SubscribeEvent
	public void registerEffect(RegistryEvent.Register<Effect> event) {
		event.getRegistry().register(new EffectCustom());
	}

	@SubscribeEvent
	public void registerPotion(RegistryEvent.Register<Potion> event) {
		event.getRegistry().register(new PotionCustom());
	}
	public static class PotionCustom extends Potion {
		public PotionCustom() {
			super(new EffectInstance(potion, 3600));
			setRegistryName("last_man_standing");
		}
	}

	public static class EffectCustom extends Effect {
		private final ResourceLocation potionIcon;
		public EffectCustom() {
			super(EffectType.NEUTRAL, -10092493);
			setRegistryName("last_man_standing");
			potionIcon = new ResourceLocation("minecraft_eragon_fraktions:textures/elfen_strength_icon.png");
		}

		@Override
		public String getName() {
			return "effect.last_man_standing";
		}

		@Override
		public boolean isBeneficial() {
			return false;
		}

		@Override
		public boolean isInstant() {
			return true;
		}

		@Override
		public boolean shouldRenderInvText(EffectInstance effect) {
			return true;
		}

		@Override
		public boolean shouldRender(EffectInstance effect) {
			return true;
		}

		@Override
		public boolean shouldRenderHUD(EffectInstance effect) {
			return true;
		}

		@Override
		public boolean isReady(int duration, int amplifier) {
			return true;
		}
	}
}
