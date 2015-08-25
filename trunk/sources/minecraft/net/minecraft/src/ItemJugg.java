package net.minecraft.src;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 


public class ItemJugg extends Item
{

    public ItemJugg(int i, int j, int k, int l, int i1)
    {
        super(i);
        field_21002_field_21002_bi = i1;
        field_21003_a21001_a = j;
        maxStackSize = 1;
    }

    public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer)
    {
    	
        itemstack.stackSize--;
		entityplayer.inventory.setInventorySlotContents(36, new ItemStack(Item.bootsChain, 1));
		entityplayer.inventory.setInventorySlotContents(37, new ItemStack(Item.legsChain, 1));
		entityplayer.inventory.setInventorySlotContents(38, new ItemStack(Item.plateChain, 1));
		entityplayer.inventory.setInventorySlotContents(39, new ItemStack(Item.helmetChain, 1));
		//field_21003_a21001_a = 20;
        //entityplayer.heal(field_21003_a21001_a);
        //field_21002_field_21002_bi = 3;
        return itemstack;
    }

    private int field_21003_a21001_a;
    public int field_21002_field_21002_bi;
}
