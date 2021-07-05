
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
public class IngameWIKIGuiWindow extends ContainerScreen<IngameWIKIGui.GuiContainerMod> {
	private World world;
	private int x, y, z;
	private PlayerEntity entity;
	public IngameWIKIGuiWindow(IngameWIKIGui.GuiContainerMod container, PlayerInventory inventory, ITextComponent text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.xSize = 134;
		this.ySize = 166;
	}
	private static final ResourceLocation texture = new ResourceLocation("minecraft_eragon_fraktions:textures/ingame_wiki.png");
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
				.bindTexture(new ResourceLocation("minecraft_eragon_fraktions:textures/ingamewikibiggerbook300brighter.png"));
		this.blit(ms, this.guiLeft + -104, this.guiTop + -65, 0, 0, 300, 300, 300, 300);
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
		this.font.drawString(ms, "Varden", 59, -13, -16763956);
		this.font.drawString(ms, "ACTIVE SKILL:", -23, -2, -16777216);
		this.font.drawString(ms, "-Last man standing", -23, 7, -16777216);
		this.font.drawString(ms, "PASSIV SKILL:", -23, 70, -16777216);
		this.font.drawString(ms, "-With a few life points,", -23, 79, -16777216);
		this.font.drawString(ms, " a strength boost activates that gives", -23, 88, -16777216);
		this.font.drawString(ms, " you the chance to smash your opponent", -23, 97, -16777216);
		this.font.drawString(ms, " into a thousand pieces.", -23, 106, -16777216);
		this.font.drawString(ms, "Activate your Last man standing ability", -20, 16, -16777216);
		this.font.drawString(ms, "to get the last bit of energy and ", -20, 25, -16777216);
		this.font.drawString(ms, "willpower out of you to strike without", -20, 34, -16777216);
		this.font.drawString(ms, " mercy.", -20, 43, -16777216);
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
		this.addButton(new Button(this.guiLeft + 237, this.guiTop + 178, 40, 20, new StringTextComponent("-->"), e -> {
			if (true) {
				MinecraftEragonFraktionsMod.PACKET_HANDLER.sendToServer(new IngameWIKIGui.ButtonPressedMessage(0, x, y, z));
				IngameWIKIGui.handleButtonAction(entity, 0, x, y, z);
			}
		}));
	}
}
