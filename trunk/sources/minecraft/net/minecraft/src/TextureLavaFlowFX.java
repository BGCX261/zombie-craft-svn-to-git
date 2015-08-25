package net.minecraft.src;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

import net.minecraft.client.Minecraft;

public class TextureLavaFlowFX extends TextureFX
{

    public TextureLavaFlowFX(Minecraft minecraft)
    {
        super(238);
        tileSize = 2;
        field_22002_anim = new CustomAnimation(minecraft, "lava_flowing", iconIndex, imageData, 3, 6);
    }

    public void onTick()
    {
        field_22002_anim.func_22005_v();
    }

    CustomAnimation field_22002_anim;
}
