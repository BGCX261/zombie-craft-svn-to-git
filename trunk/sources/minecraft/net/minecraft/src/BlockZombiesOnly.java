package net.minecraft.src;

import java.util.Random;
import java.util.ArrayList;
import java.util.List;

public class BlockZombiesOnly extends BlockBreakable
{

    protected BlockZombiesOnly(int i, int j, Material material)
	{
		super(i, j, material, true);
	}
	
	public int getBlockTextureFromSide(int i)
    {
		try {
			if (!ModLoader.getMinecraftInstance().theWorld.devMode){
				return blockIndexInTexture;
			} else {
				return 191;
			}
		} catch (Exception ex) {}
		return blockIndexInTexture;
    }
	
    public void onEntityCollidedWithBlock(World world, int i, int j, int k, Entity entity)
    {
		if (entity instanceof EntityPlayer && !mod_EntAI.doorNoClip) {
			if (!world.devMode) {
				if (((EntityPlayer)entity).pushDelay == 0) {
					entity.motionX = entity.motionX*-5F;
					entity.motionZ = entity.motionZ*-5F;
					((EntityPlayer)entity).pushDelay = 5;
				}
			}
		}
    }
	
	public void getCollidingBoundingBoxes(World world, int i, int j, int k, AxisAlignedBB axisalignedbb, ArrayList arraylist)
    {
		return;
    }
}