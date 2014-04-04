package daynightcyclecontrol;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;

public class PacketHandler implements IPacketHandler
{
    public PacketHandler()
    {
    }

    @Override
    public void onPacketData(INetworkManager manager, Packet250CustomPayload packet, Player player)
    {
        DataInputStream dis = new DataInputStream(new ByteArrayInputStream(packet.data));

        if ("SetCycle".equals(packet.channel))
        {
            try
            {
            	DayNightCycleControl.ticksInDay = (int) Math.max(0, dis.readInt());
                
            }
            catch (Exception ex)
            {
                ex.printStackTrace();
            }
        }
    }
    
    public static Packet250CustomPayload getPacket(int parCycle) {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(bos);

        try
        {
        	dos.writeInt(parCycle);
            
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

        Packet250CustomPayload pkt = new Packet250CustomPayload();
        pkt.channel = "SetCycle";
        pkt.data = bos.toByteArray();
        pkt.length = bos.size();
        
        return pkt;
	}
}
