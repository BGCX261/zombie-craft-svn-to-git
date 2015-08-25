package net.minecraft.src;

// SDK
public class SdkZcItemGunUzi extends SdkZcItemGun {
	public SdkZcItemGunUzi(int i)
	{
	    super(i);
	    firingSound = "sdkzc.NEWUZI";
	    requiredBullet = mod_SdkZombieCraft.itemBulletMp40;
	    numBullets = 1;
	    damage = 1;
	    muzzleVelocity = 1.5F;
	    muzzleVelocityRandomness = 0F;
	    spread = 3F;
	    useDelay = 1;
		recoil = 1.5F;
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
