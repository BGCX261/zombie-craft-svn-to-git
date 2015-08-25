package net.minecraft.src;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

import java.util.Random;

public class BlockRadio extends Block
{

    public BlockRadio(int i)
    {
        super(i, 30, Material.rock);
        setBlockBounds(0.25F, 0.0F, 0.10F, 0.75F, 1.00F, 0.90F);
        setLightOpacity(255);
    }

    public int getBlockTextureFromSideAndMetadata(int i, int j)
    {
        if (j == 1 || j - 6 == 1) { setBlockBounds(0.10F, 0.0F, 0.25F, 0.90F, 1.00F, 0.75F); } 
		else if (j == 2 || j - 6 == 2) { setBlockBounds(0.25F, 0.0F, 0.10F, 0.75F, 1.00F, 0.90F); } 
		else if (j == 3 || j - 6 == 3) { setBlockBounds(0.10F, 0.0F, 0.25F, 0.90F, 1.00F, 0.75F); } 
		else if (j == 4 || j - 6 == 4) { setBlockBounds(0.25F, 0.0F, 0.10F, 0.75F, 1.00F, 0.90F); }
		
        //temp till i setup the other texture sides properly
        //return 31;
		//return i > 1 ? 31 : 32;
		return (i == 4 || i == 5) ? 30 : 40;
    }

    public int getBlockTextureFromSide(int i)
    {
        return getBlockTextureFromSideAndMetadata(i, 0);
    }

    public boolean isOpaqueCube()
    {
        return false;
    }

    public void onBlockAdded(World world, int i, int j, int k)
    {

    }
	
	public void onBlockClicked(World world, int i, int j, int k, EntityPlayer entityplayer)
    {System.out.println("!!!");
	world.playRecord("radio", (int)entityplayer.posX, (int)entityplayer.posY, (int)entityplayer.posZ);	
		if (world.getBlockMetadata(i, j, k) < 5)
		{
			world.setBlockMetadata(i, j, k, world.getBlockMetadata(i, j, k)+6);
			
		}
    }

    public int idDropped(int i, Random random)
    {
        return blockID;
    }

    public boolean renderAsNormalBlock()
    {
        return false;
    }

    public boolean shouldSideBeRendered(IBlockAccess iblockaccess, int i, int j, int k, int l)
    {
        if(l == 1)
        {
            return true;
        }
        if(!super.shouldSideBeRendered(iblockaccess, i, j, k, l))
        {
            return false;
        }
        if(l == 0)
        {
            return true;
        } else
        {
            return iblockaccess.getBlockId(i, j, k) != blockID;
        }
    }
	
	public void onBlockPlacedBy(World world, int i, int j, int k, EntityLiving entityliving)
    {
		int l = MathHelper.floor_double((double)((entityliving.rotationYaw * 4F) / 360F) + 0.5D) & 3;
		if(l == 0)
		{
			world.setBlockMetadataWithNotify(i, j, k, 1);
		}
		if(l == 1)
		{
			world.setBlockMetadataWithNotify(i, j, k, 2);
		}
		if(l == 2)
		{
			world.setBlockMetadataWithNotify(i, j, k, 3);
		}
		if(l == 3)
		{
			world.setBlockMetadataWithNotify(i, j, k, 4);
		}
	}

}
