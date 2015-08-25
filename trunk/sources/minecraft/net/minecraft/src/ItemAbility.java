package net.minecraft.src;


public class ItemAbility extends Item
{

	public int abilityType;
    public ItemAbility(int i, int type)
    {
        super(i);
        maxStackSize = 1;
        abilityType = type;
    }

    public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer)
    {
    	
    	if (abilityType == 0 && !entityplayer.canCharge) {
    		entityplayer.canCharge = true;
    		itemstack.stackSize--;
    	} else if (abilityType == 1 && !entityplayer.canShock) {
    		entityplayer.canShock = true;
    		itemstack.stackSize--;
    	}
    	
	    
    	
    	return itemstack;
    }
}
