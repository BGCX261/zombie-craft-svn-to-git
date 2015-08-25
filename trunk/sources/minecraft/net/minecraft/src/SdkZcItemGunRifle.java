package net.minecraft.src;

// SDK
public class SdkZcItemGunRifle extends SdkZcItemGun {
	public SdkZcItemGunRifle(int i)
	{
	    super(i);
	    firingSound = "sdkzc.AR35g";
	    requiredBullet = mod_SdkZombieCraft.itemBulletKar98k;
	    numBullets = 1;
	    damage = 3;
	    muzzleVelocity = 2F;
	    muzzleVelocityRandomness = 0F;
	    spread = 0.5F;
	    useDelay = 2;
		recoil = 1.3F;
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
