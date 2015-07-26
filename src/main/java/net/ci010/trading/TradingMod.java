package net.ci010.trading;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.SidedProxy;

@Mod(name = TradingMod.NAME, modid = TradingMod.MODID, version = TradingMod.VERSION)
public class TradingMod
{
	public static final String MODID = "tranding";
	public static final String NAME = "Trading";
	public static final String VERSION = "1.0";

	@SidedProxy()
	public static CommonProxy proxy;

	@Instance("trading")
	public static TradingMod instance;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
	}

	@EventHandler
	public void init(FMLInitializationEvent event)
	{
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{

	}

}
