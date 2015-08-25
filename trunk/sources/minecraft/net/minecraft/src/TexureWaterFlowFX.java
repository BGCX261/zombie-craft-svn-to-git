package net.minecraft.src;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

import net.minecraft.client.Minecraft;

public class TexureWaterFlowFX extends TextureFX
{

    public TexureWaterFlowFX(Minecraft minecraft)
    {
        super(206);
        tileSize = 2;
        field_22001_anim = new CustomAnimation(minecraft, "water_flowing", iconIndex, imageData, 0, 0);
    }

    public void onTick()
    {
        field_22001_anim.func_22005_v();
    }

    CustomAnimation field_22001_anim;
}
