package net.ci010.trading.client.gui.inventory;

import net.ci010.trading.common.inventory.ContainerTrade;
import net.ci010.trading.network.AcceptTradeMesssage;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

public class GuiTrade extends GuiContainer
{
	private float oldMouseX;
	private float oldMouseY;

	private final EntityPlayer other;

	public GuiTrade(Container container)
	{
		super(container);
		other = ((ContainerTrade) this.inventorySlots).getTrade().getOtherSide(this.mc.thePlayer);
	}

	@Override
	public void initGui()
	{
		super.initGui();
		this.buttonList.add(new GuiButton(0, this.width / 2 - 19, this.height / 2 - 60, 38, 20, "accpet"));
	}

	@Override
	protected void actionPerformed(GuiButton button)
	{
		System.out.println("action!");
		if (button.id == 0)
			net.ci010.minecraftUtil.network.PacketDispatcher.instance.sendToServer(new AcceptTradeMesssage());
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
	{
		this.mc.getTextureManager().bindTexture(new ResourceLocation("textures/gui/container/furnace.png"));

		drawLower(this.guiLeft, this.guiTop);
		drawTradeInv(this.guiLeft, this.guiTop);

		drawTradeInv(this.guiLeft + this.xSize / 2 + 20, this.guiTop);

		GuiInventory.drawEntityOnScreen(this.guiLeft - 20,
										this.guiTop + 60,
										30,
										(float) (this.guiLeft - 20) - this.oldMouseX,
										(float) (this.guiTop + 10) - this.oldMouseY,
										this.mc.thePlayer);

		GuiInventory.drawEntityOnScreen(this.width - 105,
										this.guiTop + 60,
										30,
										(float) (this.width - 105) - this.oldMouseX,
										(float) (this.guiTop + 10) - this.oldMouseY,
										other);
		
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks)
	{
		super.drawScreen(mouseX, mouseY, partialTicks);
		this.oldMouseX = (float) mouseX;
		this.oldMouseY = (float) mouseY;
	}

	private void drawTradeInv(int xLeft, int yTop)
	{
		int yOffset = 78;
		int yHotBarOffset = 23;
		int xLength = this.xSize / 2 - 22;
		int yLength = this.ySize - yOffset - yHotBarOffset;
		int fix = 3;
		int captureGap = 5;

		this.drawTexturedModalRect(	xLeft,
									yTop + fix,
									0,
									yOffset + fix,
									xLength,
									yLength - fix * 2);

		// top left to right
		this.drawTexturedModalRect(	xLeft,
									yTop,
									0,
									0,
									xLength - captureGap,
									captureGap);

		// top right to bottom
		this.drawTexturedModalRect(	xLeft + xLength - captureGap,
									yTop,
									this.xSize - captureGap - 2,
									0,
									captureGap + 2,
									yLength - captureGap);

		// right bottom
		this.drawTexturedModalRect(	xLeft + xLength - captureGap,
									yTop + yLength - captureGap,
									this.xSize - captureGap - 2,
									this.ySize - captureGap,
									captureGap + 2,
									captureGap);

		// left bottom to right
		this.drawTexturedModalRect(	xLeft,
									yTop + yLength - captureGap,
									0,
									this.ySize - captureGap,
									xLength - captureGap,
									captureGap);

	}

	private void drawLower(int xLeft, int yTop)
	{
		int yOffset = 75;
		final int fix = 3;
		int captureGap = 5;
		this.drawTexturedModalRect(	xLeft,
									yTop + yOffset + fix,
									0,
									yOffset + fix,
									this.xSize,
									this.ySize - yOffset - fix);

		this.drawTexturedModalRect(	xLeft,
									yTop + yOffset,
									0,
									0,
									this.xSize,
									captureGap);
	}

}
