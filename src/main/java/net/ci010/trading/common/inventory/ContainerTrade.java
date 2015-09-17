package net.ci010.trading.common.inventory;

import net.ci010.trading.common.Trade;
import net.ci010.trading.common.TradingSystem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerTrade extends Container
{
	private Trade trade;

	public ContainerTrade(IInventory playerInventory, Trade trade)
	{
		this.trade = trade;

		if (trade == null)
			System.out.println("trade is null");
		else if (trade.initiator == null)
			System.out.println("initiator is null");
		else if (trade.invitee == null)
			System.out.println("invitee is null");
		else if (trade.initiator.tradeInventory == null)
			System.out.println("initiator inv is null");
		else if (trade.invitee.tradeInventory == null)
			System.out.println("invitee inv is null");
		
		int i;

		for (i = 0; i < 3; ++i)
		{
			for (int j = 0; j < 3; ++j)
			{
				int index = j + i * 3;// + 45;
				this.addSlotToContainer(new SlotTrade(trade.initiator.tradeInventory, index, 8 + j * 18, 6 + i * 18));
			}
		}

		for (i = 0; i < 3; ++i)
		{
			for (int j = 0; j < 3; ++j)
			{
				int index = j + i * 3;// + 54;
				this.addSlotToContainer(new SlotTrade(trade.invitee.tradeInventory, index, 116 + j * 18, 8 + 6 + i * 18));
			}
		}

		// this.addSlotToContainer(new Slot(playerInventory,0,8,6));
		//
		// this.addSlotToContainer(new Slot(playerInventory,1,116,6));

		for (i = 0; i < 3; ++i)
		{
			for (int j = 0; j < 9; ++j)
			{
				int index = j + i * 9 + 9;
				this.addSlotToContainer(new Slot(playerInventory, index, 8 + j * 18, 84 + i * 18));
			}
		}

		for (i = 0; i < 9; ++i)
		{
			this.addSlotToContainer(new Slot(playerInventory, i, 8 + i * 18, 142));
		}
	}

	@Override
	public boolean canInteractWith(EntityPlayer playerIn)
	{
		return true;
	}

	@Override
	public void onContainerClosed(EntityPlayer playerIn)
	{
		System.out.println("contianer close for "+playerIn.getName());
		if (trade == null)
			return;
		if (playerIn.equals(trade.initiator.player))
			returnItemStack(playerIn, trade.initiator.tradeInventory);
		else if (playerIn.equals(trade.invitee.player))
			returnItemStack(playerIn, trade.invitee.tradeInventory);
		TradingSystem.endATrade(this.trade);
	}

	private void returnItemStack(EntityPlayer player, IInventory inventory)
	{
		// TODO finish this method
		for (int i = 0; i < inventory.getSizeInventory(); i++)
		{
			ItemStack remainItem = inventory.getStackInSlotOnClosing(i);

			if (remainItem != null)
				player.inventory.mainInventory[player.inventory.getFirstEmptyStack()] = remainItem;
		}
	}

	public Trade getTrade()
	{
		return this.trade;
	}

}
