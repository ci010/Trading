package net.ci010.trading.common;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.ci010.trading.common.exception.NoTradeFoundException;
import net.ci010.trading.network.InviteTradeMessage;
import net.ci010.trading.network.PrepareTradeMessage;
import net.ci010.trading.network.StartTradeMessage;
import net.ci010.minecraftUtil.network.PacketDispatcher;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.ContainerPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TradingSystem
{
	private static Set<Trade> currentTrade = new HashSet<Trade>();

	@SideOnly(Side.SERVER)
	public static void inviteATrade(EntityPlayer initiator, EntityPlayer invitee)
	{
		if (initiator instanceof EntityPlayerMP)
		{
			System.out.println("is mp");
			System.out.println("start a trade");

			if (!isPlayerFree(initiator) || !isPlayerFree(invitee))
				return;

			PacketDispatcher.instance.sendTo(	new InviteTradeMessage(initiator),
												(EntityPlayerMP) invitee);

			currentTrade.add(new Trade(initiator, invitee));
		}
	}

	@SideOnly(Side.CLIENT)
	public static void synTrade(Trade trade)
	{
		currentTrade.add(trade);
	}
	
	@SideOnly(Side.SERVER)
	public static void startATrade(Trade trade)
	{
		PacketDispatcher.instance.sendTo(	new StartTradeMessage(trade.invitee.player.getUniqueID(), true),
											(EntityPlayerMP) trade.initiator.player);
		PacketDispatcher.instance.sendTo(	new StartTradeMessage(trade.initiator.player.getUniqueID(), false),
											(EntityPlayerMP) trade.invitee.player);
	}

	public static Trade getPlayerTrade(EntityPlayer player) //throws NoTradeFoundException
	{
		System.out.println("trade size is " + currentTrade.size());
		for (Trade trade : currentTrade)
		{
			if (trade.isInvolveTrade(player))
			{
				System.out.println("find player trade for" + player.getName());
				return trade;
			}
			System.out.println(trade.initiator.player.getName() + " " + trade.invitee.player.getName());
		}
		
		System.out.println("trade is null for " + player.getName());
		return null;
//		throw new NoTradeFoundException(player);
		
		
	}

	@SuppressWarnings("rawtypes")
	private static boolean isPlayerFree(EntityPlayer player)
	{
		for (Trade trade : currentTrade)
			if (trade.isInvolveTrade(player)) return false;
		
		double d0 = 8.0D;
		double d1 = 5.0D;
		List list = player.worldObj.getEntitiesWithinAABB(	EntityMob.class,
																	new AxisAlignedBB((double) player.getPosition().getX() - d0, (double) player.getPosition().getY() - d1, (double) player.getPosition().getZ() - d0, (double) player.getPosition().getX() + d0, (double) player.getPosition().getY() + d1, (double) player.getPosition().getZ() + d0));

		return list.isEmpty() && (player.openContainer instanceof ContainerPlayer);
	}

	public static void endATrade(Trade trade)
	{
		System.out.println("end a trade");
		if (trade != null)
			currentTrade.remove(trade);
	}
}
