package net.minecraft.src;

import java.util.List;
import java.util.Random;

// SDK
public class SdkZcEntityBulletCasing extends Entity
{
    public SdkZcEntityBulletCasing(World world)
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
    
    public SdkZcEntityBulletCasing(World world, double d, double d1, double d2)
    {
        super(world);
        xTile = -1;
        yTile = -1;
        zTile = -1;
        inTile = 0;
        inGround = false;
        timeInAir = 0;
        setSize(0.0625F, 0.03125F);
        setPosition(d, d1, d2);
        yOffset = 0.0F;
    }
    
    public SdkZcEntityBulletCasing(World world, EntityLiving entityliving, float heightOffset)
    {
        super(world);
        xTile = -1;
        yTile = -1;
        zTile = -1;
        inTile = 0;
        inGround = false;
        timeInAir = 0;
        entityCreatedBy = entityliving;
        createdByPlayer = entityCreatedBy instanceof EntityPlayer;
        setSize(0.0625F, 0.03125F);
        
        setLocationAndAngles(entityliving.posX, entityliving.posY + (double)entityliving.getYOffset() + heightOffset, entityliving.posZ, entityliving.rotationYaw, entityliving.rotationPitch);
        if(entityliving instanceof EntityPlayer)
    	{
	        posX -= MathHelper.cos((rotationYaw / 180F) * 3.141593F) * 0.16F;
	        posY -= 0.10000000149011612D;
	        posZ -= MathHelper.sin((rotationYaw / 180F) * 3.141593F) * 0.16F;
    	}
        
        // Around 90 degrees
        rotationYaw += 90;
        if(rotationYaw > 360)
        	rotationYaw -= 360;
        
        // Up 30 degrees
        rotationPitch -= 30;
        if (rotationPitch < -90)
        {
        	rotationPitch = (rotationPitch + 180) * -1;
        	rotationYaw *= -1;
        }
        
        posX -= MathHelper.cos((rotationYaw / 180F) * 3.141593F) * 0.16F;
        posY -= 0.10000000149011612D;
        posZ -= MathHelper.sin((rotationYaw / 180F) * 3.141593F) * 0.16F;
        setPosition(posX, posY, posZ);
        yOffset = 0.0F;
        motionX = -MathHelper.sin((rotationYaw / 180F) * 3.141593F) * MathHelper.cos((rotationPitch / 180F) * 3.141593F);
        motionZ = MathHelper.cos((rotationYaw / 180F) * 3.141593F) * MathHelper.cos((rotationPitch / 180F) * 3.141593F);
        motionY = -MathHelper.sin((rotationPitch / 180F) * 3.141593F);
        setPositionAndRotation(motionX, motionY, motionZ, 0.25F, 1.0F); // Fourth parameter is initial velocity, was 1.5F
    }

    @Override
    protected void entityInit() { }
    
    @Override
    public boolean isInRangeToRenderDist(double d)
    {
        return true;
    }

    @Override
    public void setPositionAndRotation(double d, double d1, double d2, float f, float f1)
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
    public void onUpdate()
    {
        super.onUpdate();
        if(inGround)
        {
        	if(!(entityCreatedBy instanceof EntityPlayer) && !worldObj.multiplayerWorld)
            {
            	setEntityDead();
            }
        	
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
                if(timeInTile == 1200)
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
            if(!entity1.canBeCollidedWith() || entity1 == entityCreatedBy && timeInAir < 5)
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
            if(movingobjectposition.entityHit == null)
            {
                xTile = movingobjectposition.blockX;
                yTile = movingobjectposition.blockY;
                zTile = movingobjectposition.blockZ;
                inTile = worldObj.getBlockId(xTile, yTile, zTile);
                motionX = (float)(movingobjectposition.hitVec.xCoord - posX);
                motionY = (float)(movingobjectposition.hitVec.yCoord - posY);
                motionZ = (float)(movingobjectposition.hitVec.zCoord - posZ);
                float f = MathHelper.sqrt_double(motionX * motionX + motionY * motionY + motionZ * motionZ);
                double rewindValue = 0.025D;
                posX -= (motionX / (double)f) * rewindValue;
                posY -= (motionY / (double)f) * rewindValue;
                posZ -= (motionZ / (double)f) * rewindValue;
                inGround = true;
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
        float f4 = 0.1F; // Increase arc, was 0.03F
        if(handleWaterMovement())
        {
            for(int k = 0; k < 4; k++)
            {
                float f5 = 0.25F;
                worldObj.spawnParticle("bubble", posX - motionX * (double)f5, posY - motionY * (double)f5, posZ - motionZ * (double)f5, motionX, motionY, motionZ);
            }

            f2 = 0.8F;
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
        nbttagcompound.setByte("createdByPlayer", (byte)(createdByPlayer ? 1 : 0));
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound nbttagcompound)
    {
        xTile = nbttagcompound.getShort("xTile");
        yTile = nbttagcompound.getShort("yTile");
        zTile = nbttagcompound.getShort("zTile");
        inTile = nbttagcompound.getByte("inTile") & 0xff;
        inGround = nbttagcompound.getByte("inGround") == 1;
        createdByPlayer = nbttagcompound.getByte("createdByPlayer") == 1;
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
        entityCreatedBy = null;
    }

    private int xTile;
    private int yTile;
    private int zTile;
    private int inTile;
    private boolean inGround;
    private EntityLiving entityCreatedBy;
    private int timeInTile;
    private int timeInAir;
    private boolean createdByPlayer;
}
