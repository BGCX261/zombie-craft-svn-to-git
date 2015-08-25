package net.minecraft.src;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

import java.util.Random;

// SDK
public class SdkZcItemGrenadeStun extends SdkZcItemCustomStackSize
{

    public SdkZcItemGrenadeStun(int i, int stackSize)
    {
        super(i, stackSize);
    }

    @Override
    public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer)
    {
        itemstack.stackSize--;
        world.playSoundAtEntity(entityplayer, "sdkzc.grunt", 1.0F, 1.0F / (itemRand.nextFloat() * 0.1F + 0.95F));
        if(!world.multiplayerWorld)
        {
        	world.entityJoinedWorld(new SdkZcEntityGrenadeStun(world, entityplayer));
        }
        return itemstack;
    }
}
