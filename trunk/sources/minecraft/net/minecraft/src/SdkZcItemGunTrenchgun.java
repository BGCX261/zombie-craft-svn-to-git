package net.minecraft.src;

// SDK
public class SdkZcItemGunTrenchgun extends SdkZcItemGun {

	public SdkZcItemGunTrenchgun(int i)
	{
	    super(i);
	    firingSound = "sdkzc.wpn_shtgun_st_f";
	    requiredBullet = mod_SdkZombieCraft.itemBulletTrenchgun;
	    numBullets = 5;
        damage = 2;
        muzzleVelocity = 1.5F;
        muzzleVelocityRandomness = 0F;
        spread = 8.0F;
	    useDelay = 16;
	    recoil = 8F;
	}
	
	@Override
	public SdkZcEntityBullet getBulletEntity(World world, EntityLiving entityliving, float height) {
		return new SdkZcEntityBulletShot(world, entityliving, height, this);
	}

	@Override
	public SdkZcEntityBulletCasing getBulletCasingEntity(World world, EntityLiving entityliving, float height) {
		return new SdkZcEntityBulletCasingShell(world, entityliving, height);
	}
}
