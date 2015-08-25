package net.minecraft.src;

// SDK
public class SdkZcEntityBulletCasingShell extends SdkZcEntityBulletCasing {
	
	public SdkZcEntityBulletCasingShell(World world)
    {
        super(world);
    }
    
    public SdkZcEntityBulletCasingShell(World world, double d, double d1, double d2)
    {
        super(world, d, d1, d2);
    }
	
	public SdkZcEntityBulletCasingShell(World world, EntityLiving entityliving, float heightOffset)
    {
		super(world, entityliving, heightOffset);
    }
}
