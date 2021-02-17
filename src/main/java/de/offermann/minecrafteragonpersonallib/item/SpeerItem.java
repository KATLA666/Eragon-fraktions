
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
public class SpeerItem extends MinecraftEragonPersonallibModElements.ModElement {
	@ObjectHolder("minecraft_eragon__personallib:speer")
	public static final Item block = null;
	@ObjectHolder("minecraft_eragon__personallib:entitybulletspeer")
	public static final EntityType arrow = null;
	public SpeerItem(MinecraftEragonPersonallibModElements instance) {
		super(instance, 28);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new ItemRanged());
		elements.entities.add(() -> (EntityType.Builder.<ArrowCustomEntity>create(ArrowCustomEntity::new, EntityClassification.MISC)
				.setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(1).setCustomClientFactory(ArrowCustomEntity::new)
				.size(0.5f, 0.5f)).build("entitybulletspeer").setRegistryName("entitybulletspeer"));
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void init(FMLCommonSetupEvent event) {
		RenderingRegistry.registerEntityRenderingHandler(arrow, renderManager -> new CustomRender(renderManager));
	}
	public static class ItemRanged extends Item {
		public ItemRanged() {
			super(new Item.Properties().group(EragonItemGroup.tab).maxDamage(1));
			setRegistryName("speer");
		}

		@Override
		public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity entity, Hand hand) {
			entity.setActiveHand(hand);
			return new ActionResult(ActionResultType.SUCCESS, entity.getHeldItem(hand));
		}

		@Override
		public UseAction getUseAction(ItemStack itemstack) {
			return UseAction.SPEAR;
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
					ItemStack stack = ShootableItem.getHeldAmmo(entity, e -> e.getItem() == new ItemStack(SpeerItem.block, (int) (1)).getItem());
					if (stack == ItemStack.EMPTY) {
						for (int i = 0; i < entity.inventory.mainInventory.size(); i++) {
							ItemStack teststack = entity.inventory.mainInventory.get(i);
							if (teststack != null && teststack.getItem() == new ItemStack(SpeerItem.block, (int) (1)).getItem()) {
								stack = teststack;
								break;
							}
						}
					}
					if (entity.abilities.isCreativeMode || stack != ItemStack.EMPTY) {
						ArrowCustomEntity entityarrow = shoot(world, entity, random, 1f, 5, 5);
						itemstack.damageItem(1, entity, e -> e.sendBreakAnimation(entity.getActiveHand()));
						if (entity.abilities.isCreativeMode) {
							entityarrow.pickupStatus = AbstractArrowEntity.PickupStatus.CREATIVE_ONLY;
						} else {
							if (new ItemStack(SpeerItem.block, (int) (1)).isDamageable()) {
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
			return new ItemStack(SpeerItem.block, (int) (1));
		}

		@Override
		protected ItemStack getArrowStack() {
			return new ItemStack(SpeerItem.block, (int) (1));
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
		private static final ResourceLocation texture = new ResourceLocation("minecraft_eragon__personallib:textures/speer.png");
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
			EntityModel model = new ModelSpeer();
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
	public static class ModelSpeer extends EntityModel<Entity> {
		private final ModelRenderer Stiel;
		private final ModelRenderer Stiel_r1;
		private final ModelRenderer Knauf;
		private final ModelRenderer Knauf_r1;
		private final ModelRenderer cube_r1;
		private final ModelRenderer cube_r2_r1;
		private final ModelRenderer cube_r2;
		private final ModelRenderer cube_r2_r2;
		private final ModelRenderer Schneide;
		private final ModelRenderer Schneide_r1;
		private final ModelRenderer bb_main;
		private final ModelRenderer bb_main_r1;
		public ModelSpeer() {
			textureWidth = 128;
			textureHeight = 128;
			Stiel = new ModelRenderer(this);
			Stiel.setRotationPoint(0.0F, 10.0F, 0.0F);
			Stiel_r1 = new ModelRenderer(this);
			Stiel_r1.setRotationPoint(0.0F, -18.0F, -9.0F);
			Stiel.addChild(Stiel_r1);
			setRotationAngle(Stiel_r1, 0.0F, 0.0F, -3.1416F);
			Stiel_r1.setTextureOffset(0, 0).addBox(-1.0F, -74.0F, 0.0F, 1.0F, 63.0F, 1.0F, 0.0F, false);
			Stiel_r1.setTextureOffset(4, 4).addBox(0.0F, -74.0F, 1.0F, 1.0F, 63.0F, 1.0F, 0.0F, false);
			Stiel_r1.setTextureOffset(8, 8).addBox(0.0F, -74.0F, -1.0F, 1.0F, 63.0F, 1.0F, 0.0F, false);
			Stiel_r1.setTextureOffset(12, 12).addBox(1.0F, -74.0F, 0.0F, 1.0F, 63.0F, 1.0F, 0.0F, false);
			Stiel_r1.setTextureOffset(16, 16).addBox(0.0F, -74.0F, 0.0F, 1.0F, 63.0F, 1.0F, 0.0F, false);
			Knauf = new ModelRenderer(this);
			Knauf.setRotationPoint(0.0F, 7.0F, -16.0F);
			Knauf_r1 = new ModelRenderer(this);
			Knauf_r1.setRotationPoint(0.0F, -15.0F, 7.0F);
			Knauf.addChild(Knauf_r1);
			setRotationAngle(Knauf_r1, 0.0F, 0.0F, -3.1416F);
			Knauf_r1.setTextureOffset(33, 25).addBox(-1.0F, -75.0F, -1.0F, 3.0F, 1.0F, 3.0F, 0.0F, false);
			Knauf_r1.setTextureOffset(43, 11).addBox(-1.0F, -78.0F, -2.0F, 3.0F, 3.0F, 1.0F, 0.0F, false);
			Knauf_r1.setTextureOffset(44, 4).addBox(-1.0F, -78.0F, 2.0F, 3.0F, 3.0F, 1.0F, 0.0F, false);
			Knauf_r1.setTextureOffset(34, 12).addBox(-1.0F, -79.0F, -1.0F, 3.0F, 1.0F, 3.0F, 0.0F, false);
			cube_r1 = new ModelRenderer(this);
			cube_r1.setRotationPoint(3.0F, -76.0F, 6.0F);
			Knauf.addChild(cube_r1);
			setRotationAngle(cube_r1, 0.0F, -1.5708F, 0.0F);
			cube_r2_r1 = new ModelRenderer(this);
			cube_r2_r1.setRotationPoint(-3.0F, 7.0F, 1.0F);
			cube_r1.addChild(cube_r2_r1);
			setRotationAngle(cube_r2_r1, 0.0F, 0.0F, -3.1416F);
			cube_r2_r1.setTextureOffset(27, 43).addBox(-6.0F, -132.0F, 0.0F, 3.0F, 3.0F, 1.0F, 0.0F, false);
			cube_r2_r1.setTextureOffset(27, 43).addBox(-6.0F, -132.0F, 4.0F, 3.0F, 3.0F, 1.0F, 0.0F, false);
			cube_r2 = new ModelRenderer(this);
			cube_r2.setRotationPoint(-1.0F, -76.0F, 6.0F);
			Knauf.addChild(cube_r2);
			setRotationAngle(cube_r2, 0.0F, -1.5708F, 0.0F);
			cube_r2_r2 = new ModelRenderer(this);
			cube_r2_r2.setRotationPoint(1.0F, 7.0F, 1.0F);
			cube_r2.addChild(cube_r2_r2);
			setRotationAngle(cube_r2_r2, 0.0F, 0.0F, -3.1416F);
			cube_r2_r2.setTextureOffset(42, 38).addBox(-1.0F, -132.0F, -1.0F, 3.0F, 3.0F, 1.0F, 0.0F, false);
			Schneide = new ModelRenderer(this);
			Schneide.setRotationPoint(0.0F, 24.0F, 0.0F);
			Schneide_r1 = new ModelRenderer(this);
			Schneide_r1.setRotationPoint(0.0F, -32.0F, -9.0F);
			Schneide.addChild(Schneide_r1);
			setRotationAngle(Schneide_r1, 0.0F, 0.0F, -3.1416F);
			Schneide_r1.setTextureOffset(19, 13).addBox(-3.0F, -3.0F, 0.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			Schneide_r1.setTextureOffset(25, 7).addBox(3.0F, -3.0F, 0.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			Schneide_r1.setTextureOffset(24, 0).addBox(-3.0F, -9.0F, 0.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			Schneide_r1.setTextureOffset(25, 16).addBox(4.0F, -10.0F, 0.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			Schneide_r1.setTextureOffset(25, 16).addBox(3.0F, -9.0F, 0.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			Schneide_r1.setTextureOffset(23, 23).addBox(-3.0F, -6.0F, 0.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			Schneide_r1.setTextureOffset(25, 13).addBox(3.0F, -6.0F, 0.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			Schneide_r1.setTextureOffset(23, 25).addBox(-2.0F, -11.0F, 0.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			Schneide_r1.setTextureOffset(25, 9).addBox(2.0F, -11.0F, 0.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			Schneide_r1.setTextureOffset(35, 35).addBox(-2.0F, -9.0F, -1.0F, 1.0F, 1.0F, 3.0F, 0.0F, false);
			Schneide_r1.setTextureOffset(36, 20).addBox(2.0F, -9.0F, -1.0F, 1.0F, 1.0F, 3.0F, 0.0F, false);
			Schneide_r1.setTextureOffset(20, 24).addBox(-2.0F, -8.0F, 0.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			Schneide_r1.setTextureOffset(20, 28).addBox(2.0F, -8.0F, 0.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			Schneide_r1.setTextureOffset(34, 29).addBox(-2.0F, -6.0F, -1.0F, 1.0F, 1.0F, 3.0F, 0.0F, false);
			Schneide_r1.setTextureOffset(38, 0).addBox(2.0F, -6.0F, -1.0F, 1.0F, 1.0F, 3.0F, 0.0F, false);
			Schneide_r1.setTextureOffset(20, 22).addBox(-2.0F, -5.0F, 0.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			Schneide_r1.setTextureOffset(27, 28).addBox(2.0F, -5.0F, 0.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			Schneide_r1.setTextureOffset(36, 16).addBox(-1.0F, -11.0F, -1.0F, 1.0F, 1.0F, 3.0F, 0.0F, false);
			Schneide_r1.setTextureOffset(29, 39).addBox(1.0F, -11.0F, -1.0F, 1.0F, 1.0F, 3.0F, 0.0F, false);
			Schneide_r1.setTextureOffset(24, 2).addBox(-1.0F, -10.0F, 0.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			Schneide_r1.setTextureOffset(29, 19).addBox(1.0F, -10.0F, 0.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			Schneide_r1.setTextureOffset(25, 7).addBox(-1.0F, -9.0F, -2.0F, 1.0F, 1.0F, 5.0F, 0.0F, false);
			Schneide_r1.setTextureOffset(27, 13).addBox(1.0F, -9.0F, -2.0F, 1.0F, 1.0F, 5.0F, 0.0F, false);
			Schneide_r1.setTextureOffset(27, 35).addBox(-1.0F, -8.0F, -1.0F, 1.0F, 1.0F, 3.0F, 0.0F, false);
			Schneide_r1.setTextureOffset(37, 39).addBox(1.0F, -8.0F, -1.0F, 1.0F, 1.0F, 3.0F, 0.0F, false);
			Schneide_r1.setTextureOffset(23, 21).addBox(-1.0F, -7.0F, 0.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			Schneide_r1.setTextureOffset(29, 21).addBox(1.0F, -7.0F, 0.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			Schneide_r1.setTextureOffset(24, 1).addBox(-1.0F, -6.0F, -2.0F, 1.0F, 1.0F, 5.0F, 0.0F, false);
			Schneide_r1.setTextureOffset(20, 28).addBox(1.0F, -6.0F, -2.0F, 1.0F, 1.0F, 5.0F, 0.0F, false);
			Schneide_r1.setTextureOffset(20, 16).addBox(-1.0F, -5.0F, -1.0F, 1.0F, 1.0F, 3.0F, 0.0F, false);
			Schneide_r1.setTextureOffset(39, 6).addBox(1.0F, -5.0F, -1.0F, 1.0F, 1.0F, 3.0F, 0.0F, false);
			Schneide_r1.setTextureOffset(20, 20).addBox(-1.0F, -4.0F, 0.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			Schneide_r1.setTextureOffset(29, 25).addBox(1.0F, -4.0F, 0.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			Schneide_r1.setTextureOffset(12, 8).addBox(-2.0F, -3.0F, -1.0F, 1.0F, 1.0F, 3.0F, 0.0F, false);
			Schneide_r1.setTextureOffset(39, 30).addBox(2.0F, -3.0F, -1.0F, 1.0F, 1.0F, 3.0F, 0.0F, false);
			Schneide_r1.setTextureOffset(19, 11).addBox(-2.0F, -2.0F, 0.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			Schneide_r1.setTextureOffset(20, 30).addBox(2.0F, -2.0F, 0.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			Schneide_r1.setTextureOffset(17, 0).addBox(-1.0F, -3.0F, -2.0F, 1.0F, 1.0F, 5.0F, 0.0F, false);
			Schneide_r1.setTextureOffset(27, 29).addBox(1.0F, -3.0F, -2.0F, 1.0F, 1.0F, 5.0F, 0.0F, false);
			Schneide_r1.setTextureOffset(4, 0).addBox(-1.0F, -2.0F, -1.0F, 1.0F, 1.0F, 3.0F, 0.0F, false);
			Schneide_r1.setTextureOffset(20, 40).addBox(1.0F, -2.0F, -1.0F, 1.0F, 1.0F, 3.0F, 0.0F, false);
			Schneide_r1.setTextureOffset(17, 8).addBox(-1.0F, -1.0F, 0.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			Schneide_r1.setTextureOffset(27, 30).addBox(1.0F, -1.0F, 0.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			Schneide_r1.setTextureOffset(8, 0).addBox(0.0F, -9.0F, -3.0F, 1.0F, 1.0F, 7.0F, 0.0F, false);
			Schneide_r1.setTextureOffset(29, 19).addBox(0.0F, -11.0F, -2.0F, 1.0F, 1.0F, 5.0F, 0.0F, false);
			Schneide_r1.setTextureOffset(40, 34).addBox(0.0F, -10.0F, -1.0F, 1.0F, 1.0F, 3.0F, 0.0F, false);
			Schneide_r1.setTextureOffset(31, 0).addBox(0.0F, -12.0F, -6.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			Schneide_r1.setTextureOffset(31, 2).addBox(0.0F, -12.0F, 6.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			Schneide_r1.setTextureOffset(32, 7).addBox(0.0F, -11.0F, -5.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			Schneide_r1.setTextureOffset(32, 9).addBox(0.0F, -11.0F, 5.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			Schneide_r1.setTextureOffset(20, 34).addBox(0.0F, -10.0F, -4.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			Schneide_r1.setTextureOffset(34, 16).addBox(0.0F, -10.0F, 4.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			Schneide_r1.setTextureOffset(32, 35).addBox(0.0F, -8.0F, -5.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			Schneide_r1.setTextureOffset(20, 36).addBox(0.0F, -8.0F, 5.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			Schneide_r1.setTextureOffset(31, 0).addBox(0.0F, -8.0F, -2.0F, 1.0F, 1.0F, 5.0F, 0.0F, false);
			Schneide_r1.setTextureOffset(41, 17).addBox(0.0F, -7.0F, -1.0F, 1.0F, 1.0F, 3.0F, 0.0F, false);
			Schneide_r1.setTextureOffset(34, 39).addBox(0.0F, -7.0F, -4.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			Schneide_r1.setTextureOffset(32, 6).addBox(0.0F, -5.0F, -2.0F, 1.0F, 1.0F, 5.0F, 0.0F, false);
			Schneide_r1.setTextureOffset(25, 40).addBox(0.0F, -7.0F, 4.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			Schneide_r1.setTextureOffset(16, 8).addBox(0.0F, -3.0F, -3.0F, 1.0F, 1.0F, 7.0F, 0.0F, false);
			Schneide_r1.setTextureOffset(20, 20).addBox(0.0F, -6.0F, -3.0F, 1.0F, 1.0F, 7.0F, 0.0F, false);
			Schneide_r1.setTextureOffset(41, 21).addBox(0.0F, -4.0F, -1.0F, 1.0F, 1.0F, 3.0F, 0.0F, false);
			Schneide_r1.setTextureOffset(42, 25).addBox(0.0F, -4.0F, 4.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			Schneide_r1.setTextureOffset(35, 43).addBox(0.0F, -4.0F, -4.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			Schneide_r1.setTextureOffset(20, 34).addBox(0.0F, -2.0F, -2.0F, 1.0F, 1.0F, 5.0F, 0.0F, false);
			Schneide_r1.setTextureOffset(42, 42).addBox(0.0F, -1.0F, -1.0F, 1.0F, 1.0F, 3.0F, 0.0F, false);
			Schneide_r1.setTextureOffset(39, 43).addBox(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			bb_main = new ModelRenderer(this);
			bb_main.setRotationPoint(0.0F, 24.0F, 0.0F);
			bb_main_r1 = new ModelRenderer(this);
			bb_main_r1.setRotationPoint(0.0F, -32.0F, -9.0F);
			bb_main.addChild(bb_main_r1);
			setRotationAngle(bb_main_r1, 0.0F, 0.0F, -3.1416F);
			bb_main_r1.setTextureOffset(8, 4).addBox(5.0F, -11.0F, 0.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			bb_main_r1.setTextureOffset(11, 3).addBox(4.0F, -7.0F, 0.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			bb_main_r1.setTextureOffset(16, 12).addBox(-4.0F, -10.0F, 0.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			bb_main_r1.setTextureOffset(17, 0).addBox(-5.0F, -11.0F, 0.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			bb_main_r1.setTextureOffset(17, 2).addBox(-4.0F, -7.0F, 0.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		}

		@Override
		public void setRotationAngles(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
			// previously the render function, render code was moved to a method below
		}

		@Override
		public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue,
				float alpha) {
			Stiel.render(matrixStack, buffer, packedLight, packedOverlay);
			Knauf.render(matrixStack, buffer, packedLight, packedOverlay);
			Schneide.render(matrixStack, buffer, packedLight, packedOverlay);
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
				(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.arrow.shoot")),
				SoundCategory.PLAYERS, 1, 1f / (random.nextFloat() * 0.5f + 1) + (power / 2));
		return entityarrow;
	}

	public static ArrowCustomEntity shoot(LivingEntity entity, LivingEntity target) {
		ArrowCustomEntity entityarrow = new ArrowCustomEntity(arrow, entity, entity.world);
		double d0 = target.getPosY() + (double) target.getEyeHeight() - 1.1;
		double d1 = target.getPosX() - entity.getPosX();
		double d3 = target.getPosZ() - entity.getPosZ();
		entityarrow.shoot(d1, d0 - entityarrow.getPosY() + (double) MathHelper.sqrt(d1 * d1 + d3 * d3) * 0.2F, d3, 1f * 2, 12.0F);
		entityarrow.setSilent(true);
		entityarrow.setDamage(5);
		entityarrow.setKnockbackStrength(5);
		entityarrow.setIsCritical(false);
		entity.world.addEntity(entityarrow);
		double x = entity.getPosX();
		double y = entity.getPosY();
		double z = entity.getPosZ();
		entity.world.playSound((PlayerEntity) null, (double) x, (double) y, (double) z,
				(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.arrow.shoot")),
				SoundCategory.PLAYERS, 1, 1f / (new Random().nextFloat() * 0.5f + 1));
		return entityarrow;
	}
}
