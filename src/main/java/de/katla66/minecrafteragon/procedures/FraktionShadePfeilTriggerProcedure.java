package de.katla66.minecrafteragon.procedures;

import de.katla66.minecrafteragon.world.dimension.RegenerativeZoneSchattenDimension;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.common.MinecraftForge;

import net.minecraft.world.World;
import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;
import net.minecraft.block.Blocks;

import java.util.Map;
import java.util.HashMap;

import de.katla66.minecrafteragon.MinecraftEragonFraktionsModVariables;
import de.katla66.minecrafteragon.MinecraftEragonFraktionsModElements;
import de.katla66.minecrafteragon.MinecraftEragonFraktionsMod;

@MinecraftEragonFraktionsModElements.ModElement.Tag
public class FraktionShadePfeilTriggerProcedure extends MinecraftEragonFraktionsModElements.ModElement {
	public FraktionShadePfeilTriggerProcedure(MinecraftEragonFraktionsModElements instance) {
		super(instance, 71);
		MinecraftForge.EVENT_BUS.register(this);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				MinecraftEragonFraktionsMod.LOGGER.warn("Failed to load dependency entity for procedure FraktionShadePfeilTrigger!");
			return;
		}
		if (dependencies.get("imediatesourceentity") == null) {
			if (!dependencies.containsKey("imediatesourceentity"))
				MinecraftEragonFraktionsMod.LOGGER.warn("Failed to load dependency imediatesourceentity for procedure FraktionShadePfeilTrigger!");
			return;
		}
		if (dependencies.get("amount") == null) {
			if (!dependencies.containsKey("amount"))
				MinecraftEragonFraktionsMod.LOGGER.warn("Failed to load dependency amount for procedure FraktionShadePfeilTrigger!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				MinecraftEragonFraktionsMod.LOGGER.warn("Failed to load dependency x for procedure FraktionShadePfeilTrigger!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				MinecraftEragonFraktionsMod.LOGGER.warn("Failed to load dependency y for procedure FraktionShadePfeilTrigger!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				MinecraftEragonFraktionsMod.LOGGER.warn("Failed to load dependency z for procedure FraktionShadePfeilTrigger!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				MinecraftEragonFraktionsMod.LOGGER.warn("Failed to load dependency world for procedure FraktionShadePfeilTrigger!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		Entity imediatesourceentity = (Entity) dependencies.get("imediatesourceentity");
		double amount = dependencies.get("amount") instanceof Integer ? (int) dependencies.get("amount") : (double) dependencies.get("amount");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		if ((entity instanceof PlayerEntity)) {
			if ((((entity.getCapability(MinecraftEragonFraktionsModVariables.PLAYER_VARIABLES_CAPABILITY, null)
					.orElse(new MinecraftEragonFraktionsModVariables.PlayerVariables())).shade) == 1)) {
				if ((imediatesourceentity instanceof ArrowEntity)) {
					if ((((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHealth() : -1) <= 4)) {
						if (entity instanceof LivingEntity)
							((LivingEntity) entity)
									.setHealth((float) ((amount) + ((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHealth() : -1)));
						{
							double _setval = (double) x;
							entity.getCapability(MinecraftEragonFraktionsModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
								capability.posShadeOverworldX = _setval;
								capability.syncPlayerVariables(entity);
							});
						}
						{
							double _setval = (double) y;
							entity.getCapability(MinecraftEragonFraktionsModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
								capability.posShadeOverworldY = _setval;
								capability.syncPlayerVariables(entity);
							});
						}
						{
							double _setval = (double) z;
							entity.getCapability(MinecraftEragonFraktionsModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
								capability.posShadeOverworldZ = _setval;
								capability.syncPlayerVariables(entity);
							});
						}
						world.setBlockState(new BlockPos((int) x, (int) y, (int) z), RegenerativeZoneSchattenDimension.portal.getDefaultState(), 3);
						{
							Map<String, Object> $_dependencies = new HashMap<>();
							$_dependencies.put("entity", entity);
							$_dependencies.put("world", world);
							RemovePortalblockProcedure.executeProcedure($_dependencies);
						}
						MinecraftEragonFraktionsMod.LOGGER.info("succesfully executed Class \"FraktionShadepfeiltrigger\"");
					} else {
						MinecraftEragonFraktionsMod.LOGGER.error(" Not beneath 4 Hp");
					}
				} else {
					MinecraftEragonFraktionsMod.LOGGER.error("Not an Arrow");
				}
			} else {
				MinecraftEragonFraktionsMod.LOGGER.error("Not a Shade");
			}
		} else {
			MinecraftEragonFraktionsMod.LOGGER.error("No Player");
		}
	}

	@SubscribeEvent
	public void onEntityAttacked(LivingAttackEvent event) {
		if (event != null && event.getEntity() != null) {
			Entity entity = event.getEntity();
			Entity sourceentity = event.getSource().getTrueSource();
			Entity imediatesourceentity = event.getSource().getImmediateSource();
			double i = entity.getPosX();
			double j = entity.getPosY();
			double k = entity.getPosZ();
			double amount = event.getAmount();
			World world = entity.world;
			Map<String, Object> dependencies = new HashMap<>();
			dependencies.put("x", i);
			dependencies.put("y", j);
			dependencies.put("z", k);
			dependencies.put("amount", amount);
			dependencies.put("world", world);
			dependencies.put("entity", entity);
			dependencies.put("sourceentity", sourceentity);
			dependencies.put("imediatesourceentity", imediatesourceentity);
			dependencies.put("event", event);
			this.executeProcedure(dependencies);
		}
	}
}
//locked Code Z. 103. Block.NETHER_PORTAL --> RegenerativeZoneSchattenDimension.portal