package net.minecraft.src;

public class ModelDog1 extends ModelQuadraped
{

    public ModelDog1()
    {
        super(10, 0.0F);
        head = new ModelRenderer(12, 0);
        head.addBox(-5F, -5F, -2F, 10, 14, 3, 0.0F);
        head.setPosition(0.0F, 4F, -8F);
        body = new ModelRenderer(24, 16);
        body.addBox(-6F, -11F, -10F, 12, 8, 8, 0.0F);
        body.setPosition(0.0F, 5F, 2.0F);
    }
}
