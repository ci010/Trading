package net.ci010.trading.common;

import net.ci010.trading.TradingMod;
import net.ci010.trading.network.AcceptTradeMesssage;
import net.ci010.trading.network.GuiHandler;
import net.ci010.trading.network.StartTradeMessage;
import net.minecraft.entity.player.EntityPlayer;
import net.ci010.minecraftUtil.network.PacketDispatcher;
import net.ci010.minecraftUtil.network.Proxy;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

public class CommonProxy implements Proxy
{
	public void registerHandler()
	{
		MinecraftForge.EVENT_BUS.register(new DebugHandler());
	}

	public final void registerPackets()
	{
		PacketDispatcher.instance.registerMessage(	AcceptTradeMesssage.Handler.class,
													AcceptTradeMesssage.class,
													Side.SERVER);
		PacketDispatcher.instance.registerMessage(	StartTradeMessage.Handler.class,
													StartTradeMessage.class);
		NetworkRegistry.INSTANCE.registerGuiHandler(TradingMod.instance,
													new GuiHandler());
	}

	@Override
	public EntityPlayer getPlayer(MessageContext ctx)
	{
		return ctx.getServerHandler().playerEntity;
	}

}
