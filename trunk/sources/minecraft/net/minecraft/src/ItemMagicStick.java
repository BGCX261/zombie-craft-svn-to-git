package net.minecraft.src;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 


public class ItemMagicStick extends Item
{

    public ItemMagicStick(int i)
    {
        super(i);
        maxStackSize = 1;
		xCoord = 0;
		yCoord = 0;
		zCoord = 0;
		tmp = true;
    }
	
	public boolean onItemUse(ItemStack itemstack, EntityPlayer entityplayer, World world, int i, int j, int k, int l)
    {
		if (world.getBlockId(i,j,k)!=mod_ZombieCraft.mobSpawnerWave.blockID) {
			ModLoader.getMinecraftInstance().ingameGUI.addChatMessage("Debrisblock X: "+String.valueOf(i)+" Y: "+String.valueOf(j)+" Z: "+String.valueOf(k));
			xCoord = i;
			yCoord = j;
			zCoord = k;
			tmp = !tmp;	
        return true;
		} else {
			TileEntity tileent =  ModLoader.getMinecraftInstance().theWorld.getBlockTileEntity(i,j,k);
			if (tileent instanceof TileEntityMobSpawnerWave)			
			{
				if (xCoord != 0 || yCoord != 0 || zCoord != 0)
				{
					if (tileent != null) {
							tileent.watchX = xCoord;
							tileent.watchY = yCoord;
							tileent.watchZ = zCoord;
							ModLoader.getMinecraftInstance().ingameGUI.addChatMessage("Linked to MobSpawner at X: "+String.valueOf(i)+" Y: "+String.valueOf(j)+" Z: "+String.valueOf(k));
							tmp = !tmp;				
					}	
				}
			}
			return true;
		}
		
    }
	private boolean tmp;
	private int xCoord;
	private int yCoord;
	private int zCoord;
}
