
package de.katla66.minecrafteragon.gui;

import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.World;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.ResourceLocation;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.Minecraft;

import de.katla66.minecrafteragon.MinecraftEragonFraktionsMod;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.matrix.MatrixStack;

@OnlyIn(Dist.CLIENT)
public class FraktionRegistryImperiumGuiWindow extends ContainerScreen<FraktionRegistryImperiumGui.GuiContainerMod> {
	private World world;
	private int x, y, z;
	private PlayerEntity entity;
	public FraktionRegistryImperiumGuiWindow(FraktionRegistryImperiumGui.GuiContainerMod container, PlayerInventory inventory, ITextComponent text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.xSize = 215;
		this.ySize = 166;
	}
	private static final ResourceLocation texture = new ResourceLocation("minecraft_eragon_fraktions:textures/fraktion_registry_imperium.png");
	@Override
	public void render(MatrixStack ms, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(ms);
		super.render(ms, mouseX, mouseY, partialTicks);
		this.renderHoveredTooltip(ms, mouseX, mouseY);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(MatrixStack ms, float partialTicks, int gx, int gy) {
		RenderSystem.color4f(1, 1, 1, 1);
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();
		Minecraft.getInstance().getTextureManager().bindTexture(texture);
		int k = (this.width - this.xSize) / 2;
		int l = (this.height - this.ySize) / 2;
		this.blit(ms, k, l, 0, 0, this.xSize, this.ySize, this.xSize, this.ySize);
		Minecraft.getInstance().getTextureManager()
				.bindTexture(new ResourceLocation("minecraft_eragon_fraktions:textures/treebrenchempireminions.png"));
		this.blit(ms, this.guiLeft + -2, this.guiTop + -5, 0, 0, 215, 166, 215, 166);
		RenderSystem.disableBlend();
	}

	@Override
	public boolean keyPressed(int key, int b, int c) {
		if (key == 256) {
			this.minecraft.player.closeScreen();
			return true;
		}
		return super.keyPressed(key, b, c);
	}

	@Override
	public void tick() {
		super.tick();
	}

	@Override
	protected void drawGuiContainerForegroundLayer(MatrixStack ms, int mouseX, int mouseY) {
		this.font.drawString(ms, "Choose your faction!", 55, 10, -10092544);
		this.font.drawString(ms, "Empire Minions", 59, 61, -3368704);
	}

	@Override
	public void onClose() {
		super.onClose();
		Minecraft.getInstance().keyboardListener.enableRepeatEvents(false);
	}

	@Override
	public void init(Minecraft minecraft, int width, int height) {
		super.init(minecraft, width, height);
		minecraft.keyboardListener.enableRepeatEvents(true);
		this.addButton(new Button(this.guiLeft + 5, this.guiTop + 27, 105, 20, new StringTextComponent("Empire Soldier"), e -> {
			if (true) {
				MinecraftEragonFraktionsMod.PACKET_HANDLER.sendToServer(new FraktionRegistryImperiumGui.ButtonPressedMessage(0, x, y, z));
				FraktionRegistryImperiumGui.handleButtonAction(entity, 0, x, y, z);
			}
		}));
		this.addButton(new Button(this.guiLeft + 138, this.guiTop + 27, 50, 20, new StringTextComponent("Shade"), e -> {
			if (true) {
				MinecraftEragonFraktionsMod.PACKET_HANDLER.sendToServer(new FraktionRegistryImperiumGui.ButtonPressedMessage(1, x, y, z));
				FraktionRegistryImperiumGui.handleButtonAction(entity, 1, x, y, z);
			}
		}));
		this.addButton(new Button(this.guiLeft + 137, this.guiTop + 97, 50, 20, new StringTextComponent("Urgal"), e -> {
			if (true) {
				MinecraftEragonFraktionsMod.PACKET_HANDLER.sendToServer(new FraktionRegistryImperiumGui.ButtonPressedMessage(2, x, y, z));
				FraktionRegistryImperiumGui.handleButtonAction(entity, 2, x, y, z);
			}
		}));
		this.addButton(new Button(this.guiLeft + 5, this.guiTop + 142, 40, 20, new StringTextComponent("<<<"), e -> {
			if (true) {
				MinecraftEragonFraktionsMod.PACKET_HANDLER.sendToServer(new FraktionRegistryImperiumGui.ButtonPressedMessage(3, x, y, z));
				FraktionRegistryImperiumGui.handleButtonAction(entity, 3, x, y, z);
			}
		}));
		this.addButton(new Button(this.guiLeft + 6, this.guiTop + 97, 55, 20, new StringTextComponent("Ra'zac"), e -> {
			if (true) {
				MinecraftEragonFraktionsMod.PACKET_HANDLER.sendToServer(new FraktionRegistryImperiumGui.ButtonPressedMessage(4, x, y, z));
				FraktionRegistryImperiumGui.handleButtonAction(entity, 4, x, y, z);
			}
		}));
		this.addButton(new Button(this.guiLeft + 290, this.guiTop + -36, 30, 20, new StringTextComponent("?"), e -> {
			if (true) {
				MinecraftEragonFraktionsMod.PACKET_HANDLER.sendToServer(new FraktionRegistryImperiumGui.ButtonPressedMessage(5, x, y, z));
				FraktionRegistryImperiumGui.handleButtonAction(entity, 5, x, y, z);
			}
		}));
	}
}
