package net.minecraft.src;

// SDK
public class SdkZcEntityBulletRay extends SdkZcEntityBullet
{
	public SdkZcEntityBulletRay(World world)
    {
        super(world);
        setSize(0.5F, 0.5F);
    }
    
    public SdkZcEntityBulletRay(World world, double d, double d1, double d2)
    {
        super(world, d, d1, d2);
        setSize(0.5F, 0.5F);
    }
	
	public SdkZcEntityBulletRay(World world, EntityLiving entityliving, float heightOffset, SdkZcItemGun gun)
    {
        super(world, entityliving, heightOffset, gun);
        setSize(0.5F, 0.5F);
    }
	
	@Override
	public float getEntityBrightness(float f)
    {
        return 2.0F;
    }
}
