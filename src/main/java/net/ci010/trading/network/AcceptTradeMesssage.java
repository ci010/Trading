package net.ci010.trading.network;

import io.netty.buffer.ByteBuf;
import net.ci010.trading.common.TradingSystem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class AcceptTradeMesssage implements IMessage
{
	int tradeId;

	public AcceptTradeMesssage()
	{}
	
	public AcceptTradeMesssage(int tradeId)
	{
		this.tradeId = tradeId;
	}
	
	@Override
	public void fromBytes(ByteBuf buf)
	{
		tradeId = buf.readInt();
	}

	@Override
	public void toBytes(ByteBuf buf)
	{
		buf.writeInt(tradeId);
	}

	public static class Handler extends AbstractServerMessageHandler<AcceptTradeMesssage>
	{
		@Override
		public IMessage handleServerMessage(EntityPlayer player, AcceptTradeMesssage message, MessageContext ctx)
		{
			TradingSystem.currentTrade.get(message.tradeId).accept(player);
			return null;
		}
	}
}
