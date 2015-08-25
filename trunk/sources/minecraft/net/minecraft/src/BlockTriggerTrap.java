package net.minecraft.src;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

import java.util.List;
import java.util.Random;

public class BlockTriggerTrap extends Block
{

    protected BlockTriggerTrap(int i, int j, EnumMobType enummobtype, Item item1, Block block1, int tmpCost, int tmpCount, boolean power)
    {
        super(i, j, Material.plants);
        triggerMobType = enummobtype;
        setTickOnLoad(true);
		itemType = item1;		
		blockType = block1;
		cost = tmpCost;
		count = tmpCount;
		powerNeeded = power;
		
    }

    public int tickRate()
    {
        return 5;
    }

    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int i, int j, int k)
    {
        return null;
    }

    public boolean isOpaqueCube()
    {
        return false;
    }

    public boolean canPlaceBlockAt(World world, int i, int j, int k)
    {
        return true;//world.isBlockOpaqueCube(i, j - 1, k);
    }

    public void onBlockAdded(World world, int i, int j, int k)
    {
    }

    public void onNeighborBlockChange(World world, int i, int j, int k, int l)
    {
        boolean flag = false;
        /*if(!world.isBlockOpaqueCube(i, j - 1, k))
        {
            flag = true;
        }*/
        if(flag)
        {
            dropBlockAsItem(world, i, j, k, world.getBlockMetadata(i, j, k));
            world.setBlockWithNotify(i, j, k, 0);
        }
    }
    
    public void randomDisplayTick(World world, int i, int j, int k, Random random)
    {
    	updateTick(world,i,j,k,random);
    	if (cost == 0) {
			 if(world.getBlockMetadata(i, j, k) == 1)
			 {
				 if (random.nextInt(100) == 0) {
					 world.playSoundEffect(i, j, k, "sdkzc.trap_sound", 0.4F, 1.0F);
				 }
			 }
    	}
    	//System.out.println("!?");
    	
    }

    public void updateTick(World world, int i, int j, int k, Random random)
    {
		 if(world.multiplayerWorld)
		 {
			 return;
         }
		 
		 if (cost == 0) {
			 
			 //System.out.println(i + " ?? " + k);
			 if(world.getBlockMetadata(i, j, k) == 1)
			 {
				 //System.out.println("");
				 //System.out.println(i + " - " + k);
				 
				 
				 
				 
				 
				 
				 //blockIndexInTexture = random.nextInt(6);
				 //getBlockTextureFromSide(1);
				 //world.markBlocksDirty(i, j, k, i, j, k);
				 
				 //schedule tick update
				 //world.scheduledUpdatesAreImmediate = true;
				 //update stuff?
				 setBlockBoundsBasedOnState(world,i,j,k);
				 if (world.trapTimer < System.currentTimeMillis()) {
					 //System.out.println("resetting trap block");
					 world.setBlockMetadataWithNotify(i, j, k, 0);
				 } else {
					 
					 world.setBlockMetadataWithNotify(i,j,k,1);
				 }

				 world.func_22136_c(i,j,k,blockID,tickRate());
				 world.notifyBlocksOfNeighborChange(i,j,k,blockID);
				 //world.scheduledUpdatesAreImmediate = false;
	            //return;
			 } else {
				 //update stuff?
				 
				 world.setBlockMetadataWithNotify(i,j,k,0);
				 //world.func_22136_c(i,j,k,blockID,tickRate());
				 //world.notifyBlocksOfNeighborChange(i,j,k,blockID);
				 
				 setBlockBoundsBasedOnState(world,i,j,k);
				 
				 //System.out.println(i + " 00 " + k);
				 //System.out.println("set texture to 48");
				 //blockIndexInTexture = 48;
			 }
		 } else {
			 if (world.trapTimer < System.currentTimeMillis()) {
				 if(world.getBlockMetadata(i, j, k) == 1)
				 {
					 world.setBlockMetadataWithNotify(i,j,k,0);
					 if (world.getBlockId(i,j+1,k) == 69) {
						 ((BlockLever)Block.lever).deactivate(world, i, j+1, k);
					 }
				 }
			 }
		 }
		 setStateIfMobInteractsWithPlate(world, i, j, k);
		 return;	
    }

    public void onEntityCollidedWithBlock(World world, int i, int j, int k, Entity entity)
    {
        if(world.multiplayerWorld)
        {
            return;
        }
        
        
        setStateIfMobInteractsWithPlate(world, i, j, k);
        return;
        //}
    }
    
    public int getBlockTextureFromSideAndMetadata(int i, int j)
    {
    	try {
			if (j == 1 && cost == 0) {
				return stateToBlockID[ModLoader.getMinecraftInstance().theWorld.rand.nextInt(4)];
			} else {
				if (!ModLoader.getMinecraftInstance().theWorld.devMode){
					return blockIndexInTexture;
				} else {
					return 191;
				}
			}
		} catch (Exception ex) {}
		return blockIndexInTexture;
    }
	
    /*public int getBlockTextureFromSide(int i)
    {
		
    }*/

    private void setStateIfMobInteractsWithPlate(World world, int i, int j, int k)
    {
        boolean flag = world.getBlockMetadata(i, j, k) == 1;
        boolean flag1 = false;
        float f = 0.125F;
        List list = null;
        if (cost > 0) {
        	list = world.getEntitiesWithinAABB(EntityPlayer.class, AxisAlignedBB.getBoundingBoxFromPool((float)i + f, j, (float)k + f, (float)(i + 1) - f, (double)j + 0.25D, (float)(k + 1) - f));
        } else {
        	list = world.getEntitiesWithinAABB(EntityLiving.class, AxisAlignedBB.getBoundingBoxFromPool((float)i + f, j, (float)k + f, (float)(i + 1) - f, (double)j + 0.25D, (float)(k + 1) - f));
        }
        if(list.size() > 0)
        {
            flag1 = true;
        }
		if(flag1)// && !flag)
        {
			//If it's a trap trigger block
			if (cost > 0) {
				if (powerNeeded && !world.field_22145_q.getPower())
				{
					ModLoader.getMinecraftInstance().theWorld.showNoPower = true;
				} else {
					ModLoader.getMinecraftInstance().theWorld.showNoPower = false;
		            //world.setBlockMetadataWithNotify(i, j, k, 1);
		            world.notifyBlocksOfNeighborChange(i, j, k, blockID);
		            world.notifyBlocksOfNeighborChange(i, j - 1, k, blockID);
		            world.markBlocksDirty(i, j, k, i, j, k);
		            //world.playSoundEffect((double)i + 0.5D, (double)j + 0.10000000000000001D, (double)k + 0.5D, "random.click", 0.3F, 0.6F);
					ModLoader.getMinecraftInstance().theWorld.weapCost = cost;			
					ModLoader.getMinecraftInstance().theWorld.weapCount = count;
					ModLoader.getMinecraftInstance().ingameGUI.triggerX = i;
					ModLoader.getMinecraftInstance().ingameGUI.triggerY = j;
					ModLoader.getMinecraftInstance().ingameGUI.triggerZ = k;	
					
					ModLoader.getMinecraftInstance().theWorld.showTrapUse = true;
					//System.out.println("2");
				}
			//If it's a trap kill block
			} else {
				Entity ent = null;
				if (world.trapTimer > System.currentTimeMillis() && world.getBlockMetadata(i,j,k) == 1) {
					
					for (int ii = 0; ii < list.size(); ii++) {
						
						ent = (Entity)list.get(ii);
						if (ent instanceof EntityLiving) {
							if (((EntityLiving)ent).attackEntityFrom(null, 5)) {
								world.playSoundEffect(ent.posX, ent.posY, ent.posZ, "sdkzc.trap_shock", 0.4F, 1.0F);
							}
						}
					}
				}
			}
				
			
		}	
        if(!flag1)// && flag)
        {
        	if (cost > 0) {
	            //world.setBlockMetadataWithNotify(i, j, k, 0);
	            world.notifyBlocksOfNeighborChange(i, j, k, blockID);
	            world.notifyBlocksOfNeighborChange(i, j - 1, k, blockID);
	            world.markBlocksDirty(i, j, k, i, j, k);
				ModLoader.getMinecraftInstance().theWorld.showTrapUse = false;
        	}
        }
        if(flag1)
        {
            world.func_22136_c(i, j, k, blockID,0);
        }
    }

    public void onBlockRemoval(World world, int i, int j, int k)
    {
        int l = world.getBlockMetadata(i, j, k);
        if(l > 0)
        {
            world.notifyBlocksOfNeighborChange(i, j, k, blockID);
            world.notifyBlocksOfNeighborChange(i, j - 1, k, blockID);
        }
        super.onBlockRemoval(world, i, j, k);
    }

    public void setBlockBoundsBasedOnState(IBlockAccess iblockaccess, int i, int j, int k)
    {
        boolean flag = iblockaccess.getBlockMetadata(i, j, k) == 1;
        float f = 0.0625F;
        float thickness = 0.05F;
        if(flag)
        {
        	//updateTick(ModLoader.getMinecraftInstance().theWorld,i,j,k,ModLoader.getMinecraftInstance().theWorld.rand);
        	if (ModLoader.getMinecraftInstance().theWorld.rand.nextInt(2) == 1) {
        	//if (ModLoader.getMinecraftInstance().theWorld.trapTimer % 2 == 0) {
        		setBlockBounds(0.0F, 0.0F, 0.5F-(thickness/2), 1.0F, 1.0F, 0.5F+(thickness/2));
        	} else {
        		setBlockBounds(0.5F-(thickness/2), 0.0F, 0.0F, 0.5F+(thickness/2), 1.0F, 1.0F);
        	}
        } else
        {
        	if (ModLoader.getMinecraftInstance().theWorld.devMode) {
        		setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.00F, 1.0F );
        	} else {
        		setBlockBounds(0.0F, 0.0F, 0.0F, 0.0F, 0.00F, 0.0F );
        	}
        }
    }

    public void func_237_e()
    {
        /*float f = 0.5F;
        float f1 = 0.125F;
        float f2 = 0.5F;
        setBlockBounds(0.5F - f, 0.5F - f1, 0.5F - f2, 0.5F + f, 0.5F + f1, 0.5F + f2);*/
    }

    
    public int stateToBlockID[] = {138,154,170,186};
    
    private EnumMobType triggerMobType;
	private boolean showBuyMenu;
	public Item itemType;
	public Block blockType;
	public int cost;
	public int count;
	private boolean powerNeeded;
}
