package de.offermann.minecrafteragonpersonallib.procedures;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.common.MinecraftForge;

import net.minecraft.world.World;
import net.minecraft.potion.Effects;
import net.minecraft.potion.EffectInstance;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;

import java.util.Map;
import java.util.HashMap;
import java.util.Collection;

import de.offermann.minecrafteragonpersonallib.MinecraftEragonPersonallibModElements;
import de.offermann.minecrafteragonpersonallib.MinecraftEragonPersonallibMod;

@MinecraftEragonPersonallibModElements.ModElement.Tag
public class FraktionEmpireSoldierProcedure extends MinecraftEragonPersonallibModElements.ModElement {
	public FraktionEmpireSoldierProcedure(MinecraftEragonPersonallibModElements instance) {
		super(instance, 36);
		MinecraftForge.EVENT_BUS.register(this);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				MinecraftEragonPersonallibMod.LOGGER.warn("Failed to load dependency entity for procedure FraktionEmpireSoldier!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		double Speed = 0;
		double haste = 0;
		double poisinus = 0;
		double slowness = 0;
		double regenaration = 0;
		double absorbtion = 0;
		if (((entity.getPersistentData().getDouble("empire_soldier")) == 1)) {
			if ((((entity.getPersistentData().getBoolean("activepotionspeed")) == (false)) && (new Object() {
				boolean check(Entity _entity) {
					if (_entity instanceof LivingEntity) {
						Collection<EffectInstance> effects = ((LivingEntity) _entity).getActivePotionEffects();
						for (EffectInstance effect : effects) {
							if (effect.getPotion() == Effects.SPEED)
								return true;
						}
					}
					return false;
				}
			}.check(entity)))) {
				Speed = (double) (new Object() {
					int check(Entity _entity) {
						if (_entity instanceof LivingEntity) {
							Collection<EffectInstance> effects = ((LivingEntity) _entity).getActivePotionEffects();
							for (EffectInstance effect : effects) {
								if (effect.getPotion() == Effects.SPEED)
									return effect.getDuration();
							}
						}
						return 0;
					}
				}.check(entity));
				if (entity instanceof LivingEntity)
					((LivingEntity) entity).addPotionEffect(new EffectInstance(Effects.SPEED, (int) (Speed), (int) 1, (false), (false)));
				entity.getPersistentData().putBoolean("activepotionspeed", (true));
			} else if ((((entity.getPersistentData().getBoolean("activepotionhaste")) == (false)) && (new Object() {
				boolean check(Entity _entity) {
					if (_entity instanceof LivingEntity) {
						Collection<EffectInstance> effects = ((LivingEntity) _entity).getActivePotionEffects();
						for (EffectInstance effect : effects) {
							if (effect.getPotion() == Effects.HASTE)
								return true;
						}
					}
					return false;
				}
			}.check(entity)))) {
				haste = (double) (new Object() {
					int check(Entity _entity) {
						if (_entity instanceof LivingEntity) {
							Collection<EffectInstance> effects = ((LivingEntity) _entity).getActivePotionEffects();
							for (EffectInstance effect : effects) {
								if (effect.getPotion() == Effects.HASTE)
									return effect.getDuration();
							}
						}
						return 0;
					}
				}.check(entity));
				if (entity instanceof LivingEntity)
					((LivingEntity) entity).addPotionEffect(new EffectInstance(Effects.HASTE, (int) (haste), (int) 1, (false), (false)));
				entity.getPersistentData().putBoolean("activepotionhaste", (true));
			} else if ((((entity.getPersistentData().getBoolean("activepotionpoisinus")) == (false)) && (new Object() {
				boolean check(Entity _entity) {
					if (_entity instanceof LivingEntity) {
						Collection<EffectInstance> effects = ((LivingEntity) _entity).getActivePotionEffects();
						for (EffectInstance effect : effects) {
							if (effect.getPotion() == Effects.POISON)
								return true;
						}
					}
					return false;
				}
			}.check(entity)))) {
				poisinus = (double) (new Object() {
					int check(Entity _entity) {
						if (_entity instanceof LivingEntity) {
							Collection<EffectInstance> effects = ((LivingEntity) _entity).getActivePotionEffects();
							for (EffectInstance effect : effects) {
								if (effect.getPotion() == Effects.POISON)
									return effect.getDuration();
							}
						}
						return 0;
					}
				}.check(entity));
				if (entity instanceof LivingEntity)
					((LivingEntity) entity).addPotionEffect(new EffectInstance(Effects.POISON, (int) (poisinus), (int) 1, (false), (false)));
				entity.getPersistentData().putBoolean("activepotionpoisinus", (true));
			} else if ((((entity.getPersistentData().getBoolean("activepotionslowness")) == (false)) && (new Object() {
				boolean check(Entity _entity) {
					if (_entity instanceof LivingEntity) {
						Collection<EffectInstance> effects = ((LivingEntity) _entity).getActivePotionEffects();
						for (EffectInstance effect : effects) {
							if (effect.getPotion() == Effects.SLOWNESS)
								return true;
						}
					}
					return false;
				}
			}.check(entity)))) {
				slowness = (double) (new Object() {
					int check(Entity _entity) {
						if (_entity instanceof LivingEntity) {
							Collection<EffectInstance> effects = ((LivingEntity) _entity).getActivePotionEffects();
							for (EffectInstance effect : effects) {
								if (effect.getPotion() == Effects.SLOWNESS)
									return effect.getDuration();
							}
						}
						return 0;
					}
				}.check(entity));
				if (entity instanceof LivingEntity)
					((LivingEntity) entity).addPotionEffect(new EffectInstance(Effects.SLOWNESS, (int) (slowness), (int) 1, (false), (false)));
				entity.getPersistentData().putBoolean("activepotionslowness", (true));
			} else if ((((entity.getPersistentData().getBoolean("activepotionregenaration")) == (false)) && (new Object() {
				boolean check(Entity _entity) {
					if (_entity instanceof LivingEntity) {
						Collection<EffectInstance> effects = ((LivingEntity) _entity).getActivePotionEffects();
						for (EffectInstance effect : effects) {
							if (effect.getPotion() == Effects.REGENERATION)
								return true;
						}
					}
					return false;
				}
			}.check(entity)))) {
				regenaration = (double) (new Object() {
					int check(Entity _entity) {
						if (_entity instanceof LivingEntity) {
							Collection<EffectInstance> effects = ((LivingEntity) _entity).getActivePotionEffects();
							for (EffectInstance effect : effects) {
								if (effect.getPotion() == Effects.REGENERATION)
									return effect.getDuration();
							}
						}
						return 0;
					}
				}.check(entity));
				if (entity instanceof LivingEntity)
					((LivingEntity) entity)
							.addPotionEffect(new EffectInstance(Effects.REGENERATION, (int) (regenaration), (int) 1, (false), (false)));
				entity.getPersistentData().putBoolean("activepotionregenaration", (true));
			} else if ((((entity.getPersistentData().getBoolean("activepotionabsorption")) == (false)) && (new Object() {
				boolean check(Entity _entity) {
					if (_entity instanceof LivingEntity) {
						Collection<EffectInstance> effects = ((LivingEntity) _entity).getActivePotionEffects();
						for (EffectInstance effect : effects) {
							if (effect.getPotion() == Effects.ABSORPTION)
								return true;
						}
					}
					return false;
				}
			}.check(entity)))) {
				absorbtion = (double) (new Object() {
					int check(Entity _entity) {
						if (_entity instanceof LivingEntity) {
							Collection<EffectInstance> effects = ((LivingEntity) _entity).getActivePotionEffects();
							for (EffectInstance effect : effects) {
								if (effect.getPotion() == Effects.ABSORPTION)
									return effect.getDuration();
							}
						}
						return 0;
					}
				}.check(entity));
				if (entity instanceof LivingEntity)
					((LivingEntity) entity).addPotionEffect(new EffectInstance(Effects.ABSORPTION, (int) (absorbtion), (int) 1, (false), (false)));
				entity.getPersistentData().putBoolean("activepotionabsorption", (true));
			}
		}
	}

	@SubscribeEvent
	public void onPlayerTick(TickEvent.PlayerTickEvent event) {
		if (event.phase == TickEvent.Phase.END) {
			Entity entity = event.player;
			World world = entity.world;
			double i = entity.getPosX();
			double j = entity.getPosY();
			double k = entity.getPosZ();
			Map<String, Object> dependencies = new HashMap<>();
			dependencies.put("x", i);
			dependencies.put("y", j);
			dependencies.put("z", k);
			dependencies.put("world", world);
			dependencies.put("entity", entity);
			dependencies.put("event", event);
			this.executeProcedure(dependencies);
		}
	}
}
