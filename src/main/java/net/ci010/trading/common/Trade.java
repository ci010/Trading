package net.ci010.trading.common;

import java.util.UUID;

import net.ci010.trading.TradingMod;
import net.ci010.trading.common.inventory.InventoryTrade;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;

public class Trade implements IExchange
{
	public final Trader initiator;
	public final Trader invitee;

	public Trade(EntityPlayer initiator, EntityPlayer invitee)
	{
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

	public void start()
	{
		initiator.player.openGui(TradingMod.instance, 0, null, 0, 0, 0);
		invitee.player.openGui(TradingMod.instance, 0, null, 0, 0, 0);
	}

	public boolean isInvolveTrade(EntityPlayer player)
	{
		return initiator.player.getUniqueID().equals(player.getUniqueID()) || invitee.player.getUniqueID().equals(player.getUniqueID());
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

	public void ready(EntityPlayer player)
	{
		if (initiator.equals(player))
			initiator.isReady = true;
		if (invitee.equals(player))
			invitee.isReady = true;
		if (initiator.isReady && invitee.isReady)
			start();
	}

	public boolean canFinishTrade()
	{
		return this.initiator.hasPlaceToExchange() && this.invitee.hasPlaceToExchange();
	}

	public class Trader
	{
		public final EntityPlayer player;
		// public final UUID uuid;
		// public final int entityId;
		boolean isReady = false;
		boolean isAccept = false;
		public InventoryTrade tradeInventory;

		public Trader(EntityPlayer player)
		{
			this.player = player;
			this.tradeInventory = new InventoryTrade(player);
			// uuid = player.getUniqueID();
			// entityId = player.getEntityId();
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
