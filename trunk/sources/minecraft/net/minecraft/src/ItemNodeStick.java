package net.minecraft.src;
import java.util.List;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 


public class ItemNodeStick extends Item
{

    public ItemNodeStick(int i)
    {
        super(i);
        maxStackSize = 1;
		xCoord = 0;
		yCoord = 0;
		zCoord = 0;
		tmp = true;
    }
    
    public boolean onItemUse(ItemStack itemstack, EntityPlayer entityplayer, World world, int i, int j, int k, int l) {
    	
    	//System.out.println("use");
    	return false;
    	
    }
	
	public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer)
    {
		
		if (!world.devMode) {
			return itemstack;
		}
		
		List entList = entityplayer.worldObj.getEntitiesWithinAABBExcludingEntity((Entity)entityplayer,entityplayer.boundingBox.expand(0.3D,1D,0.3D));
		
		Entity tEnt = null;
		boolean foundWP = false;
		
		for (int i = 0; i < entList.size(); i++) {
			
			tEnt = (Entity)entList.get(i);
			
			if (tEnt instanceof EntityNode) {
				foundWP = true;
				tEnt.setEntityDead();
				mod_EntAI.QueueWPNodesInit = true;
				break;
			}
		}
		
		//System.out.println("RC");
		if (!foundWP) {
			Entity ent = new EntityNode(entityplayer.worldObj);
			ent.setPosition(entityplayer.posX,entityplayer.posY-1,entityplayer.posZ);
			entityplayer.worldObj.entityJoinedWorld(ent);
			//force waypoint reload
			mod_EntAI.QueueWPNodesInit = true;
		}
		
        return itemstack;
    }
	
	private boolean tmp;
	private int xCoord;
	private int yCoord;
	private int zCoord;
}
