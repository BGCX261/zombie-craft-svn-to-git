package net.minecraft.src;

// SDK
public class SdkZcItemGunKar98k extends SdkZcItemGun {

	public SdkZcItemGunKar98k(int i)
	{
	    super(i);
	    firingSound = "sdkzc.wpn_kar98_st_r";
	    requiredBullet = mod_SdkZombieCraft.itemBulletKar98k;
	    numBullets = 1;
	    damage = 2;
	    muzzleVelocity = 2F;
	    muzzleVelocityRandomness = 0F;
	    spread = 0F;
	    useDelay = 16;
		recoil = 5F;
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
