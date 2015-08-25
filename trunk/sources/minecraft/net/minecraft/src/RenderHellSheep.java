package net.minecraft.src;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

import org.lwjgl.opengl.GL11;

public class RenderHellSheep extends RenderLiving
{

    public RenderHellSheep(ModelBase modelbase, ModelBase modelbase1, float f)
    {
        super(modelbase, f);
        setRenderPassModel(modelbase1);
    }

    protected boolean shouldRenderPass(EntityLiving entityliving, int i, float f)
    {
        return true;//func_176_a((EntitySheep)entityliving, i, f);
    }
}
