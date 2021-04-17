package de.katla66.minecrafteragon.procedures;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.common.MinecraftForge;

import net.minecraft.world.World;
import net.minecraft.world.IWorld;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.ResourceLocation;
import net.minecraft.potion.Effects;
import net.minecraft.potion.EffectInstance;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;

import java.util.function.Function;
import java.util.Map;
import java.util.HashMap;
import java.util.Comparator;

import de.katla66.minecrafteragon.MinecraftEragonFraktionsModVariables;
import de.katla66.minecrafteragon.MinecraftEragonFraktionsModElements;
import de.katla66.minecrafteragon.MinecraftEragonFraktionsMod;

@MinecraftEragonFraktionsModElements.ModElement.Tag
public class FraktionShadeProcedure extends MinecraftEragonFraktionsModElements.ModElement {
	public FraktionShadeProcedure(MinecraftEragonFraktionsModElements instance) {
		super(instance, 41);
		MinecraftForge.EVENT_BUS.register(this);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				MinecraftEragonFraktionsMod.LOGGER.warn("Failed to load dependency entity for procedure FraktionShade!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				MinecraftEragonFraktionsMod.LOGGER.warn("Failed to load dependency x for procedure FraktionShade!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				MinecraftEragonFraktionsMod.LOGGER.warn("Failed to load dependency y for procedure FraktionShade!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				MinecraftEragonFraktionsMod.LOGGER.warn("Failed to load dependency z for procedure FraktionShade!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				MinecraftEragonFraktionsMod.LOGGER.warn("Failed to load dependency world for procedure FraktionShade!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		if ((((entity.getCapability(MinecraftEragonFraktionsModVariables.PLAYER_VARIABLES_CAPABILITY, null)
				.orElse(new MinecraftEragonFraktionsModVariables.PlayerVariables())).shade) == 1)) {
			if (((world.getLight(new BlockPos((int) x, (int) y, (int) z))) <= 10)) {
				if (entity instanceof LivingEntity)
					((LivingEntity) entity).addPotionEffect(new EffectInstance(Effects.NIGHT_VISION, (int) 2000, (int) 0, (false), (false)));
				entity.getPersistentData().putDouble("nightvisionactive", 1);
			}
			if (((((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHealth() : -1) <= 4) && (((Entity) world
					.getEntitiesWithinAABB(ArrowEntity.class,
							new AxisAlignedBB(x - (3 / 2d), y - (3 / 2d), z - (3 / 2d), x + (3 / 2d), y + (3 / 2d), z + (3 / 2d)), null)
					.stream().sorted(new Object() {
						Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
							return Comparator.comparing((Function<Entity, Double>) (_entcnd -> _entcnd.getDistanceSq(_x, _y, _z)));
						}
					}.compareDistOf(x, y, z)).findFirst().orElse(null)) != null))) {
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
				{
					Entity _ent = entity;
					if (!_ent.world.isRemote && _ent.world.getServer() != null) {
						_ent.world.getServer().getCommandManager().handleCommand(
								_ent.getCommandSource().withFeedbackDisabled().withPermissionLevel(4),
								"execute in minecraft_eragon_fraktions:regenerative_zone_schatten run tp @s ~ 60 ~");
					}
				}
			}
			if (((world.func_241828_r().getRegistry(Registry.BIOME_KEY).getKey(world.getBiome(new BlockPos((int) x, (int) y, (int) z))) != null
					&& world.func_241828_r().getRegistry(Registry.BIOME_KEY).getKey(world.getBiome(new BlockPos((int) x, (int) y, (int) z)))
							.equals(new ResourceLocation("minecraft_eragon_fraktions:regeration_zone")))
					&& (((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHealth() : -1) >= 20))) {
				{
					Entity _ent = entity;
					if (!_ent.world.isRemote && _ent.world.getServer() != null) {
						_ent.world.getServer().getCommandManager().handleCommand(
								_ent.getCommandSource().withFeedbackDisabled().withPermissionLevel(4),
								(((("execute in minecraft:overworld run tp @s ") + "" + (((((entity
										.getCapability(MinecraftEragonFraktionsModVariables.PLAYER_VARIABLES_CAPABILITY, null)
										.orElse(new MinecraftEragonFraktionsModVariables.PlayerVariables())).posShadeOverworldX))
										+ ""
										+ (((" ") + ""
												+ (((((entity.getCapability(MinecraftEragonFraktionsModVariables.PLAYER_VARIABLES_CAPABILITY, null)
														.orElse(new MinecraftEragonFraktionsModVariables.PlayerVariables())).posShadeOverworldY)) + ""
														+ (" ")))))))))
										+ "" + (((entity.getCapability(MinecraftEragonFraktionsModVariables.PLAYER_VARIABLES_CAPABILITY, null)
												.orElse(new MinecraftEragonFraktionsModVariables.PlayerVariables())).posShadeOverworldZ))));
					}
				}
			}
			if ((world.func_241828_r().getRegistry(Registry.BIOME_KEY).getKey(world.getBiome(new BlockPos((int) x, (int) y, (int) z))) != null
					&& world.func_241828_r().getRegistry(Registry.BIOME_KEY).getKey(world.getBiome(new BlockPos((int) x, (int) y, (int) z)))
							.equals(new ResourceLocation("minecraft_eragon_fraktions:regeration_zone")))) {
				if ((!(world.getBlockState(new BlockPos((int) x, (int) (y - 15), (int) z)).isSolid()))) {
					{
						Entity _ent = entity;
						if (!_ent.world.isRemote && _ent.world.getServer() != null) {
							_ent.world.getServer().getCommandManager().handleCommand(
									_ent.getCommandSource().withFeedbackDisabled().withPermissionLevel(4),
									"fill ~2 ~-1 ~2 ~-2 ~-1 ~-2 minecraft:blackstone");
						}
					}
				}
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
