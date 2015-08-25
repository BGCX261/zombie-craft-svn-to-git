package net.minecraft.src;

// SDK
public class SdkZcEntityBulletShot extends SdkZcEntityBullet {
	
	public SdkZcEntityBulletShot(World world)
    {
        super(world);
        setSize(0.03125F, 0.03125F);
    }
    
    public SdkZcEntityBulletShot(World world, double d, double d1, double d2)
    {
        super(world, d, d1, d2);
        setSize(0.03125F, 0.03125F);
    }
	
	public SdkZcEntityBulletShot(World world, EntityLiving entityliving, float heightOffset, SdkZcItemGun gun)
    {
        super(world, entityliving, heightOffset, gun);
        setSize(0.03125F, 0.03125F);
    }
}
