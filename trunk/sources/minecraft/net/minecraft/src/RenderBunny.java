package net.minecraft.src;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

import org.lwjgl.opengl.GL11;

public class RenderBunny extends RenderLiving
{

    public RenderBunny(ModelBase modelbase, float f)
    {
        super(modelbase, f);
    }

    public void func_22098_doRenderLiving(EntityBunny entitybunny, double d, double d1, double d2, 
            float f, float f1)
    {
        super.doRenderLiving(entitybunny, d, d1, d2, f, f1);
    }

    protected float func_22100_a(EntityBunny entitybunny, float f)
    {
        float f1 = entitybunny.field_22057_q + (entitybunny.field_22058_b - entitybunny.field_22057_q) * f;
        float f2 = entitybunny.field_22057_z + (entitybunny.field_22057_c - entitybunny.field_22057_z) * f;
        return (MathHelper.sin(f1) + 1.0F) * f2;
    }

    protected float func_167_c(EntityLiving entityliving, float f)
    {
        return func_22100_a((EntityBunny)entityliving, f);
    }

    public void func_22099_doRenderLiving(EntityLiving entityliving, double d, double d1, double d2, 
            float f, float f1)
    {
        func_22098_doRenderLiving((EntityBunny)entityliving, d, d1, d2, f, f1);
    }

    public void doRender(Entity entity, double d, double d1, double d2, 
            float f, float f1)
    {
        func_22098_doRenderLiving((EntityBunny)entity, d, d1, d2, f, f1);
    }

    protected void func_22097_rotBunny(EntityBunny entitybunny)
    {
        if(!entitybunny.isCollidedHorizontally && entitybunny.worldObj == null)
        {
            if(entitybunny.motionZ > 0.5D)
            {
                GL11.glRotatef(35F, -1F, 0.0F, 0.0F);
            } else
            if(entitybunny.motionZ < -0.5D)
            {
                GL11.glRotatef(-35F, -1F, 0.0F, 0.0F);
            } else
            {
                GL11.glRotatef((float)(entitybunny.motionZ * 70D), -1F, 0.0F, 0.0F);
            }
        }
    }

    protected void preRenderCallback(EntityLiving entityliving, float f)
    {
        func_22097_rotBunny((EntityBunny)entityliving);
    }
}
