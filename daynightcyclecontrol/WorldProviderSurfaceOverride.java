package daynightcyclecontrol;

import net.minecraft.world.WorldProvider;

public class WorldProviderSurfaceOverride extends WorldProvider
{
    /**
     * Returns the dimension's name, e.g. "The End", "Nether", or "Overworld".
     */
    public String getDimensionName()
    {
        return "Overworld";
    }
    
    @Override
    public float calculateCelestialAngle(long par1, float par3)
    {
    	long ticksInDay = DayNightCycleControl.ticksInDay;
        int j = (int)(par1 % ticksInDay);
        float f1 = ((float)j + par3) / ticksInDay - 0.25F;

        if (f1 < 0.0F)
        {
            ++f1;
        }

        if (f1 > 1.0F)
        {
            --f1;
        }

        float f2 = f1;
        f1 = 1.0F - (float)((Math.cos((double)f1 * Math.PI) + 1.0D) / 2.0D);
        f1 = f2 + (f1 - f2) / 3.0F;
        return f1;
    }
    
    @Override
    public int getMoonPhase(long par1)
    {
        return (int)(par1 / DayNightCycleControl.ticksInDay) % 8;
    }
}
