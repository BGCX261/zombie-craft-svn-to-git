package net.minecraft.src;

// SDK
public class SdkZcItemGunM1Garand extends SdkZcItemGun {

	public SdkZcItemGunM1Garand(int i)
	{
	    super(i);
	    firingSound = "sdkzc.wpn_m1carbine_st_f";
	    requiredBullet = mod_SdkZombieCraft.itemBulletM1Garand;
	    numBullets = 1;
	    damage = 3;
	    muzzleVelocity = 2F;
	    muzzleVelocityRandomness = 0F;
	    spread = 0F;
	    useDelay = 5;
		recoil = 3F;
	}
	
	@Override
	public SdkZcEntityBullet getBulletEntity(World world, EntityLiving entityliving, float heightOffset) {
		return new SdkZcEntityBullet(world, entityliving, heightOffset, this);
	}

	@Override
	public SdkZcEntityBulletCasing getBulletCasingEntity(World world, EntityLiving entityliving, float heightOffset) {
		return new SdkZcEntityBulletCasing(world, entityliving, heightOffset);
	}
}
