package de.katla66.minecrafteragon.procedures;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.common.MinecraftForge;

import net.minecraft.world.World;
import net.minecraft.potion.Effects;
import net.minecraft.potion.EffectInstance;
import net.minecraft.item.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;

import java.util.Map;
import java.util.HashMap;
import java.util.Collection;

import de.katla66.minecrafteragon.MinecraftEragonFraktionsModVariables;
import de.katla66.minecrafteragon.MinecraftEragonFraktionsModElements;
import de.katla66.minecrafteragon.MinecraftEragonFraktionsMod;

@MinecraftEragonFraktionsModElements.ModElement.Tag
public class FraktionEmpireSoldierProcedure extends MinecraftEragonFraktionsModElements.ModElement {
	public FraktionEmpireSoldierProcedure(MinecraftEragonFraktionsModElements instance) {
		super(instance, 36);
		MinecraftForge.EVENT_BUS.register(this);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				MinecraftEragonFraktionsMod.LOGGER.warn("Failed to load dependency entity for procedure FraktionEmpireSoldier!");
			return;
		}
		if (dependencies.get("itemstack") == null) {
			if (!dependencies.containsKey("itemstack"))
				MinecraftEragonFraktionsMod.LOGGER.warn("Failed to load dependency itemstack for procedure FraktionEmpireSoldier!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		ItemStack itemstack = (ItemStack) dependencies.get("itemstack");
		double Speed = 0;
		double haste = 0;
		double poisinus = 0;
		double slowness = 0;
		double regenaration = 0;
		double absorbtion = 0;
		double speedLevel = 0;
		double hasteLevel = 0;
		double poisinusLevel = 0;
		double slownessLevel = 0;
		double regenerationLevel = 0;
		double absoprtionLevel = 0;
		double speedStrong = 0;
		if ((((itemstack).getItem() == new ItemStack(Items.POTION, (int) (1)).getItem())
				|| (((itemstack).getItem() == new ItemStack(Items.SPLASH_POTION, (int) (1)).getItem())
						|| ((itemstack).getItem() == new ItemStack(Items.LINGERING_POTION, (int) (1)).getItem())))) {
			if ((((entity.getCapability(MinecraftEragonFraktionsModVariables.PLAYER_VARIABLES_CAPABILITY, null)
					.orElse(new MinecraftEragonFraktionsModVariables.PlayerVariables())).empireSoldier) == 1)) {
				if ((new Object() {
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
				}.check(entity))) {
					if (((((itemstack).getOrCreateTag().getString("Potion"))).equals("strong_swiftness"))) {
						Speed = (double) 3600;
						speedStrong = (double) 1;
					} else if (((((itemstack).getOrCreateTag().getString("Potion"))).equals("long_swiftness"))) {
						Speed = (double) 9600;
						speedStrong = (double) 0;
					} else if (((((itemstack).getOrCreateTag().getString("Potion"))).equals("minecraft:swiftness"))) {
						Speed = (double) 1800;
						speedStrong = (double) 0;
					}
					speedLevel = (double) (new Object() {
						int check(Entity _entity) {
							if (_entity instanceof LivingEntity) {
								Collection<EffectInstance> effects = ((LivingEntity) _entity).getActivePotionEffects();
								for (EffectInstance effect : effects) {
									if (effect.getPotion() == Effects.SPEED)
										return effect.getAmplifier();
								}
							}
							return 0;
						}
					}.check(entity));
					if (entity instanceof LivingEntity)
						((LivingEntity) entity).addPotionEffect(
								new EffectInstance(Effects.SPEED, (int) (Speed), (int) (((speedLevel) + 1) + (speedStrong)), (true), (true)));
				} else if ((new Object() {
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
				}.check(entity))) {
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
					hasteLevel = (double) (new Object() {
						int check(Entity _entity) {
							if (_entity instanceof LivingEntity) {
								Collection<EffectInstance> effects = ((LivingEntity) _entity).getActivePotionEffects();
								for (EffectInstance effect : effects) {
									if (effect.getPotion() == Effects.HASTE)
										return effect.getAmplifier();
								}
							}
							return 0;
						}
					}.check(entity));
					if (entity instanceof LivingEntity)
						((LivingEntity) entity)
								.addPotionEffect(new EffectInstance(Effects.HASTE, (int) (haste), (int) ((hasteLevel) + 1), (false), (false)));
				} else if ((new Object() {
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
				}.check(entity))) {
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
					poisinusLevel = (double) (new Object() {
						int check(Entity _entity) {
							if (_entity instanceof LivingEntity) {
								Collection<EffectInstance> effects = ((LivingEntity) _entity).getActivePotionEffects();
								for (EffectInstance effect : effects) {
									if (effect.getPotion() == Effects.POISON)
										return effect.getAmplifier();
								}
							}
							return 0;
						}
					}.check(entity));
					if (entity instanceof LivingEntity)
						((LivingEntity) entity)
								.addPotionEffect(new EffectInstance(Effects.POISON, (int) (poisinus), (int) ((poisinusLevel) + 1), (false), (false)));
					entity.getPersistentData().putBoolean("activepotionpoisinus", (true));
				} else if ((new Object() {
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
				}.check(entity))) {
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
					slownessLevel = (double) (new Object() {
						int check(Entity _entity) {
							if (_entity instanceof LivingEntity) {
								Collection<EffectInstance> effects = ((LivingEntity) _entity).getActivePotionEffects();
								for (EffectInstance effect : effects) {
									if (effect.getPotion() == Effects.SLOWNESS)
										return effect.getAmplifier();
								}
							}
							return 0;
						}
					}.check(entity));
					if (entity instanceof LivingEntity)
						((LivingEntity) entity).addPotionEffect(
								new EffectInstance(Effects.SLOWNESS, (int) (slowness), (int) ((slownessLevel) + 1), (false), (false)));
					entity.getPersistentData().putBoolean("activepotionslowness", (true));
				} else if ((new Object() {
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
				}.check(entity))) {
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
					regenerationLevel = (double) (new Object() {
						int check(Entity _entity) {
							if (_entity instanceof LivingEntity) {
								Collection<EffectInstance> effects = ((LivingEntity) _entity).getActivePotionEffects();
								for (EffectInstance effect : effects) {
									if (effect.getPotion() == Effects.REGENERATION)
										return effect.getAmplifier();
								}
							}
							return 0;
						}
					}.check(entity));
					if (entity instanceof LivingEntity)
						((LivingEntity) entity).addPotionEffect(
								new EffectInstance(Effects.REGENERATION, (int) (regenaration), (int) ((regenerationLevel) + 1), (false), (false)));
					entity.getPersistentData().putBoolean("activepotionregenaration", (true));
				} else if ((new Object() {
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
				}.check(entity))) {
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
					absoprtionLevel = (double) (new Object() {
						int check(Entity _entity) {
							if (_entity instanceof LivingEntity) {
								Collection<EffectInstance> effects = ((LivingEntity) _entity).getActivePotionEffects();
								for (EffectInstance effect : effects) {
									if (effect.getPotion() == Effects.ABSORPTION)
										return effect.getAmplifier();
								}
							}
							return 0;
						}
					}.check(entity));
					if (entity instanceof LivingEntity)
						((LivingEntity) entity).addPotionEffect(
								new EffectInstance(Effects.ABSORPTION, (int) (absorbtion), (int) ((absoprtionLevel) + 1), (false), (false)));
					entity.getPersistentData().putBoolean("activepotionabsorption", (true));
				}
			}
		}
	}

	@SubscribeEvent
	public void onUseItemStart(LivingEntityUseItemEvent.Finish event) {
		if (event != null && event.getEntity() != null) {
			Entity entity = event.getEntity();
			double i = entity.getPosX();
			double j = entity.getPosY();
			double k = entity.getPosZ();
			double duration = event.getDuration();
			ItemStack itemstack = event.getItem();
			World world = entity.world;
			Map<String, Object> dependencies = new HashMap<>();
			dependencies.put("x", i);
			dependencies.put("y", j);
			dependencies.put("z", k);
			dependencies.put("itemstack", itemstack);
			dependencies.put("duration", duration);
			dependencies.put("world", world);
			dependencies.put("entity", entity);
			dependencies.put("event", event);
			this.executeProcedure(dependencies);
		}
	}
}
