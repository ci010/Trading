package net.ci010.trading.common.command;

import net.ci010.trading.common.TradingSystem;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.PlayerNotFoundException;
import net.minecraft.entity.player.EntityPlayer;

public class TradingCommand extends CommandBase
{
	@Override
	public String getName()
	{
		return "trade";
	}

	@Override
	public String getCommandUsage(ICommandSender sender)
	{
		return "trade.command.usage";
	}

	@Override
	public void execute(ICommandSender sender, String[] args)
			throws CommandException
	{
		EntityPlayer invitee;
		if(args.length==1)
		{
			try
			{
				invitee = getPlayer(sender, args[0]);
			}
			catch (PlayerNotFoundException e)
			{
				throw new CommandException("trade.command.exception.noname");
			}
			
			TradingSystem.startATrade(getPlayer(sender, sender.getName()), invitee);
		}
		else
			throw new CommandException("trade.command.exception.usage");
	}
}
