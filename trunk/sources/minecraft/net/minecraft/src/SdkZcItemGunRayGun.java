package net.minecraft.src;

// SDK
public class SdkZcItemGunRayGun extends SdkZcItemGun {

	public SdkZcItemGunRayGun(int i)
	{
	    super(i);
	    firingSound = "sdkzc.raygun";
	    requiredBullet = mod_SdkZombieCraft.itemBulletRayGun;
	    numBullets = 1;
        damage = 14;
        muzzleVelocity = 2F;
        muzzleVelocityRandomness = 0F;
        spread = 0F;
	    useDelay = 6;
	    recoil = 1.2F;
	}
	
	@Override
	public SdkZcEntityBullet getBulletEntity(World world, EntityLiving entityliving, float height) {
		return new SdkZcEntityBulletRay(world, entityliving, height, this);
	}

	@Override
	public SdkZcEntityBulletCasing getBulletCasingEntity(World world, EntityLiving entityliving, float height) {
		return null;
	}
}
