package net.minecraft.src;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

import net.minecraft.client.Minecraft;

public class TextureWaterFX extends TextureFX
{

    public TextureWaterFX(Minecraft minecraft)
    {
        super(205);
        field_22004_anim = new CustomAnimation(minecraft, "water_still", iconIndex, imageData);
    }

    public void onTick()
    {
        field_22004_anim.func_22005_v();
    }

    CustomAnimation field_22004_anim;
}
