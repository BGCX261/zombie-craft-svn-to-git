package net.minecraft.src;

// SDK
public class SdkZcItemGunFlamethrower extends SdkZcItemGun {

	public SdkZcItemGunFlamethrower(int i)
	{
	    super(i);
	    firingSound = "sdkzc.flamethrower";
	    firingSound = "sdkzc.wpn_flm_ignite_mn";
	    
	    requiredBullet = mod_SdkZombieCraft.itemBulletFlamethrower;
	    numBullets = 1;
        damage = 1;
        muzzleVelocity = 0.75F;
        muzzleVelocityRandomness = 0F;
        spread = 0F;
	    useDelay = 1;
	    recoil = 0F;
	    soundDelay = 12;
	}
	
	@Override
	public SdkZcEntityBullet getBulletEntity(World world, EntityLiving entityliving, float height) {
		return new SdkZcEntityBulletFlame(world, entityliving, height, this);
	}

	@Override
	public SdkZcEntityBulletCasing getBulletCasingEntity(World world, EntityLiving entityliving, float height) {
		return null;
	}
}
