
package de.katla66.minecrafteragonpersonallib.itemgroup;

import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemGroup;

import de.katla66.minecrafteragonpersonallib.item.ErgaonLogoItem;
import de.katla66.minecrafteragonpersonallib.MinecraftEragonFraktionsModElements;

@MinecraftEragonFraktionsModElements.ModElement.Tag
public class EragonItemGroup extends MinecraftEragonFraktionsModElements.ModElement {
	public EragonItemGroup(MinecraftEragonFraktionsModElements instance) {
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
