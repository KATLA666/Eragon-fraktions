package de.katla66.minecrafteragon.procedures;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.common.MinecraftForge;

import net.minecraft.world.World;
import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.potion.Effects;
import net.minecraft.potion.EffectInstance;
import net.minecraft.item.ItemStack;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;

import java.util.Map;
import java.util.HashMap;

import de.katla66.minecrafteragon.MinecraftEragonFraktionsModVariables;
import de.katla66.minecrafteragon.MinecraftEragonFraktionsModElements;
import de.katla66.minecrafteragon.MinecraftEragonFraktionsMod;

@MinecraftEragonFraktionsModElements.ModElement.Tag
public class FraktionRaZacProcedure extends MinecraftEragonFraktionsModElements.ModElement {
	public FraktionRaZacProcedure(MinecraftEragonFraktionsModElements instance) {
		super(instance, 40);
		MinecraftForge.EVENT_BUS.register(this);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				MinecraftEragonFraktionsMod.LOGGER.warn("Failed to load dependency entity for procedure FraktionRaZac!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				MinecraftEragonFraktionsMod.LOGGER.warn("Failed to load dependency x for procedure FraktionRaZac!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				MinecraftEragonFraktionsMod.LOGGER.warn("Failed to load dependency y for procedure FraktionRaZac!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				MinecraftEragonFraktionsMod.LOGGER.warn("Failed to load dependency z for procedure FraktionRaZac!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				MinecraftEragonFraktionsMod.LOGGER.warn("Failed to load dependency world for procedure FraktionRaZac!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		if ((((entity.getCapability(MinecraftEragonFraktionsModVariables.PLAYER_VARIABLES_CAPABILITY, null)
				.orElse(new MinecraftEragonFraktionsModVariables.PlayerVariables())).razac) == 1)) {
			if (((world.getLight(new BlockPos((int) x, (int) y, (int) z))) <= 10)) {
				if (entity instanceof LivingEntity)
					((LivingEntity) entity).addPotionEffect(new EffectInstance(Effects.NIGHT_VISION, (int) 2000, (int) 0, (false), (false)));
				entity.getPersistentData().putDouble("nightvisionactive", 1);
			}
			if (((world.getWorldInfo().getDayTime()) <= 12000)) {
				if (((world.getLight(new BlockPos((int) x, (int) y, (int) z))) >= 6)) {
					if ((!((entity instanceof PlayerEntity)
							? ((PlayerEntity) entity).inventory.hasItemStack(((entity instanceof LivingEntity)
									? ((LivingEntity) entity)
											.getItemStackFromSlot(EquipmentSlotType.fromSlotTypeAndIndex(EquipmentSlotType.Group.ARMOR, (int) 3))
									: ItemStack.EMPTY))
							: false))) {
						if (entity instanceof LivingEntity)
							((LivingEntity) entity).addPotionEffect(new EffectInstance(Effects.BLINDNESS, (int) 25, (int) 0, (false), (false)));
						if (entity instanceof LivingEntity)
							((LivingEntity) entity).addPotionEffect(new EffectInstance(Effects.SLOWNESS, (int) 25, (int) 0, (false), (false)));
						if (entity instanceof LivingEntity)
							((LivingEntity) entity).addPotionEffect(new EffectInstance(Effects.WEAKNESS, (int) 25, (int) 0, (false), (false)));
					}
				}
			} else if (((world.getWorldInfo().getDayTime()) >= 12000)) {
				if (entity instanceof LivingEntity)
					((LivingEntity) entity).addPotionEffect(new EffectInstance(Effects.BAD_OMEN, (int) 25, (int) 0, (false), (false)));
				if (entity instanceof LivingEntity)
					((LivingEntity) entity).addPotionEffect(new EffectInstance(Effects.SPEED, (int) 25, (int) 0, (false), (false)));
				if (entity instanceof LivingEntity)
					((LivingEntity) entity).addPotionEffect(new EffectInstance(Effects.STRENGTH, (int) 25, (int) 0, (false), (false)));
			}
			if ((((world.getWorldInfo().getDayTime()) <= 12000) && (((world.getLight(new BlockPos((int) x, (int) y, (int) z))) > 10)
					&& ((entity.getPersistentData().getDouble("nightvisionactive")) == 1)))) {
				if (entity instanceof LivingEntity) {
					((LivingEntity) entity).removePotionEffect(Effects.NIGHT_VISION);
				}
				entity.getPersistentData().putDouble("nightvisionactive", 0);
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
