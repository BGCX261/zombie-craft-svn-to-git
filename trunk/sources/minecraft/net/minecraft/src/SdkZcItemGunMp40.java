package net.minecraft.src;

// SDK
public class SdkZcItemGunMp40 extends SdkZcItemGun {
	public SdkZcItemGunMp40(int i)
	{
	    super(i);
	    firingSound = "sdkzc.wpn_mp40_st_f";
	    requiredBullet = mod_SdkZombieCraft.itemBulletMp40;
	    numBullets = 1;
	    damage = 2;
	    muzzleVelocity = 1.5F;
	    muzzleVelocityRandomness = 0F;
	    spread = 1F;
	    useDelay = 2;
		recoil = 1F;
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
