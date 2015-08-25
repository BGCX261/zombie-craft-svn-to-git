package net.minecraft.src;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

import java.util.Random;

// SDK
public class ItemChickenGun extends SdkZcItemCustomStackSize
{

    public ItemChickenGun(int i, int stackSize)
    {
    	super(i, stackSize);
    }

    @Override
    public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer)
    {
    	if(!world.multiplayerWorld)
        {
	    	EntityChicken entitychicken = new EntityChicken(world);
	        entitychicken.setLocationAndAngles(entityplayer.posX, entityplayer.posY-0.5, entityplayer.posZ, entityplayer.rotationYaw, 0.0F);
	        world.entityJoinedWorld(entitychicken);
	        
	        float f = 1.4F;
	        entitychicken.motionX = -MathHelper.sin((entityplayer.rotationYaw / 180F) * 3.141593F) * MathHelper.cos((entityplayer.rotationPitch / 180F) * 3.141593F) * f;
	        entitychicken.motionZ = MathHelper.cos((entityplayer.rotationYaw / 180F) * 3.141593F) * MathHelper.cos((entityplayer.rotationPitch / 180F) * 3.141593F) * f;
	        entitychicken.motionY = -MathHelper.sin((entityplayer.rotationPitch / 180F) * 3.141593F) * f;
	        //func_20048_a(motionX, motionY, motionZ, 1.5F, 1.0F);
	        
	        //entitychicken.setRelVel(entityplayer,entitychicken,0.0F,0.0F,1.0F,8.0F);
        }
        
        itemstack.stackSize--;
        world.playSoundAtEntity(entityplayer, "mob.chickenhurt", 1.0F, 1.0F / (itemRand.nextFloat() * 0.1F + 0.95F));
        return itemstack;
    }
}
