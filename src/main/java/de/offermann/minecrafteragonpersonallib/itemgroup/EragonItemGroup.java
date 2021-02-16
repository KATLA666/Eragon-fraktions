
package de.offermann.minecrafteragonpersonallib.itemgroup;

import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemGroup;

import de.offermann.minecrafteragonpersonallib.item.ErgaonLogoItem;
import de.offermann.minecrafteragonpersonallib.MinecraftEragonPersonallibModElements;

@MinecraftEragonPersonallibModElements.ModElement.Tag
public class EragonItemGroup extends MinecraftEragonPersonallibModElements.ModElement {
	public EragonItemGroup(MinecraftEragonPersonallibModElements instance) {
		super(instance, 29);
	}

	@Override
	public void initElements() {
		tab = new ItemGroup("taberagon") {
			@OnlyIn(Dist.CLIENT)
			@Override
			public ItemStack createIcon() {
				return new ItemStack(ErgaonLogoItem.block, (int) (1));
			}

			@OnlyIn(Dist.CLIENT)
			public boolean hasSearchBar() {
				return false;
			}
		};
	}
	public static ItemGroup tab;
}
