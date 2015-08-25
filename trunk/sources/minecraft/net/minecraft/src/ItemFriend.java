package net.minecraft.src;


public class ItemFriend extends Item
{

    public ItemFriend(int i)
    {
        super(i);
        maxStackSize = 1;
    }

    public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer)
    {
    	int friendCount = 0;
    	for (int i = 0; i < entityplayer.worldObj.loadedEntityList.size(); i++) {
    		Entity tEnt = (Entity)entityplayer.worldObj.loadedEntityList.get(i);
    		if (tEnt instanceof EntityFriend) { friendCount++; } 
    	}
    	
    	if (friendCount < 4) {
	        itemstack.stackSize--;
			double d1 = (double)entityplayer.posX + 0.5D + ((double)world.rand.nextFloat() - 0.5D) * 2D;
			double d3 = MathHelper.floor_double(entityplayer.boundingBox.minY); //(double)entityplayer.posY;// + 0.5D + ((double)world.rand.nextFloat() - 0.5D) * 2D;
			double d5 = (double)entityplayer.posZ + 0.5D + ((double)world.rand.nextFloat() - 0.5D) * 2D;
			EntityFriend entityfriend = new EntityFriend(world);					
	        entityfriend.setLocationAndAngles(d1, d3, d5, world.rand.nextFloat() * 360F, 0.0F);
	        world.entityJoinedWorld(entityfriend);
    	}
    	return itemstack;
    }
}
