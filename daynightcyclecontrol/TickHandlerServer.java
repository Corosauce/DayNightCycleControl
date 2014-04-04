package daynightcyclecontrol;

import java.util.EnumSet;

import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;
import CoroUtil.OldUtil;
import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;
import cpw.mods.fml.common.network.PacketDispatcher;

public class TickHandlerServer implements ITickHandler
{

	public long lastCheckTime = 0;
	
	@Override
    public void tickStart(EnumSet<TickType> type, Object... tickData) {
    	
    }

    @Override
    public void tickEnd(EnumSet<TickType> type, Object... tickData)
    {
        if (type.equals(EnumSet.of(TickType.SERVER)))
        {
        	onTickInGame();
        }
    }

    @Override
    public EnumSet<TickType> ticks()
    {
        return EnumSet.of(TickType.SERVER);
    }

    @Override
    public String getLabel() { return null; }
    

    public void onTickInGame()
    {
    	World world = DimensionManager.getWorld(0);
    	if (world != null) {
    		//test overworld time extending
    		if (!(world.provider instanceof WorldProviderSurfaceOverride)) {
    			OldUtil.setPrivateValueSRGMCP(World.class, world, "field_73011_w", "provider", new WorldProviderSurfaceOverride());
    			world.provider.registerWorld(world);
    			PacketDispatcher.sendPacketToAllPlayers(PacketHandler.getPacket(DayNightCycleControl.ticksInDay));
    		} else {
    			//System.out.println("yay!!!!!");
    		}
    	}
    }
}
