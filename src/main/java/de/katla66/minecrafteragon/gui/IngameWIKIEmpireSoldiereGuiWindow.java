
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
public class IngameWIKIEmpireSoldiereGuiWindow extends ContainerScreen<IngameWIKIEmpireSoldiereGui.GuiContainerMod> {
	private World world;
	private int x, y, z;
	private PlayerEntity entity;
	public IngameWIKIEmpireSoldiereGuiWindow(IngameWIKIEmpireSoldiereGui.GuiContainerMod container, PlayerInventory inventory, ITextComponent text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.xSize = 176;
		this.ySize = 166;
	}
	private static final ResourceLocation texture = new ResourceLocation("minecraft_eragon_fraktions:textures/ingame_wiki_empire_soldiere.png");
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
		this.blit(ms, this.guiLeft + -92, this.guiTop + -65, 0, 0, 300, 300, 300, 300);
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
		this.font.drawString(ms, "Empire Soldier", 51, -11, -65536);
		this.font.drawString(ms, "ACTIVE SKILL:", -11, -2, -16777216);
		this.font.drawString(ms, "-Call an Empire soldier to your side", -11, 7, -16777216);
		this.font.drawString(ms, " who will fight with you your whole", -11, 16, -16777216);
		this.font.drawString(ms, " life (can be used several times).", -11, 25, -16777216);
		this.font.drawString(ms, "PASSIV SKILL:", -11, 70, -16777216);
		this.font.drawString(ms, "-Galbatorix spell makes some potions", -11, 79, -16777216);
		this.font.drawString(ms, " you drink appear one level higher.", -11, 88, -16777216);
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
		this.addButton(new Button(this.guiLeft + -119, this.guiTop + 178, 40, 20, new StringTextComponent("<--"), e -> {
			if (true) {
				MinecraftEragonFraktionsMod.PACKET_HANDLER.sendToServer(new IngameWIKIEmpireSoldiereGui.ButtonPressedMessage(0, x, y, z));
				IngameWIKIEmpireSoldiereGui.handleButtonAction(entity, 0, x, y, z);
			}
		}));
		this.addButton(new Button(this.guiLeft + 258, this.guiTop + 178, 40, 20, new StringTextComponent("-->"), e -> {
			if (true) {
				MinecraftEragonFraktionsMod.PACKET_HANDLER.sendToServer(new IngameWIKIEmpireSoldiereGui.ButtonPressedMessage(1, x, y, z));
				IngameWIKIEmpireSoldiereGui.handleButtonAction(entity, 1, x, y, z);
			}
		}));
	}
}
