package net.minecraft.src;

public class RenderDog extends RenderLiving
{

    public RenderDog(ModelBase modelbase, ModelBase modelbase1, float f)
    {
        super(modelbase, f);
        setRenderPassModel(modelbase1);
    }

    protected boolean func_21141_a(EntityDog entitydog, int i)
    {
        loadTexture("/mob/Dogb.png");
        return i == 0;
    }

    protected boolean shouldRenderPass(EntityLiving entityliving, int i, float f)
    {
        return func_21141_a((EntityDog)entityliving, i);
    }
}
