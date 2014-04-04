package daynightcyclecontrol;

import java.util.EnumSet;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.world.World;
import CoroUtil.OldUtil;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;

public class TickHandlerClient implements ITickHandler
{
    
	public static Minecraft mc = null;
	
    public TickHandlerClient() {
    	
    }
	
    @Override
    public void tickStart(EnumSet<TickType> type, Object... tickData) {
    	
    }

    @Override
    public void tickEnd(EnumSet<TickType> type, Object... tickData)
    {
        if (type.equals(EnumSet.of(TickType.RENDER)))
        {
            //onRenderTick();
        }
        else if (type.equals(EnumSet.of(TickType.CLIENT)))
        {
            GuiScreen guiscreen = Minecraft.getMinecraft().currentScreen;
            
            mc = FMLClientHandler.instance().getClient();
        	if (mc.theWorld == null || mc.thePlayer == null) return;
        	//world instance requiring calls below here
            
            //test overworld time extending
    		World world = mc.theWorld;//DimensionManager.getWorld(0);
    		if (world.provider.dimensionId == 0/*RPGMod.epochDimID*/ && !(world.provider instanceof WorldProviderSurfaceOverride)) {
    			OldUtil.setPrivateValueSRGMCP(World.class, world, "field_73011_w", "provider", new WorldProviderSurfaceOverride());
    			world.provider.registerWorld(world);
    		} else {
    			//System.out.println("yay!!!!!");
    		}
        }
    }

	@Override
    public EnumSet<TickType> ticks()
    {
        return EnumSet.of(TickType.RENDER, TickType.CLIENT);
        // In my testing only RENDER, CLIENT, & PLAYER did anything on the client side.
        // Read 'cpw.mods.fml.common.TickType.java' for a full list and description of available types
    }

    @Override
    public String getLabel()
    {
        return null;
    }
    
    public void worldRenderTick(float partialTicks)
    {
    	
    }
    
}