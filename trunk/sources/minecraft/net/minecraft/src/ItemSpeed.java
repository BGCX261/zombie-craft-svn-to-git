package net.minecraft.src;


public class ItemSpeed extends Item
{

    public ItemSpeed(int i)
    {
        super(i);
        maxStackSize = 1;
    }

    public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer)
    {
    	if (!entityplayer.speedCola) {
	        itemstack.stackSize--;
			entityplayer.speedCola = true;
			double d1 = (double)entityplayer.posX + 0.5D + ((double)world.rand.nextFloat() - 0.5D) * 2D;
			double d3 = (double)entityplayer.posY + 0.5D + ((double)world.rand.nextFloat() - 0.5D) * 2D;
			double d5 = (double)entityplayer.posZ + 0.5D + ((double)world.rand.nextFloat() - 0.5D) * 2D;
			world.spawnParticle("bubble", d1, d3, d5, 0.0D, 0.0D, 0.0D);
    	}
        return itemstack;
    }
}
