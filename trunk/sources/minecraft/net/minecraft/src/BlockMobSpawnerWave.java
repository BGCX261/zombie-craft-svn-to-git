package net.minecraft.src;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

import java.util.Random;

public class BlockMobSpawnerWave extends BlockContainer
{

    protected BlockMobSpawnerWave(int i, int j)
    {
        super(i, j, Material.rock);
    }

    protected TileEntity getBlockEntity()
    {
        return new TileEntityMobSpawnerWave();
    }

    public int idDropped(int i, Random random)
    {
        return Block.mobSpawner.blockID;
    }

    public int quantityDropped(Random random)
    {
        return 1;
    }

    public boolean isOpaqueCube()
    {
        return false;
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
	
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int i, int j, int k)
    {
        return null;
    }

}
