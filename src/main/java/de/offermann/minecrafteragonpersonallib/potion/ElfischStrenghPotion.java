
package de.offermann.minecrafteragonpersonallib.potion;

import net.minecraftforge.registries.ObjectHolder;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.RegistryEvent;

import net.minecraft.world.World;
import net.minecraft.util.ResourceLocation;
import net.minecraft.potion.EffectType;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effect;
import net.minecraft.entity.LivingEntity;

import java.util.Map;
import java.util.HashMap;

import de.offermann.minecrafteragonpersonallib.procedures.ElfischStrenghPotionStartedappliedProcedure;
import de.offermann.minecrafteragonpersonallib.MinecraftEragonPersonallibModElements;

@MinecraftEragonPersonallibModElements.ModElement.Tag
public class ElfischStrenghPotion extends MinecraftEragonPersonallibModElements.ModElement {
	@ObjectHolder("minecraft_eragon__personallib:elfisch_strengh")
	public static final Effect potion = null;
	public ElfischStrenghPotion(MinecraftEragonPersonallibModElements instance) {
		super(instance, 12);
		FMLJavaModLoadingContext.get().getModEventBus().register(this);
	}

	@SubscribeEvent
	public void registerEffect(RegistryEvent.Register<Effect> event) {
		event.getRegistry().register(new EffectCustom());
	}
	public static class EffectCustom extends Effect {
		private final ResourceLocation potionIcon;
		public EffectCustom() {
			super(EffectType.NEUTRAL, -6750208);
			setRegistryName("elfisch_strengh");
			potionIcon = new ResourceLocation("minecraft_eragon__personallib:textures/elfen_strength_icon128_pxl.png");
		}

		@Override
		public String getName() {
			return "effect.elfisch_strengh";
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
		public void performEffect(LivingEntity entity, int amplifier) {
			World world = entity.world;
			double x = entity.posX;
			double y = entity.posY;
			double z = entity.posZ;
			{
				Map<String, Object> $_dependencies = new HashMap<>();
				$_dependencies.put("entity", entity);
				ElfischStrenghPotionStartedappliedProcedure.executeProcedure($_dependencies);
			}
		}

		@Override
		public boolean isReady(int duration, int amplifier) {
			return true;
		}
	}
}
