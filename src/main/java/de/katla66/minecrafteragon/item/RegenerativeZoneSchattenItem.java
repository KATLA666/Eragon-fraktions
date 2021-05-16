
package de.katla66.minecrafteragon.item;

import net.minecraftforge.registries.ObjectHolder;

import net.minecraft.world.World;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.ActionResultType;
import net.minecraft.item.ItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.entity.player.PlayerEntity;

import de.katla66.minecrafteragon.world.dimension.RegenerativeZoneSchattenDimension;
import de.katla66.minecrafteragon.procedures.RegenerativeZoneSchattenCanMakePortalProcedure;
import de.katla66.minecrafteragon.itemgroup.EragonItemGroup;

import com.google.common.collect.ImmutableMap;

public class RegenerativeZoneSchattenItem extends Item {
	@ObjectHolder("minecraft_eragon_fraktions:regenerative_zone_schatten")
	public static final Item block = null;
	public RegenerativeZoneSchattenItem() {
		super(new Item.Properties().group(EragonItemGroup.tab).maxDamage(64));
	}

	@Override
	public ActionResultType onItemUse(ItemUseContext context) {
		PlayerEntity entity = context.getPlayer();
		BlockPos pos = context.getPos().offset(context.getFace());
		ItemStack itemstack = context.getItem();
		World world = context.getWorld();
		if (!entity.canPlayerEdit(pos, context.getFace(), itemstack)) {
			return ActionResultType.FAIL;
		} else {
			int x = pos.getX();
			int y = pos.getY();
			int z = pos.getZ();
			if (world.isAirBlock(pos) && RegenerativeZoneSchattenCanMakePortalProcedure.executeProcedure(ImmutableMap.of("entity", entity)))
				RegenerativeZoneSchattenDimension.portal.portalSpawn(world, pos);
			itemstack.damageItem(1, entity, c -> c.sendBreakAnimation(context.getHand()));
			return ActionResultType.SUCCESS;
		}
	}
}
