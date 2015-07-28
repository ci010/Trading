package net.ci010.trading.common;

import net.ci010.trading.TradingMod;
import net.ci010.trading.network.AcceptTradeMesssage;
import net.ci010.trading.network.GuiHandler;
import net.ci010.trading.network.PacketDispatcher;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.relauncher.Side;

public class CommonProxy
{
	public void registerHandler()
	{
		MinecraftForge.EVENT_BUS.register(new DebugHandler());
	}
	public final void registerPackets()
	{
		PacketDispatcher.registerMessage(AcceptTradeMesssage.Handler.class, AcceptTradeMesssage.class, Side.SERVER);
		NetworkRegistry.INSTANCE.registerGuiHandler(TradingMod.instance, new GuiHandler());
	}
	
}
