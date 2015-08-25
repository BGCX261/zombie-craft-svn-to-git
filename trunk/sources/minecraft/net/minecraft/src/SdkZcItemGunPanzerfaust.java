package net.minecraft.src;

// SDK
public class SdkZcItemGunPanzerfaust extends SdkZcItemGun {

	public SdkZcItemGunPanzerfaust(int i)
	{
	    super(i);
	    firingSound = "sdkzc.panzerfaust";
	    requiredBullet = mod_SdkZombieCraft.itemBulletPanzerfaust;
	    numBullets = 1;
	    damage = 10;
	    muzzleVelocity = 2F;
	    muzzleVelocityRandomness = 0F;
	    spread = 0F;
	    useDelay = 30;
		recoil = 0F;
	}
	
	@Override
	public SdkZcEntityBullet getBulletEntity(World world, EntityLiving entityliving, float height) {
		return new SdkZcEntityBulletRocket(world, entityliving, height, this);
	}

	@Override
	public SdkZcEntityBulletCasing getBulletCasingEntity(World world, EntityLiving entityliving, float height) {
		return null;
	}
}
