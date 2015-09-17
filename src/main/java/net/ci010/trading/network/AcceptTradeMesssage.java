package net.ci010.trading.network;

import io.netty.buffer.ByteBuf;
import net.ci010.minecraftUtil.network.AbstractServerMessageHandler;
import net.ci010.trading.common.TradingSystem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class AcceptTradeMesssage implements IMessage
{
	public AcceptTradeMesssage()
	{}
	
	@Override
	public void fromBytes(ByteBuf buf)
	{

	}

	@Override
	public void toBytes(ByteBuf buf)
	{
	
	}

	public static class Handler extends AbstractServerMessageHandler<AcceptTradeMesssage>
	{
		@Override
		public IMessage handleServerMessage(EntityPlayer player, AcceptTradeMesssage message, MessageContext ctx)
		{
			System.out.println("handle accpet trade message");
			TradingSystem.getPlayerTrade(player).accept(player);
			return null;
		}
	}
}
