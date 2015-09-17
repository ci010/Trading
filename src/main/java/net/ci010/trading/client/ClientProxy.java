package net.ci010.trading.client;

import net.ci010.trading.common.CommonProxy;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class ClientProxy extends CommonProxy
{
	@Override
	public EntityPlayer getPlayer(MessageContext ctx)
	{
		return Minecraft.getMinecraft().thePlayer;
	}
}
