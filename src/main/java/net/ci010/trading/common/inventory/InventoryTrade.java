package net.ci010.trading.common.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IChatComponent;

public class InventoryTrade implements IInventory
{
	private ItemStack[] stacks = new ItemStack[9];
	private EntityPlayer owner;

	public InventoryTrade getClone() throws CloneNotSupportedException
	{
		return (InventoryTrade) super.clone();
	}

	public InventoryTrade(EntityPlayer player)
	{
		this.owner = player;
	}

	public EntityPlayer getOwner()
	{
		return this.owner;
	}

	public int getFullSlot()
	{
		int num = 0;
		for (int i = 0; i < this.stacks.length; i++)
		{
			if (stacks[i] != null)
				num++;
		}
		return num;
	}

	@Override
	public String getName()
	{
		return "container.itemExchange";
	}

	@Override
	public boolean hasCustomName()
	{
		return false;
	}

	@Override
	public IChatComponent getDisplayName()
	{
		return new ChatComponentTranslation(this.getName(), new Object[0]);
	}

	@Override
	public int getSizeInventory()
	{
		return this.stacks.length;
	}

	@Override
	public ItemStack getStackInSlot(int index)
	{
		return this.stacks[index];
	}

	@Override
	public ItemStack decrStackSize(int index, int count)
	{
		if (this.stacks[index] != null)
		{
			ItemStack itemstack;

			if (this.stacks[index].stackSize <= count)
			{
				itemstack = this.stacks[index];

				this.stacks[index] = null;
				this.markDirty();
				return itemstack;
			}
			else
			{
				itemstack = this.stacks[index].splitStack(count);

				if (this.stacks[index].stackSize == 0)
				{
					this.stacks[index] = null;
				}

				this.markDirty();

				return itemstack;
			}
		}
		return null;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int index)
	{
		if (this.stacks[index] != null)
		{
			ItemStack itemstack = this.stacks[index];
			this.stacks[index] = null;
			return itemstack;
		}
		return null;
	}

	@Override
	public void setInventorySlotContents(int index, ItemStack stack)
	{
		this.stacks[index] = stack;

		if (stack != null && stack.stackSize > this.getInventoryStackLimit())
		{
			stack.stackSize = this.getInventoryStackLimit();
		}
		this.markDirty();

	}

	@Override
	public int getInventoryStackLimit()
	{
		return 64;
	}

	@Override
	public void markDirty()
	{
		// TODO 自动生成的方法存根

	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player)
	{
		return player.equals(this.owner);
	}

	@Override
	public void openInventory(EntityPlayer player)
	{
	}

	@Override
	public void closeInventory(EntityPlayer player)
	{
	}

	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack)
	{
		return true;
	}

	@Override
	public int getField(int id)
	{
		// TODO 自动生成的方法存根
		return 0;
	}

	@Override
	public void setField(int id, int value)
	{
		// TODO 自动生成的方法存根

	}

	@Override
	public int getFieldCount()
	{
		// TODO 自动生成的方法存根
		return 0;
	}

	@Override
	public void clear()
	{
		for (int i = 0; i < this.stacks.length; i++)
		{
			this.stacks[i] = null;
		}
	}

}
