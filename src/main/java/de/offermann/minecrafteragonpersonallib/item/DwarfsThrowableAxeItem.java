
package de.offermann.minecrafteragonpersonallib.item;

import net.minecraftforge.registries.ObjectHolder;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.fml.network.NetworkHooks;
import net.minecraftforge.fml.network.FMLPlayMessages;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.World;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Hand;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.ActionResult;
import net.minecraft.network.IPacket;
import net.minecraft.item.UseAction;
import net.minecraft.item.ShootableItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.IRendersAsItem;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.Entity;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.IRenderTypeBuffer;

import java.util.Random;

import de.offermann.minecrafteragonpersonallib.itemgroup.EragonItemGroup;
import de.offermann.minecrafteragonpersonallib.MinecraftEragonPersonallibModElements;

import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.mojang.blaze3d.matrix.MatrixStack;

@MinecraftEragonPersonallibModElements.ModElement.Tag
public class DwarfsThrowableAxeItem extends MinecraftEragonPersonallibModElements.ModElement {
	@ObjectHolder("minecraft_eragon__personallib:dwarfs_throwable_axe")
	public static final Item block = null;
	@ObjectHolder("minecraft_eragon__personallib:entitybulletdwarfs_throwable_axe")
	public static final EntityType arrow = null;
	public DwarfsThrowableAxeItem(MinecraftEragonPersonallibModElements instance) {
		super(instance, 27);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new ItemRanged());
		elements.entities.add(() -> (EntityType.Builder.<ArrowCustomEntity>create(ArrowCustomEntity::new, EntityClassification.MISC)
				.setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(1).setCustomClientFactory(ArrowCustomEntity::new)
				.size(0.5f, 0.5f)).build("entitybulletdwarfs_throwable_axe").setRegistryName("entitybulletdwarfs_throwable_axe"));
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void init(FMLCommonSetupEvent event) {
		RenderingRegistry.registerEntityRenderingHandler(arrow, renderManager -> new CustomRender(renderManager));
	}
	public static class ItemRanged extends Item {
		public ItemRanged() {
			super(new Item.Properties().group(EragonItemGroup.tab).maxDamage(1));
			setRegistryName("dwarfs_throwable_axe");
		}

		@Override
		public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity entity, Hand hand) {
			entity.setActiveHand(hand);
			return new ActionResult(ActionResultType.SUCCESS, entity.getHeldItem(hand));
		}

		@Override
		public UseAction getUseAction(ItemStack itemstack) {
			return UseAction.BOW;
		}

		@Override
		public int getUseDuration(ItemStack itemstack) {
			return 72000;
		}

		@Override
		public void onPlayerStoppedUsing(ItemStack itemstack, World world, LivingEntity entityLiving, int timeLeft) {
			if (!world.isRemote && entityLiving instanceof ServerPlayerEntity) {
				ServerPlayerEntity entity = (ServerPlayerEntity) entityLiving;
				double x = entity.getPosX();
				double y = entity.getPosY();
				double z = entity.getPosZ();
				if (true) {
					ItemStack stack = ShootableItem.getHeldAmmo(entity,
							e -> e.getItem() == new ItemStack(DwarfsThrowableAxeItem.block, (int) (1)).getItem());
					if (stack == ItemStack.EMPTY) {
						for (int i = 0; i < entity.inventory.mainInventory.size(); i++) {
							ItemStack teststack = entity.inventory.mainInventory.get(i);
							if (teststack != null && teststack.getItem() == new ItemStack(DwarfsThrowableAxeItem.block, (int) (1)).getItem()) {
								stack = teststack;
								break;
							}
						}
					}
					if (entity.abilities.isCreativeMode || stack != ItemStack.EMPTY) {
						ArrowCustomEntity entityarrow = shoot(world, entity, random, 1.4f, 8, 2);
						itemstack.damageItem(1, entity, e -> e.sendBreakAnimation(entity.getActiveHand()));
						if (entity.abilities.isCreativeMode) {
							entityarrow.pickupStatus = AbstractArrowEntity.PickupStatus.CREATIVE_ONLY;
						} else {
							if (new ItemStack(DwarfsThrowableAxeItem.block, (int) (1)).isDamageable()) {
								if (stack.attemptDamageItem(1, random, entity)) {
									stack.shrink(1);
									stack.setDamage(0);
									if (stack.isEmpty())
										entity.inventory.deleteStack(stack);
								}
							} else {
								stack.shrink(1);
								if (stack.isEmpty())
									entity.inventory.deleteStack(stack);
							}
						}
					}
				}
			}
		}
	}

	@OnlyIn(value = Dist.CLIENT, _interface = IRendersAsItem.class)
	public static class ArrowCustomEntity extends AbstractArrowEntity implements IRendersAsItem {
		public ArrowCustomEntity(FMLPlayMessages.SpawnEntity packet, World world) {
			super(arrow, world);
		}

		public ArrowCustomEntity(EntityType<? extends ArrowCustomEntity> type, World world) {
			super(type, world);
		}

		public ArrowCustomEntity(EntityType<? extends ArrowCustomEntity> type, double x, double y, double z, World world) {
			super(type, x, y, z, world);
		}

		public ArrowCustomEntity(EntityType<? extends ArrowCustomEntity> type, LivingEntity entity, World world) {
			super(type, entity, world);
		}

		@Override
		public IPacket<?> createSpawnPacket() {
			return NetworkHooks.getEntitySpawningPacket(this);
		}

		@Override
		@OnlyIn(Dist.CLIENT)
		public ItemStack getItem() {
			return new ItemStack(DwarfsThrowableAxeItem.block, (int) (1));
		}

		@Override
		protected ItemStack getArrowStack() {
			return new ItemStack(DwarfsThrowableAxeItem.block, (int) (1));
		}

		@Override
		protected void arrowHit(LivingEntity entity) {
			super.arrowHit(entity);
			entity.setArrowCountInEntity(entity.getArrowCountInEntity() - 1);
		}

		@Override
		public void tick() {
			super.tick();
			double x = this.getPosX();
			double y = this.getPosY();
			double z = this.getPosZ();
			World world = this.world;
			Entity entity = this.func_234616_v_();
			if (this.inGround) {
				
			}
		}
	}

	public static class CustomRender extends EntityRenderer<ArrowCustomEntity> {
		private static final ResourceLocation texture = new ResourceLocation("minecraft_eragon__personallib:textures/axedieechte.png");
		public CustomRender(EntityRendererManager renderManager) {
			super(renderManager);
		}

		@Override
		public void render(ArrowCustomEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn,
				int packedLightIn) {
			IVertexBuilder vb = bufferIn.getBuffer(RenderType.getEntityCutout(this.getEntityTexture(entityIn)));
			matrixStackIn.push();
			matrixStackIn.rotate(Vector3f.YP.rotationDegrees(MathHelper.lerp(partialTicks, entityIn.prevRotationYaw, entityIn.rotationYaw) - 90));
			matrixStackIn.rotate(Vector3f.ZP.rotationDegrees(90 + MathHelper.lerp(partialTicks, entityIn.prevRotationPitch, entityIn.rotationPitch)));
			EntityModel model = new Modelcustom_model();
			model.render(matrixStackIn, vb, packedLightIn, OverlayTexture.NO_OVERLAY, 1, 1, 1, 0.0625f);
			matrixStackIn.pop();
			super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
		}

		@Override
		public ResourceLocation getEntityTexture(ArrowCustomEntity entity) {
			return texture;
		}
	}

	// Made with Blockbench 3.7.5
	// Exported for Minecraft version 1.15
	// Paste this class into your mod and generate all required imports
	public static class Modelcustom_model extends EntityModel<Entity> {
		private final ModelRenderer bb_main;
		private final ModelRenderer cube_r1;
		private final ModelRenderer cube_r2;
		private final ModelRenderer cube_r3;
		private final ModelRenderer cube_r4;
		private final ModelRenderer cube_r5;
		private final ModelRenderer cube_r6;
		private final ModelRenderer cube_r7;
		private final ModelRenderer cube_r8;
		private final ModelRenderer cube_r9;
		private final ModelRenderer cube_r10;
		private final ModelRenderer cube_r11;
		private final ModelRenderer cube_r12;
		private final ModelRenderer cube_r13;
		private final ModelRenderer cube_r14;
		private final ModelRenderer cube_r15;
		public Modelcustom_model() {
			textureWidth = 64;
			textureHeight = 64;
			bb_main = new ModelRenderer(this);
			bb_main.setRotationPoint(3.0F, -4.0F, -4.0F);
			setRotationAngle(bb_main, -0.2618F, -1.5708F, 0.0F);
			cube_r1 = new ModelRenderer(this);
			cube_r1.setRotationPoint(3.0581F, -2.9692F, -10.4821F);
			bb_main.addChild(cube_r1);
			setRotationAngle(cube_r1, 0.0F, 1.5708F, 0.0F);
			cube_r1.setTextureOffset(3, 2).addBox(-9.0893F, 7.3482F, -3.5179F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			cube_r2 = new ModelRenderer(this);
			cube_r2.setRotationPoint(4.0581F, -1.9692F, -10.4821F);
			bb_main.addChild(cube_r2);
			setRotationAngle(cube_r2, 0.0F, 1.5708F, 0.0F);
			cube_r2.setTextureOffset(16, 0).addBox(-10.0893F, 4.3482F, -3.5179F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			cube_r2.setTextureOffset(16, 0).addBox(-10.0893F, 1.3482F, -3.5179F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			cube_r2.setTextureOffset(16, 0).addBox(-10.0893F, 2.3482F, -3.5179F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			cube_r2.setTextureOffset(16, 0).addBox(-10.0893F, 3.3482F, -3.5179F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			cube_r3 = new ModelRenderer(this);
			cube_r3.setRotationPoint(2.0581F, -1.9692F, -10.4821F);
			bb_main.addChild(cube_r3);
			setRotationAngle(cube_r3, 0.0F, 1.5708F, 0.0F);
			cube_r3.setTextureOffset(3, 6).addBox(-9.0893F, 1.3482F, -3.5179F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			cube_r3.setTextureOffset(3, 6).addBox(-9.0893F, 4.3482F, -3.5179F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			cube_r3.setTextureOffset(3, 6).addBox(-9.0893F, 3.3482F, -3.5179F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			cube_r3.setTextureOffset(3, 6).addBox(-9.0893F, 2.3482F, -3.5179F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			cube_r4 = new ModelRenderer(this);
			cube_r4.setRotationPoint(2.0581F, -1.9692F, -9.4821F);
			bb_main.addChild(cube_r4);
			setRotationAngle(cube_r4, 0.0F, 1.5708F, 0.0F);
			cube_r4.setTextureOffset(3, 6).addBox(-9.0893F, 1.3482F, -3.5179F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			cube_r4.setTextureOffset(3, 6).addBox(-9.0893F, 2.3482F, -3.5179F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			cube_r4.setTextureOffset(3, 6).addBox(-9.0893F, 4.3482F, -3.5179F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			cube_r4.setTextureOffset(3, 6).addBox(-9.0893F, 3.3482F, -3.5179F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			cube_r5 = new ModelRenderer(this);
			cube_r5.setRotationPoint(2.0581F, -2.9692F, -8.4821F);
			bb_main.addChild(cube_r5);
			setRotationAngle(cube_r5, 0.0F, 1.5708F, 0.0F);
			cube_r5.setTextureOffset(3, 6).addBox(-9.0893F, 3.3482F, -3.5179F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			cube_r5.setTextureOffset(3, 6).addBox(-9.0893F, 4.3482F, -3.5179F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			cube_r5.setTextureOffset(3, 6).addBox(-9.0893F, 5.3482F, -3.5179F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			cube_r6 = new ModelRenderer(this);
			cube_r6.setRotationPoint(3.0581F, -2.9692F, -11.4821F);
			bb_main.addChild(cube_r6);
			setRotationAngle(cube_r6, 0.0F, 1.5708F, 0.0F);
			cube_r6.setTextureOffset(6, 3).addBox(-9.0893F, 2.3482F, -3.5179F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			cube_r6.setTextureOffset(3, 8).addBox(-13.0893F, 4.3482F, -3.5179F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			cube_r6.setTextureOffset(3, 8).addBox(-13.0893F, 3.3482F, -3.5179F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			cube_r6.setTextureOffset(0, 11).addBox(-14.0893F, 4.3482F, -3.5179F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			cube_r6.setTextureOffset(0, 3).addBox(-11.0893F, 6.3482F, -2.5179F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			cube_r6.setTextureOffset(3, 2).addBox(-9.0893F, 7.3482F, -3.5179F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			cube_r6.setTextureOffset(3, 4).addBox(-8.0893F, 7.3482F, -3.5179F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			cube_r6.setTextureOffset(4, 0).addBox(-7.0893F, 6.3482F, -3.5179F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			cube_r6.setTextureOffset(0, 5).addBox(-7.0893F, 5.3482F, -3.5179F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			cube_r6.setTextureOffset(0, 0).addBox(-8.0893F, 3.3482F, -3.5179F, 1.0F, 2.0F, 1.0F, 0.0F, false);
			cube_r6.setTextureOffset(3, 6).addBox(-9.0893F, 3.3482F, -3.5179F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			cube_r6.setTextureOffset(6, 3).addBox(-9.0893F, 2.3482F, -3.5179F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			cube_r6.setTextureOffset(6, 5).addBox(-9.0893F, 1.3482F, -3.5179F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			cube_r6.setTextureOffset(0, 7).addBox(-10.0893F, 0.3482F, -3.5179F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			cube_r6.setTextureOffset(6, 7).addBox(-11.0893F, 0.3482F, -3.5179F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			cube_r6.setTextureOffset(7, 1).addBox(-12.0893F, 1.3482F, -3.5179F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			cube_r6.setTextureOffset(0, 9).addBox(-18.0893F, 13.3482F, -4.5179F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			cube_r6.setTextureOffset(6, 9).addBox(-17.0893F, 12.3482F, -4.5179F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			cube_r6.setTextureOffset(9, 4).addBox(-16.0893F, 11.3482F, -4.5179F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			cube_r6.setTextureOffset(9, 8).addBox(-15.0893F, 10.3482F, -4.5179F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			cube_r6.setTextureOffset(3, 10).addBox(-14.0893F, 9.3482F, -4.5179F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			cube_r6.setTextureOffset(9, 10).addBox(-18.0893F, 13.3482F, -2.5179F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			cube_r6.setTextureOffset(10, 0).addBox(-17.0893F, 12.3482F, -2.5179F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			cube_r6.setTextureOffset(10, 2).addBox(-16.0893F, 11.3482F, -2.5179F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			cube_r6.setTextureOffset(0, 11).addBox(-14.0893F, 4.3482F, -3.5179F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			cube_r6.setTextureOffset(6, 11).addBox(-15.0893F, 10.3482F, -2.5179F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			cube_r6.setTextureOffset(3, 12).addBox(-14.0893F, 9.3482F, -2.5179F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			cube_r6.setTextureOffset(9, 12).addBox(-13.0893F, 8.3482F, -2.5179F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			cube_r6.setTextureOffset(12, 5).addBox(-13.0893F, 8.3482F, -4.5179F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			cube_r6.setTextureOffset(12, 7).addBox(-12.0893F, 7.3482F, -4.5179F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			cube_r6.setTextureOffset(12, 9).addBox(-12.0893F, 7.3482F, -2.5179F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			cube_r6.setTextureOffset(12, 11).addBox(-12.0893F, 7.3482F, -3.5179F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			cube_r6.setTextureOffset(0, 13).addBox(-13.0893F, 5.3482F, -3.5179F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			cube_r6.setTextureOffset(6, 13).addBox(-13.0893F, 8.3482F, -3.5179F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			cube_r6.setTextureOffset(12, 13).addBox(-11.0893F, 5.3482F, -3.5179F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			cube_r6.setTextureOffset(13, 1).addBox(-14.0893F, 9.3482F, -3.5179F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			cube_r6.setTextureOffset(13, 3).addBox(-15.0893F, 10.3482F, -3.5179F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			cube_r6.setTextureOffset(3, 14).addBox(-12.0893F, 6.3482F, -3.5179F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			cube_r6.setTextureOffset(9, 14).addBox(-16.0893F, 11.3482F, -3.5179F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			cube_r6.setTextureOffset(0, 15).addBox(-12.0893F, 6.3482F, -3.5179F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			cube_r6.setTextureOffset(6, 15).addBox(-13.0893F, 7.3482F, -3.5179F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			cube_r6.setTextureOffset(12, 15).addBox(-17.0893F, 12.3482F, -3.5179F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			cube_r6.setTextureOffset(15, 6).addBox(-14.0893F, 8.3482F, -3.5179F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			cube_r6.setTextureOffset(15, 8).addBox(-15.0893F, 9.3482F, -3.5179F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			cube_r6.setTextureOffset(15, 10).addBox(-16.0893F, 10.3482F, -3.5179F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			cube_r6.setTextureOffset(15, 12).addBox(-18.0893F, 12.3482F, -3.5179F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			cube_r6.setTextureOffset(15, 14).addBox(-17.0893F, 11.3482F, -3.5179F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			cube_r6.setTextureOffset(3, 16).addBox(-18.0893F, 12.3482F, -3.5179F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			cube_r6.setTextureOffset(9, 16).addBox(-18.0893F, 12.3482F, -3.5179F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			cube_r6.setTextureOffset(15, 16).addBox(-18.0893F, 13.3482F, -3.5179F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			cube_r6.setTextureOffset(16, 0).addBox(-10.0893F, 6.3482F, -3.5179F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			cube_r6.setTextureOffset(16, 2).addBox(-11.0893F, 7.3482F, -3.5179F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			cube_r6.setTextureOffset(16, 4).addBox(-17.0893F, 13.3482F, -3.5179F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			cube_r6.setTextureOffset(0, 17).addBox(-12.0893F, 8.3482F, -3.5179F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			cube_r6.setTextureOffset(6, 17).addBox(-13.0893F, 9.3482F, -3.5179F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			cube_r6.setTextureOffset(12, 17).addBox(-14.0893F, 10.3482F, -3.5179F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			cube_r6.setTextureOffset(3, 18).addBox(-15.0893F, 11.3482F, -3.5179F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			cube_r6.setTextureOffset(9, 18).addBox(-16.0893F, 12.3482F, -3.5179F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			cube_r7 = new ModelRenderer(this);
			cube_r7.setRotationPoint(2.0581F, -4.9692F, -11.4821F);
			bb_main.addChild(cube_r7);
			setRotationAngle(cube_r7, 0.0F, 1.5708F, 0.0F);
			cube_r7.setTextureOffset(3, 6).addBox(-9.0893F, 7.3482F, -3.5179F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			cube_r7.setTextureOffset(3, 6).addBox(-9.0893F, 8.3482F, -3.5179F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			cube_r7.setTextureOffset(3, 6).addBox(-9.0893F, 6.3482F, -3.5179F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			cube_r7.setTextureOffset(16, 2).addBox(-11.0893F, 8.3482F, -3.5179F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			cube_r8 = new ModelRenderer(this);
			cube_r8.setRotationPoint(2.0581F, -4.9692F, -12.4821F);
			bb_main.addChild(cube_r8);
			setRotationAngle(cube_r8, 0.0F, 1.5708F, 0.0F);
			cube_r8.setTextureOffset(3, 6).addBox(-9.0893F, 7.3482F, -3.5179F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			cube_r8.setTextureOffset(3, 6).addBox(-9.0893F, 8.3482F, -3.5179F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			cube_r9 = new ModelRenderer(this);
			cube_r9.setRotationPoint(4.0581F, -1.9692F, -9.4821F);
			bb_main.addChild(cube_r9);
			setRotationAngle(cube_r9, 0.0F, 1.5708F, 0.0F);
			cube_r9.setTextureOffset(16, 0).addBox(-10.0893F, 4.3482F, -3.5179F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			cube_r9.setTextureOffset(16, 0).addBox(-10.0893F, 3.3482F, -3.5179F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			cube_r9.setTextureOffset(16, 0).addBox(-10.0893F, 2.3482F, -3.5179F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			cube_r10 = new ModelRenderer(this);
			cube_r10.setRotationPoint(4.0581F, 1.0308F, -11.4821F);
			bb_main.addChild(cube_r10);
			setRotationAngle(cube_r10, 0.0F, 1.5708F, 0.0F);
			cube_r10.setTextureOffset(16, 0).addBox(-10.0893F, -1.6518F, -3.5179F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			cube_r10.setTextureOffset(16, 0).addBox(-10.0893F, -0.6518F, -3.5179F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			cube_r10.setTextureOffset(16, 0).addBox(-10.0893F, 0.3482F, -3.5179F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			cube_r10.setTextureOffset(16, 0).addBox(-10.0893F, 1.3482F, -3.5179F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			cube_r11 = new ModelRenderer(this);
			cube_r11.setRotationPoint(4.0581F, -0.9692F, -12.4821F);
			bb_main.addChild(cube_r11);
			setRotationAngle(cube_r11, 0.0F, 1.5708F, 0.0F);
			cube_r11.setTextureOffset(16, 0).addBox(-10.0893F, 2.3482F, -3.5179F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			cube_r11.setTextureOffset(16, 0).addBox(-10.0893F, 3.3482F, -3.5179F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			cube_r11.setTextureOffset(16, 0).addBox(-10.0893F, 4.3482F, -3.5179F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			cube_r12 = new ModelRenderer(this);
			cube_r12.setRotationPoint(4.0581F, -1.9692F, -13.4821F);
			bb_main.addChild(cube_r12);
			setRotationAngle(cube_r12, 0.0F, 1.5708F, 0.0F);
			cube_r12.setTextureOffset(16, 0).addBox(-10.0893F, 4.3482F, -3.5179F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			cube_r12.setTextureOffset(16, 0).addBox(-10.0893F, 5.3482F, -3.5179F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			cube_r13 = new ModelRenderer(this);
			cube_r13.setRotationPoint(3.0581F, -2.9692F, -12.4821F);
			bb_main.addChild(cube_r13);
			setRotationAngle(cube_r13, 0.0F, 1.5708F, 0.0F);
			cube_r13.setTextureOffset(3, 8).addBox(-13.0893F, 2.3482F, -3.5179F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			cube_r13.setTextureOffset(0, 11).addBox(-14.0893F, 2.3482F, -3.5179F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			cube_r14 = new ModelRenderer(this);
			cube_r14.setRotationPoint(3.0581F, -1.9692F, -13.4821F);
			bb_main.addChild(cube_r14);
			setRotationAngle(cube_r14, 0.0F, 1.5708F, 0.0F);
			cube_r14.setTextureOffset(3, 8).addBox(-13.0893F, 0.3482F, -3.5179F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			cube_r15 = new ModelRenderer(this);
			cube_r15.setRotationPoint(3.0581F, -1.9692F, -14.4821F);
			bb_main.addChild(cube_r15);
			setRotationAngle(cube_r15, 0.0F, 1.5708F, 0.0F);
			cube_r15.setTextureOffset(3, 8).addBox(-13.0893F, 0.3482F, -3.5179F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		}

		@Override
		public void setRotationAngles(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
			// previously the render function, render code was moved to a method below
		}

		@Override
		public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue,
				float alpha) {
			bb_main.render(matrixStack, buffer, packedLight, packedOverlay);
		}

		public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
			modelRenderer.rotateAngleX = x;
			modelRenderer.rotateAngleY = y;
			modelRenderer.rotateAngleZ = z;
		}
	}
	public static ArrowCustomEntity shoot(World world, LivingEntity entity, Random random, float power, double damage, int knockback) {
		ArrowCustomEntity entityarrow = new ArrowCustomEntity(arrow, entity, world);
		entityarrow.shoot(entity.getLookVec().x, entity.getLookVec().y, entity.getLookVec().z, power * 2, 0);
		entityarrow.setSilent(true);
		entityarrow.setIsCritical(false);
		entityarrow.setDamage(damage);
		entityarrow.setKnockbackStrength(knockback);
		world.addEntity(entityarrow);
		double x = entity.getPosX();
		double y = entity.getPosY();
		double z = entity.getPosZ();
		world.playSound((PlayerEntity) null, (double) x, (double) y, (double) z,
				(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.egg.throw")),
				SoundCategory.PLAYERS, 1, 1f / (random.nextFloat() * 0.5f + 1) + (power / 2));
		return entityarrow;
	}

	public static ArrowCustomEntity shoot(LivingEntity entity, LivingEntity target) {
		ArrowCustomEntity entityarrow = new ArrowCustomEntity(arrow, entity, entity.world);
		double d0 = target.getPosY() + (double) target.getEyeHeight() - 1.1;
		double d1 = target.getPosX() - entity.getPosX();
		double d3 = target.getPosZ() - entity.getPosZ();
		entityarrow.shoot(d1, d0 - entityarrow.getPosY() + (double) MathHelper.sqrt(d1 * d1 + d3 * d3) * 0.2F, d3, 1.4f * 2, 12.0F);
		entityarrow.setSilent(true);
		entityarrow.setDamage(8);
		entityarrow.setKnockbackStrength(2);
		entityarrow.setIsCritical(false);
		entity.world.addEntity(entityarrow);
		double x = entity.getPosX();
		double y = entity.getPosY();
		double z = entity.getPosZ();
		entity.world.playSound((PlayerEntity) null, (double) x, (double) y, (double) z,
				(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.egg.throw")),
				SoundCategory.PLAYERS, 1, 1f / (new Random().nextFloat() * 0.5f + 1));
		return entityarrow;
	}
}
