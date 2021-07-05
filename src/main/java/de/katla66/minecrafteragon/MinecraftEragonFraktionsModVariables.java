package de.katla66.minecrafteragon;

import net.minecraftforge.fml.network.PacketDistributor;
import net.minecraftforge.fml.network.NetworkEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.Capability;

import net.minecraft.world.storage.WorldSavedData;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.World;
import net.minecraft.world.IWorld;
import net.minecraft.world.IServerWorld;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Direction;
import net.minecraft.network.PacketBuffer;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.client.Minecraft;

import java.util.function.Supplier;

public class MinecraftEragonFraktionsModVariables {
	public MinecraftEragonFraktionsModVariables(MinecraftEragonFraktionsModElements elements) {
		elements.addNetworkMessage(WorldSavedDataSyncMessage.class, WorldSavedDataSyncMessage::buffer, WorldSavedDataSyncMessage::new,
				WorldSavedDataSyncMessage::handler);
		elements.addNetworkMessage(PlayerVariablesSyncMessage.class, PlayerVariablesSyncMessage::buffer, PlayerVariablesSyncMessage::new,
				PlayerVariablesSyncMessage::handler);
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::init);
	}

	private void init(FMLCommonSetupEvent event) {
		CapabilityManager.INSTANCE.register(PlayerVariables.class, new PlayerVariablesStorage(), PlayerVariables::new);
	}

	@SubscribeEvent
	public void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
		if (!event.getPlayer().world.isRemote()) {
			WorldSavedData mapdata = MapVariables.get(event.getPlayer().world);
			WorldSavedData worlddata = WorldVariables.get(event.getPlayer().world);
			if (mapdata != null)
				MinecraftEragonFraktionsMod.PACKET_HANDLER.send(PacketDistributor.PLAYER.with(() -> (ServerPlayerEntity) event.getPlayer()),
						new WorldSavedDataSyncMessage(0, mapdata));
			if (worlddata != null)
				MinecraftEragonFraktionsMod.PACKET_HANDLER.send(PacketDistributor.PLAYER.with(() -> (ServerPlayerEntity) event.getPlayer()),
						new WorldSavedDataSyncMessage(1, worlddata));
		}
	}

	@SubscribeEvent
	public void onPlayerChangedDimension(PlayerEvent.PlayerChangedDimensionEvent event) {
		if (!event.getPlayer().world.isRemote()) {
			WorldSavedData worlddata = WorldVariables.get(event.getPlayer().world);
			if (worlddata != null)
				MinecraftEragonFraktionsMod.PACKET_HANDLER.send(PacketDistributor.PLAYER.with(() -> (ServerPlayerEntity) event.getPlayer()),
						new WorldSavedDataSyncMessage(1, worlddata));
		}
	}
	public static class WorldVariables extends WorldSavedData {
		public static final String DATA_NAME = "minecraft_eragon_fraktions_worldvars";
		public double HerobrineDies = 0;
		public WorldVariables() {
			super(DATA_NAME);
		}

		public WorldVariables(String s) {
			super(s);
		}

		@Override
		public void read(CompoundNBT nbt) {
			HerobrineDies = nbt.getDouble("HerobrineDies");
		}

		@Override
		public CompoundNBT write(CompoundNBT nbt) {
			nbt.putDouble("HerobrineDies", HerobrineDies);
			return nbt;
		}

		public void syncData(IWorld world) {
			this.markDirty();
			if (world instanceof World && !world.isRemote())
				MinecraftEragonFraktionsMod.PACKET_HANDLER.send(PacketDistributor.DIMENSION.with(((World) world)::getDimensionKey),
						new WorldSavedDataSyncMessage(1, this));
		}
		static WorldVariables clientSide = new WorldVariables();
		public static WorldVariables get(IWorld world) {
			if (world instanceof ServerWorld) {
				return ((ServerWorld) world).getSavedData().getOrCreate(WorldVariables::new, DATA_NAME);
			} else {
				return clientSide;
			}
		}
	}

	public static class MapVariables extends WorldSavedData {
		public static final String DATA_NAME = "minecraft_eragon_fraktions_mapvars";
		public boolean setupconfig = true;
		public MapVariables() {
			super(DATA_NAME);
		}

		public MapVariables(String s) {
			super(s);
		}

		@Override
		public void read(CompoundNBT nbt) {
			setupconfig = nbt.getBoolean("setupconfig");
		}

		@Override
		public CompoundNBT write(CompoundNBT nbt) {
			nbt.putBoolean("setupconfig", setupconfig);
			return nbt;
		}

		public void syncData(IWorld world) {
			this.markDirty();
			if (world instanceof World && !world.isRemote())
				MinecraftEragonFraktionsMod.PACKET_HANDLER.send(PacketDistributor.ALL.noArg(), new WorldSavedDataSyncMessage(0, this));
		}
		static MapVariables clientSide = new MapVariables();
		public static MapVariables get(IWorld world) {
			if (world instanceof IServerWorld) {
				return ((IServerWorld) world).getWorld().getServer().getWorld(World.OVERWORLD).getSavedData().getOrCreate(MapVariables::new,
						DATA_NAME);
			} else {
				return clientSide;
			}
		}
	}

	public static class WorldSavedDataSyncMessage {
		public int type;
		public WorldSavedData data;
		public WorldSavedDataSyncMessage(PacketBuffer buffer) {
			this.type = buffer.readInt();
			this.data = this.type == 0 ? new MapVariables() : new WorldVariables();
			this.data.read(buffer.readCompoundTag());
		}

		public WorldSavedDataSyncMessage(int type, WorldSavedData data) {
			this.type = type;
			this.data = data;
		}

		public static void buffer(WorldSavedDataSyncMessage message, PacketBuffer buffer) {
			buffer.writeInt(message.type);
			buffer.writeCompoundTag(message.data.write(new CompoundNBT()));
		}

		public static void handler(WorldSavedDataSyncMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
			NetworkEvent.Context context = contextSupplier.get();
			context.enqueueWork(() -> {
				if (!context.getDirection().getReceptionSide().isServer()) {
					if (message.type == 0)
						MapVariables.clientSide = (MapVariables) message.data;
					else
						WorldVariables.clientSide = (WorldVariables) message.data;
				}
			});
			context.setPacketHandled(true);
		}
	}
	@CapabilityInject(PlayerVariables.class)
	public static Capability<PlayerVariables> PLAYER_VARIABLES_CAPABILITY = null;
	@SubscribeEvent
	public void onAttachCapabilities(AttachCapabilitiesEvent<Entity> event) {
		if (event.getObject() instanceof PlayerEntity && !(event.getObject() instanceof FakePlayer))
			event.addCapability(new ResourceLocation("minecraft_eragon_fraktions", "player_variables"), new PlayerVariablesProvider());
	}
	private static class PlayerVariablesProvider implements ICapabilitySerializable<INBT> {
		private final LazyOptional<PlayerVariables> instance = LazyOptional.of(PLAYER_VARIABLES_CAPABILITY::getDefaultInstance);
		@Override
		public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
			return cap == PLAYER_VARIABLES_CAPABILITY ? instance.cast() : LazyOptional.empty();
		}

		@Override
		public INBT serializeNBT() {
			return PLAYER_VARIABLES_CAPABILITY.getStorage().writeNBT(PLAYER_VARIABLES_CAPABILITY, this.instance.orElseThrow(RuntimeException::new),
					null);
		}

		@Override
		public void deserializeNBT(INBT nbt) {
			PLAYER_VARIABLES_CAPABILITY.getStorage().readNBT(PLAYER_VARIABLES_CAPABILITY, this.instance.orElseThrow(RuntimeException::new), null,
					nbt);
		}
	}

	private static class PlayerVariablesStorage implements Capability.IStorage<PlayerVariables> {
		@Override
		public INBT writeNBT(Capability<PlayerVariables> capability, PlayerVariables instance, Direction side) {
			CompoundNBT nbt = new CompoundNBT();
			nbt.putDouble("varden", instance.varden);
			nbt.putDouble("zwergen", instance.zwergen);
			nbt.putDouble("elfen", instance.elfen);
			nbt.putDouble("surda", instance.surda);
			nbt.putDouble("empireSoldier", instance.empireSoldier);
			nbt.putDouble("shade", instance.shade);
			nbt.putDouble("razac", instance.razac);
			nbt.putDouble("urgal", instance.urgal);
			nbt.putDouble("elvenenteredforest", instance.elvenenteredforest);
			nbt.putDouble("posShadeOverworldX", instance.posShadeOverworldX);
			nbt.putDouble("posShadeOverworldY", instance.posShadeOverworldY);
			nbt.putDouble("posShadeOverworldZ", instance.posShadeOverworldZ);
			nbt.putString("posShadeDimesionID", instance.posShadeDimesionID);
			nbt.putDouble("clicked", instance.clicked);
			nbt.putBoolean("razacBadomen", instance.razacBadomen);
			nbt.putBoolean("CEM", instance.CEM);
			return nbt;
		}

		@Override
		public void readNBT(Capability<PlayerVariables> capability, PlayerVariables instance, Direction side, INBT inbt) {
			CompoundNBT nbt = (CompoundNBT) inbt;
			instance.varden = nbt.getDouble("varden");
			instance.zwergen = nbt.getDouble("zwergen");
			instance.elfen = nbt.getDouble("elfen");
			instance.surda = nbt.getDouble("surda");
			instance.empireSoldier = nbt.getDouble("empireSoldier");
			instance.shade = nbt.getDouble("shade");
			instance.razac = nbt.getDouble("razac");
			instance.urgal = nbt.getDouble("urgal");
			instance.elvenenteredforest = nbt.getDouble("elvenenteredforest");
			instance.posShadeOverworldX = nbt.getDouble("posShadeOverworldX");
			instance.posShadeOverworldY = nbt.getDouble("posShadeOverworldY");
			instance.posShadeOverworldZ = nbt.getDouble("posShadeOverworldZ");
			instance.posShadeDimesionID = nbt.getString("posShadeDimesionID");
			instance.clicked = nbt.getDouble("clicked");
			instance.razacBadomen = nbt.getBoolean("razacBadomen");
			instance.CEM = nbt.getBoolean("CEM");
		}
	}

	public static class PlayerVariables {
		public double varden = 0;
		public double zwergen = 0;
		public double elfen = 0;
		public double surda = 0;
		public double empireSoldier = 0;
		public double shade = 0;
		public double razac = 0;
		public double urgal = 0;
		public double elvenenteredforest = 0;
		public double posShadeOverworldX = 0;
		public double posShadeOverworldY = 0;
		public double posShadeOverworldZ = 0;
		public String posShadeDimesionID = "";
		public double clicked = 0;
		public boolean razacBadomen = true;
		public boolean CEM = false;
		public void syncPlayerVariables(Entity entity) {
			if (entity instanceof ServerPlayerEntity)
				MinecraftEragonFraktionsMod.PACKET_HANDLER.send(PacketDistributor.PLAYER.with(() -> (ServerPlayerEntity) entity),
						new PlayerVariablesSyncMessage(this));
		}
	}
	@SubscribeEvent
	public void onPlayerLoggedInSyncPlayerVariables(PlayerEvent.PlayerLoggedInEvent event) {
		if (!event.getPlayer().world.isRemote())
			((PlayerVariables) event.getPlayer().getCapability(PLAYER_VARIABLES_CAPABILITY, null).orElse(new PlayerVariables()))
					.syncPlayerVariables(event.getPlayer());
	}

	@SubscribeEvent
	public void onPlayerRespawnedSyncPlayerVariables(PlayerEvent.PlayerRespawnEvent event) {
		if (!event.getPlayer().world.isRemote())
			((PlayerVariables) event.getPlayer().getCapability(PLAYER_VARIABLES_CAPABILITY, null).orElse(new PlayerVariables()))
					.syncPlayerVariables(event.getPlayer());
	}

	@SubscribeEvent
	public void onPlayerChangedDimensionSyncPlayerVariables(PlayerEvent.PlayerChangedDimensionEvent event) {
		if (!event.getPlayer().world.isRemote())
			((PlayerVariables) event.getPlayer().getCapability(PLAYER_VARIABLES_CAPABILITY, null).orElse(new PlayerVariables()))
					.syncPlayerVariables(event.getPlayer());
	}

	@SubscribeEvent
	public void clonePlayer(PlayerEvent.Clone event) {
		PlayerVariables original = ((PlayerVariables) event.getOriginal().getCapability(PLAYER_VARIABLES_CAPABILITY, null)
				.orElse(new PlayerVariables()));
		PlayerVariables clone = ((PlayerVariables) event.getEntity().getCapability(PLAYER_VARIABLES_CAPABILITY, null).orElse(new PlayerVariables()));
		clone.CEM = original.CEM;
		if (!event.isWasDeath()) {
			clone.varden = original.varden;
			clone.zwergen = original.zwergen;
			clone.elfen = original.elfen;
			clone.surda = original.surda;
			clone.empireSoldier = original.empireSoldier;
			clone.shade = original.shade;
			clone.razac = original.razac;
			clone.urgal = original.urgal;
			clone.elvenenteredforest = original.elvenenteredforest;
			clone.posShadeOverworldX = original.posShadeOverworldX;
			clone.posShadeOverworldY = original.posShadeOverworldY;
			clone.posShadeOverworldZ = original.posShadeOverworldZ;
			clone.posShadeDimesionID = original.posShadeDimesionID;
			clone.clicked = original.clicked;
			clone.razacBadomen = original.razacBadomen;
		}
	}
	public static class PlayerVariablesSyncMessage {
		public PlayerVariables data;
		public PlayerVariablesSyncMessage(PacketBuffer buffer) {
			this.data = new PlayerVariables();
			new PlayerVariablesStorage().readNBT(null, this.data, null, buffer.readCompoundTag());
		}

		public PlayerVariablesSyncMessage(PlayerVariables data) {
			this.data = data;
		}

		public static void buffer(PlayerVariablesSyncMessage message, PacketBuffer buffer) {
			buffer.writeCompoundTag((CompoundNBT) new PlayerVariablesStorage().writeNBT(null, message.data, null));
		}

		public static void handler(PlayerVariablesSyncMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
			NetworkEvent.Context context = contextSupplier.get();
			context.enqueueWork(() -> {
				if (!context.getDirection().getReceptionSide().isServer()) {
					PlayerVariables variables = ((PlayerVariables) Minecraft.getInstance().player.getCapability(PLAYER_VARIABLES_CAPABILITY, null)
							.orElse(new PlayerVariables()));
					variables.varden = message.data.varden;
					variables.zwergen = message.data.zwergen;
					variables.elfen = message.data.elfen;
					variables.surda = message.data.surda;
					variables.empireSoldier = message.data.empireSoldier;
					variables.shade = message.data.shade;
					variables.razac = message.data.razac;
					variables.urgal = message.data.urgal;
					variables.elvenenteredforest = message.data.elvenenteredforest;
					variables.posShadeOverworldX = message.data.posShadeOverworldX;
					variables.posShadeOverworldY = message.data.posShadeOverworldY;
					variables.posShadeOverworldZ = message.data.posShadeOverworldZ;
					variables.posShadeDimesionID = message.data.posShadeDimesionID;
					variables.clicked = message.data.clicked;
					variables.razacBadomen = message.data.razacBadomen;
					variables.CEM = message.data.CEM;
				}
			});
			context.setPacketHandled(true);
		}
	}
}
