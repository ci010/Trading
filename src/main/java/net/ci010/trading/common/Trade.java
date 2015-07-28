package net.ci010.trading.common;

import net.ci010.trading.common.inventory.InventoryTrade;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;

public class Trade implements IExchange
{
	int id;

	public final Trader initiator;
	public final Trader invitee;

	public Trade(int id, EntityPlayer initiator, EntityPlayer invitee)
	{
		this.id = id;
		this.initiator = new Trader(initiator);
		this.invitee = new Trader(invitee);
	}

	@Override
	public void exchange()
	{
		if (canFinishTrade())
		{
			InventoryTrade temp;
			try
			{
				temp = initiator.tradeInventory.getClone();
				initiator.tradeInventory = invitee.tradeInventory;
				invitee.tradeInventory = temp;
			}
			catch (CloneNotSupportedException e)
			{
				e.printStackTrace();
			}
		}
	}

	public boolean isInvolveTrade(EntityPlayer player)
	{
		return initiator.equals(player) || invitee.equals(player);
	}

	public EntityPlayer getOtherSide(EntityPlayer player)
	{
		if (player.getName().equals(initiator.player.getName()))
			return invitee.player;
		else if (player.getName().equals(invitee.player.getName()))
			return initiator.player;
		else
			return null;
	}

	public void accept(EntityPlayer player)
	{
		if (initiator.equals(player))
			initiator.isAccept = true;
		if (invitee.equals(player))
			invitee.isAccept = true;
		if (initiator.isAccept && invitee.isAccept)
			exchange();
	}

	public boolean canFinishTrade()
	{
		return this.initiator.hasPlaceToExchange() && this.invitee.hasPlaceToExchange();
	}

	public class Trader
	{
		public final EntityPlayer player;
		boolean isAccept;
		public InventoryTrade tradeInventory;

		public Trader(EntityPlayer player)
		{
			this.player = player;
		}

		public boolean hasPlaceToExchange()
		{
			return tradeInventory.getFullSlot() <= getInventoryRemaindPlace(player.inventory);
		}

		private int getInventoryRemaindPlace(InventoryPlayer inventory)
		{
			int num = 0;
			for (int i = 0; i < inventory.mainInventory.length; i++)
			{
				if (inventory.mainInventory[i] == null)
					num++;
			}
			return num;
		}
	}

}
