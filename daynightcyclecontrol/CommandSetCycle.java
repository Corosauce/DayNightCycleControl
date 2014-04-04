package daynightcyclecontrol;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ChatMessageComponent;
import cpw.mods.fml.common.network.PacketDispatcher;

public class CommandSetCycle extends CommandBase
{
    public String getCommandName()
    {
        return "setcycle";
    }

    /**
     * Return the required permission level for this command.
     */
    public int getRequiredPermissionLevel()
    {
        return 2;
    }

    public String getCommandUsage(ICommandSender par1ICommandSender)
    {
        return "eg: /setcycle 72000";
    }

    public void processCommand(ICommandSender par1ICommandSender, String[] par2ArrayOfStr)
    {
        if (par2ArrayOfStr.length > 0)
        {
        	int what = Integer.valueOf(par2ArrayOfStr[0]);
        	
        	DayNightCycleControl.ticksInDay = what;
        	DayNightCycleControl.savePath.delete();
        	DayNightCycleControl.config.save();
        	PacketDispatcher.sendPacketToAllPlayers(PacketHandler.getPacket(DayNightCycleControl.ticksInDay));
        	
        	if(par1ICommandSender instanceof EntityPlayerMP)
			{
				EntityPlayer player = getCommandSenderAsPlayer(par1ICommandSender);
				par1ICommandSender.sendChatToPlayer(ChatMessageComponent.createFromText("Day/Night cycle now set to " + what + " game ticks"));	
			}
        	
        	return;
        }

        throw new WrongUsageException("eg: /setcycle 72000", new Object[0]);
    }
}
