package net.minecraft.src;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

import java.util.Random;

public class BlockBarricade extends BlockDoor
{
	
	public int state = 5;
	
	//public int stateToBlockID[] = {mod_ZombieCraft.barricadeS0.blockID,mod_ZombieCraft.barricadeS1.blockID,mod_ZombieCraft.barricadeS2.blockID,mod_ZombieCraft.barricadeS3.blockID,mod_ZombieCraft.barricadeS4.blockID,mod_ZombieCraft.barricadeS5.blockID};
	public int stateToBlockID[] = {117,118,119,120,121,64};
	
    protected BlockBarricade(int i, Material material)
    {
        super(i, material);
        //blockIndexInTexture = 97;
        updateTexture();
        if(material == Material.iron)
        {
            blockIndexInTexture++;
        }
        float f = 0.5F;
        float f1 = 1.0F;
        setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, f1, 0.5F + f);
        
    }
    
    protected BlockBarricade(int i, Material material, int stateTemp)
    {
        super(i, material);
        state = stateTemp;
        //blockIndexInTexture = 97;
        updateTexture();
        
        float f = 0.5F;
        float f1 = 1.0F;
        
        setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, f1, 0.5F + f);
        
    }
    
    public void updateTexture() {
    	if(state == 5)
		{
			blockIndexInTexture = 97;
		}
		else if(state == 4)
		{
			blockIndexInTexture = 98;
		}
		else if(state == 3)
		{
			blockIndexInTexture = 150;				
		}
		else if(state == 2)
		{
			blockIndexInTexture = 151;
		}
		else if(state == 1)
		{
			blockIndexInTexture = 152;
		}
		else if(state == 0)
		{
			blockIndexInTexture = 153;
		}
    }

    public int getBlockTextureFromSideAndMetadata(int i, int j)
    {
        if(i == 0 || i == 1)
        {
            return blockIndexInTexture;
        }
        int k = func_312_c(j);
        if((k == 0 || k == 2) ^ (i <= 3))
        {
            return blockIndexInTexture;
        }
        int l = k / 2 + (i & 1 ^ k);
        l += (j & 4) / 4;
        int i1 = blockIndexInTexture - (j & 8) * 2;
        if((l & 1) != 0)
        {
            i1 = -i1;
        }
        return i1;
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
        return 7;
    }

    public AxisAlignedBB getSelectedBoundingBoxFromPool(World world, int i, int j, int k)
    {
    	//System.out.println("ooo");
    	//if (true) return null;
        setBlockBoundsBasedOnState(world, i, j, k);
        //setBlockBounds(0.0F, 0.0F, 0.0F, 0.1F, 0.1F, 0.1F);
        return super.getSelectedBoundingBoxFromPool(world, i, j, k);
    }

    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int i, int j, int k)
    {
    	//if ((world.getBlockMetadata(i, j, k) & 8) != 0 || (state == 0 && (mod_EntAI.playerRef == null || (mod_EntAI.playerRef.barrierX != i && mod_EntAI.playerRef.barrierY != j && mod_EntAI.playerRef.barrierZ != k)))) return null;
    	if ((world.getBlockMetadata(i, j, k) & 8) != 0 || (state == 0) || mod_EntAI.doorNoClip) return null;
        setBlockBoundsBasedOnState(world, i, j, k);
    	//setBlockBounds(0.0F, 0.0F, 0.0F, 0.1F, 0.1F, 0.1F);
        return super.getCollisionBoundingBoxFromPool(world, i, j, k);
    }

    public void setBlockBoundsBasedOnState(IBlockAccess iblockaccess, int i, int j, int k)
    {
        func_313_b(func_312_c(iblockaccess.getBlockMetadata(i, j, k)));
    }

    public void func_313_b(int i)
    {
        float f = 0.1875F;
        float e = (1F-f)/2F;
        setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
        
        /*if (i == 0) {
        	setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 1.0F);        	
        }*/
        
        if(i == 0)
        {
            setBlockBounds(0.0F, 0.0F, e, 1.0F, 1.0F, e+f);
        }
        if(i == 1)
        {
            setBlockBounds(1.0F - (e+f), 0.0F, 0.0F, e+f, 1.0F, 1.0F);
        }
        if(i == 2)
        {
            setBlockBounds(0.0F, 0.0F, 1.0F - (e+f), 1.0F, 1.0F, e+f);
        }
        if(i == 3)
        {
            setBlockBounds(e, 0.0F, 0.0F, e+f, 1.0F, 1.0F);
        }
    }

    public void onBlockClicked(World world, int i, int j, int k, EntityPlayer entityplayer)
    {
		tryRepairDoor(world, i, j, k, entityplayer);
		//tryDamageDoor(world, i, j, k, entityplayer);
    }
    
    public boolean damageDoor(World world, int i, int j, int k, EntityPlayer entityplayer, int meta) {
    	
    	if(world.getBlockId(i, j + 1, k) == blockID)
        {
    		//System.out.print("updating: ");
    		//System.out.println(state-1);
    		if (state > 0) {
    			world.setBlock(i, j + 1, k, stateToBlockID[state-1]);
    		} else {
    			//world.setBlock(i, j + 1, k, stateToBlockID[5]);
				// dont repair when broken
    		}
            //world.setBlockMetadataWithNotify(i, j + 1, k, (meta ^ 4) + 8);
        }
    	if (state > 0) {
			world.setBlock(i, j, k, stateToBlockID[state-1]);
			return true;
		} else {
			//world.setBlock(i, j, k, stateToBlockID[5]);
			// dont repair when broken
			return false;
		}
        //world.setBlockMetadataWithNotify(i, j, k, (meta ^ 4) + stateToBit(state));
        //world.markBlocksDirty(i, j - 1, k, i, j, k);
    }
    
    public boolean upgradeDoor(World world, int i, int j, int k, EntityPlayer entityplayer, int meta) {
    	
    	if(world.getBlockId(i, j + 1, k) == blockID)
        {
    		//System.out.print("updating: ");
    		//System.out.println(state+1);
    		if (state < 5) {
    			world.setBlock(i, j + 1, k, stateToBlockID[state+1]);
    		}
            //world.setBlockMetadataWithNotify(i, j + 1, k, (meta ^ 4) + 8);
        }
    	if (state < 5) {
			world.setBlock(i, j, k, stateToBlockID[state+1]);
			return true;
		} else {
			return false;
		}
        //world.setBlockMetadataWithNotify(i, j, k, (meta ^ 4) + stateToBit(state));
        //world.markBlocksDirty(i, j - 1, k, i, j, k);
    }
    
    public void updateDoors(World world, int i, int j, int k, EntityPlayer entityplayer, boolean repair) {
    	
    	
    	
    	int ii = i-1;
    	int jj = j;
    	int kk = k;
    	
    	int l = world.getBlockMetadata(ii, jj, kk);
    	if(isBarricade(world, ii, jj, kk)) {
    		if (repair) {
    			upgradeDoor(world, ii, jj, kk, entityplayer, (l));
    		} else {
    			damageDoor(world, ii, jj, kk, entityplayer, (l));
    		}
    		updateDoorMeta(world, ii, jj, kk, entityplayer, (l));
    	}
    	ii+=2;
    	l = world.getBlockMetadata(ii, jj, kk);
    	if(isBarricade(world, ii, jj, kk)) {
    		if (repair) {
    			upgradeDoor(world, ii, jj, kk, entityplayer, (l));
    		} else {
    			damageDoor(world, ii, jj, kk, entityplayer, (l));
    		}
    		updateDoorMeta(world, ii, jj, kk, entityplayer, (l));
    	}
    	ii-=1;
    	kk+=1;
    	l = world.getBlockMetadata(ii, jj, kk);
    	if(isBarricade(world, ii, jj, kk)) {
    		if (repair) {
    			upgradeDoor(world, ii, jj, kk, entityplayer, (l));
    		} else {
    			damageDoor(world, ii, jj, kk, entityplayer, (l));
    		}
    		updateDoorMeta(world, ii, jj, kk, entityplayer, (l));
    	}
    	kk-=2;
    	l = world.getBlockMetadata(ii, jj, kk);
    	if(isBarricade(world, ii, jj, kk)) {
    		if (repair) {
    			upgradeDoor(world, ii, jj, kk, entityplayer, (l));
    		} else {
    			damageDoor(world, ii, jj, kk, entityplayer, (l));
    		}
    		updateDoorMeta(world, ii, jj, kk, entityplayer, (l));
    	}
    	kk+=2;
    	l = world.getBlockMetadata(ii, jj, kk);
    	if(isBarricade(world, ii, jj, kk)) {
    		if (repair) {
    			upgradeDoor(world, ii, jj, kk, entityplayer, (l));
    		} else {
    			damageDoor(world, ii, jj, kk, entityplayer, (l));
    		}
    		updateDoorMeta(world, ii, jj, kk, entityplayer, (l));
    	}
    	
    }
    
    public void updateDoorMeta(World world, int i, int j, int k, EntityPlayer entityplayer, int l) {
    	//int l = world.getBlockMetadata(i, j, k);
    	if(isBarricade(world, i, j + 1, k))
        {
            world.setBlockMetadata(i, j + 1, k, (l) + 8);
        }
        world.setBlockMetadata(i, j, k, (l));
        //world.markBlocksDirty(i, j - 1, k, i, j, k);
    }
    
    public boolean isBarricade(World world, int i, int j, int k) {
    	if (world.getBlockId(i, j, k) >= 117 && world.getBlockId(i, j, k) <= 121 || world.getBlockId(i, j, k) == 64 || world.getBlockId(i, j, k) == 71) {
    		return true;
    	}
    	return false;
    }
    
    public boolean tryRepairDoor(World world, int i, int j, int k, EntityPlayer entityplayer) {
    	int l = world.getBlockMetadata(i, j, k);
    
	    if((l & 8) != 0)
	    {
	        if(world.getBlockId(i, j - 1, k) == blockID)
	        {
	        	tryRepairDoor(world, i, j - 1, k, entityplayer);
	        }
	        return true;
	    }
		
		boolean success = upgradeDoor(world, i, j, k, entityplayer, l);
		
		if (success) {
    		updateDoors(world, i, j, k, entityplayer, true);
    	}
		
		updateDoorMeta(world, i, j, k, entityplayer, l);
		
		if(isBarricade(world, i, j + 1, k))
	    {
	        world.setBlockMetadataWithNotify(i, j + 1, k, (l) + 8);
	    }
	    world.setBlockMetadataWithNotify(i, j, k, (l));
	    world.markBlocksDirty(i, j - 1, k, i, j, k);
		
	    /*System.out.print("Setting meta: ");
	    System.out.println((l ^ 4) + stateToBit(state));*/
		
	    
		return success;
	}

    public boolean tryDamageDoor(World world, int i, int j, int k, EntityPlayer entityplayer) {
    	return blockActivated(world, i, j, k, entityplayer);
    }
	
	
    public void onEntityCollidedWithBlock(World world, int i, int j, int k, Entity entity)
    {
    	
    	int oldid = world.getBlockId(i, j - 1, k);
		if ((oldid != 117 && oldid != 1) && entity instanceof EntityMobs && !(entity instanceof EntityFriend) && ((EntityLiving)entity).health > 0)
		{
			//System.out.println(oldid);
			EntityMobs ent = (EntityMobs)entity;
			ent.curBlockDamage++;
			ent.isJumping = false;
			ent.isBreaking = true;
			ent.motionY = -0.4F;
			//System.out.println(ent.curBlockDamage);
			
			if (ent.curBlockDamage % 110 == 0) {
				ent.swingArm = true;
			}
			
			if (ent.curBlockDamage % 130 == 0)
			{
				ent.curBlockDamage = 0;
				
				tryDamageDoor(world,i,j,k,ModLoader.getMinecraftInstance().thePlayer);
				((EntityMobs)entity).noMoveTicks = 0;
				int newid = world.getBlockId(i, j - 1, k);
				
				if (newid != oldid) {
		            if(newid == 117) {
		            	world.playSoundAtEntity(entity, "sdkzc.barricadecollapse", 1.0F, 1.0F / entity.rand.nextFloat() * 0.1F + 0.95F);
		            } else {
		            	world.playSoundAtEntity(entity, "sdkzc.woodbreak", 1.0F, 1.0F / entity.rand.nextFloat() * 0.1F + 0.95F);
		            }
				}
				//if (success) {
					
					//world.playSoundAtEntity(entity, "mob.sheep", 0.5F, 1.0F / entity.rand.nextFloat() * 0.1F + 0.95F);
					
					//System.out.println(success);
				//}
			}
		}
		
		if (entity instanceof EntityPlayer) {
			if (!world.devMode) {
				if (((EntityPlayer)entity).pushDelay == 0 && oldid == 117) {
					entity.motionX = entity.motionX*-5F;
					entity.motionZ = entity.motionZ*-5F;
					((EntityPlayer)entity).pushDelay = 5;
				}
			}
			//entity.prevPosX = entity.posX;
			//entity.prevPosZ = entity.posZ;
			//entity.posZ += entity.motionZ*-5F;
			//entity.motionX = 0;
			//entity.motionZ = 0;
			//System.out.println("player collide");
		}
    }

    public boolean blockActivated(World world, int i, int j, int k, EntityPlayer entityplayer)
    {
    	
    	
    	
    	int l = world.getBlockMetadata(i, j, k);
    	
    	
    	//state++;
    	
    	//
    	
    	//if (state > 128) { state = 0; }
    	
    	//world.setBlockMetadataWithNotify(i, j, k, i);
    	
    	//int state = getState(l);
    	//state--;
    	
    	//if (state < 0) { state = 5; }
    	//state = 1;
    	/*
    	//state 5
    	state = l + 16;
    	
    	//state 4
    	state = l + 32;
    	
    	//state 3
    	state = l + 64;
    	
    	//state 2
    	state = l + 128;
    	
    	//state 1
    	state = l + 256;
    	*/
    	//state 0
    	//not set
    	
    	
    	
    	
    	//update top and bottom block part of door, as well as notify bottom if top is clicked
    	if((l & 8) != 0)
        {
            if(world.getBlockId(i, j - 1, k) == blockID)
            {
                blockActivated(world, i, j - 1, k, entityplayer);
            }
            return true;
        }
    	
    	boolean success = damageDoor(world, i, j, k, entityplayer, l);
    	if (success) {
    		updateDoors(world, i, j, k, entityplayer, false);
    		
    	}
    	
    	updateDoorMeta(world, i, j, k, entityplayer, l);
    	
    	if(isBarricade(world, i, j + 1, k))
        {
            world.setBlockMetadataWithNotify(i, j + 1, k, (l) + 8);
        }
        world.setBlockMetadataWithNotify(i, j, k, (l));
        world.markBlocksDirty(i, j - 1, k, i, j, k);
    	
        /*System.out.print("Setting meta: ");
        System.out.println((l ^ 4) + stateToBit(state));*/
    	
        int hm = world.getBlockMetadata(i, j, k);
        
        
        
    	return success;
    	
        /*if(blockMaterial == Material.iron)
        {
            return true;
        }
        int l = world.getBlockMetadata(i, j, k);
        if((l & 8) != 0)
        {
            if(world.getBlockId(i, j - 1, k) == blockID)
            {
                blockActivated(world, i, j - 1, k, entityplayer);
            }
            return true;
        }
        if(world.getBlockId(i, j + 1, k) == blockID)
        {
            world.setBlockMetadataWithNotify(i, j + 1, k, (l ^ 4) + 8);
        }
        world.setBlockMetadataWithNotify(i, j, k, l ^ 4);
        world.markBlocksDirty(i, j - 1, k, i, j, k);
        if(Math.random() < 0.5D)
        {
            world.playSoundEffect((double)i + 0.5D, (double)j + 0.5D, (double)k + 0.5D, "random.door_open", 1.0F, world.rand.nextFloat() * 0.1F + 0.9F);
        } else
        {
            world.playSoundEffect((double)i + 0.5D, (double)j + 0.5D, (double)k + 0.5D, "random.door_close", 1.0F, world.rand.nextFloat() * 0.1F + 0.9F);
        }
        return true;*/
    }

    public void func_311_a(World world, int i, int j, int k, boolean flag)
    {
        int l = world.getBlockMetadata(i, j, k);
        if((l & 8) != 0)
        {
            if(world.getBlockId(i, j - 1, k) == blockID)
            {
                func_311_a(world, i, j - 1, k, flag);
            }
            return;
        }
        boolean flag1 = (world.getBlockMetadata(i, j, k) & 4) > 0;
        if(flag1 == flag)
        {
            return;
        }
        if(world.getBlockId(i, j + 1, k) == blockID)
        {
            world.setBlockMetadataWithNotify(i, j + 1, k, (l ^ 4) + 8);
        }
        world.setBlockMetadataWithNotify(i, j, k, l ^ 4);
        world.markBlocksDirty(i, j - 1, k, i, j, k);
        if(Math.random() < 0.5D)
        {
            world.playSoundEffect((double)i + 0.5D, (double)j + 0.5D, (double)k + 0.5D, "random.door_open", 1.0F, world.rand.nextFloat() * 0.1F + 0.9F);
        } else
        {
            world.playSoundEffect((double)i + 0.5D, (double)j + 0.5D, (double)k + 0.5D, "random.door_close", 1.0F, world.rand.nextFloat() * 0.1F + 0.9F);
        }
    }

    public void onNeighborBlockChange(World world, int i, int j, int k, int l)
    {
        int i1 = world.getBlockMetadata(i, j, k);
        if((i1 & 8) != 0)
        {
            if(world.getBlockId(i, j - 1, k) != blockID)
            {
                world.setBlockWithNotify(i, j, k, 0);
            }
            if(l > 0 && Block.blocksList[l].canProvidePower())
            {
                onNeighborBlockChange(world, i, j - 1, k, l);
            }
        } else
        {
            boolean flag = false;
            if(world.getBlockId(i, j + 1, k) != blockID)
            {
                world.setBlockWithNotify(i, j, k, 0);
                flag = true;
            }
            if(!world.isBlockOpaqueCube(i, j - 1, k))
            {
                world.setBlockWithNotify(i, j, k, 0);
                flag = true;
                if(world.getBlockId(i, j + 1, k) == blockID)
                {
                    world.setBlockWithNotify(i, j + 1, k, 0);
                }
            }
            if(flag)
            {
                dropBlockAsItem(world, i, j, k, i1);
            } else
            if(l > 0 && Block.blocksList[l].canProvidePower())
            {
                boolean flag1 = world.isBlockIndirectlyGettingPowered(i, j, k) || world.isBlockIndirectlyGettingPowered(i, j + 1, k);
                func_311_a(world, i, j, k, flag1);
            }
        }
    }

    public int idDropped(int i, Random random)
    {
        if((i & 8) != 0)
        {
            return 0;
        }
        if(blockMaterial == Material.iron)
        {
            return Item.doorSteel.shiftedIndex;
        } else
        {
            return Item.doorWood.shiftedIndex;
        }
    }

    public MovingObjectPosition collisionRayTrace(World world, int i, int j, int k, Vec3D vec3d, Vec3D vec3d1)
    {
    	//called when block is selected
    	//System.out.println("what?!");
        setBlockBoundsBasedOnState(world, i, j, k);
        return super.collisionRayTrace(world, i, j, k, vec3d, vec3d1);
    }

    public int func_312_c(int i)
    {
        if((i & 4) == 0)
        {
            return i - 1 & 3;
        } else
        {
            return i & 3;
        }
    }

    public boolean canPlaceBlockAt(World world, int i, int j, int k)
    {
        if(j >= 127)
        {
            return false;
        } else
        {
            return world.isBlockOpaqueCube(i, j - 1, k) && super.canPlaceBlockAt(world, i, j, k) && super.canPlaceBlockAt(world, i, j + 1, k);
        }
    }
}
