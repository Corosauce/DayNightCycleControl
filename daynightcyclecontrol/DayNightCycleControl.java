package daynightcyclecontrol;

import java.io.File;

import net.minecraftforge.common.Configuration;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.NetworkMod;

@NetworkMod(channels = { "SetCycle" }, clientSideRequired = true, serverSideRequired = true, packetHandler = PacketHandler.class)
@Mod(modid = "daynightcyclecontrol", name="Day Night Cycle Control", version="v1.0")
public class DayNightCycleControl {
	
	@Mod.Instance( value = "daynightcyclecontrol" )
	public static DayNightCycleControl instance;
	public static String modID = "daynightcyclecontrol";

    public Configuration preInitConfig;
    @SidedProxy(clientSide = "daynightcyclecontrol.ClientProxy", serverSide = "daynightcyclecontrol.CommonProxy")
    public static CommonProxy proxy;
    
    public static int ticksInDay = 72000;
    
    public static File savePath = new File("." + File.separator + "config" + File.separator + "daynightcyclecontrol.cfg");

    public static Configuration config = null;
    
    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
    	config = new Configuration(savePath/*event.getSuggestedConfigurationFile()*/);
    	saveConfig();
    }
    
    public static void saveConfig() {
    	try {
	    	ticksInDay = config.get("default", "ticksInDay", Integer.valueOf(ticksInDay)).getInt();
    	} catch (Exception ex) {
    		ex.printStackTrace();
    	}
    	config.save();
    }
    
    @Mod.EventHandler
    public void load(FMLInitializationEvent event)
    {
    	proxy.init();
    }
    
    @Mod.EventHandler
	public void serverStarting(FMLServerStartingEvent event) {
    	event.registerServerCommand(new CommandSetCycle());
    }

}
