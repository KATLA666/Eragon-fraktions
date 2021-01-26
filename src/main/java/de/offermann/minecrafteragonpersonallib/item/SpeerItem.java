
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
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Item;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.IRendersAsItem;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.Entity;
import net.minecraft.client.renderer.model.ModelBox;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.EntityRenderer;

import java.util.Random;

import de.offermann.minecrafteragonpersonallib.MinecraftEragonPersonallibModElements;

import com.mojang.blaze3d.platform.GlStateManager;

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
		RenderingRegistry.registerEntityRenderingHandler(ArrowCustomEntity.class, renderManager -> new CustomRender(renderManager));
	}
	public static class ItemRanged extends Item {
		public ItemRanged() {
			super(new Item.Properties().group(ItemGroup.COMBAT).maxDamage(1));
			setRegistryName("speer");
		}

		@Override
		public UseAction getUseAction(ItemStack itemstack) {
			return UseAction.SPEAR;
		}

		@Override
		public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity entity, Hand hand) {
			entity.setActiveHand(hand);
			return new ActionResult(ActionResultType.SUCCESS, entity.getHeldItem(hand));
		}

		@Override
		public int getUseDuration(ItemStack itemstack) {
			return 72000;
		}

		@Override
		public void onPlayerStoppedUsing(ItemStack itemstack, World world, LivingEntity entityLiving, int timeLeft) {
			if (!world.isRemote && entityLiving instanceof ServerPlayerEntity) {
				ServerPlayerEntity entity = (ServerPlayerEntity) entityLiving;
				double x = entity.posX;
				double y = entity.posY;
				double z = entity.posZ;
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
			double x = this.posX;
			double y = this.posY;
			double z = this.posZ;
			World world = this.world;
			Entity entity = this.getShooter();
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
		public void doRender(ArrowCustomEntity bullet, double d, double d1, double d2, float f, float f1) {
			this.bindEntityTexture(bullet);
			GlStateManager.pushMatrix();
			GlStateManager.translatef((float) d, (float) d1, (float) d2);
			GlStateManager.rotatef(f, 0, 1, 0);
			GlStateManager.rotatef(90f - bullet.prevRotationPitch - (bullet.rotationPitch - bullet.prevRotationPitch) * f1, 1, 0, 0);
			EntityModel model = new ModelSpeer();
			model.render(bullet, 0, 0, 0, 0, 0, 0.0625f);
			GlStateManager.popMatrix();
		}

		@Override
		protected ResourceLocation getEntityTexture(ArrowCustomEntity entity) {
			return texture;
		}
	}

	// Made with Blockbench 3.7.5
	// Exported for Minecraft version 1.14
	// Paste this class into your mod and generate all required imports
	public static class ModelSpeer extends EntityModel {
		private final RendererModel Stiel;
		private final RendererModel Knauf;
		private final RendererModel cube_r1;
		private final RendererModel cube_r2;
		private final RendererModel Schneide;
		private final RendererModel bb_main;
		public ModelSpeer() {
			textureWidth = 128;
			textureHeight = 128;
			Stiel = new RendererModel(this);
			Stiel.setRotationPoint(0.0F, 10.0F, 0.0F);
			Stiel.cubeList.add(new ModelBox(Stiel, 16, 16, 0.0F, -78.0F, -9.0F, 1, 63, 1, 0.0F, false));
			Stiel.cubeList.add(new ModelBox(Stiel, 12, 12, 1.0F, -78.0F, -9.0F, 1, 63, 1, 0.0F, false));
			Stiel.cubeList.add(new ModelBox(Stiel, 8, 8, 0.0F, -78.0F, -10.0F, 1, 63, 1, 0.0F, false));
			Stiel.cubeList.add(new ModelBox(Stiel, 4, 4, 0.0F, -78.0F, -8.0F, 1, 63, 1, 0.0F, false));
			Stiel.cubeList.add(new ModelBox(Stiel, 0, 0, -1.0F, -78.0F, -9.0F, 1, 63, 1, 0.0F, false));
			Knauf = new RendererModel(this);
			Knauf.setRotationPoint(0.0F, 7.0F, -16.0F);
			Knauf.cubeList.add(new ModelBox(Knauf, 34, 12, -1.0F, -80.0F, 6.0F, 3, 1, 3, 0.0F, false));
			Knauf.cubeList.add(new ModelBox(Knauf, 44, 4, -1.0F, -79.0F, 9.0F, 3, 3, 1, 0.0F, false));
			Knauf.cubeList.add(new ModelBox(Knauf, 43, 11, -1.0F, -79.0F, 5.0F, 3, 3, 1, 0.0F, false));
			Knauf.cubeList.add(new ModelBox(Knauf, 33, 25, -1.0F, -76.0F, 6.0F, 3, 1, 3, 0.0F, false));
			cube_r1 = new RendererModel(this);
			cube_r1.setRotationPoint(3.0F, -76.0F, 6.0F);
			Knauf.addChild(cube_r1);
			setRotationAngle(cube_r1, 0.0F, -1.5708F, 0.0F);
			cube_r1.cubeList.add(new ModelBox(cube_r1, 27, 43, 0.0F, -3.0F, 0.0F, 3, 3, 1, 0.0F, false));
			cube_r2 = new RendererModel(this);
			cube_r2.setRotationPoint(-1.0F, -76.0F, 6.0F);
			Knauf.addChild(cube_r2);
			setRotationAngle(cube_r2, 0.0F, -1.5708F, 0.0F);
			cube_r2.cubeList.add(new ModelBox(cube_r2, 42, 38, 0.0F, -3.0F, 0.0F, 3, 3, 1, 0.0F, false));
			Schneide = new RendererModel(this);
			Schneide.setRotationPoint(0.0F, 24.0F, 0.0F);
			Schneide.cubeList.add(new ModelBox(Schneide, 39, 43, 0.0F, -18.0F, -9.0F, 1, 1, 1, 0.0F, false));
			Schneide.cubeList.add(new ModelBox(Schneide, 42, 42, 0.0F, -19.0F, -10.0F, 1, 1, 3, 0.0F, false));
			Schneide.cubeList.add(new ModelBox(Schneide, 20, 34, 0.0F, -20.0F, -11.0F, 1, 1, 5, 0.0F, false));
			Schneide.cubeList.add(new ModelBox(Schneide, 35, 43, 0.0F, -22.0F, -13.0F, 1, 1, 1, 0.0F, false));
			Schneide.cubeList.add(new ModelBox(Schneide, 42, 25, 0.0F, -22.0F, -5.0F, 1, 1, 1, 0.0F, false));
			Schneide.cubeList.add(new ModelBox(Schneide, 41, 21, 0.0F, -22.0F, -10.0F, 1, 1, 3, 0.0F, false));
			Schneide.cubeList.add(new ModelBox(Schneide, 20, 20, 0.0F, -24.0F, -12.0F, 1, 1, 7, 0.0F, false));
			Schneide.cubeList.add(new ModelBox(Schneide, 16, 8, 0.0F, -21.0F, -12.0F, 1, 1, 7, 0.0F, false));
			Schneide.cubeList.add(new ModelBox(Schneide, 25, 40, 0.0F, -25.0F, -5.0F, 1, 1, 1, 0.0F, false));
			Schneide.cubeList.add(new ModelBox(Schneide, 32, 6, 0.0F, -23.0F, -11.0F, 1, 1, 5, 0.0F, false));
			Schneide.cubeList.add(new ModelBox(Schneide, 34, 39, 0.0F, -25.0F, -13.0F, 1, 1, 1, 0.0F, false));
			Schneide.cubeList.add(new ModelBox(Schneide, 41, 17, 0.0F, -25.0F, -10.0F, 1, 1, 3, 0.0F, false));
			Schneide.cubeList.add(new ModelBox(Schneide, 31, 0, 0.0F, -26.0F, -11.0F, 1, 1, 5, 0.0F, false));
			Schneide.cubeList.add(new ModelBox(Schneide, 20, 36, 0.0F, -26.0F, -4.0F, 1, 1, 1, 0.0F, false));
			Schneide.cubeList.add(new ModelBox(Schneide, 32, 35, 0.0F, -26.0F, -14.0F, 1, 1, 1, 0.0F, false));
			Schneide.cubeList.add(new ModelBox(Schneide, 34, 16, 0.0F, -28.0F, -5.0F, 1, 1, 1, 0.0F, false));
			Schneide.cubeList.add(new ModelBox(Schneide, 20, 34, 0.0F, -28.0F, -13.0F, 1, 1, 1, 0.0F, false));
			Schneide.cubeList.add(new ModelBox(Schneide, 32, 9, 0.0F, -29.0F, -4.0F, 1, 1, 1, 0.0F, false));
			Schneide.cubeList.add(new ModelBox(Schneide, 32, 7, 0.0F, -29.0F, -14.0F, 1, 1, 1, 0.0F, false));
			Schneide.cubeList.add(new ModelBox(Schneide, 31, 2, 0.0F, -30.0F, -3.0F, 1, 1, 1, 0.0F, false));
			Schneide.cubeList.add(new ModelBox(Schneide, 31, 0, 0.0F, -30.0F, -15.0F, 1, 1, 1, 0.0F, false));
			Schneide.cubeList.add(new ModelBox(Schneide, 40, 34, 0.0F, -28.0F, -10.0F, 1, 1, 3, 0.0F, false));
			Schneide.cubeList.add(new ModelBox(Schneide, 29, 19, 0.0F, -29.0F, -11.0F, 1, 1, 5, 0.0F, false));
			Schneide.cubeList.add(new ModelBox(Schneide, 8, 0, 0.0F, -27.0F, -12.0F, 1, 1, 7, 0.0F, false));
			Schneide.cubeList.add(new ModelBox(Schneide, 27, 30, 1.0F, -19.0F, -9.0F, 1, 1, 1, 0.0F, false));
			Schneide.cubeList.add(new ModelBox(Schneide, 17, 8, -1.0F, -19.0F, -9.0F, 1, 1, 1, 0.0F, false));
			Schneide.cubeList.add(new ModelBox(Schneide, 20, 40, 1.0F, -20.0F, -10.0F, 1, 1, 3, 0.0F, false));
			Schneide.cubeList.add(new ModelBox(Schneide, 4, 0, -1.0F, -20.0F, -10.0F, 1, 1, 3, 0.0F, false));
			Schneide.cubeList.add(new ModelBox(Schneide, 27, 29, 1.0F, -21.0F, -11.0F, 1, 1, 5, 0.0F, false));
			Schneide.cubeList.add(new ModelBox(Schneide, 17, 0, -1.0F, -21.0F, -11.0F, 1, 1, 5, 0.0F, false));
			Schneide.cubeList.add(new ModelBox(Schneide, 20, 30, 2.0F, -20.0F, -9.0F, 1, 1, 1, 0.0F, false));
			Schneide.cubeList.add(new ModelBox(Schneide, 19, 11, -2.0F, -20.0F, -9.0F, 1, 1, 1, 0.0F, false));
			Schneide.cubeList.add(new ModelBox(Schneide, 39, 30, 2.0F, -21.0F, -10.0F, 1, 1, 3, 0.0F, false));
			Schneide.cubeList.add(new ModelBox(Schneide, 12, 8, -2.0F, -21.0F, -10.0F, 1, 1, 3, 0.0F, false));
			Schneide.cubeList.add(new ModelBox(Schneide, 29, 25, 1.0F, -22.0F, -9.0F, 1, 1, 1, 0.0F, false));
			Schneide.cubeList.add(new ModelBox(Schneide, 20, 20, -1.0F, -22.0F, -9.0F, 1, 1, 1, 0.0F, false));
			Schneide.cubeList.add(new ModelBox(Schneide, 39, 6, 1.0F, -23.0F, -10.0F, 1, 1, 3, 0.0F, false));
			Schneide.cubeList.add(new ModelBox(Schneide, 20, 16, -1.0F, -23.0F, -10.0F, 1, 1, 3, 0.0F, false));
			Schneide.cubeList.add(new ModelBox(Schneide, 20, 28, 1.0F, -24.0F, -11.0F, 1, 1, 5, 0.0F, false));
			Schneide.cubeList.add(new ModelBox(Schneide, 24, 1, -1.0F, -24.0F, -11.0F, 1, 1, 5, 0.0F, false));
			Schneide.cubeList.add(new ModelBox(Schneide, 29, 21, 1.0F, -25.0F, -9.0F, 1, 1, 1, 0.0F, false));
			Schneide.cubeList.add(new ModelBox(Schneide, 23, 21, -1.0F, -25.0F, -9.0F, 1, 1, 1, 0.0F, false));
			Schneide.cubeList.add(new ModelBox(Schneide, 37, 39, 1.0F, -26.0F, -10.0F, 1, 1, 3, 0.0F, false));
			Schneide.cubeList.add(new ModelBox(Schneide, 27, 35, -1.0F, -26.0F, -10.0F, 1, 1, 3, 0.0F, false));
			Schneide.cubeList.add(new ModelBox(Schneide, 27, 13, 1.0F, -27.0F, -11.0F, 1, 1, 5, 0.0F, false));
			Schneide.cubeList.add(new ModelBox(Schneide, 25, 7, -1.0F, -27.0F, -11.0F, 1, 1, 5, 0.0F, false));
			Schneide.cubeList.add(new ModelBox(Schneide, 29, 19, 1.0F, -28.0F, -9.0F, 1, 1, 1, 0.0F, false));
			Schneide.cubeList.add(new ModelBox(Schneide, 24, 2, -1.0F, -28.0F, -9.0F, 1, 1, 1, 0.0F, false));
			Schneide.cubeList.add(new ModelBox(Schneide, 29, 39, 1.0F, -29.0F, -10.0F, 1, 1, 3, 0.0F, false));
			Schneide.cubeList.add(new ModelBox(Schneide, 36, 16, -1.0F, -29.0F, -10.0F, 1, 1, 3, 0.0F, false));
			Schneide.cubeList.add(new ModelBox(Schneide, 27, 28, 2.0F, -23.0F, -9.0F, 1, 1, 1, 0.0F, false));
			Schneide.cubeList.add(new ModelBox(Schneide, 20, 22, -2.0F, -23.0F, -9.0F, 1, 1, 1, 0.0F, false));
			Schneide.cubeList.add(new ModelBox(Schneide, 38, 0, 2.0F, -24.0F, -10.0F, 1, 1, 3, 0.0F, false));
			Schneide.cubeList.add(new ModelBox(Schneide, 34, 29, -2.0F, -24.0F, -10.0F, 1, 1, 3, 0.0F, false));
			Schneide.cubeList.add(new ModelBox(Schneide, 20, 28, 2.0F, -26.0F, -9.0F, 1, 1, 1, 0.0F, false));
			Schneide.cubeList.add(new ModelBox(Schneide, 20, 24, -2.0F, -26.0F, -9.0F, 1, 1, 1, 0.0F, false));
			Schneide.cubeList.add(new ModelBox(Schneide, 36, 20, 2.0F, -27.0F, -10.0F, 1, 1, 3, 0.0F, false));
			Schneide.cubeList.add(new ModelBox(Schneide, 35, 35, -2.0F, -27.0F, -10.0F, 1, 1, 3, 0.0F, false));
			Schneide.cubeList.add(new ModelBox(Schneide, 25, 9, 2.0F, -29.0F, -9.0F, 1, 1, 1, 0.0F, false));
			Schneide.cubeList.add(new ModelBox(Schneide, 23, 25, -2.0F, -29.0F, -9.0F, 1, 1, 1, 0.0F, false));
			Schneide.cubeList.add(new ModelBox(Schneide, 25, 13, 3.0F, -24.0F, -9.0F, 1, 1, 1, 0.0F, false));
			Schneide.cubeList.add(new ModelBox(Schneide, 23, 23, -3.0F, -24.0F, -9.0F, 1, 1, 1, 0.0F, false));
			Schneide.cubeList.add(new ModelBox(Schneide, 25, 16, 3.0F, -27.0F, -9.0F, 1, 1, 1, 0.0F, false));
			Schneide.cubeList.add(new ModelBox(Schneide, 25, 16, 4.0F, -28.0F, -9.0F, 1, 1, 1, 0.0F, false));
			Schneide.cubeList.add(new ModelBox(Schneide, 24, 0, -3.0F, -27.0F, -9.0F, 1, 1, 1, 0.0F, false));
			Schneide.cubeList.add(new ModelBox(Schneide, 25, 7, 3.0F, -21.0F, -9.0F, 1, 1, 1, 0.0F, false));
			Schneide.cubeList.add(new ModelBox(Schneide, 19, 13, -3.0F, -21.0F, -9.0F, 1, 1, 1, 0.0F, false));
			bb_main = new RendererModel(this);
			bb_main.setRotationPoint(0.0F, 24.0F, 0.0F);
			bb_main.cubeList.add(new ModelBox(bb_main, 17, 2, -4.0F, -25.0F, -9.0F, 1, 1, 1, 0.0F, false));
			bb_main.cubeList.add(new ModelBox(bb_main, 17, 0, -5.0F, -29.0F, -9.0F, 1, 1, 1, 0.0F, false));
			bb_main.cubeList.add(new ModelBox(bb_main, 16, 12, -4.0F, -28.0F, -9.0F, 1, 1, 1, 0.0F, false));
			bb_main.cubeList.add(new ModelBox(bb_main, 11, 3, 4.0F, -25.0F, -9.0F, 1, 1, 1, 0.0F, false));
			bb_main.cubeList.add(new ModelBox(bb_main, 8, 4, 5.0F, -29.0F, -9.0F, 1, 1, 1, 0.0F, false));
		}

		@Override
		public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
			Stiel.render(f5);
			Knauf.render(f5);
			Schneide.render(f5);
			bb_main.render(f5);
		}

		public void setRotationAngle(RendererModel modelRenderer, float x, float y, float z) {
			modelRenderer.rotateAngleX = x;
			modelRenderer.rotateAngleY = y;
			modelRenderer.rotateAngleZ = z;
		}

		public void setRotationAngles(Entity e, float f, float f1, float f2, float f3, float f4, float f5) {
			super.setRotationAngles(e, f, f1, f2, f3, f4, f5);
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
		double x = entity.posX;
		double y = entity.posY;
		double z = entity.posZ;
		world.playSound((PlayerEntity) null, (double) x, (double) y, (double) z,
				(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.arrow.shoot")),
				SoundCategory.PLAYERS, 1, 1f / (random.nextFloat() * 0.5f + 1) + (power / 2));
		return entityarrow;
	}

	public static ArrowCustomEntity shoot(LivingEntity entity, LivingEntity target) {
		ArrowCustomEntity entityarrow = new ArrowCustomEntity(arrow, entity, entity.world);
		double d0 = target.posY + (double) target.getEyeHeight() - 1.1;
		double d1 = target.posX - entity.posX;
		double d3 = target.posZ - entity.posZ;
		entityarrow.shoot(d1, d0 - entityarrow.posY + (double) MathHelper.sqrt(d1 * d1 + d3 * d3) * 0.2F, d3, 1f * 2, 12.0F);
		entityarrow.setSilent(true);
		entityarrow.setDamage(5);
		entityarrow.setKnockbackStrength(5);
		entityarrow.setIsCritical(false);
		entity.world.addEntity(entityarrow);
		double x = entity.posX;
		double y = entity.posY;
		double z = entity.posZ;
		entity.world.playSound((PlayerEntity) null, (double) x, (double) y, (double) z,
				(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.arrow.shoot")),
				SoundCategory.PLAYERS, 1, 1f / (new Random().nextFloat() * 0.5f + 1));
		return entityarrow;
	}
}
