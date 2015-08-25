package net.minecraft.src;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

import java.util.Map;
import java.util.Random;
import net.minecraft.client.Minecraft;

public abstract class BaseMod
{

    public BaseMod()
    {
    }

    public String toString()
    {
        return (new StringBuilder(String.valueOf(getClass().getName()))).append(" ").append(Version()).toString();
    }

    public abstract String Version();

    public int AddFuel(int i)
    {
        return 0;
    }

    public void AddRecipes(CraftingManager craftingmanager)
    {
    }

    public Entity DispenseEntity(World world, double d, double d1, double d2, 
            float f, float f1, int i)
    {
        return null;
    }

    public void AddRenderer(Map map)
    {
    }

    public void GenerateNether(World world, Random random, int i, int j)
    {
    }

    public void GenerateSurface(World world, Random random, int i, int j)
    {
    }

    public GuiScreen OpenModGUI(EntityPlayer entityplayer, Object obj)
    {
        return null;
    }

    public void OSDHook(Minecraft minecraft, boolean flag)
    {
    }

    public void RegisterAnimation(Minecraft minecraft)
    {
    }

    public boolean RenderBlock(RenderBlocks renderblocks, IBlockAccess iblockaccess, int i, int j, int k, Block block, int l)
    {
        return false;
    }

    public void KeyboardEvent(KeyBinding keybinding)
    {
    }

    public void ModsLoaded()
    {
    }
}
