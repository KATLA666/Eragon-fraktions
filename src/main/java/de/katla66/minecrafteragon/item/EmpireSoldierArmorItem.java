
package de.katla66.minecrafteragon.item;

import net.minecraftforge.registries.ObjectHolder;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.util.ResourceLocation;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.ArmorItem;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.BipedModel;

import de.katla66.minecrafteragon.itemgroup.EragonItemGroup;
import de.katla66.minecrafteragon.MinecraftEragonFraktionsModElements;

import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.mojang.blaze3d.matrix.MatrixStack;

@MinecraftEragonFraktionsModElements.ModElement.Tag
public class EmpireSoldierArmorItem extends MinecraftEragonFraktionsModElements.ModElement {
	@ObjectHolder("minecraft_eragon_fraktions:empire_soldier_armor_helmet")
	public static final Item helmet = null;
	@ObjectHolder("minecraft_eragon_fraktions:empire_soldier_armor_chestplate")
	public static final Item body = null;
	@ObjectHolder("minecraft_eragon_fraktions:empire_soldier_armor_leggings")
	public static final Item legs = null;
	@ObjectHolder("minecraft_eragon_fraktions:empire_soldier_armor_boots")
	public static final Item boots = null;
	public EmpireSoldierArmorItem(MinecraftEragonFraktionsModElements instance) {
		super(instance, 70);
	}

	@Override
	public void initElements() {
		IArmorMaterial armormaterial = new IArmorMaterial() {
			@Override
			public int getDurability(EquipmentSlotType slot) {
				return new int[]{13, 15, 16, 11}[slot.getIndex()] * 25;
			}

			@Override
			public int getDamageReductionAmount(EquipmentSlotType slot) {
				return new int[]{2, 5, 6, 2}[slot.getIndex()];
			}

			@Override
			public int getEnchantability() {
				return 9;
			}

			@Override
			public net.minecraft.util.SoundEvent getSoundEvent() {
				return (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation(""));
			}

			@Override
			public Ingredient getRepairMaterial() {
				return Ingredient.EMPTY;
			}

			@OnlyIn(Dist.CLIENT)
			@Override
			public String getName() {
				return "empire_soldier_armor";
			}

			@Override
			public float getToughness() {
				return 0f;
			}

			@Override
			public float getKnockbackResistance() {
				return 0f;
			}
		};
		elements.items.add(() -> new ArmorItem(armormaterial, EquipmentSlotType.HEAD, new Item.Properties().group(EragonItemGroup.tab)) {
			@Override
			@OnlyIn(Dist.CLIENT)
			public BipedModel getArmorModel(LivingEntity living, ItemStack stack, EquipmentSlotType slot, BipedModel defaultModel) {
				BipedModel armorModel = new BipedModel(1);

				armorModel.isSneak = living.isSneaking();
				armorModel.isSitting = defaultModel.isSitting;
				armorModel.isChild = living.isChild();
				return armorModel;
			}

			@Override
			public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlotType slot, String type) {
				return "minecraft_eragon_fraktions:textures/modelempiresoldierarmor.png";
			}
		}.setRegistryName("empire_soldier_armor_helmet"));
	}
// Made with Blockbench 3.8.4
// Exported for Minecraft version 1.15 - 1.16
// Paste this class into your mod and generate all required imports


	public class ModelEmpireSoldierArmor extends BipedModel<LivingEntity> {
		private final ModelRenderer field_178736_x;
		private final ModelRenderer field_178723_h;
		private final ModelRenderer field_178721_j;
		private final ModelRenderer field_78116_c;
		private final ModelRenderer field_78115_e;
		private final ModelRenderer field_178724_i;
		private final ModelRenderer field_178722_k;
		private final ModelRenderer head;

		public ModelEmpireSoldierArmor(float modelSize) {
			super(modelSize, 0.0F, 32, 16);
			textureWidth = 32;
			textureHeight = 16;

			field_178736_x = new ModelRenderer(this);
			field_178736_x.setRotationPoint(0.0F, 0.0F, 0.0F);


			field_178723_h = new ModelRenderer(this);
			field_178723_h.setRotationPoint(-5.0F, 2.0F, 0.0F);


			field_178721_j = new ModelRenderer(this);
			field_178721_j.setRotationPoint(-1.9F, 12.0F, 0.0F);


			field_78116_c = new ModelRenderer(this);
			field_78116_c.setRotationPoint(0.0F, 0.0F, 0.0F);


			field_78115_e = new ModelRenderer(this);
			field_78115_e.setRotationPoint(0.0F, 0.0F, 0.0F);


			field_178724_i = new ModelRenderer(this);
			field_178724_i.setRotationPoint(5.0F, 2.0F, 0.0F);


			field_178722_k = new ModelRenderer(this);
			field_178722_k.setRotationPoint(1.9F, 12.0F, 0.0F);


			head = new ModelRenderer(this);
			head.setRotationPoint(0.0F, 24.0F, 0.0F);
			head.setTextureOffset(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0.0F, false);
		}
		//public void setRotationAngles(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
			//previously the render function, render code was moved to a method below
		//}



		@Override
		public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
			field_178736_x.render(matrixStack, buffer, packedLight, packedOverlay);
			field_178723_h.render(matrixStack, buffer, packedLight, packedOverlay);
			field_178721_j.render(matrixStack, buffer, packedLight, packedOverlay);
			field_78116_c.render(matrixStack, buffer, packedLight, packedOverlay);
			field_78115_e.render(matrixStack, buffer, packedLight, packedOverlay);
			field_178724_i.render(matrixStack, buffer, packedLight, packedOverlay);
			field_178722_k.render(matrixStack, buffer, packedLight, packedOverlay);
			head.render(matrixStack, buffer, packedLight, packedOverlay);
		}

		public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
			modelRenderer.rotateAngleX = x;
			modelRenderer.rotateAngleY = y;
			modelRenderer.rotateAngleZ = z;
		}
	}
}
