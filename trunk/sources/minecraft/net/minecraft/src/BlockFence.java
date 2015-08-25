package net.minecraft.src;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 


import java.util.ArrayList;

public class BlockFence extends Block
{

    public BlockFence(int i, int j)
    {
        super(i, j, Material.wood);
    }

    public boolean canPlaceBlockAt(World world, int i, int j, int k)
    {
        if(!world.getBlockMaterial(i, j - 1, k).isSolid())
        {
            return false;
        } else
        {
            return super.canPlaceBlockAt(world, i, j, k);
        }
    }

    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int i, int j, int k)
    {
    	//if (mod_EntAI.doorNoClip) return null;
        return AxisAlignedBB.getBoundingBoxFromPool(i, j, k, i + 1, (float)j + 1.5F, k + 1);
    }
	
	public void getCollidingBoundingBoxes(World world, int i, int j, int k, AxisAlignedBB axisalignedbb, ArrayList arraylist)
    {
		if (mod_EntAI.doorNoClip) return;
        arraylist.add(AxisAlignedBB.getBoundingBoxFromPool(i, j, k, i + 1.0, (double)j + 1.5D, k + 1.0));
        //arraylist.add(AxisAlignedBB.getBoundingBoxFromPool(i+0.2, j, k+0.2, i + 0.6, (double)j + 2.5D, k + 0.6));
    }
		
	public void onBlockRemoval(World world, int i, int j, int k)
    {
		if (world.getBlockId(i+1,j,k)==Block.fence.blockID || world.getBlockId(i+1,j,k)==mod_ZombieCraft.debris1000Plate.blockID)
		{
			world.setBlockWithNotify(i+1,j,k,0);
			System.out.println("block removed");
		}
		if (world.getBlockId(i,j+1,k)==Block.fence.blockID || world.getBlockId(i,j+1,k)==mod_ZombieCraft.debris1000Plate.blockID)
		{
			world.setBlockWithNotify(i,j+1,k,0);
			System.out.println("block removed");
		}
		if (world.getBlockId(i,j,k+1)==Block.fence.blockID || world.getBlockId(i,j,k+1)==mod_ZombieCraft.debris1000Plate.blockID)
		{
			world.setBlockWithNotify(i,j,k+1,0);
			System.out.println("block removed");
		}
		if (world.getBlockId(i-1,j,k)==Block.fence.blockID || world.getBlockId(i-1,j,k)==mod_ZombieCraft.debris1000Plate.blockID)
		{
			world.setBlockWithNotify(i-1,j,k,0);
			System.out.println("block removed");
		}      
		if (world.getBlockId(i,j-1,k)==Block.fence.blockID || world.getBlockId(i,j-1,k)==mod_ZombieCraft.debris1000Plate.blockID)
		{
			world.setBlockWithNotify(i,j-1,k,0);
			System.out.println("block removed");
		}    
		if (world.getBlockId(i,j,k-1)==Block.fence.blockID || world.getBlockId(i,j,k-1)==mod_ZombieCraft.debris1000Plate.blockID)
		{
			world.setBlockWithNotify(i,j,k-1,0);
			System.out.println("block removed");
		}
		super.onBlockRemoval(world, i, j, k);
    }

    public boolean isOpaqueCube()
    {
        return false;
    }

    public boolean renderAsNormalBlock()
    {
        return false;
    }

    public int getRenderType()
    {
        return 11;
    	//return 0;
    }
    
    public boolean canCollideCheck(int i, boolean flag)
    {
        return isCollidable();
    }

    public boolean isCollidable()
    {
        return true;//!mod_EntAI.doorNoClip;
    }
}
