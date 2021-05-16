package de.katla66.minecrafteragon.procedures;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.common.MinecraftForge;

import net.minecraft.world.World;
import net.minecraft.potion.Potion;
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
		double Potion = 0;
		double PotionLevel = 0;
		double PotionStrong = 0;
		double speedLevel = 0;
		double Speed = 0;
		double speedStrong = 0;
		double TutleMasterLevel = 0;
		double turtleMasterStrong = 0;
		double turtleMaster = 0;
		if ((((itemstack).getItem() == new ItemStack(Items.POTION, (int) (1)).getItem())
				|| (((itemstack).getItem() == new ItemStack(Items.SPLASH_POTION, (int) (1)).getItem())
						|| ((itemstack).getItem() == new ItemStack(Items.LINGERING_POTION, (int) (1)).getItem())))) {
			if ((((entity.getCapability(MinecraftEragonFraktionsModVariables.PLAYER_VARIABLES_CAPABILITY, null)
					.orElse(new MinecraftEragonFraktionsModVariables.PlayerVariables())).empireSoldier) == 1)) {
				if ((((itemstack).getOrCreateTag().getString("Potion")).contains("swiftness"))) {
					if (((((itemstack).getOrCreateTag().getString("Potion"))).equals("minecraft:strong_swiftness"))) {
						Potion = (double) 3600;
						PotionStrong = (double) 1;
					} else if (((((itemstack).getOrCreateTag().getString("Potion"))).equals("minecraft:long_swiftness"))) {
						Potion = (double) 9600;
						PotionStrong = (double) 0;
					} else if (((((itemstack).getOrCreateTag().getString("Potion"))).equals("minecraft:swiftness"))) {
						Potion = (double) 1800;
						PotionStrong = (double) 0;
					}
					PotionLevel = (double) (new Object() {
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
					if (entity instanceof LivingEntity) {
						((LivingEntity) entity).removePotionEffect(Effects.SPEED);
					}
					if (entity instanceof LivingEntity)
						((LivingEntity) entity).addPotionEffect(
								new EffectInstance(Effects.SPEED, (int) (Potion), (int) (((PotionLevel) + 1) + (PotionStrong)), (true), (true)));
				} else if ((((itemstack).getOrCreateTag().getString("Potion")).contains("strength"))) {
					if (((((itemstack).getOrCreateTag().getString("Potion"))).equals("minecraft:strong_strength"))) {
						Potion = (double) 400;
						PotionStrong = (double) 1;
					} else if (((((itemstack).getOrCreateTag().getString("Potion"))).equals("minecraft:long_strength"))) {
						Potion = (double) 800;
						PotionStrong = (double) 1;
					} else if (((((itemstack).getOrCreateTag().getString("Potion"))).equals("minecraft:strength"))) {
						Potion = (double) 400;
						PotionStrong = (double) 1;
					}
					PotionLevel = (double) (new Object() {
						int check(Entity _entity) {
							if (_entity instanceof LivingEntity) {
								Collection<EffectInstance> effects = ((LivingEntity) _entity).getActivePotionEffects();
								for (EffectInstance effect : effects) {
									if (effect.getPotion() == Effects.STRENGTH)
										return effect.getAmplifier();
								}
							}
							return 0;
						}
					}.check(entity));
					if (entity instanceof LivingEntity) {
						((LivingEntity) entity).removePotionEffect(Effects.STRENGTH);
					}
					if (entity instanceof LivingEntity)
						((LivingEntity) entity).addPotionEffect(
								new EffectInstance(Effects.STRENGTH, (int) (Potion), (int) (((PotionLevel) + 1) + (PotionStrong)), (true), (true)));
				} else if ((((itemstack).getOrCreateTag().getString("Potion")).contains("poison"))) {
					if (((((itemstack).getOrCreateTag().getString("Potion"))).equals("minecraft:strong_poison"))) {
						Potion = (double) 3600;
						PotionStrong = (double) 1;
					} else if (((((itemstack).getOrCreateTag().getString("Potion"))).equals("minecraft:long_poison"))) {
						Potion = (double) 9600;
						PotionStrong = (double) 0;
					} else if (((((itemstack).getOrCreateTag().getString("Potion"))).equals("minecraft:poison"))) {
						Potion = (double) 1800;
						PotionStrong = (double) 0;
					}
					PotionLevel = (double) (new Object() {
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
					if (entity instanceof LivingEntity) {
						((LivingEntity) entity).removePotionEffect(Effects.POISON);
					}
					if (entity instanceof LivingEntity)
						((LivingEntity) entity).addPotionEffect(
								new EffectInstance(Effects.POISON, (int) (Potion), (int) (((PotionLevel) + 1) + (PotionStrong)), (true), (true)));
				} else if ((((itemstack).getOrCreateTag().getString("Potion")).contains("slowness"))) {
					if (((((itemstack).getOrCreateTag().getString("Potion"))).equals("minecraft:strong_slowness"))) {
						Potion = (double) 3600;
						PotionStrong = (double) 1;
					} else if (((((itemstack).getOrCreateTag().getString("Potion"))).equals("minecraft:long_slowness"))) {
						Potion = (double) 9600;
						PotionStrong = (double) 0;
					} else if (((((itemstack).getOrCreateTag().getString("Potion"))).equals("minecraft:slowness"))) {
						Potion = (double) 1800;
						PotionStrong = (double) 0;
					}
					PotionLevel = (double) (new Object() {
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
					if (entity instanceof LivingEntity) {
						((LivingEntity) entity).removePotionEffect(Effects.SLOWNESS);
					}
					if (entity instanceof LivingEntity)
						((LivingEntity) entity).addPotionEffect(
								new EffectInstance(Effects.SLOWNESS, (int) (Potion), (int) (((PotionLevel) + 1) + (PotionStrong)), (true), (true)));
				} else if ((((itemstack).getOrCreateTag().getString("Potion")).contains("regeneration"))) {
					if (((((itemstack).getOrCreateTag().getString("Potion"))).equals("minecraft:strong_regeneration"))) {
						Potion = (double) 3600;
						PotionStrong = (double) 1;
					} else if (((((itemstack).getOrCreateTag().getString("Potion"))).equals("minecraft:long_regeneration"))) {
						Potion = (double) 9600;
						PotionStrong = (double) 0;
					} else if (((((itemstack).getOrCreateTag().getString("Potion"))).equals("minecraft:regeneration"))) {
						Potion = (double) 1800;
						PotionStrong = (double) 0;
					}
					PotionLevel = (double) (new Object() {
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
					if (entity instanceof LivingEntity) {
						((LivingEntity) entity).removePotionEffect(Effects.REGENERATION);
					}
					if (entity instanceof LivingEntity)
						((LivingEntity) entity).addPotionEffect(new EffectInstance(Effects.REGENERATION, (int) (Potion),
								(int) (((PotionLevel) + 1) + (PotionStrong)), (true), (true)));
				} else if ((new Object() {
					boolean check(Entity _entity) {
						if (_entity instanceof LivingEntity) {
							Collection<EffectInstance> effects = ((LivingEntity) _entity).getActivePotionEffects();
							for (EffectInstance effect : effects) {
								if (effect.getPotion() == Effects.JUMP_BOOST)
									return true;
							}
						}
						return false;
					}
				}.check(entity))) {
					if (((((itemstack).getOrCreateTag().getString("Potion"))).equals("minecraft:strong_leaping"))) {
						Potion = (double) 3600;
						PotionStrong = (double) 1;
					} else if (((((itemstack).getOrCreateTag().getString("Potion"))).equals("minecraft:long_leaping"))) {
						Potion = (double) 9600;
						PotionStrong = (double) 0;
					} else if (((((itemstack).getOrCreateTag().getString("Potion"))).equals("minecraft:leaping"))) {
						Potion = (double) 1800;
						PotionStrong = (double) 0;
					}
					PotionLevel = (double) (new Object() {
						int check(Entity _entity) {
							if (_entity instanceof LivingEntity) {
								Collection<EffectInstance> effects = ((LivingEntity) _entity).getActivePotionEffects();
								for (EffectInstance effect : effects) {
									if (effect.getPotion() == Effects.JUMP_BOOST)
										return effect.getAmplifier();
								}
							}
							return 0;
						}
					}.check(entity));
					if (entity instanceof LivingEntity) {
						((LivingEntity) entity).removePotionEffect(Effects.JUMP_BOOST);
					}
					if (entity instanceof LivingEntity)
						((LivingEntity) entity).addPotionEffect(
								new EffectInstance(Effects.JUMP_BOOST, (int) (Potion), (int) (((PotionLevel) + 1) + (PotionStrong)), (true), (true)));
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
