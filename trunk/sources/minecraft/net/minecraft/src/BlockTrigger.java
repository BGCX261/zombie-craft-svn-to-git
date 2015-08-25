package net.minecraft.src;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

import java.util.List;
import java.util.Random;

public class BlockTrigger extends Block
{

    protected BlockTrigger(int i, int j, EnumMobType enummobtype, Item item1, Block block1, int tmpCost, int tmpCount, boolean power)
    {
        super(i, j, Material.plants);
        triggerMobType = enummobtype;
        setTickOnLoad(true);
		itemType = item1;		
		blockType = block1;
		cost = tmpCost;
		count = tmpCount;
		debris = false;
		powerNeeded = power;
    }
    
    protected BlockTrigger(int i, int j, EnumMobType enummobtype, Item item1, Block block1, int tmpCost, int tmpCount)
    {
        super(i, j, Material.plants);
        triggerMobType = enummobtype;
        setTickOnLoad(true);
		itemType = item1;		
		blockType = block1;
		cost = tmpCost;
		count = tmpCount;
		debris = false;
		powerNeeded = false;
    }
 
	protected BlockTrigger(int i, int j, EnumMobType enummobtype, int tmpCost, boolean power)
    {
        super(i, j, Material.plants);
        triggerMobType = enummobtype;
        setTickOnLoad(true);
		cost = tmpCost;
		powerNeeded = power;
		debris = true;
	}
	
    public int tickRate()
    {
        return 20;
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
        return world.isBlockOpaqueCube(i, j - 1, k);
    }

    public void onBlockAdded(World world, int i, int j, int k)
    {
    }

    public void onNeighborBlockChange(World world, int i, int j, int k, int l)
    {
        boolean flag = false;
        if(!world.isBlockOpaqueCube(i, j - 1, k))
        {
            flag = true;
        }
        if(flag)
        {
            dropBlockAsItem(world, i, j, k, world.getBlockMetadata(i, j, k));
            world.setBlockWithNotify(i, j, k, 0);
        }
    }

    public void updateTick(World world, int i, int j, int k, Random random)
    {
		 if(world.multiplayerWorld)
        {
            return;
        }
        if(world.getBlockMetadata(i, j, k) == 0)
        {
            return;
        } else
        {
            setStateIfMobInteractsWithPlate(world, i, j, k);
            return;
        }		
    }

    public void onEntityCollidedWithBlock(World world, int i, int j, int k, Entity entity)
    {
        if(world.multiplayerWorld)
        {
            return;
        }
        if(world.getBlockMetadata(i, j, k) == 1)
        {
            return;
        } else
        {
            setStateIfMobInteractsWithPlate(world, i, j, k);
            return;
        }
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

    private void setStateIfMobInteractsWithPlate(World world, int i, int j, int k)
    {
        boolean flag = world.getBlockMetadata(i, j, k) == 1;
        boolean flag1 = false;
        float f = 0.125F;
        List list = null;
        list = world.getEntitiesWithinAABB(EntityPlayer.class, AxisAlignedBB.getBoundingBoxFromPool((float)i + f, j, (float)k + f, (float)(i + 1) - f, (double)j + 0.25D, (float)(k + 1) - f));
        if(list.size() > 0)
        {
            flag1 = true;
        }
		if(flag1)// && !flag)
        {
            world.setBlockMetadataWithNotify(i, j, k, 1);
            world.notifyBlocksOfNeighborChange(i, j, k, blockID);
            world.notifyBlocksOfNeighborChange(i, j - 1, k, blockID);
            world.markBlocksDirty(i, j, k, i, j, k);
            //world.playSoundEffect((double)i + 0.5D, (double)j + 0.10000000000000001D, (double)k + 0.5D, "random.click", 0.3F, 0.6F);
			if (powerNeeded && !world.field_22145_q.getPower())
			{
				ModLoader.getMinecraftInstance().theWorld.showNoPower = true;
			} else {
				ModLoader.getMinecraftInstance().theWorld.showNoPower = false;				
				if (debris) {
					ModLoader.getMinecraftInstance().theWorld.showBuyMenu2 = true;
					ModLoader.getMinecraftInstance().theWorld.weapCost = cost;
					ModLoader.getMinecraftInstance().theWorld.debrisX = i;
					ModLoader.getMinecraftInstance().theWorld.debrisY = j;
					ModLoader.getMinecraftInstance().theWorld.debrisZ = k;	
				} else {
					ModLoader.getMinecraftInstance().theWorld.weapCost = cost;			
					ModLoader.getMinecraftInstance().theWorld.weapCount = count;
					ModLoader.getMinecraftInstance().ingameGUI.triggerX = i;
					ModLoader.getMinecraftInstance().ingameGUI.triggerY = j;
					ModLoader.getMinecraftInstance().ingameGUI.triggerZ = k;
					
					if (itemType==null) {
					ModLoader.getMinecraftInstance().theWorld.blockToBuy = blockType;
					ModLoader.getMinecraftInstance().theWorld.itemToBuy = null;			
					ModLoader.getMinecraftInstance().theWorld.buyItem = false;
					ModLoader.getMinecraftInstance().theWorld.showBuyMenu = true;	
					} else {
					ModLoader.getMinecraftInstance().theWorld.itemToBuy = itemType;
					ModLoader.getMinecraftInstance().theWorld.blockToBuy = null;
					ModLoader.getMinecraftInstance().theWorld.buyItem = true;
					ModLoader.getMinecraftInstance().theWorld.showBuyMenu = true;	
					}
				}
			}
		}	
        if(!flag1)// && flag)
        {
            ModLoader.getMinecraftInstance().theWorld.showNoPower = false;				
			world.setBlockMetadataWithNotify(i, j, k, 0);
            world.notifyBlocksOfNeighborChange(i, j, k, blockID);
            world.notifyBlocksOfNeighborChange(i, j - 1, k, blockID);
            world.markBlocksDirty(i, j, k, i, j, k);
            //world.playSoundEffect((double)i + 0.5D, (double)j + 0.10000000000000001D, (double)k + 0.5D, "random.click", 0.3F, 0.5F);
			if (debris) { ModLoader.getMinecraftInstance().theWorld.showBuyMenu2 = false; } else { ModLoader.getMinecraftInstance().theWorld.showBuyMenu = false; }
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
        /*boolean flag = iblockaccess.getBlockMetadata(i, j, k) == 1;
        float f = 0.0625F;
        if(flag)
        {
            setBlockBounds(f, 0.0F, f, 1.0F - f, 0.03125F, 1.0F - f);
        } else
        {
            setBlockBounds(f, 0.0F, f, 1.0F - f, 0.0625F, 1.0F - f);
        }*/
    }

    public void func_237_e()
    {
        /*float f = 0.5F;
        float f1 = 0.125F;
        float f2 = 0.5F;
        setBlockBounds(0.5F - f, 0.5F - f1, 0.5F - f2, 0.5F + f, 0.5F + f1, 0.5F + f2);*/
    }

    private EnumMobType triggerMobType;
	private boolean showBuyMenu;
	public Item itemType;
	public Block blockType;
	public int cost;
	public int count;
	private boolean debris;
	private boolean powerNeeded;
}
