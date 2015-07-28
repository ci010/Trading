package net.ci010.trading.common;

import java.util.LinkedList;
import java.util.List;

import net.ci010.trading.TradingMod;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;

public class TradingSystem
{
	public static List<Trade> currentTrade = new LinkedList<Trade>();

	public static void startATrade(EntityPlayer initiator, EntityPlayer invitee)
	{
		if (isPlayerReady(initiator) && isPlayerReady(invitee))
		{
			int id = currentTrade.size();
			currentTrade.add(new Trade(id, initiator, invitee));
			initiator.openGui(	TradingMod.instance,
								0,
								initiator.worldObj,
								initiator.getPosition().getX(),
								initiator.getPosition().getY(),
								initiator.getPosition().getZ());
			invitee.openGui(TradingMod.instance,
							0,
							invitee.worldObj,
							invitee.getPosition().getX(),
							invitee.getPosition().getY(),
							invitee.getPosition().getZ());
		}
	}

	public static Trade getPlayerTrade(EntityPlayer player)
	{
		for (Trade trade : currentTrade)
		{
			if (trade.isInvolveTrade(player))
			{
				return trade;
			}
		}
		return null;
	}

	private static boolean isPlayerReady(EntityPlayer player)
	{
		double d0 = 8.0D;
		double d1 = 5.0D;
		List list = player.worldObj.getEntitiesWithinAABB(	EntityMob.class,
															new AxisAlignedBB((double) player.getPosition().getX() - d0, (double) player.getPosition().getY() - d1, (double) player.getPosition().getZ() - d0, (double) player.getPosition().getX() + d0, (double) player.getPosition().getY() + d1, (double) player.getPosition().getZ() + d0));

		return !list.isEmpty();
	}

	public static void endATrade(int id)
	{
		currentTrade.remove(id);
	}

	public static void endATrade(Trade trade)
	{
		if(trade ==null)
			return;
		currentTrade.remove(trade.id);
	}
}
