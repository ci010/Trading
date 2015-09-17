package net.ci010.trading.common;

import net.ci010.trading.TradingMod;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.living.LivingEvent.LivingJumpEvent;
import net.minecraftforge.event.entity.player.PlayerOpenContainerEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class DebugHandler
{
	@SubscribeEvent
	public void onPlayerJump(LivingJumpEvent event)
	{
		if(event.entityLiving instanceof EntityPlayer)
		{
//			((EntityPlayer)event.entityLiving).openGui(TradingMod.instance, 0, event.entityLiving.worldObj, 0, 0, 0);
		}
	}
	@SubscribeEvent
	public void onContainerOpen(PlayerOpenContainerEvent event)
	{
//		System.out.println("open container");
	}
}

