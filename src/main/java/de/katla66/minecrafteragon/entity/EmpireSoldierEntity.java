
package de.katla66.minecrafteragon.entity;

import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.monster.ZombieEntity;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.fml.network.NetworkHooks;
import net.minecraftforge.fml.network.FMLPlayMessages;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.DeferredWorkQueue;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.World;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Hand;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ActionResultType;
import net.minecraft.network.IPacket;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.item.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.entity.ai.attributes.GlobalEntityTypeAttributes;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.Entity;
import net.minecraft.entity.CreatureAttribute;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.entity.layers.BipedArmorLayer;
import net.minecraft.client.renderer.entity.BipedRenderer;

import java.util.Map;
import java.util.HashMap;

import de.katla66.minecrafteragon.procedures.EmpireSoldierRightClickedOnEntityProcedure;
import de.katla66.minecrafteragon.itemgroup.EragonItemGroup;
import de.katla66.minecrafteragon.MinecraftEragonFraktionsModElements;

@MinecraftEragonFraktionsModElements.ModElement.Tag
public class EmpireSoldierEntity extends MinecraftEragonFraktionsModElements.ModElement {
	public static EntityType entity = null;
	public EmpireSoldierEntity(MinecraftEragonFraktionsModElements instance) {
		super(instance, 37);
		FMLJavaModLoadingContext.get().getModEventBus().register(new ModelRegisterHandler());
	}

	@Override
	public void initElements() {
		entity = (EntityType.Builder.<CustomEntity>create(CustomEntity::new, EntityClassification.MONSTER).setShouldReceiveVelocityUpdates(true)
				.setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(CustomEntity::new).size(0.6f, 1.8f)).build("empire_soldier")
						.setRegistryName("empire_soldier");
		elements.entities.add(() -> entity);
		elements.items.add(() -> new SpawnEggItem(entity, -65536, -16777216, new Item.Properties().group(EragonItemGroup.tab))
				.setRegistryName("empire_soldier_spawn_egg"));
	}

	@Override
	public void init(FMLCommonSetupEvent event) {
		DeferredWorkQueue.runLater(this::setupAttributes);
	}
	private static class ModelRegisterHandler {
		@SubscribeEvent
		@OnlyIn(Dist.CLIENT)
		public void registerModels(ModelRegistryEvent event) {
			RenderingRegistry.registerEntityRenderingHandler(entity, renderManager -> {
				BipedRenderer customRender = new BipedRenderer(renderManager, new BipedModel(0), 0.5f) {
					@Override
					public ResourceLocation getEntityTexture(Entity entity) {
						return new ResourceLocation("minecraft_eragon_fraktions:textures/axe.png");
					}
				};
				customRender.addLayer(new BipedArmorLayer(customRender, new BipedModel(0.5f), new BipedModel(1)));
				return customRender;
			});
		}
	}
	private void setupAttributes() {
		AttributeModifierMap.MutableAttribute ammma = MobEntity.func_233666_p_();
		ammma = ammma.createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.3);
		ammma = ammma.createMutableAttribute(Attributes.MAX_HEALTH, 20);
		ammma = ammma.createMutableAttribute(Attributes.ARMOR, 0);
		ammma = ammma.createMutableAttribute(Attributes.ATTACK_DAMAGE, 0);
		ammma = ammma.createMutableAttribute(Attributes.ATTACK_KNOCKBACK, 0.1);
		GlobalEntityTypeAttributes.put(entity, ammma.create());
	}
	public static class CustomEntity extends WolfEntity {
		public CustomEntity(FMLPlayMessages.SpawnEntity packet, World world) {
			this(entity, world);
		}

		public CustomEntity(EntityType<CustomEntity> type, World world) {
			super(type, world);
			experienceValue = 0;
			setNoAI(false);
			setCustomName(new StringTextComponent("Empiresoldier"));
			setCustomNameVisible(true);
			enablePersistence();
			this.setItemStackToSlot(EquipmentSlotType.MAINHAND, new ItemStack(Items.IRON_SWORD, (int) (1)));
			this.setItemStackToSlot(EquipmentSlotType.HEAD, new ItemStack(Items.IRON_HELMET, (int) (1)));
			this.setItemStackToSlot(EquipmentSlotType.CHEST, new ItemStack(Items.CHAINMAIL_CHESTPLATE, (int) (1)));
			this.setItemStackToSlot(EquipmentSlotType.LEGS, new ItemStack(Items.LEATHER_LEGGINGS, (int) (1)));
			this.setItemStackToSlot(EquipmentSlotType.FEET, new ItemStack(Items.IRON_BOOTS, (int) (1)));
		}

		@Override
		public IPacket<?> createSpawnPacket() {
			return NetworkHooks.getEntitySpawningPacket(this);
		}

		@Override
		protected void registerGoals() {
			super.registerGoals();
			this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.2, true));
			this.targetSelector.addGoal(2, new HurtByTargetGoal(this).setCallsForHelp(this.getClass()));
			this.goalSelector.addGoal(1, new RandomWalkingGoal(this, 1));
			this.targetSelector.addGoal(2, new HurtByTargetGoal(this));
			this.targetSelector.addGoal(5, new NearestAttackableTargetGoal(this, ZombieEntity.class, true, false));
			this.targetSelector.addGoal(5, new NearestAttackableTargetGoal(this, MonsterEntity.class, true, false));
			this.goalSelector.addGoal(10, new FollowOwnerGoal(this, 1.0D, 10.0F, 2.0F, false));
			this.goalSelector.addGoal(2, new SwimGoal(this));
		}

		@Override
		public CreatureAttribute getCreatureAttribute() {
			return CreatureAttribute.UNDEFINED;
		}

		@Override
		public boolean canDespawn(double distanceToClosestPlayer) {
			return false;
		}

		@Override
		public net.minecraft.util.SoundEvent getHurtSound(DamageSource ds) {
			return (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.generic.hurt"));
		}

		@Override
		public net.minecraft.util.SoundEvent getDeathSound() {
			return (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.generic.death"));
		}

		@Override
		public ActionResultType func_230254_b_(PlayerEntity sourceentity, Hand hand) {
			ItemStack itemstack = sourceentity.getHeldItem(hand);
			ActionResultType retval = ActionResultType.func_233537_a_(this.world.isRemote());
			super.func_230254_b_(sourceentity, hand);
			double x = this.getPosX();
			double y = this.getPosY();
			double z = this.getPosZ();
			Entity entity = this;
			{
				Map<String, Object> $_dependencies = new HashMap<>();
				$_dependencies.put("entity", entity);
				EmpireSoldierRightClickedOnEntityProcedure.executeProcedure($_dependencies);
			}
			return retval;
		}
	}
}
