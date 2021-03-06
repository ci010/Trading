package net.ci010.trading;

import net.ci010.minecraftUtil.network.NetworkMod;
import net.ci010.minecraftUtil.network.PacketDispatcher;
import net.ci010.minecraftUtil.network.Proxy;
import net.ci010.trading.common.CommonProxy;
import net.ci010.trading.common.command.TestCommand;
import net.ci010.trading.common.command.TradingCommand;
import net.minecraft.command.ServerCommandManager;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.SidedProxy;

@Mod(name = TradingMod.NAME, modid = TradingMod.MODID, version = TradingMod.VERSION)
public class TradingMod implements NetworkMod
{
	public static final String MODID = "tranding";
	public static final String NAME = "Trading";
	public static final String VERSION = "1.0";

	@SidedProxy(serverSide = "net.ci010.trading.common.CommonProxy", clientSide = "net.ci010.trading.client.ClientProxy")
	public static CommonProxy proxy;

	@Instance("trading")
	public static TradingMod instance;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		instance = this;
		PacketDispatcher.initInstance(MODID, this);
	}

	@EventHandler
	public void init(FMLInitializationEvent event)
	{
		proxy.registerPackets();
		proxy.registerHandler();
	}
	
	@EventHandler
	public void serverStarting(FMLServerStartingEvent event)
	{
		ServerCommandManager serverCmdManager = (ServerCommandManager) event.getServer().getCommandManager();
		serverCmdManager.registerCommand(new TradingCommand());
		serverCmdManager.registerCommand(new TestCommand());
	}

	@Override
	public Proxy getProxy()
	{
		return proxy;
	}
}
