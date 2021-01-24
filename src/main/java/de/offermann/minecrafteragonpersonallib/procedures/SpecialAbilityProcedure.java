package de.offermann.minecrafteragonpersonallib.procedures;

import net.minecraft.world.World;
import net.minecraft.world.IWorld;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;

import java.util.Random;
import java.util.Map;

import de.offermann.minecrafteragonpersonallib.item.DwarfsThrowableAxeItem;
import de.offermann.minecrafteragonpersonallib.MinecraftEragonPersonallibModElements;

@MinecraftEragonPersonallibModElements.ModElement.Tag
public class SpecialAbilityProcedure extends MinecraftEragonPersonallibModElements.ModElement {
	public SpecialAbilityProcedure(MinecraftEragonPersonallibModElements instance) {
		super(instance, 9);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				System.err.println("Failed to load dependency entity for procedure SpecialAbility!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				System.err.println("Failed to load dependency world for procedure SpecialAbility!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		IWorld world = (IWorld) dependencies.get("world");
		if (((entity.getPersistentData().getDouble("varden")) == 1)) {
			if (entity instanceof PlayerEntity && !entity.world.isRemote) {
				((PlayerEntity) entity).sendStatusMessage(new StringTextComponent("\u00EF\u00BF\u00BDcin working"), (false));
			}
		} else if (((entity.getPersistentData().getDouble("elfen")) == 1)) {
			if (((entity.getPersistentData().getDouble("clockreadystrong")) == 1)) {
				if (world instanceof World && !world.getWorld().isRemote && entity instanceof LivingEntity) {
					ArrowEntity entityToSpawn = new ArrowEntity(world.getWorld(), (LivingEntity) entity);
					entityToSpawn.shoot(entity.getLookVec().x, entity.getLookVec().y, entity.getLookVec().z, (float) 5, 0);
					entityToSpawn.setDamage((float) 10);
					entityToSpawn.setKnockbackStrength((int) 1);
					world.addEntity(entityToSpawn);
				}
				entity.getPersistentData().putDouble("clock", 0);
				entity.getPersistentData().putDouble("clockreadystrong", 0);
			} else {
				if (entity instanceof PlayerEntity && !entity.world.isRemote) {
					((PlayerEntity) entity)
							.sendStatusMessage(
									new StringTextComponent((((("\u00EF\u00BF\u00BDcDie Abklingzeit betr\u00EF\u00BF\u00BDgt noch: ") + ""
											+ ((6000 - (entity.getPersistentData().getDouble("clock")))))) + "" + (" \u00EF\u00BF\u00BDcTicks!"))),
									(true));
				}
			}
		} else if (((entity.getPersistentData().getDouble("zwergen")) == 1)) {
			if (((entity.getPersistentData().getDouble("clockready")) == 1)) {
				if (world instanceof World && !world.getWorld().isRemote && entity instanceof LivingEntity) {
					DwarfsThrowableAxeItem.shoot(world.getWorld(), (LivingEntity) entity, new Random(), (float) 5, (float) 10, (int) 1);
				}
				entity.getPersistentData().putDouble("clock", 0);
				entity.getPersistentData().putDouble("clockready", 0);
			} else {
				if (entity instanceof PlayerEntity && !entity.world.isRemote) {
					((PlayerEntity) entity)
							.sendStatusMessage(
									new StringTextComponent((((("\u00EF\u00BF\u00BDcDie Abklingzeit betr\u00EF\u00BF\u00BDgt noch: ") + ""
											+ ((3000 - (entity.getPersistentData().getDouble("clock")))))) + "" + (" \u00EF\u00BF\u00BDcTicks!"))),
									(true));
				}
			}
		} else if (((entity.getPersistentData().getDouble("surda")) == 1)) {
			if (entity instanceof PlayerEntity && !entity.world.isRemote) {
				((PlayerEntity) entity).sendStatusMessage(new StringTextComponent("\u00EF\u00BF\u00BDcin working"), (false));
			}
		} else if (((entity.getPersistentData().getDouble("empire_soldier")) == 1)) {
			if (entity instanceof PlayerEntity && !entity.world.isRemote) {
				((PlayerEntity) entity).sendStatusMessage(new StringTextComponent("\u00EF\u00BF\u00BDcin working"), (false));
			}
		} else if (((entity.getPersistentData().getDouble("shade")) == 1)) {
			if (entity instanceof PlayerEntity && !entity.world.isRemote) {
				((PlayerEntity) entity).sendStatusMessage(new StringTextComponent("\u00EF\u00BF\u00BDcin working"), (false));
			}
		} else if (((entity.getPersistentData().getDouble("ra'zac")) == 1)) {
			if (entity instanceof PlayerEntity && !entity.world.isRemote) {
				((PlayerEntity) entity).sendStatusMessage(new StringTextComponent("\u00EF\u00BF\u00BDcin working"), (false));
			}
		} else if (((entity.getPersistentData().getDouble("urgal")) == 1)) {
			if (((entity.getPersistentData().getDouble("clockready")) == 1)) {
				entity.setMotion((Math.sin(Math.toRadians(((entity.rotationYaw) + 180))) * 3), Math.sin(Math.toRadians((0 - (entity.rotationPitch)))),
						(Math.cos(Math.toRadians((entity.rotationYaw))) * 3));
				entity.getPersistentData().putDouble("clock", 0);
				entity.getPersistentData().putDouble("clockready", 0);
			} else {
				if (entity instanceof PlayerEntity && !entity.world.isRemote) {
					((PlayerEntity) entity).sendStatusMessage(new StringTextComponent((((("\u00A7cDie Abklingzeit betr\u00EF\u00BF\u00BDgt noch: ")
							+ "" + ((3000 - (entity.getPersistentData().getDouble("clock")))))) + "" + ("\u00A7cTicks!"))), (true));
				}
			}
		}
	}
}
