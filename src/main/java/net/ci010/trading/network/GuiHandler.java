package net.ci010.trading.network;

import net.ci010.trading.client.gui.GuiRequest;
import net.ci010.trading.client.gui.inventory.GuiTrade;
import net.ci010.trading.common.TradingSystem;
import net.ci010.trading.common.inventory.ContainerTrade;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler
{
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		switch (ID)
		{
			case 0:
				return new ContainerTrade(player.inventory, TradingSystem.getPlayerTrade(player));
			case 1:

		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		switch (ID)
		{
			case 0:
				System.out.println("try to return a gui container");
				return new GuiTrade(new ContainerTrade(player.inventory, TradingSystem.getPlayerTrade(player)));
			case 1:
				return new GuiRequest();
		}
		return null;
	}

}
