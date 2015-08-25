package net.minecraft.src;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

import java.util.List;
import java.util.Random;

// SDK
public class SdkZcEntityBullet extends Entity
{
    public SdkZcEntityBullet(World world)
    {
        super(world);
        xTile = -1;
        yTile = -1;
        zTile = -1;
        inTile = 0;
        inGround = false;
        timeInAir = 0;
        setSize(0.0625F, 0.03125F);
    }
    
    public SdkZcEntityBullet(World world, double d, double d1, double d2)
    {
        this(world);
        
        setPosition(d, d1, d2);
        yOffset = 0.0F;
        
        serverSpawned = true;
    }
    
    public SdkZcEntityBullet(World world, EntityLiving entityliving, float heightOffset, SdkZcItemGun gun)
    {
        this(world);
        entityFiredBy = entityliving;
        damage = gun.damage;
        
        setLocationAndAngles(entityliving.posX, entityliving.posY + (double)entityliving.getEyeHeight() + heightOffset, entityliving.posZ, entityliving.rotationYaw, entityliving.rotationPitch);
        posX -= MathHelper.cos((rotationYaw / 180F) * 3.141593F) * 0.16F;
        posY -= 0.10000000149011612D;
        posZ -= MathHelper.sin((rotationYaw / 180F) * 3.141593F) * 0.16F;
        setPosition(posX, posY, posZ);
        yOffset = 0.0F;
        motionX = -MathHelper.sin((rotationYaw / 180F) * 3.141593F) * MathHelper.cos((rotationPitch / 180F) * 3.141593F);
        motionZ = MathHelper.cos((rotationYaw / 180F) * 3.141593F) * MathHelper.cos((rotationPitch / 180F) * 3.141593F);
        motionY = -MathHelper.sin((rotationPitch / 180F) * 3.141593F);
        
        // Get base spread
        float spread = gun.spread;
        
        // If has recoil
        if(entityliving instanceof EntityPlayer)
        {
        	float recoilPerTick = gun.recoil / gun.useDelay;
        	float maxRecoil = recoilPerTick / (float)mod_SdkZombieCraft.RECOIL_FIX_FACTOR;
        	
        	// Add recoil spread
        	if(maxRecoil > 0)
	        	spread *= 1 + (mod_SdkZombieCraft.currentRecoilV / maxRecoil);
        }
        
        // If moving
        if(Math.abs(entityliving.motionX) > 0.1D || Math.abs(entityliving.motionY) > 0.1D || Math.abs(entityliving.motionZ) > 0.1D)
        {
        	spread *= 2;
        }
        // If in the air
        if(!entityliving.onGround)
        {
        	spread *= 2;
        }
        
        setArrowHeading(motionX, motionY, motionZ,
        		(float)(gun.muzzleVelocity + (worldObj.rand.nextFloat() - 0.5 ) * gun.muzzleVelocityRandomness), spread);
    }

    @Override
    protected void entityInit() { }

    public void setArrowHeading(double d, double d1, double d2, float f,
    		float f1)
    {
        float f2 = MathHelper.sqrt_double(d * d + d1 * d1 + d2 * d2);
        d /= f2;
        d1 /= f2;
        d2 /= f2;
        d += rand.nextGaussian() * 0.0074999998323619366D * (double)f1;
        d1 += rand.nextGaussian() * 0.0074999998323619366D * (double)f1;
        d2 += rand.nextGaussian() * 0.0074999998323619366D * (double)f1;
        d *= f;
        d1 *= f;
        d2 *= f;
        motionX = d;
        motionY = d1;
        motionZ = d2;
        float f3 = MathHelper.sqrt_double(d * d + d2 * d2);
        prevRotationYaw = rotationYaw = (float)((Math.atan2(d, d2) * 180D) / 3.1415927410125732D);
        prevRotationPitch = rotationPitch = (float)((Math.atan2(d1, f3) * 180D) / 3.1415927410125732D);
        timeInTile = 0;
    }
    
    @Override
    public boolean isInRangeToRenderDist(double d)
    {
        return true;
    }

    @Override
    public void onUpdate()
    {
        super.onUpdate();
        
        // So it doesn't go on forever
        if(timeInAir == 200)
        {
            setEntityDead();
        }
        
        if(prevRotationPitch == 0.0F && prevRotationYaw == 0.0F)
        {
            float f = MathHelper.sqrt_double(motionX * motionX + motionZ * motionZ);
            prevRotationYaw = rotationYaw = (float)((Math.atan2(motionX, motionZ) * 180D) / 3.1415927410125732D);
            prevRotationPitch = rotationPitch = (float)((Math.atan2(motionY, f) * 180D) / 3.1415927410125732D);
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
            } else
            {
                timeInTile++;
                if(timeInTile == 200)
                {
                    setEntityDead();
                }
                return;
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
            if(!entity1.canBeCollidedWith() || (entity1 == entityFiredBy || entity1 instanceof EntityPlayer || entity1 instanceof EntityFriend || (entityFiredBy != null && entity1 == entityFiredBy.ridingEntity)) && timeInAir < 5 || serverSpawned)
            {
                continue;
            }
            if (entity1 instanceof EntityLiving) {
            	if (((EntityLiving)entity1).health < 1) {
            		continue;
            	}
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
        		}
        		
        		if(movingobjectposition.entityHit instanceof EntityLiving)
        		{
        			if(!movingobjectposition.entityHit.attackEntityFrom(entityFiredBy, damage))
        				((EntityLiving)movingobjectposition.entityHit).attackEntityFrom2(entityFiredBy, damage, true);
        		}
        		else
        			movingobjectposition.entityHit.attackEntityFrom(entityFiredBy, damage);
        	} else
            {
                xTile = movingobjectposition.blockX;
                yTile = movingobjectposition.blockY;
                zTile = movingobjectposition.blockZ;
                inTile = worldObj.getBlockId(xTile, yTile, zTile);
                //if fence
                if (inTile == 85) {
                	cancelStop = true;
                //if door, and if 4th bit is true(top door piece)
                } else if (((BlockBarricade)mod_ZombieCraft.barricadeS5).isBarricade(worldObj, xTile, yTile, zTile)) {
                	if ((worldObj.getBlockMetadata(xTile, yTile, zTile) & 8) != 0) {
                		cancelStop = true;
                	}
                } else {
	                motionX = (float)(movingobjectposition.hitVec.xCoord - posX);
	                motionY = (float)(movingobjectposition.hitVec.yCoord - posY);
	                motionZ = (float)(movingobjectposition.hitVec.zCoord - posZ);
	                float f = MathHelper.sqrt_double(motionX * motionX + motionY * motionY + motionZ * motionZ);
	                posX -= (motionX / (double)f) * 0.05000000074505806D;
	                posY -= (motionY / (double)f) * 0.05000000074505806D;
	                posZ -= (motionZ / (double)f) * 0.05000000074505806D;
	                inGround = true;
                }
                
                // Break glass on impact
                if(inTile == Block.glass.blockID && Block.glass.blockHardness < 1000000F)
                {
                	mod_SdkZombieCraft.mc.effectRenderer.func_1186_a(xTile, yTile, zTile);
                	mod_SdkZombieCraft.mc.sndManager.playSound(Block.glass.stepSound.func_1146_a(), (float)xTile + 0.5F, (float)yTile + 0.5F, (float)zTile + 0.5F, (Block.glass.stepSound.func_1147_b() + 1.0F) / 2.0F, Block.glass.stepSound.func_1144_c() * 0.8F);
                	worldObj.setBlockWithNotify(xTile, yTile, zTile, 0);
                	Block.glass.onBlockDestroyedByPlayer(worldObj, xTile, yTile, zTile, worldObj.getBlockMetadata(xTile, yTile, zTile));
                }
                
                
            }
            
        	if (!cancelStop) {
        		//worldObj.playSoundAtEntity(this, "sdkzc.impact", 0.2F, 1.0F / (rand.nextFloat() * 0.1F + 0.95F));
        		setEntityDead();
        	} else {
        		cancelStop = false;
        	}
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
        float f2 = 0.99F;
        float f4 = 0F; // Removed drop off, was 0.03F
        if(handleWaterMovement())
        {
            for(int k = 0; k < 4; k++)
            {
                float f5 = 0.25F;
                worldObj.spawnParticle("bubble", posX - motionX * (double)f5, posY - motionY * (double)f5, posZ - motionZ * (double)f5, motionX, motionY, motionZ);
            }

            f2 = 0.8F;
            f4 = 0.03F; // Drop in water
        }
        motionX *= f2;
        motionY *= f2;
        motionZ *= f2;
        motionY -= f4;
        setPosition(posX, posY, posZ);
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound nbttagcompound)
    {
        nbttagcompound.setShort("xTile", (short)xTile);
        nbttagcompound.setShort("yTile", (short)yTile);
        nbttagcompound.setShort("zTile", (short)zTile);
        nbttagcompound.setByte("inTile", (byte)inTile);
        nbttagcompound.setByte("inGround", (byte)(inGround ? 1 : 0));
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound nbttagcompound)
    {
        xTile = nbttagcompound.getShort("xTile");
        yTile = nbttagcompound.getShort("yTile");
        zTile = nbttagcompound.getShort("zTile");
        inTile = nbttagcompound.getByte("inTile") & 0xff;
        inGround = nbttagcompound.getByte("inGround") == 1;
    }

    @Override
    public float getShadowSize()
    {
        return 0.0F;
    }
    
    @Override
    public void setEntityDead()
    {
        super.setEntityDead();
        entityFiredBy = null;
    }

    protected int xTile;
    protected int yTile;
    protected int zTile;
    protected int inTile;
    protected boolean inGround;
    public EntityLiving entityFiredBy;
    protected int timeInTile;
    protected int timeInAir;

	protected int damage;
	protected boolean serverSpawned;
	
	public boolean cancelStop = false;
}
