package net.minecraft.src;

// SDK
public class SdkZcItemGunMagum extends SdkZcItemGun {
	public SdkZcItemGunMagum(int i)
	{
	    super(i);
	    firingSound = "sdkzc.wpn_mp40_st_f";
	    requiredBullet = mod_SdkZombieCraft.itemBulletMagnum;
	    numBullets = 1;
	    damage = 4;
	    muzzleVelocity = 2F;
	    muzzleVelocityRandomness = 0F;
	    spread = 0F;
	    useDelay = 10;
		recoil = 5.0F;
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
