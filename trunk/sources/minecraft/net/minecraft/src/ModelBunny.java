package net.minecraft.src;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 


public class ModelBunny extends ModelBase
{

    public ModelBunny()
    {
        byte byte0 = 16;
        field_22446_a = new ModelRenderer(0, 0);
        field_22446_a.addBox(-2F, -1F, -4F, 4, 4, 6, 0.0F);
        field_22446_a.setPosition(0.0F, -1 + byte0, -4F);
        onGround = new ModelRenderer(14, 0);
        onGround.addBox(-2F, -5F, -3F, 1, 4, 2, 0.0F);
        onGround.setPosition(0.0F, -1 + byte0, -4F);
        field_22447_g2 = new ModelRenderer(14, 0);
        field_22447_g2.addBox(1.0F, -5F, -3F, 1, 4, 2, 0.0F);
        field_22447_g2.setPosition(0.0F, -1 + byte0, -4F);
        field_22440_h = new ModelRenderer(20, 0);
        field_22440_h.addBox(-4F, 0.0F, -3F, 2, 3, 2, 0.0F);
        field_22440_h.setPosition(0.0F, -1 + byte0, -4F);
        field_22438_r2 = new ModelRenderer(20, 0);
        field_22438_r2.addBox(2.0F, 0.0F, -3F, 2, 3, 2, 0.0F);
        field_22438_r2.setPosition(0.0F, -1 + byte0, -4F);
        field_22445_b = new ModelRenderer(0, 10);
        field_22445_b.addBox(-3F, -4F, -3F, 6, 8, 6, 0.0F);
        field_22445_b.setPosition(0.0F, 0 + byte0, 0.0F);
        field_22444_b2 = new ModelRenderer(0, 24);
        field_22444_b2.addBox(-2F, 4F, -2F, 4, 3, 4, 0.0F);
        field_22444_b2.setPosition(0.0F, 0 + byte0, 0.0F);
        field_22443_e1 = new ModelRenderer(24, 16);
        field_22443_e1.addBox(-2F, 0.0F, -1F, 2, 2, 2);
        field_22443_e1.setPosition(3F, 3 + byte0, -3F);
        field_22442_e2 = new ModelRenderer(24, 16);
        field_22442_e2.addBox(0.0F, 0.0F, -1F, 2, 2, 2);
        field_22442_e2.setPosition(-3F, 3 + byte0, -3F);
        field_22439_l1 = new ModelRenderer(16, 24);
        field_22439_l1.addBox(-2F, 0.0F, -4F, 2, 2, 4);
        field_22439_l1.setPosition(3F, 3 + byte0, 4F);
        field_22441_n2 = new ModelRenderer(16, 24);
        field_22441_n2.addBox(0.0F, 0.0F, -4F, 2, 2, 4);
        field_22441_n2.setPosition(-3F, 3 + byte0, 4F);
    }

    public void render(float f, float f1, float f2, float f3, float f4, float f5)
    {
        setRotationAngles(f, f1, f2, f3, f4, f5);
        field_22446_a.render(f5);
        onGround.render(f5);
        field_22447_g2.render(f5);
        field_22440_h.render(f5);
        field_22438_r2.render(f5);
        field_22445_b.render(f5);
        field_22444_b2.render(f5);
        field_22443_e1.render(f5);
        field_22442_e2.render(f5);
        field_22439_l1.render(f5);
        field_22441_n2.render(f5);
    }

    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5)
    {
        field_22446_a.rotateAngleX = -(f4 / 57.29578F);
        field_22446_a.rotateAngleY = f3 / 57.29578F;
        onGround.rotateAngleX = field_22446_a.rotateAngleX;
        onGround.rotateAngleY = field_22446_a.rotateAngleY;
        field_22447_g2.rotateAngleX = field_22446_a.rotateAngleX;
        field_22447_g2.rotateAngleY = field_22446_a.rotateAngleY;
        field_22440_h.rotateAngleX = field_22446_a.rotateAngleX;
        field_22440_h.rotateAngleY = field_22446_a.rotateAngleY;
        field_22438_r2.rotateAngleX = field_22446_a.rotateAngleX;
        field_22438_r2.rotateAngleY = field_22446_a.rotateAngleY;
        field_22445_b.rotateAngleX = 1.570796F;
        field_22444_b2.rotateAngleX = 1.570796F;
        field_22443_e1.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.0F * f1;
        field_22439_l1.rotateAngleX = MathHelper.cos(f * 0.6662F + 3.141593F) * 1.2F * f1;
        field_22442_e2.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.0F * f1;
        field_22441_n2.rotateAngleX = MathHelper.cos(f * 0.6662F + 3.141593F) * 1.2F * f1;
    }

    public ModelRenderer field_22446_a;
    public ModelRenderer field_22445_b;
    public ModelRenderer field_22444_b2;
    public ModelRenderer field_22443_e1;
    public ModelRenderer field_22442_e2;
    public ModelRenderer field_22439_l1;
    public ModelRenderer field_22441_n2;
    public ModelRenderer onGround;
    public ModelRenderer field_22447_g2;
    public ModelRenderer field_22440_h;
    public ModelRenderer field_22438_r2;
}
