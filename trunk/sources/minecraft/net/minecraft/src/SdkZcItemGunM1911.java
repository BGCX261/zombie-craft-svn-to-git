package net.minecraft.src;

// SDK
public class SdkZcItemGunM1911 extends SdkZcItemGun {

	public SdkZcItemGunM1911(int i)
	{
	    super(i);
	    firingSound = "sdkzc.wpn_colt1911_st_f";
	    requiredBullet = mod_SdkZombieCraft.itemBulletM1911;
	    numBullets = 1;
	    damage = 1;
	    muzzleVelocity = 1.5F;
	    muzzleVelocityRandomness = 0F;
	    spread = 0F;
	    useDelay = 8;
	    recoil = 2F;
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
