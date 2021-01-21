package de.offermann.minecrafteragonpersonallib.procedures;

import net.minecraft.util.text.StringTextComponent;
import net.minecraft.item.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.enchantment.EnchantmentHelper;

import java.util.Map;

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
		if (dependencies.get("itemstack") == null) {
			if (!dependencies.containsKey("itemstack"))
				System.err.println("Failed to load dependency itemstack for procedure SpecialAbility!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		ItemStack itemstack = (ItemStack) dependencies.get("itemstack");
		if (((entity.getPersistentData().getDouble("varden")) == 1)) {
			if (entity instanceof PlayerEntity && !entity.world.isRemote) {
				((PlayerEntity) entity).sendStatusMessage(new StringTextComponent("\u00A7cin working"), (false));
			}
		} else if (((entity.getPersistentData().getDouble("elfen")) == 1)) {
			if (((EnchantmentHelper.getEnchantmentLevel(Enchantments.INFINITY, new ItemStack(Items.BOW, (int) (1)))) == 0)) {
				((itemstack)).addEnchantment(Enchantments.INFINITY, (int) 1);
			}
		}
		if (((entity.getPersistentData().getDouble("zwergen")) == 1)) {
			if (entity instanceof PlayerEntity && !entity.world.isRemote) {
				((PlayerEntity) entity).sendStatusMessage(new StringTextComponent("\u00A7cin working"), (false));
			}
		} else if (((entity.getPersistentData().getDouble("imperium")) == 1)) {
			if (entity instanceof PlayerEntity && !entity.world.isRemote) {
				((PlayerEntity) entity).sendStatusMessage(new StringTextComponent("\u00A7cin working"), (false));
			}
		}
	}
}
