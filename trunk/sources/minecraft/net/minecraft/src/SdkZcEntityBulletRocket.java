package net.minecraft.src;

import java.util.List;

// SDK
public class SdkZcEntityBulletRocket extends SdkZcEntityBullet {
	public SdkZcEntityBulletRocket(World world)
    {
        super(world);
        setSize(0.25F, 0.25F);
    }
    
    public SdkZcEntityBulletRocket(World world, double d, double d1, double d2)
    {
        super(world, d, d1, d2);
        setSize(0.25F, 0.25F);
    }
	
	public SdkZcEntityBulletRocket(World world, EntityLiving entityliving, float heightOffset, SdkZcItemGun gun)
    {
        super(world, entityliving, heightOffset, gun);
        setSize(0.25F, 0.25F);
    }
	
	@Override
    public void onUpdate()
    {
		// Hack to not call parent
		onEntityUpdate();
        
        // Blow up after x ticks
        if(timeInAir == 200)
        {
        	explode();
            return;
        }
        // Smoke
        else if(timeInAir % 2 == 0)
        {
        	double fFactor = 0.625D;
        	double totalMotion = Math.sqrt(motionX * motionX + motionZ * motionZ + motionY * motionY);
        	
        	worldObj.spawnParticle("smoke", posX - motionX / totalMotion * fFactor, posY - motionY / totalMotion * fFactor, posZ - motionZ / totalMotion * fFactor, 0.0D, 0.0D, 0.0D);
        }
        
        if(inGround)
        {
            int i = worldObj.getBlockId(xTile, yTile, zTile);
            if(i != inTile)
            {
                inGround = false;
                motionX *= rand.nextFloat() * 0.2F;
                motionY *= rand.nextFloat() * 0.2F;
                motionZ *= rand.nextFloat() * 0.2F;
                timeInTile = 0;
                timeInAir = 0;
            }
        } else
        {
            timeInAir++;
        }
        Vec3D vec3d = Vec3D.createVector(posX, posY, posZ);
        Vec3D vec3d1 = Vec3D.createVector(posX + motionX, posY + motionY, posZ + motionZ);
        MovingObjectPosition movingobjectposition = worldObj.rayTraceBlocks(vec3d, vec3d1);
        vec3d = Vec3D.createVector(posX, posY, posZ);
        vec3d1 = Vec3D.createVector(posX + motionX, posY + motionY, posZ + motionZ);
        if(movingobjectposition != null)
        {
            vec3d1 = Vec3D.createVector(movingobjectposition.hitVec.xCoord, movingobjectposition.hitVec.yCoord, movingobjectposition.hitVec.zCoord);
        }
        Entity entity = null;
        List list = worldObj.getEntitiesWithinAABBExcludingEntity(this, boundingBox.addCoord(motionX, motionY, motionZ).expand(1.0D, 1.0D, 1.0D));
        double d = 0.0D;
        for(int j = 0; j < list.size(); j++)
        {
            Entity entity1 = (Entity)list.get(j);
            if(!entity1.canBeCollidedWith() || (entity1 == entityFiredBy || (entityFiredBy != null && entity1 == entityFiredBy.ridingEntity)) && timeInAir < 5 || serverSpawned)
            {
                continue;
            }
            float f3 = 0.3F;
            AxisAlignedBB axisalignedbb = entity1.boundingBox.expand(f3, f3, f3);
            MovingObjectPosition movingobjectposition1 = axisalignedbb.func_1169_a(vec3d, vec3d1);
            if(movingobjectposition1 == null)
            {
                continue;
            }
            double d1 = vec3d.distanceTo(movingobjectposition1.hitVec);
            if(d1 < d || d == 0.0D)
            {
                entity = entity1;
                d = d1;
            }
        }

        if(entity != null)
        {
            movingobjectposition = new MovingObjectPosition(entity);
        }
        if(movingobjectposition != null)
        {
        	if(movingobjectposition.entityHit != null)
        	{
        		if(movingobjectposition.entityHit instanceof EntityPlayer)
        		{
        			int tempDamage = damage;
        			if(worldObj.difficultySetting == 0)
                    {
        				tempDamage = 0;
                    }
                    if(worldObj.difficultySetting == 1)
                    {
                    	tempDamage = tempDamage / 3 + 1;
                    }
                    if(worldObj.difficultySetting == 3)
                    {
                    	tempDamage = (tempDamage * 3) / 2;
                    }
                    
                    ((EntityLiving)movingobjectposition.entityHit).attackEntityFrom2(entityFiredBy, damage, true);
        		}
        		else if(movingobjectposition.entityHit instanceof EntityLiving)
        			((EntityLiving)movingobjectposition.entityHit).attackEntityFrom2(entityFiredBy, damage, true);
        		else
        			movingobjectposition.entityHit.attackEntityFrom(entityFiredBy, damage);
        	}
        	
        	explode();
        }
        posX += motionX;
        posY += motionY;
        posZ += motionZ;
        float f1 = MathHelper.sqrt_double(motionX * motionX + motionZ * motionZ);
        rotationYaw = (float)((Math.atan2(motionX, motionZ) * 180D) / 3.1415927410125732D);
        for(rotationPitch = (float)((Math.atan2(motionY, f1) * 180D) / 3.1415927410125732D); rotationPitch - prevRotationPitch < -180F; prevRotationPitch -= 360F) { }
        for(; rotationPitch - prevRotationPitch >= 180F; prevRotationPitch += 360F) { }
        for(; rotationYaw - prevRotationYaw < -180F; prevRotationYaw -= 360F) { }
        for(; rotationYaw - prevRotationYaw >= 180F; prevRotationYaw += 360F) { }
        rotationPitch = prevRotationPitch + (rotationPitch - prevRotationPitch) * 0.2F;
        rotationYaw = prevRotationYaw + (rotationYaw - prevRotationYaw) * 0.2F;
        float f2 = 1.0025574F; // Should hit 1.25 in 10 seconds
        float f4 = 0F; // No drop
        if(handleWaterMovement())
        {
            for(int k = 0; k < 4; k++)
            {
                float f5 = 0.25F;
                worldObj.spawnParticle("bubble", posX - motionX * (double)f5, posY - motionY * (double)f5, posZ - motionZ * (double)f5, motionX, motionY, motionZ);
            }

            f2 = 0.95F; // Slow down less in water
            f4 = 0.03F; // Drop in water
        }
        motionX *= f2;
        motionY *= f2;
        motionZ *= f2;
        motionY -= f4;
        setPosition(posX, posY, posZ);
    }

	private void explode()
	{
		worldObj.createExplosion(null, posX, posY, posZ, 3F);
		setEntityDead();
	}
}
