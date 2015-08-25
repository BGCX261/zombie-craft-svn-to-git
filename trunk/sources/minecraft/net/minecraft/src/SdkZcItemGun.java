package net.minecraft.src;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

import java.util.Random;

// SDK
public abstract class SdkZcItemGun extends SdkZcItemCustomUseDelay
{
	public String firingSound;
	public Item requiredBullet;
	public int numBullets = 1;
	public int damage;
	public float muzzleVelocity = 1.5F;
	public float muzzleVelocityRandomness = 0F;
	public float spread = 1.0F;
	public float recoil = 1F;
	public int soundDelay = -1;
	
	protected long lastShot = 0;
	protected long lastSound = 0;
	protected long lastEmptySound = 0;
	
    public SdkZcItemGun(int i)
    {
        super(i);
        maxStackSize = 1;
    }

    @Override
    public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer)
    {
    	if(world.func_22139_r() - lastShot < 0) // Sanity check
    		lastShot = 0;
    	
    	if(world.func_22139_r() - lastSound < 0) // New Sound Sanity check
    		lastSound = 0;
    	
    	if((lastShot == 0 || world.func_22139_r() - lastShot >= useDelay) && !mod_SdkZombieCraft.reloadTimes.containsKey(entityplayer))
    	{
    		int result = useItemInInventory(entityplayer.inventory, requiredBullet.shiftedIndex);
	        if(result > 0)
	        {
	        	if(soundDelay == 0 || lastSound == 0 || world.func_22139_r() - lastSound > soundDelay)
	        	{
	        		world.playSoundAtEntity(entityplayer, firingSound, 0.25F, 1.0F / (itemRand.nextFloat() * 0.1F + 0.95F));
	        		lastSound = world.func_22139_r();
	        	}
	            
	            if(!world.multiplayerWorld)
	            {
		            for(int i = 0; i < numBullets; i++)
		            {
		            	Entity bulletEntity = getBulletEntity(world, entityplayer, 0);
		            	
		            	if(bulletEntity != null)
		            		world.entityJoinedWorld(bulletEntity);
		            }
		            
		            Entity bulletCasingEntity = getBulletCasingEntity(world, entityplayer, 0);
		            
		            if(bulletCasingEntity != null)
		            	world.entityJoinedWorld(bulletCasingEntity);
	            }
		            
	            double recoilV = Math.min(recoil, entityplayer.rotationPitch + 90F);
	            double recoilH = world.rand.nextFloat() * recoil * 0.5F - recoil * 0.25F;
	            
	            if(!entityplayer.isSneaking())
	            {
	            	recoilV *= 2;
	            	recoilH *= 2;
	            }
	            if(!entityplayer.onGround)
	            {
	            	recoilV *= 2;
	            	recoilH *= 2;
	            }
	            
	            mod_SdkZombieCraft.currentRecoilV += recoilV;
	            mod_SdkZombieCraft.currentRecoilH += recoilH;
	            
	            entityplayer.rotationPitch -= recoilV;
	            entityplayer.rotationYaw += recoilH;
		        
		        if(result == 2)
		        	mod_SdkZombieCraft.handleReload(world, entityplayer, true);
		        
		        lastShot = world.func_22139_r();
		        
		        entityplayer.muzzleFlash();
	        }
	        else
	        {
	        	if(lastEmptySound == 0 || world.func_22139_r() - lastEmptySound > 20)
	        	{
	        		world.playSoundAtEntity(entityplayer, "sdkzc.gunempty", 1.0F, 1.0F / (itemRand.nextFloat() * 0.1F + 0.95F));
	        		lastEmptySound = world.func_22139_r();
	        	}
	        }
    	}
    	
        return itemstack;
    }

    public int useItemInInventory(InventoryPlayer inventory, int i)
    {
    	
    	
    	/*System.out.println(j);
        if(j < 0)
        {
        	j = getInventorySlotContainItem(inventory, i);
        }*/
    	
        int j = getInventorySlotContainItem(inventory, i);
        if(j < 0)
        {
            return 0;
        }
        
        if(requiredBullet == mod_SdkZombieCraft.itemBulletFlamethrower)
        {
        	inventory.mainInventory[j].damageItem(1);
        	if(inventory.mainInventory[j].stackSize == 0)
            {
        		inventory.mainInventory[j] = new ItemStack(Item.bucketEmpty);
        		
        		if(getInventorySlotContainItem(inventory, i) >= 0)
        		{
        			return 2;
        		}
            }
        }
        else
        {
	        if(--inventory.mainInventory[j].stackSize <= 0)
	        {
	        	inventory.mainInventory[j] = null;

	        	if(getInventorySlotContainItem(inventory, i) >= 0)
        		{
        			return 2;
        		}
	        }
        }
        
        return 1;
    }
    
    
    
    private int getInventorySlotContainItem(InventoryPlayer inventory, int i)
    {
    	int j = inventory.getSlotAboveUsedItem(inventory, i);
    	
    	if (j > -1) {
    		return j;
    	}
    	
    	for(j = 0; j < 9; j++)
        {
    		for (int k = 0; k < 4; k++) {
    			int ii = j + (k*9);
    			if(inventory.mainInventory[ii] != null && inventory.mainInventory[ii].itemID == i)
	            {
	                return ii;
	            }
    		}
        }
    	
        /*for(j = 0; j < inventory.mainInventory.length; j++)
        {
            if(inventory.mainInventory[j] != null && inventory.mainInventory[j].itemID == i)
            {
                return j;
            }
        }*/

        return -1;
    }
    
    public abstract SdkZcEntityBullet getBulletEntity(World world, EntityLiving entityliving, float height);
    public abstract SdkZcEntityBulletCasing getBulletCasingEntity(World world, EntityLiving entityliving, float height);
}
