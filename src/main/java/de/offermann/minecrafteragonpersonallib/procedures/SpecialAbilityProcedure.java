package de.offermann.minecrafteragonpersonallib.procedures;

import net.minecraft.util.text.StringTextComponent;
import net.minecraft.potion.Effects;
import net.minecraft.potion.EffectInstance;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;

import java.util.Random;
import java.util.Map;

import de.offermann.minecrafteragonpersonallib.potion.LastManStandingPotion;
import de.offermann.minecrafteragonpersonallib.item.SpeerItem;
import de.offermann.minecrafteragonpersonallib.item.DwarfsThrowableAxeItem;
import de.offermann.minecrafteragonpersonallib.MinecraftEragonPersonallibModElements;
import de.offermann.minecrafteragonpersonallib.MinecraftEragonPersonallibMod;

@MinecraftEragonPersonallibModElements.ModElement.Tag
public class SpecialAbilityProcedure extends MinecraftEragonPersonallibModElements.ModElement {
	public SpecialAbilityProcedure(MinecraftEragonPersonallibModElements instance) {
		super(instance, 9);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				MinecraftEragonPersonallibMod.LOGGER.warn("Failed to load dependency entity for procedure SpecialAbility!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if (((entity.getPersistentData().getDouble("varden")) == 1)) {
			if (((entity.getPersistentData().getDouble("clockreadystrong")) == 1)) {
				if (entity instanceof LivingEntity)
					((LivingEntity) entity).addPotionEffect(new EffectInstance(Effects.ABSORPTION, (int) 600, (int) 1, (false), (false)));
				if (entity instanceof LivingEntity)
					((LivingEntity) entity).addPotionEffect(new EffectInstance(Effects.STRENGTH, (int) 300, (int) 0, (false), (false)));
				if (entity instanceof LivingEntity)
					((LivingEntity) entity).addPotionEffect(new EffectInstance(Effects.SPEED, (int) 300, (int) 0, (false), (false)));
				if (entity instanceof LivingEntity)
					((LivingEntity) entity).addPotionEffect(new EffectInstance(LastManStandingPotion.potion, (int) 300, (int) 0, (false), (false)));
				entity.getPersistentData().putDouble("clock", 0);
				entity.getPersistentData().putDouble("clockreadystrong", 0);
			} else {
				if (entity instanceof PlayerEntity && !entity.world.isRemote()) {
					((PlayerEntity) entity).sendStatusMessage(new StringTextComponent(
							(((("\u00A7cDie Abklinkzeit betr\u00E4gt noch ") + "" + ((6000 - (entity.getPersistentData().getDouble("clock")))))) + ""
									+ ("\u00A7Ticks!"))),
							(true));
				}
			}
		} else if (((entity.getPersistentData().getDouble("elfen")) == 1)) {
			if (((entity.getPersistentData().getDouble("clockreadystrong")) == 1)) {
				if (entity instanceof LivingEntity) {
					Entity _ent = entity;
					if (!_ent.world.isRemote()) {
						ArrowEntity entityToSpawn = new ArrowEntity(_ent.world, (LivingEntity) entity);
						entityToSpawn.shoot(entity.getLookVec().x, entity.getLookVec().y, entity.getLookVec().z, (float) 5, 0);
						entityToSpawn.setDamage((float) 10);
						entityToSpawn.setKnockbackStrength((int) 5);
						_ent.world.addEntity(entityToSpawn);
					}
				}
				entity.getPersistentData().putDouble("clock", 0);
				entity.getPersistentData().putDouble("clockreadystrong", 0);
			} else {
				if (entity instanceof PlayerEntity && !entity.world.isRemote()) {
					((PlayerEntity) entity)
							.sendStatusMessage(
									new StringTextComponent((((("\u00EF\u00BF\u00BDcDie Abklingzeit betr\u00EF\u00BF\u00BDgt noch: ") + ""
											+ ((6000 - (entity.getPersistentData().getDouble("clock")))))) + "" + (" \u00EF\u00BF\u00BDcTicks!"))),
									(true));
				}
			}
		} else if (((entity.getPersistentData().getDouble("zwergen")) == 1)) {
			if (((entity.getPersistentData().getDouble("clockreadystrong")) == 1)) {
				if (entity instanceof LivingEntity) {
					Entity _ent = entity;
					if (!_ent.world.isRemote()) {
						DwarfsThrowableAxeItem.shoot(_ent.world, (LivingEntity) entity, new Random(), (float) 5, (float) 10, (int) 1);
					}
				}
				entity.getPersistentData().putDouble("clock", 0);
				entity.getPersistentData().putDouble("clockreadystrong", 0);
			} else {
				if (entity instanceof PlayerEntity && !entity.world.isRemote()) {
					((PlayerEntity) entity)
							.sendStatusMessage(
									new StringTextComponent((((("\u00EF\u00BF\u00BDcDie Abklingzeit betr\u00EF\u00BF\u00BDgt noch: ") + ""
											+ ((6000 - (entity.getPersistentData().getDouble("clock")))))) + "" + (" \u00EF\u00BF\u00BDcTicks!"))),
									(true));
				}
			}
		} else if (((entity.getPersistentData().getDouble("surda")) == 1)) {
			if (((entity.getPersistentData().getDouble("clockreadystrong")) == 1)) {
				if (entity instanceof LivingEntity) {
					Entity _ent = entity;
					if (!_ent.world.isRemote()) {
						SpeerItem.shoot(_ent.world, (LivingEntity) entity, new Random(), (float) 5, (float) 10, (int) 1);
					}
				}
				entity.getPersistentData().putDouble("clock", 0);
				entity.getPersistentData().putDouble("clockreadystrong", 0);
			} else {
				if (entity instanceof PlayerEntity && !entity.world.isRemote()) {
					((PlayerEntity) entity).sendStatusMessage(new StringTextComponent(
							(((("\u00A7cDie Abklinkzeit betr\u00E4gt noch ") + "" + ((6000 - (entity.getPersistentData().getDouble("clock")))))) + ""
									+ ("\u00A7cTicks!"))),
							(true));
				}
			}
		} else if (((entity.getPersistentData().getDouble("empire_soldier")) == 1)) {
			if (entity instanceof PlayerEntity && !entity.world.isRemote()) {
				((PlayerEntity) entity).sendStatusMessage(new StringTextComponent("\u00EF\u00BF\u00BDcin working"), (false));
			}
		} else if (((entity.getPersistentData().getDouble("shade")) == 1)) {
			if (entity instanceof PlayerEntity && !entity.world.isRemote()) {
				((PlayerEntity) entity).sendStatusMessage(new StringTextComponent("\u00EF\u00BF\u00BDcin working"), (false));
			}
		} else if (((entity.getPersistentData().getDouble("ra'zac")) == 1)) {
			if (entity instanceof PlayerEntity && !entity.world.isRemote()) {
				((PlayerEntity) entity).sendStatusMessage(new StringTextComponent("\u00EF\u00BF\u00BDcin working"), (false));
			}
		} else if (((entity.getPersistentData().getDouble("urgal")) == 1)) {
			if (((entity.getPersistentData().getDouble("clockready")) == 1)) {
				entity.getPersistentData().putDouble("clock", 0);
				entity.getPersistentData().putDouble("clockready", 0);
				entity.getPersistentData().putBoolean("dash", (true));
			} else {
				if (entity instanceof PlayerEntity && !entity.world.isRemote()) {
					((PlayerEntity) entity).sendStatusMessage(new StringTextComponent(
							(((("\u00A7cDie Abklinkzeit betr\u00E4gt noch ") + "" + ((3000 - (entity.getPersistentData().getDouble("clock")))))) + ""
									+ ("\u00A7cTicks!"))),
							(true));
				}
			}
		}
	}
}
