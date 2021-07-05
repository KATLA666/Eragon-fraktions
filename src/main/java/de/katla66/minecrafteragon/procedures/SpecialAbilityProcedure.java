package de.katla66.minecrafteragon.procedures;

import net.minecraft.world.IWorld;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.scoreboard.ScoreCriteria;
import net.minecraft.scoreboard.Score;
import net.minecraft.potion.Effects;
import net.minecraft.potion.EffectInstance;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;

import java.util.Random;
import java.util.Map;
import java.util.HashMap;

import java.io.IOException;
import java.io.FileReader;
import java.io.File;
import java.io.BufferedReader;

import de.katla66.minecrafteragon.potion.LastManStandingPotion;
import de.katla66.minecrafteragon.item.SpeerItem;
import de.katla66.minecrafteragon.item.DwarfsThrowableAxeItem;
import de.katla66.minecrafteragon.MinecraftEragonFraktionsModVariables;
import de.katla66.minecrafteragon.MinecraftEragonFraktionsModElements;
import de.katla66.minecrafteragon.MinecraftEragonFraktionsMod;

import com.google.gson.JsonObject;
import com.google.gson.Gson;

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
		double MaxEmpireSoldier = 0;
		double DecayTimeLong = 0;
		double DecyTimeshort = 0;
		File eragonConfig = new File("config", File.separator + "eragon factions config.json");
		{
			try {
				BufferedReader eragonConfigReader = new BufferedReader(new FileReader(eragonConfig));
				StringBuilder jsonstringbuilder = new StringBuilder();
				String line;
				while ((line = eragonConfigReader.readLine()) != null) {
					jsonstringbuilder.append(line);
				}
				eragonConfigReader.close();
				JsonObject gson = new Gson().fromJson(jsonstringbuilder.toString(), JsonObject.class);
				MaxEmpireSoldier = (double) gson.get("Max Empire Soldier (standard: 4)").getAsDouble();
				DecayTimeLong = (double) gson.get("Decay time long in ticks (standard: 6000)").getAsDouble();
				DecyTimeshort = (double) gson.get("Decay time short in ticks (standard: 3000)").getAsDouble();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if ((((entity.getCapability(MinecraftEragonFraktionsModVariables.PLAYER_VARIABLES_CAPABILITY, null)
				.orElse(new MinecraftEragonFraktionsModVariables.PlayerVariables())).varden) == 1)) {
			if (((entity.getPersistentData().getDouble("clockready")) == 1)) {
				if (entity instanceof LivingEntity)
					((LivingEntity) entity).addPotionEffect(new EffectInstance(Effects.ABSORPTION, (int) 600, (int) 1, (false), (false)));
				if (entity instanceof LivingEntity)
					((LivingEntity) entity).addPotionEffect(new EffectInstance(Effects.STRENGTH, (int) 300, (int) 0, (false), (false)));
				if (entity instanceof LivingEntity)
					((LivingEntity) entity).addPotionEffect(new EffectInstance(Effects.SPEED, (int) 300, (int) 0, (false), (false)));
				if (entity instanceof LivingEntity)
					((LivingEntity) entity).addPotionEffect(new EffectInstance(LastManStandingPotion.potion, (int) 300, (int) 0, (false), (false)));
				entity.getPersistentData().putDouble("clock", 0);
				entity.getPersistentData().putDouble("clockready", 0);
			} else {
				if (entity instanceof PlayerEntity && !entity.world.isRemote()) {
					((PlayerEntity) entity).sendStatusMessage(new StringTextComponent(
							(((("\u00A7cThe decay time is still ") + "" + (((DecyTimeshort) - (entity.getPersistentData().getDouble("clock")))))) + ""
									+ ("Ticks!"))),
							(true));
				}
			}
		} else if ((((entity.getCapability(MinecraftEragonFraktionsModVariables.PLAYER_VARIABLES_CAPABILITY, null)
				.orElse(new MinecraftEragonFraktionsModVariables.PlayerVariables())).elfen) == 1)) {
			if (((entity.getPersistentData().getDouble("clockreadystrong")) == 1)) {
				if (entity instanceof LivingEntity) {
					Entity _ent = entity;
					if (!_ent.world.isRemote()) {
						ArrowEntity entityToSpawn = new ArrowEntity(_ent.world, (LivingEntity) entity);
						entityToSpawn.shoot(entity.getLookVec().x, entity.getLookVec().y, entity.getLookVec().z, (float) 10, 0);
						entityToSpawn.setDamage((float) 6);
						entityToSpawn.setKnockbackStrength((int) 5);
						_ent.world.addEntity(entityToSpawn);
					}
				}
				entity.getPersistentData().putDouble("clock", 0);
				entity.getPersistentData().putDouble("clockreadystrong", 0);
			} else {
				if (entity instanceof PlayerEntity && !entity.world.isRemote()) {
					((PlayerEntity) entity).sendStatusMessage(new StringTextComponent(
							(((("\u00A7cThe decay time is still ") + "" + (((DecayTimeLong) - (entity.getPersistentData().getDouble("clock")))))) + ""
									+ (" Ticks!"))),
							(true));
				}
			}
		} else if ((((entity.getCapability(MinecraftEragonFraktionsModVariables.PLAYER_VARIABLES_CAPABILITY, null)
				.orElse(new MinecraftEragonFraktionsModVariables.PlayerVariables())).zwergen) == 1)) {
			if (((entity.getPersistentData().getDouble("clockreadystrong")) == 1)) {
				if (entity instanceof LivingEntity) {
					Entity _ent = entity;
					if (!_ent.world.isRemote()) {
						DwarfsThrowableAxeItem.shoot(_ent.world, (LivingEntity) entity, new Random(), (float) 5, (float) 3, (int) 1);
					}
				}
				entity.getPersistentData().putDouble("clock", 0);
				entity.getPersistentData().putDouble("clockreadystrong", 0);
			} else {
				if (entity instanceof PlayerEntity && !entity.world.isRemote()) {
					((PlayerEntity) entity).sendStatusMessage(new StringTextComponent(
							(((("\u00A7cThe decay time is still ") + "" + (((DecayTimeLong) - (entity.getPersistentData().getDouble("clock")))))) + ""
									+ ("Ticks!"))),
							(true));
				}
			}
		} else if ((((entity.getCapability(MinecraftEragonFraktionsModVariables.PLAYER_VARIABLES_CAPABILITY, null)
				.orElse(new MinecraftEragonFraktionsModVariables.PlayerVariables())).surda) == 1)) {
			if (((entity.getPersistentData().getDouble("clockreadystrong")) == 1)) {
				if (entity instanceof LivingEntity) {
					Entity _ent = entity;
					if (!_ent.world.isRemote()) {
						SpeerItem.shoot(_ent.world, (LivingEntity) entity, new Random(), (float) 5, (float) 3, (int) 1);
					}
				}
				entity.getPersistentData().putDouble("clock", 0);
				entity.getPersistentData().putDouble("clockreadystrong", 0);
			} else {
				if (entity instanceof PlayerEntity && !entity.world.isRemote()) {
					((PlayerEntity) entity).sendStatusMessage(new StringTextComponent(
							(((("\u00A7cThe decay time is still ") + "" + (((DecayTimeLong) - (entity.getPersistentData().getDouble("clock")))))) + ""
									+ ("Ticks!"))),
							(true));
				}
			}
		} else if ((((entity.getCapability(MinecraftEragonFraktionsModVariables.PLAYER_VARIABLES_CAPABILITY, null)
				.orElse(new MinecraftEragonFraktionsModVariables.PlayerVariables())).empireSoldier) == 1)) {
			if (((entity.getPersistentData().getDouble("clockreadystrong")) == 1)) {
				if (((new Object() {
					public int getScore(String score) {
						if (entity instanceof PlayerEntity) {
							Scoreboard _sc = ((PlayerEntity) entity).getWorldScoreboard();
							ScoreObjective _so = _sc.getObjective(score);
							if (_so != null) {
								Score _scr = _sc.getOrCreateScore(((PlayerEntity) entity).getScoreboardName(), _so);
								return _scr.getScorePoints();
							}
						}
						return 0;
					}
				}.getScore("CEM")) < (MaxEmpireSoldier))) {
					{
						Map<String, Object> $_dependencies = new HashMap<>();
						$_dependencies.put("entity", entity);
						SummonSoldierProcedure.executeProcedure($_dependencies);
					}
					{
						Entity _ent = entity;
						if (_ent instanceof PlayerEntity) {
							Scoreboard _sc = ((PlayerEntity) _ent).getWorldScoreboard();
							ScoreObjective _so = _sc.getObjective("CEM");
							if (_so == null) {
								_so = _sc.addObjective("CEM", ScoreCriteria.DUMMY, new StringTextComponent("CEM"), ScoreCriteria.RenderType.INTEGER);
							}
							Score _scr = _sc.getOrCreateScore(((PlayerEntity) _ent).getScoreboardName(), _so);
							_scr.setScorePoints((int) ((new Object() {
								public int getScore(String score) {
									if (entity instanceof PlayerEntity) {
										Scoreboard _sc = ((PlayerEntity) entity).getWorldScoreboard();
										ScoreObjective _so = _sc.getObjective(score);
										if (_so != null) {
											Score _scr = _sc.getOrCreateScore(((PlayerEntity) entity).getScoreboardName(), _so);
											return _scr.getScorePoints();
										}
									}
									return 0;
								}
							}.getScore("CEM")) + 1));
						}
					}
					entity.getPersistentData().putDouble("clock", 0);
					entity.getPersistentData().putDouble("clockreadystrong", 0);
					if (entity instanceof PlayerEntity && !entity.world.isRemote()) {
						((PlayerEntity) entity).sendStatusMessage(new StringTextComponent((((("\u00A7a") + "" + ((new Object() {
							public int getScore(String score) {
								if (entity instanceof PlayerEntity) {
									Scoreboard _sc = ((PlayerEntity) entity).getWorldScoreboard();
									ScoreObjective _so = _sc.getObjective(score);
									if (_so != null) {
										Score _scr = _sc.getOrCreateScore(((PlayerEntity) entity).getScoreboardName(), _so);
										return _scr.getScorePoints();
									}
								}
								return 0;
							}
						}.getScore("CEM"))))) + "" + (((" \u00A7aSoldier(s) of ") + "" + ((MaxEmpireSoldier)))))), (true));
					}
				} else {
					if (entity instanceof PlayerEntity && !entity.world.isRemote()) {
						((PlayerEntity) entity).sendStatusMessage(new StringTextComponent("\u00A7cMaximum soldier limit reached"), (true));
					}
				}
			} else {
				if (entity instanceof PlayerEntity && !entity.world.isRemote()) {
					((PlayerEntity) entity).sendStatusMessage(new StringTextComponent(
							(((("\u00A7cThe decay time is still ") + "" + (((DecayTimeLong) - (entity.getPersistentData().getDouble("clock")))))) + ""
									+ (" Ticks!"))),
							(true));
				}
			}
		} else if ((((entity.getCapability(MinecraftEragonFraktionsModVariables.PLAYER_VARIABLES_CAPABILITY, null)
				.orElse(new MinecraftEragonFraktionsModVariables.PlayerVariables())).shade) == 1)) {
			if (((entity.getPersistentData().getDouble("clockready")) == 1)) {
				{
					Entity _ent = entity;
					if (!_ent.world.isRemote && _ent.world.getServer() != null) {
						_ent.world.getServer().getCommandManager().handleCommand(
								_ent.getCommandSource().withFeedbackDisabled().withPermissionLevel(4),
								"summon minecraft:fireball ^ ^1 ^2 {ExplosionPower:3.5,direction:[0.0,0.0,0.0]}");
					}
				}
				entity.getPersistentData().putDouble("clock", 0);
				entity.getPersistentData().putDouble("clockready", 0);
			} else {
				if (entity instanceof PlayerEntity && !entity.world.isRemote()) {
					((PlayerEntity) entity).sendStatusMessage(new StringTextComponent(
							(((("\u00A7cThe decay time is still ") + "" + (((DecyTimeshort) - (entity.getPersistentData().getDouble("clock")))))) + ""
									+ (" \u00A7cTicks"))),
							(true));
				}
			}
		} else if ((((entity.getCapability(MinecraftEragonFraktionsModVariables.PLAYER_VARIABLES_CAPABILITY, null)
				.orElse(new MinecraftEragonFraktionsModVariables.PlayerVariables())).razac) == 1)) {
			if (((entity.getPersistentData().getDouble("clockready")) == 1)) {
				{
					Map<String, Object> $_dependencies = new HashMap<>();
					$_dependencies.put("x", x);
					$_dependencies.put("y", y);
					$_dependencies.put("z", z);
					$_dependencies.put("world", world);
					LahmenderAhtemRazacProcedure.executeProcedure($_dependencies);
				}
				entity.getPersistentData().putDouble("clock", 0);
				entity.getPersistentData().putDouble("clockready", 0);
			} else {
				if (entity instanceof PlayerEntity && !entity.world.isRemote()) {
					((PlayerEntity) entity).sendStatusMessage(new StringTextComponent(
							(((("\u00A7cThe decay time is still ") + "" + (((DecyTimeshort) - (entity.getPersistentData().getDouble("clock")))))) + ""
									+ ("Ticks!"))),
							(true));
				}
			}
		}
		if ((((entity.getCapability(MinecraftEragonFraktionsModVariables.PLAYER_VARIABLES_CAPABILITY, null)
				.orElse(new MinecraftEragonFraktionsModVariables.PlayerVariables())).urgal) == 1)) {
			if ((((entity instanceof PlayerEntity) ? ((PlayerEntity) entity).getFoodStats().getFoodLevel() : 0) >= 15)) {
				entity.setMotion((Math.sin(Math.toRadians(((entity.rotationYaw) + 180))) * 3), Math.sin(Math.toRadians((0 - (entity.rotationPitch)))),
						(Math.cos(Math.toRadians((entity.rotationYaw))) * 3));
				if (entity instanceof PlayerEntity)
					((PlayerEntity) entity).getFoodStats().setFoodLevel(
							(int) (((entity instanceof PlayerEntity) ? ((PlayerEntity) entity).getFoodStats().getFoodLevel() : 0) - 12));
			} else {
				if (entity instanceof PlayerEntity && !entity.world.isRemote()) {
					((PlayerEntity) entity).sendStatusMessage(new StringTextComponent("\u00A7cNot enough Food!"), (true));
				}
			}
		}
	}
}
