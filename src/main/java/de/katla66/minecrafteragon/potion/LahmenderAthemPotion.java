
package de.katla66.minecrafteragon.potion;

import net.minecraftforge.registries.ObjectHolder;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.RegistryEvent;

import net.minecraft.world.World;
import net.minecraft.util.ResourceLocation;
import net.minecraft.potion.EffectType;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effect;
import net.minecraft.entity.ai.attributes.AttributeModifierManager;
import net.minecraft.entity.LivingEntity;

import java.util.Map;
import java.util.HashMap;

import de.katla66.minecrafteragon.procedures.LahmenderAthemPotionStartedappliedProcedure;
import de.katla66.minecrafteragon.MinecraftEragonFraktionsModElements;

@MinecraftEragonFraktionsModElements.ModElement.Tag
public class LahmenderAthemPotion extends MinecraftEragonFraktionsModElements.ModElement {
	@ObjectHolder("minecraft_eragon_fraktions:lahmender_athem")
	public static final Effect potion = null;
	public LahmenderAthemPotion(MinecraftEragonFraktionsModElements instance) {
		super(instance, 48);
		FMLJavaModLoadingContext.get().getModEventBus().register(this);
	}

	@SubscribeEvent
	public void registerEffect(RegistryEvent.Register<Effect> event) {
		event.getRegistry().register(new EffectCustom());
	}
	public static class EffectCustom extends Effect {
		private final ResourceLocation potionIcon;
		public EffectCustom() {
			super(EffectType.HARMFUL, -6711040);
			setRegistryName("lahmender_athem");
			potionIcon = new ResourceLocation("minecraft_eragon_fraktions:textures/fireball.png");
		}

		@Override
		public String getName() {
			return "effect.lahmender_athem";
		}

		@Override
		public boolean isBeneficial() {
			return false;
		}

		@Override
		public boolean isInstant() {
			return false;
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
		public void applyAttributesModifiersToEntity(LivingEntity entity, AttributeModifierManager attributeMapIn, int amplifier) {
			World world = entity.world;
			double x = entity.getPosX();
			double y = entity.getPosY();
			double z = entity.getPosZ();
			{
				Map<String, Object> $_dependencies = new HashMap<>();
				$_dependencies.put("entity", entity);
				LahmenderAthemPotionStartedappliedProcedure.executeProcedure($_dependencies);
			}
		}

		@Override
		public boolean isReady(int duration, int amplifier) {
			return true;
		}
	}
}
