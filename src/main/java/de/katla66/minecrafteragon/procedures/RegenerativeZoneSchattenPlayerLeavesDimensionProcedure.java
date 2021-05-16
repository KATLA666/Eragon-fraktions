package de.katla66.minecrafteragon.procedures;

import net.minecraft.potion.Effects;
import net.minecraft.potion.EffectInstance;
import net.minecraft.item.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;

import java.util.Map;

import de.katla66.minecrafteragon.potion.NoFallPotion;
import de.katla66.minecrafteragon.MinecraftEragonFraktionsModElements;
import de.katla66.minecrafteragon.MinecraftEragonFraktionsMod;

@MinecraftEragonFraktionsModElements.ModElement.Tag
public class RegenerativeZoneSchattenPlayerLeavesDimensionProcedure extends MinecraftEragonFraktionsModElements.ModElement {
	public RegenerativeZoneSchattenPlayerLeavesDimensionProcedure(MinecraftEragonFraktionsModElements instance) {
		super(instance, 51);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				MinecraftEragonFraktionsMod.LOGGER
						.warn("Failed to load dependency entity for procedure RegenerativeZoneSchattenPlayerLeavesDimension!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if (entity instanceof LivingEntity)
			((LivingEntity) entity).addPotionEffect(new EffectInstance(Effects.FIRE_RESISTANCE, (int) 2400, (int) 0, (false), (false)));
		if (entity instanceof LivingEntity)
			((LivingEntity) entity).addPotionEffect(new EffectInstance(Effects.STRENGTH, (int) 200, (int) 0, (false), (false)));
		if (entity instanceof LivingEntity)
			((LivingEntity) entity).addPotionEffect(new EffectInstance(NoFallPotion.potion, (int) 200, (int) 0, (false), (false)));
		if (entity instanceof PlayerEntity) {
			ItemStack _stktoremove = new ItemStack(Items.CAKE, (int) (1));
			((PlayerEntity) entity).inventory.func_234564_a_(p -> _stktoremove.getItem() == p.getItem(),
					(int) ((new ItemStack(Items.CAKE, (int) (1))).getCount()), ((PlayerEntity) entity).container.func_234641_j_());
		}
		{
			Entity _ent = entity;
			if (!_ent.world.isRemote && _ent.world.getServer() != null) {
				_ent.world.getServer().getCommandManager().handleCommand(_ent.getCommandSource().withFeedbackDisabled().withPermissionLevel(4),
						"fill ~-8 ~-8 ~-8 ~8 ~8 ~8 air replace minecraft_eragon_fraktions:portalblock");
			}
		}
	}
}
