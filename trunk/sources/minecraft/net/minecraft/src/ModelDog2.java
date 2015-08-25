package net.minecraft.src;

public class ModelDog2 extends ModelQuadraped
{

    public ModelDog2()
    {
        super(10, 0.0F);
        head = new ModelRenderer(0, 0);
        head.addBox(-4F, -2F, -6F, 8, 8, 6, 0.0F);
        head.setPosition(0.0F, 4F, -8F);
        field_21227_b = new ModelRenderer(8, 15);
        field_21227_b.addBox(-2F, 3F, -12F, 4, 4, 6, 0.0F);
        field_21227_b.setPosition(0.0F, 3F, -7F);
        body = new ModelRenderer(28, 6);
        body.addBox(-5F, -8F, -9F, 10, 16, 6, 0.0F);
        body.setPosition(0.0F, 5F, 2.0F);
    }

    public void render(float f, float f1, float f2, float f3, float f4, float f5)
    {
        super.render(f, f1, f2, f3, f4, f5);
        field_21227_b.render(f5);
    }

    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5)
    {
        super.setRotationAngles(f, f1, f2, f3, f4, f5);
        field_21227_b.rotateAngleY = head.rotateAngleY;
        field_21227_b.rotateAngleX = head.rotateAngleX;
    }

    ModelRenderer field_21227_b;
}
