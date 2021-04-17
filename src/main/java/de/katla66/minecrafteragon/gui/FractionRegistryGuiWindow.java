
package de.katla66.minecrafteragon.gui;

import org.lwjgl.opengl.GL11;

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

import com.mojang.blaze3d.matrix.MatrixStack;

@OnlyIn(Dist.CLIENT)
public class FractionRegistryGuiWindow extends ContainerScreen<FractionRegistryGui.GuiContainerMod> {
	private World world;
	private int x, y, z;
	private PlayerEntity entity;
	public FractionRegistryGuiWindow(FractionRegistryGui.GuiContainerMod container, PlayerInventory inventory, ITextComponent text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.xSize = 215;
		this.ySize = 166;
	}
	private static final ResourceLocation texture = new ResourceLocation("minecraft_eragon_fraktions:textures/fraction_registry.png");
	@Override
	public void render(MatrixStack ms, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(ms);
		super.render(ms, mouseX, mouseY, partialTicks);
		this.renderHoveredTooltip(ms, mouseX, mouseY);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(MatrixStack ms, float par1, int par2, int par3) {
		GL11.glColor4f(1, 1, 1, 1);
		Minecraft.getInstance().getTextureManager().bindTexture(texture);
		int k = (this.width - this.xSize) / 2;
		int l = (this.height - this.ySize) / 2;
		this.blit(ms, k, l, 0, 0, this.xSize, this.ySize, this.xSize, this.ySize);
		Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("minecraft_eragon_fraktions:textures/treebrench2.png"));
		this.blit(ms, this.guiLeft + 6, this.guiTop + 0, 0, 0, 215, 166, 215, 166);
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
		this.font.drawString(ms, "Choose your faction!", 56, 7, -16763956);
		this.font.drawString(ms, "Resistance", 77, 67, -16711681);
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
		this.addButton(new Button(this.guiLeft + 12, this.guiTop + 34, 55, 20, new StringTextComponent(" Varden "), e -> {
			MinecraftEragonFraktionsMod.PACKET_HANDLER.sendToServer(new FractionRegistryGui.ButtonPressedMessage(0, x, y, z));
			FractionRegistryGui.handleButtonAction(entity, 0, x, y, z);
		}));
		this.addButton(new Button(this.guiLeft + 12, this.guiTop + 104, 50, 20, new StringTextComponent("Eleven"), e -> {
			MinecraftEragonFraktionsMod.PACKET_HANDLER.sendToServer(new FractionRegistryGui.ButtonPressedMessage(1, x, y, z));
			FractionRegistryGui.handleButtonAction(entity, 1, x, y, z);
		}));
		this.addButton(new Button(this.guiLeft + 138, this.guiTop + 104, 55, 20, new StringTextComponent("Dwarfs"), e -> {
			MinecraftEragonFraktionsMod.PACKET_HANDLER.sendToServer(new FractionRegistryGui.ButtonPressedMessage(2, x, y, z));
			FractionRegistryGui.handleButtonAction(entity, 2, x, y, z);
		}));
		this.addButton(new Button(this.guiLeft + 171, this.guiTop + 142, 40, 20, new StringTextComponent(">>>"), e -> {
			MinecraftEragonFraktionsMod.PACKET_HANDLER.sendToServer(new FractionRegistryGui.ButtonPressedMessage(3, x, y, z));
			FractionRegistryGui.handleButtonAction(entity, 3, x, y, z);
		}));
		this.addButton(new Button(this.guiLeft + 139, this.guiTop + 34, 50, 20, new StringTextComponent("Surda"), e -> {
			MinecraftEragonFraktionsMod.PACKET_HANDLER.sendToServer(new FractionRegistryGui.ButtonPressedMessage(4, x, y, z));
			FractionRegistryGui.handleButtonAction(entity, 4, x, y, z);
		}));
		this.addButton(new Button(this.guiLeft + 289, this.guiTop + -35, 30, 20, new StringTextComponent("?"), e -> {
			MinecraftEragonFraktionsMod.PACKET_HANDLER.sendToServer(new FractionRegistryGui.ButtonPressedMessage(5, x, y, z));
			FractionRegistryGui.handleButtonAction(entity, 5, x, y, z);
		}));
	}
}
