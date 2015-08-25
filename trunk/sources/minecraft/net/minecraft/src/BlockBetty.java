package net.minecraft.src;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

import java.util.*;

public class BlockBetty extends Block
{

    protected BlockBetty(int i, int j)
    {
        super(i, Material.plants);
        blockIndexInTexture = j;
        setTickOnLoad(true);
        float f = 0.2F;
        setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, f * 3F, 0.5F + f);
    }

    public boolean canPlaceBlockAt(World world, int i, int j, int k)
    {		
		if (!world.isAirBlock(i,j-1,k) && world.getBlockId(i,j-1,k)!=107)
		{
			return true;
		} else {
			return false;
		}
    }
	
	public void onNeighborBlockChange(World world, int i, int j, int k, int l)
    {	
		super.onNeighborBlockChange(world, i, j, k, l);        
		canBlockStay(world, i, j, k);
    }
	
	public boolean canBlockStay(World world, int i, int j, int k)
    {
		if (!world.isAirBlock(i,j-1,k) && world.getBlockId(i,j-1,k)!=107)
		{
            return true;
		} else {
			dropBlockAsItem(world, i, j, k, world.getBlockMetadata(i, j, k));
			world.setBlockWithNotify(i, j, k, 0);
			return false;
		}
	}

    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int i, int j, int k)
    {
        return null;
    }
	
	public void onEntityCollidedWithBlock(World world, int i, int j, int k3, Entity entity2)
    {
		//System.out.println(entity2);
		if (entity2 instanceof EntityZombie || entity2 instanceof EntityHellSheep) {
			float explosionSize = 3.0F;
			int k = MathHelper.floor_double(i - (double)explosionSize - 1.0D);
			int i1 = MathHelper.floor_double(i + (double)explosionSize + 1.0D);
			int k1 = MathHelper.floor_double(j - (double)explosionSize - 1.0D);
			int l1 = MathHelper.floor_double(j + (double)explosionSize + 1.0D);
			int i2 = MathHelper.floor_double(k3 - (double)explosionSize - 1.0D);
			int j2 = MathHelper.floor_double(k3 + (double)explosionSize + 1.0D);
			List list = world.getEntitiesWithinAABBExcludingEntity(null, AxisAlignedBB.getBoundingBoxFromPool(k, k1, i2, i1, l1, j2));
			Vec3D vec3d = Vec3D.createVector(i, j, k3);
			for(int k2 = 0; k2 < list.size(); k2++)
			{
				Entity entity = (Entity)list.get(k2);
				double d4 = entity.getDistance(i, j, k3) / (double)explosionSize;
				if(d4 <= 1.0D)
				{
					if (entity instanceof EntityZombie || entity instanceof EntityHellSheep) {
						double d6 = entity.posX - i;
						double d8 = entity.posY - j;
						double d10 = entity.posZ - k3;
						double d11 = MathHelper.sqrt_double(d6 * d6 + d8 * d8 + d10 * d10);
						d6 /= d11;
						d8 /= d11;
						d10 /= d11;
						double d12 = world.func_675_a(vec3d, entity.boundingBox);
						double d13 = (1.0D - d4) * d12;
						entity.attackEntityFrom(null, 50);
						for(int k5 = 0; k5 < 20; k5++)
							{
								double d1 = (double)entity.posX + 0.5D + ((double)world.rand.nextFloat() - 0.5D) * 2D;
								double d3 = (double)entity.posY + 0.5D + ((double)world.rand.nextFloat() - 0.5D) * 2D;
								double d5 = (double)entity.posZ + 0.5D + ((double)world.rand.nextFloat() - 0.5D) * 2D;
								world.spawnParticle("smoke", d1, d3, d5, 0.0D, 0.0D, 0.0D);
								world.spawnParticle("flame", d1, d3, d5, 0.0D, 0.0D, 0.0D);
						}
						world.playSoundEffect(i, j, k3, "random.explode", 4F, (1.0F + (world.rand.nextFloat() - world.rand.nextFloat()) * 0.2F) * 0.7F);        
						double d14 = d13;
						entity.motionX += d6 * d14;
						entity.motionY += d8 * d14;
						entity.motionZ += d10 * d14;
					}
				}
			}
			world.setBlockWithNotify(i, j, k3, 0);
		}
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
        return 1;
    }
}
