package de.katla66.minecrafteragon.procedures;

import net.minecraft.world.IWorld;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.potion.Effects;
import net.minecraft.potion.EffectInstance;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;

import java.util.Random;
import java.util.Map;
import java.util.HashMap;

import de.katla66.minecrafteragon.potion.LastManStandingPotion;
import de.katla66.minecrafteragon.item.SpeerItem;
import de.katla66.minecrafteragon.item.FireballshotItem;
import de.katla66.minecrafteragon.item.DwarfsThrowableAxeItem;
import de.katla66.minecrafteragon.MinecraftEragonFraktionsModElements;
import de.katla66.minecrafteragon.MinecraftEragonFraktionsMod;

@MinecraftEragonFraktionsModElements.ModElement.Tag
public class SpecialAbilityProcedure extends MinecraftEragonFraktionsModElements.ModElement {
	public SpecialAbilityProcedure(MinecraftEragonFraktionsModElements instance) {
		super(instance, 9);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				MinecraftEragonFraktionsMod.LOGGER.warn("Failed to load dependency entity for procedure SpecialAbility!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				MinecraftEragonFraktionsMod.LOGGER.warn("Failed to load dependency x for procedure SpecialAbility!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				MinecraftEragonFraktionsMod.LOGGER.warn("Failed to load dependency y for procedure SpecialAbility!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				MinecraftEragonFraktionsMod.LOGGER.warn("Failed to load dependency z for procedure SpecialAbility!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				MinecraftEragonFraktionsMod.LOGGER.warn("Failed to load dependency world for procedure SpecialAbility!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
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
							(((("\u00A7cThe decay time is still") + "" + ((6000 - (entity.getPersistentData().getDouble("clock")))))) + ""
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
					((PlayerEntity) entity).sendStatusMessage(new StringTextComponent(
							(((("\u00A7cThe decay time is still") + "" + ((6000 - (entity.getPersistentData().getDouble("clock")))))) + ""
									+ (" \u00EF\u00BF\u00BDcTicks!"))),
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
					((PlayerEntity) entity).sendStatusMessage(new StringTextComponent(
							(((("\u00A7cThe decay time is still") + "" + ((6000 - (entity.getPersistentData().getDouble("clock")))))) + ""
									+ (" \u00EF\u00BF\u00BDcTicks!"))),
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
							(((("\u00A7cThe decay time is still") + "" + ((6000 - (entity.getPersistentData().getDouble("clock")))))) + ""
									+ ("\u00A7cTicks!"))),
							(true));
				}
			}
		} else if (((entity.getPersistentData().getDouble("empire_soldier")) == 1)) {
			if (((entity.getPersistentData().getDouble("clockreadystrong")) == 1)) {
				{
					Map<String, Object> $_dependencies = new HashMap<>();
					$_dependencies.put("entity", entity);
					SummonSoldierProcedure.executeProcedure($_dependencies);
				}
			} else {
				if (entity instanceof PlayerEntity && !entity.world.isRemote()) {
					((PlayerEntity) entity).sendStatusMessage(new StringTextComponent(
							(((("\u00A7cThe decay time is still") + "" + ((6000 - (entity.getPersistentData().getDouble("clock")))))) + ""
									+ ("\u00A7cTicks!"))),
							(true));
				}
			}
		} else if (((entity.getPersistentData().getDouble("shade")) == 1)) {
			if (entity instanceof LivingEntity) {
				Entity _ent = entity;
				if (!_ent.world.isRemote()) {
					FireballshotItem.shoot(_ent.world, (LivingEntity) entity, new Random(), (float) 1, (float) 5, (int) 5);
				}
			}
		} else if (((entity.getPersistentData().getDouble("ra'zac")) == 1)) {
			if (((entity.getPersistentData().getDouble("clockreadystrong")) == 1)) {
				{
					Map<String, Object> $_dependencies = new HashMap<>();
					$_dependencies.put("x", x);
					$_dependencies.put("y", y);
					$_dependencies.put("z", z);
					$_dependencies.put("world", world);
					LahmenderAhtemRazacProcedure.executeProcedure($_dependencies);
				}
				entity.getPersistentData().putDouble("clock", 0);
				entity.getPersistentData().putDouble("clockreadystrong", 0);
			} else {
				if (entity instanceof PlayerEntity && !entity.world.isRemote()) {
					((PlayerEntity) entity).sendStatusMessage(new StringTextComponent(
							(((("\u00A7cThe decay time is still") + "" + ((6000 - (entity.getPersistentData().getDouble("clock")))))) + ""
									+ ("\u00A7cTicks!"))),
							(true));
				}
			}
		}
		if (((entity.getPersistentData().getDouble("urgal")) == 1)) {
			if ((((entity instanceof PlayerEntity) ? ((PlayerEntity) entity).getFoodStats().getFoodLevel() : 0) >= 15)) {
				entity.setMotion((Math.sin(Math.toRadians(((entity.rotationYaw) + 180))) * 3), Math.sin(Math.toRadians((0 - (entity.rotationPitch)))),
						(Math.cos(Math.toRadians((entity.rotationYaw))) * 3));
				if (entity instanceof PlayerEntity)
					((PlayerEntity) entity).getFoodStats().setFoodLevel(
							(int) (((entity instanceof PlayerEntity) ? ((PlayerEntity) entity).getFoodStats().getFoodLevel() : 0) - 10));
			} else {
				if (entity instanceof PlayerEntity && !entity.world.isRemote()) {
					((PlayerEntity) entity).sendStatusMessage(new StringTextComponent("\u00A7cNot enough Food!"), (true));
				}
			}
		}
	}
}
