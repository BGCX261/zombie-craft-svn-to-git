package net.minecraft.src;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

import net.minecraft.client.Minecraft;

public class TextureLavaFX extends TextureFX
{

    public TextureLavaFX(Minecraft minecraft)
    {
        super(237);
        field_22003_anim = new CustomAnimation(minecraft, "lava_still", iconIndex, imageData);
    }

    public void onTick()
    {
        field_22003_anim.func_22005_v();
    }

    CustomAnimation field_22003_anim;
}
