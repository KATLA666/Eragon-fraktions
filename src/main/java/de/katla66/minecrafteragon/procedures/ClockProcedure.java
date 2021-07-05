package de.katla66.minecrafteragon.procedures;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.common.MinecraftForge;

import net.minecraft.world.World;
import net.minecraft.entity.Entity;

import java.util.Map;
import java.util.HashMap;

import java.io.IOException;
import java.io.FileReader;
import java.io.File;
import java.io.BufferedReader;

import de.katla66.minecrafteragon.MinecraftEragonFraktionsModElements;
import de.katla66.minecrafteragon.MinecraftEragonFraktionsMod;

import com.google.gson.JsonObject;
import com.google.gson.Gson;

@MinecraftEragonFraktionsModElements.ModElement.Tag
public class ClockProcedure extends MinecraftEragonFraktionsModElements.ModElement {
	public ClockProcedure(MinecraftEragonFraktionsModElements instance) {
		super(instance, 20);
		MinecraftForge.EVENT_BUS.register(this);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				MinecraftEragonFraktionsMod.LOGGER.warn("Failed to load dependency entity for procedure Clock!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		double TimerLong = 0;
		double TimerShort = 0;
		File eragonConfig = new File("config", File.separator + "eragon factions config.json");
		{
			try {
				BufferedReader eragonConfigReader = new BufferedReader(new FileReader(eragonConfig));
				StringBuilder jsonstringbuilder = new StringBuilder();
				String line;
				while ((line = eragonConfigReader.readLine()) != null) {
					jsonstringbuilder.append(line);
				}
				eragonConfigReader.close();
				JsonObject gson = new Gson().fromJson(jsonstringbuilder.toString(), JsonObject.class);
				TimerLong = (double) gson.get("Decay time long in ticks (standard: 6000)").getAsDouble();
				TimerShort = (double) gson.get("Decay time short in ticks (standard: 3000)").getAsDouble();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		entity.getPersistentData().putDouble("clock", ((entity.getPersistentData().getDouble("clock")) + 1));
		if (((entity.getPersistentData().getDouble("clock")) >= (TimerShort))) {
			entity.getPersistentData().putDouble("clockready", 1);
		}
		if (((entity.getPersistentData().getDouble("clock")) >= (TimerLong))) {
			entity.getPersistentData().putDouble("clockreadystrong", 1);
			entity.getPersistentData().putDouble("clock", 0);
		}
	}

	@SubscribeEvent
	public void onPlayerTick(TickEvent.PlayerTickEvent event) {
		if (event.phase == TickEvent.Phase.END) {
			Entity entity = event.player;
			World world = entity.world;
			double i = entity.getPosX();
			double j = entity.getPosY();
			double k = entity.getPosZ();
			Map<String, Object> dependencies = new HashMap<>();
			dependencies.put("x", i);
			dependencies.put("y", j);
			dependencies.put("z", k);
			dependencies.put("world", world);
			dependencies.put("entity", entity);
			dependencies.put("event", event);
			this.executeProcedure(dependencies);
		}
	}
}
