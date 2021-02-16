
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

import de.offermann.minecrafteragonpersonallib.itemgroup.EragonItemGroup;
import de.offermann.minecrafteragonpersonallib.MinecraftEragonPersonallibModElements;

import com.mojang.blaze3d.platform.GlStateManager;

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
		RenderingRegistry.registerEntityRenderingHandler(ArrowCustomEntity.class, renderManager -> new CustomRender(renderManager));
	}
	public static class ItemRanged extends Item {
		public ItemRanged() {
			super(new Item.Properties().group(EragonItemGroup.tab).maxDamage(1));
			setRegistryName("dwarfs_throwable_axe");
		}

		@Override
		public UseAction getUseAction(ItemStack itemstack) {
			return UseAction.BOW;
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
			double x = this.posX;
			double y = this.posY;
			double z = this.posZ;
			World world = this.world;
			Entity entity = this.getShooter();
			if (this.inGround) {
				this.remove();
			}
		}
	}

	public static class CustomRender extends EntityRenderer<ArrowCustomEntity> {
		private static final ResourceLocation texture = new ResourceLocation("minecraft_eragon__personallib:textures/axedieechte.png");
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
			EntityModel model = new Modelcustom_model();
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
	public static class Modelcustom_model extends EntityModel {
		private final RendererModel bb_main;
		private final RendererModel cube_r1;
		private final RendererModel cube_r2;
		private final RendererModel cube_r3;
		private final RendererModel cube_r4;
		private final RendererModel cube_r5;
		private final RendererModel cube_r6;
		private final RendererModel cube_r7;
		private final RendererModel cube_r8;
		private final RendererModel cube_r9;
		private final RendererModel cube_r10;
		private final RendererModel cube_r11;
		private final RendererModel cube_r12;
		private final RendererModel cube_r13;
		private final RendererModel cube_r14;
		private final RendererModel cube_r15;
		public Modelcustom_model() {
			textureWidth = 64;
			textureHeight = 64;
			bb_main = new RendererModel(this);
			bb_main.setRotationPoint(0.0F, 24.0F, 0.0F);
			cube_r1 = new RendererModel(this);
			cube_r1.setRotationPoint(2.0893F, -27.3482F, -7.4821F);
			bb_main.addChild(cube_r1);
			setRotationAngle(cube_r1, 0.0F, 1.5708F, 0.0F);
			cube_r1.cubeList.add(new ModelBox(cube_r1, 3, 2, 0.9107F, 0.3482F, -3.5179F, 1, 1, 1, 0.0F, false));
			cube_r2 = new RendererModel(this);
			cube_r2.setRotationPoint(3.0893F, -26.3482F, -7.4821F);
			bb_main.addChild(cube_r2);
			setRotationAngle(cube_r2, 0.0F, 1.5708F, 0.0F);
			cube_r2.cubeList.add(new ModelBox(cube_r2, 16, 0, -0.0893F, 1.3482F, -3.5179F, 1, 1, 1, 0.0F, false));
			cube_r2.cubeList.add(new ModelBox(cube_r2, 16, 0, -0.0893F, 4.3482F, -3.5179F, 1, 1, 1, 0.0F, false));
			cube_r2.cubeList.add(new ModelBox(cube_r2, 16, 0, -0.0893F, 3.3482F, -3.5179F, 1, 1, 1, 0.0F, false));
			cube_r2.cubeList.add(new ModelBox(cube_r2, 16, 0, -0.0893F, 2.3482F, -3.5179F, 1, 1, 1, 0.0F, false));
			cube_r3 = new RendererModel(this);
			cube_r3.setRotationPoint(1.0893F, -26.3482F, -7.4821F);
			bb_main.addChild(cube_r3);
			setRotationAngle(cube_r3, 0.0F, 1.5708F, 0.0F);
			cube_r3.cubeList.add(new ModelBox(cube_r3, 3, 6, 0.9107F, 4.3482F, -3.5179F, 1, 1, 1, 0.0F, false));
			cube_r3.cubeList.add(new ModelBox(cube_r3, 3, 6, 0.9107F, 1.3482F, -3.5179F, 1, 1, 1, 0.0F, false));
			cube_r3.cubeList.add(new ModelBox(cube_r3, 3, 6, 0.9107F, 2.3482F, -3.5179F, 1, 1, 1, 0.0F, false));
			cube_r3.cubeList.add(new ModelBox(cube_r3, 3, 6, 0.9107F, 3.3482F, -3.5179F, 1, 1, 1, 0.0F, false));
			cube_r4 = new RendererModel(this);
			cube_r4.setRotationPoint(1.0893F, -26.3482F, -6.4821F);
			bb_main.addChild(cube_r4);
			setRotationAngle(cube_r4, 0.0F, 1.5708F, 0.0F);
			cube_r4.cubeList.add(new ModelBox(cube_r4, 3, 6, 0.9107F, 4.3482F, -3.5179F, 1, 1, 1, 0.0F, false));
			cube_r4.cubeList.add(new ModelBox(cube_r4, 3, 6, 0.9107F, 3.3482F, -3.5179F, 1, 1, 1, 0.0F, false));
			cube_r4.cubeList.add(new ModelBox(cube_r4, 3, 6, 0.9107F, 1.3482F, -3.5179F, 1, 1, 1, 0.0F, false));
			cube_r4.cubeList.add(new ModelBox(cube_r4, 3, 6, 0.9107F, 2.3482F, -3.5179F, 1, 1, 1, 0.0F, false));
			cube_r5 = new RendererModel(this);
			cube_r5.setRotationPoint(1.0893F, -27.3482F, -5.4821F);
			bb_main.addChild(cube_r5);
			setRotationAngle(cube_r5, 0.0F, 1.5708F, 0.0F);
			cube_r5.cubeList.add(new ModelBox(cube_r5, 3, 6, 0.9107F, 4.3482F, -3.5179F, 1, 1, 1, 0.0F, false));
			cube_r5.cubeList.add(new ModelBox(cube_r5, 3, 6, 0.9107F, 3.3482F, -3.5179F, 1, 1, 1, 0.0F, false));
			cube_r5.cubeList.add(new ModelBox(cube_r5, 3, 6, 0.9107F, 2.3482F, -3.5179F, 1, 1, 1, 0.0F, false));
			cube_r6 = new RendererModel(this);
			cube_r6.setRotationPoint(2.0893F, -27.3482F, -8.4821F);
			bb_main.addChild(cube_r6);
			setRotationAngle(cube_r6, 0.0F, 1.5708F, 0.0F);
			cube_r6.cubeList.add(new ModelBox(cube_r6, 6, 3, 0.9107F, 5.3482F, -3.5179F, 1, 1, 1, 0.0F, false));
			cube_r6.cubeList.add(new ModelBox(cube_r6, 3, 8, -3.0893F, 3.3482F, -3.5179F, 1, 1, 1, 0.0F, false));
			cube_r6.cubeList.add(new ModelBox(cube_r6, 3, 8, -3.0893F, 4.3482F, -3.5179F, 1, 1, 1, 0.0F, false));
			cube_r6.cubeList.add(new ModelBox(cube_r6, 0, 11, -4.0893F, 3.3482F, -3.5179F, 1, 1, 1, 0.0F, false));
			cube_r6.cubeList.add(new ModelBox(cube_r6, 0, 3, -1.0893F, 1.3482F, -2.5179F, 1, 1, 1, 0.0F, false));
			cube_r6.cubeList.add(new ModelBox(cube_r6, 3, 2, 0.9107F, 0.3482F, -3.5179F, 1, 1, 1, 0.0F, false));
			cube_r6.cubeList.add(new ModelBox(cube_r6, 3, 4, 1.9107F, 0.3482F, -3.5179F, 1, 1, 1, 0.0F, false));
			cube_r6.cubeList.add(new ModelBox(cube_r6, 4, 0, 2.9107F, 1.3482F, -3.5179F, 1, 1, 1, 0.0F, false));
			cube_r6.cubeList.add(new ModelBox(cube_r6, 0, 5, 2.9107F, 2.3482F, -3.5179F, 1, 1, 1, 0.0F, false));
			cube_r6.cubeList.add(new ModelBox(cube_r6, 0, 0, 1.9107F, 3.3482F, -3.5179F, 1, 2, 1, 0.0F, false));
			cube_r6.cubeList.add(new ModelBox(cube_r6, 3, 6, 0.9107F, 4.3482F, -3.5179F, 1, 1, 1, 0.0F, false));
			cube_r6.cubeList.add(new ModelBox(cube_r6, 6, 3, 0.9107F, 5.3482F, -3.5179F, 1, 1, 1, 0.0F, false));
			cube_r6.cubeList.add(new ModelBox(cube_r6, 6, 5, 0.9107F, 6.3482F, -3.5179F, 1, 1, 1, 0.0F, false));
			cube_r6.cubeList.add(new ModelBox(cube_r6, 0, 7, -0.0893F, 7.3482F, -3.5179F, 1, 1, 1, 0.0F, false));
			cube_r6.cubeList.add(new ModelBox(cube_r6, 6, 7, -1.0893F, 7.3482F, -3.5179F, 1, 1, 1, 0.0F, false));
			cube_r6.cubeList.add(new ModelBox(cube_r6, 7, 1, -2.0893F, 6.3482F, -3.5179F, 1, 1, 1, 0.0F, false));
			cube_r6.cubeList.add(new ModelBox(cube_r6, 0, 9, -8.0893F, -5.6518F, -4.5179F, 1, 1, 1, 0.0F, false));
			cube_r6.cubeList.add(new ModelBox(cube_r6, 6, 9, -7.0893F, -4.6518F, -4.5179F, 1, 1, 1, 0.0F, false));
			cube_r6.cubeList.add(new ModelBox(cube_r6, 9, 4, -6.0893F, -3.6518F, -4.5179F, 1, 1, 1, 0.0F, false));
			cube_r6.cubeList.add(new ModelBox(cube_r6, 9, 8, -5.0893F, -2.6518F, -4.5179F, 1, 1, 1, 0.0F, false));
			cube_r6.cubeList.add(new ModelBox(cube_r6, 3, 10, -4.0893F, -1.6518F, -4.5179F, 1, 1, 1, 0.0F, false));
			cube_r6.cubeList.add(new ModelBox(cube_r6, 9, 10, -8.0893F, -5.6518F, -2.5179F, 1, 1, 1, 0.0F, false));
			cube_r6.cubeList.add(new ModelBox(cube_r6, 10, 0, -7.0893F, -4.6518F, -2.5179F, 1, 1, 1, 0.0F, false));
			cube_r6.cubeList.add(new ModelBox(cube_r6, 10, 2, -6.0893F, -3.6518F, -2.5179F, 1, 1, 1, 0.0F, false));
			cube_r6.cubeList.add(new ModelBox(cube_r6, 0, 11, -4.0893F, 3.3482F, -3.5179F, 1, 1, 1, 0.0F, false));
			cube_r6.cubeList.add(new ModelBox(cube_r6, 6, 11, -5.0893F, -2.6518F, -2.5179F, 1, 1, 1, 0.0F, false));
			cube_r6.cubeList.add(new ModelBox(cube_r6, 3, 12, -4.0893F, -1.6518F, -2.5179F, 1, 1, 1, 0.0F, false));
			cube_r6.cubeList.add(new ModelBox(cube_r6, 9, 12, -3.0893F, -0.6518F, -2.5179F, 1, 1, 1, 0.0F, false));
			cube_r6.cubeList.add(new ModelBox(cube_r6, 12, 5, -3.0893F, -0.6518F, -4.5179F, 1, 1, 1, 0.0F, false));
			cube_r6.cubeList.add(new ModelBox(cube_r6, 12, 7, -2.0893F, 0.3482F, -4.5179F, 1, 1, 1, 0.0F, false));
			cube_r6.cubeList.add(new ModelBox(cube_r6, 12, 9, -2.0893F, 0.3482F, -2.5179F, 1, 1, 1, 0.0F, false));
			cube_r6.cubeList.add(new ModelBox(cube_r6, 12, 11, -2.0893F, 0.3482F, -3.5179F, 1, 1, 1, 0.0F, false));
			cube_r6.cubeList.add(new ModelBox(cube_r6, 0, 13, -3.0893F, 2.3482F, -3.5179F, 1, 1, 1, 0.0F, false));
			cube_r6.cubeList.add(new ModelBox(cube_r6, 6, 13, -3.0893F, -0.6518F, -3.5179F, 1, 1, 1, 0.0F, false));
			cube_r6.cubeList.add(new ModelBox(cube_r6, 12, 13, -1.0893F, 2.3482F, -3.5179F, 1, 1, 1, 0.0F, false));
			cube_r6.cubeList.add(new ModelBox(cube_r6, 13, 1, -4.0893F, -1.6518F, -3.5179F, 1, 1, 1, 0.0F, false));
			cube_r6.cubeList.add(new ModelBox(cube_r6, 13, 3, -5.0893F, -2.6518F, -3.5179F, 1, 1, 1, 0.0F, false));
			cube_r6.cubeList.add(new ModelBox(cube_r6, 3, 14, -2.0893F, 1.3482F, -3.5179F, 1, 1, 1, 0.0F, false));
			cube_r6.cubeList.add(new ModelBox(cube_r6, 9, 14, -6.0893F, -3.6518F, -3.5179F, 1, 1, 1, 0.0F, false));
			cube_r6.cubeList.add(new ModelBox(cube_r6, 0, 15, -2.0893F, 1.3482F, -3.5179F, 1, 1, 1, 0.0F, false));
			cube_r6.cubeList.add(new ModelBox(cube_r6, 6, 15, -3.0893F, 0.3482F, -3.5179F, 1, 1, 1, 0.0F, false));
			cube_r6.cubeList.add(new ModelBox(cube_r6, 12, 15, -7.0893F, -4.6518F, -3.5179F, 1, 1, 1, 0.0F, false));
			cube_r6.cubeList.add(new ModelBox(cube_r6, 15, 6, -4.0893F, -0.6518F, -3.5179F, 1, 1, 1, 0.0F, false));
			cube_r6.cubeList.add(new ModelBox(cube_r6, 15, 8, -5.0893F, -1.6518F, -3.5179F, 1, 1, 1, 0.0F, false));
			cube_r6.cubeList.add(new ModelBox(cube_r6, 15, 10, -6.0893F, -2.6518F, -3.5179F, 1, 1, 1, 0.0F, false));
			cube_r6.cubeList.add(new ModelBox(cube_r6, 15, 12, -8.0893F, -4.6518F, -3.5179F, 1, 1, 1, 0.0F, false));
			cube_r6.cubeList.add(new ModelBox(cube_r6, 15, 14, -7.0893F, -3.6518F, -3.5179F, 1, 1, 1, 0.0F, false));
			cube_r6.cubeList.add(new ModelBox(cube_r6, 3, 16, -8.0893F, -4.6518F, -3.5179F, 1, 1, 1, 0.0F, false));
			cube_r6.cubeList.add(new ModelBox(cube_r6, 9, 16, -8.0893F, -4.6518F, -3.5179F, 1, 1, 1, 0.0F, false));
			cube_r6.cubeList.add(new ModelBox(cube_r6, 15, 16, -8.0893F, -5.6518F, -3.5179F, 1, 1, 1, 0.0F, false));
			cube_r6.cubeList.add(new ModelBox(cube_r6, 16, 0, -0.0893F, 1.3482F, -3.5179F, 1, 1, 1, 0.0F, false));
			cube_r6.cubeList.add(new ModelBox(cube_r6, 16, 2, -1.0893F, 0.3482F, -3.5179F, 1, 1, 1, 0.0F, false));
			cube_r6.cubeList.add(new ModelBox(cube_r6, 16, 4, -7.0893F, -5.6518F, -3.5179F, 1, 1, 1, 0.0F, false));
			cube_r6.cubeList.add(new ModelBox(cube_r6, 0, 17, -2.0893F, -0.6518F, -3.5179F, 1, 1, 1, 0.0F, false));
			cube_r6.cubeList.add(new ModelBox(cube_r6, 6, 17, -3.0893F, -1.6518F, -3.5179F, 1, 1, 1, 0.0F, false));
			cube_r6.cubeList.add(new ModelBox(cube_r6, 12, 17, -4.0893F, -2.6518F, -3.5179F, 1, 1, 1, 0.0F, false));
			cube_r6.cubeList.add(new ModelBox(cube_r6, 3, 18, -5.0893F, -3.6518F, -3.5179F, 1, 1, 1, 0.0F, false));
			cube_r6.cubeList.add(new ModelBox(cube_r6, 9, 18, -6.0893F, -4.6518F, -3.5179F, 1, 1, 1, 0.0F, false));
			cube_r7 = new RendererModel(this);
			cube_r7.setRotationPoint(1.0893F, -29.3482F, -8.4821F);
			bb_main.addChild(cube_r7);
			setRotationAngle(cube_r7, 0.0F, 1.5708F, 0.0F);
			cube_r7.cubeList.add(new ModelBox(cube_r7, 3, 6, 0.9107F, 4.3482F, -3.5179F, 1, 1, 1, 0.0F, false));
			cube_r7.cubeList.add(new ModelBox(cube_r7, 3, 6, 0.9107F, 3.3482F, -3.5179F, 1, 1, 1, 0.0F, false));
			cube_r7.cubeList.add(new ModelBox(cube_r7, 3, 6, 0.9107F, 5.3482F, -3.5179F, 1, 1, 1, 0.0F, false));
			cube_r7.cubeList.add(new ModelBox(cube_r7, 16, 2, -1.0893F, 3.3482F, -3.5179F, 1, 1, 1, 0.0F, false));
			cube_r8 = new RendererModel(this);
			cube_r8.setRotationPoint(1.0893F, -29.3482F, -9.4821F);
			bb_main.addChild(cube_r8);
			setRotationAngle(cube_r8, 0.0F, 1.5708F, 0.0F);
			cube_r8.cubeList.add(new ModelBox(cube_r8, 3, 6, 0.9107F, 4.3482F, -3.5179F, 1, 1, 1, 0.0F, false));
			cube_r8.cubeList.add(new ModelBox(cube_r8, 3, 6, 0.9107F, 3.3482F, -3.5179F, 1, 1, 1, 0.0F, false));
			cube_r9 = new RendererModel(this);
			cube_r9.setRotationPoint(3.0893F, -26.3482F, -6.4821F);
			bb_main.addChild(cube_r9);
			setRotationAngle(cube_r9, 0.0F, 1.5708F, 0.0F);
			cube_r9.cubeList.add(new ModelBox(cube_r9, 16, 0, -0.0893F, 1.3482F, -3.5179F, 1, 1, 1, 0.0F, false));
			cube_r9.cubeList.add(new ModelBox(cube_r9, 16, 0, -0.0893F, 2.3482F, -3.5179F, 1, 1, 1, 0.0F, false));
			cube_r9.cubeList.add(new ModelBox(cube_r9, 16, 0, -0.0893F, 3.3482F, -3.5179F, 1, 1, 1, 0.0F, false));
			cube_r10 = new RendererModel(this);
			cube_r10.setRotationPoint(3.0893F, -23.3482F, -8.4821F);
			bb_main.addChild(cube_r10);
			setRotationAngle(cube_r10, 0.0F, 1.5708F, 0.0F);
			cube_r10.cubeList.add(new ModelBox(cube_r10, 16, 0, -0.0893F, 1.3482F, -3.5179F, 1, 1, 1, 0.0F, false));
			cube_r10.cubeList.add(new ModelBox(cube_r10, 16, 0, -0.0893F, 0.3482F, -3.5179F, 1, 1, 1, 0.0F, false));
			cube_r10.cubeList.add(new ModelBox(cube_r10, 16, 0, -0.0893F, -0.6518F, -3.5179F, 1, 1, 1, 0.0F, false));
			cube_r10.cubeList.add(new ModelBox(cube_r10, 16, 0, -0.0893F, -1.6518F, -3.5179F, 1, 1, 1, 0.0F, false));
			cube_r11 = new RendererModel(this);
			cube_r11.setRotationPoint(3.0893F, -25.3482F, -9.4821F);
			bb_main.addChild(cube_r11);
			setRotationAngle(cube_r11, 0.0F, 1.5708F, 0.0F);
			cube_r11.cubeList.add(new ModelBox(cube_r11, 16, 0, -0.0893F, 1.3482F, -3.5179F, 1, 1, 1, 0.0F, false));
			cube_r11.cubeList.add(new ModelBox(cube_r11, 16, 0, -0.0893F, 0.3482F, -3.5179F, 1, 1, 1, 0.0F, false));
			cube_r11.cubeList.add(new ModelBox(cube_r11, 16, 0, -0.0893F, -0.6518F, -3.5179F, 1, 1, 1, 0.0F, false));
			cube_r12 = new RendererModel(this);
			cube_r12.setRotationPoint(3.0893F, -26.3482F, -10.4821F);
			bb_main.addChild(cube_r12);
			setRotationAngle(cube_r12, 0.0F, 1.5708F, 0.0F);
			cube_r12.cubeList.add(new ModelBox(cube_r12, 16, 0, -0.0893F, 1.3482F, -3.5179F, 1, 1, 1, 0.0F, false));
			cube_r12.cubeList.add(new ModelBox(cube_r12, 16, 0, -0.0893F, 0.3482F, -3.5179F, 1, 1, 1, 0.0F, false));
			cube_r13 = new RendererModel(this);
			cube_r13.setRotationPoint(2.0893F, -27.3482F, -9.4821F);
			bb_main.addChild(cube_r13);
			setRotationAngle(cube_r13, 0.0F, 1.5708F, 0.0F);
			cube_r13.cubeList.add(new ModelBox(cube_r13, 3, 8, -3.0893F, 5.3482F, -3.5179F, 1, 1, 1, 0.0F, false));
			cube_r13.cubeList.add(new ModelBox(cube_r13, 0, 11, -4.0893F, 5.3482F, -3.5179F, 1, 1, 1, 0.0F, false));
			cube_r14 = new RendererModel(this);
			cube_r14.setRotationPoint(2.0893F, -26.3482F, -10.4821F);
			bb_main.addChild(cube_r14);
			setRotationAngle(cube_r14, 0.0F, 1.5708F, 0.0F);
			cube_r14.cubeList.add(new ModelBox(cube_r14, 3, 8, -3.0893F, 5.3482F, -3.5179F, 1, 1, 1, 0.0F, false));
			cube_r15 = new RendererModel(this);
			cube_r15.setRotationPoint(2.0893F, -26.3482F, -11.4821F);
			bb_main.addChild(cube_r15);
			setRotationAngle(cube_r15, 0.0F, 1.5708F, 0.0F);
			cube_r15.cubeList.add(new ModelBox(cube_r15, 3, 8, -3.0893F, 5.3482F, -3.5179F, 1, 1, 1, 0.0F, false));
		}

		@Override
		public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
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
				(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.egg.throw")),
				SoundCategory.PLAYERS, 1, 1f / (random.nextFloat() * 0.5f + 1) + (power / 2));
		return entityarrow;
	}

	public static ArrowCustomEntity shoot(LivingEntity entity, LivingEntity target) {
		ArrowCustomEntity entityarrow = new ArrowCustomEntity(arrow, entity, entity.world);
		double d0 = target.posY + (double) target.getEyeHeight() - 1.1;
		double d1 = target.posX - entity.posX;
		double d3 = target.posZ - entity.posZ;
		entityarrow.shoot(d1, d0 - entityarrow.posY + (double) MathHelper.sqrt(d1 * d1 + d3 * d3) * 0.2F, d3, 1.4f * 2, 12.0F);
		entityarrow.setSilent(true);
		entityarrow.setDamage(8);
		entityarrow.setKnockbackStrength(2);
		entityarrow.setIsCritical(false);
		entity.world.addEntity(entityarrow);
		double x = entity.posX;
		double y = entity.posY;
		double z = entity.posZ;
		entity.world.playSound((PlayerEntity) null, (double) x, (double) y, (double) z,
				(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.egg.throw")),
				SoundCategory.PLAYERS, 1, 1f / (new Random().nextFloat() * 0.5f + 1));
		return entityarrow;
	}
}
