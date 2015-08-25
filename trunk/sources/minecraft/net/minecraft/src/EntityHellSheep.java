package net.minecraft.src;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 
import java.util.Random;

public class EntityHellSheep extends EntityMobs
{

    public EntityHellSheep(World world)
    {
        super(world);
        texture = "/mob/sheep.png";
        setSize(0.9F, 0.9F);
		moveSpeed = 0.8F;
		scoreValue = 100;
    }
	    protected void entityInit()
    {
        super.entityInit();
        dataWatcher.addObject(16, Byte.valueOf((byte)-1));
    }

    public void writeEntityToNBT(NBTTagCompound nbttagcompound)
    {
        super.writeEntityToNBT(nbttagcompound);
    }

    public void readEntityFromNBT(NBTTagCompound nbttagcompound)
    {
        super.readEntityFromNBT(nbttagcompound);
    }

    public void onUpdate()
    {
        lastActiveTime = timeSinceIgnited;
        if(worldObj.multiplayerWorld)
        {
            int i = func_21091_q();
            if(i > 0 && timeSinceIgnited == 0)
            {
                worldObj.playSoundAtEntity(this, "random.fuse", 1.0F, 0.5F);
            }
            timeSinceIgnited += i;
            if(timeSinceIgnited < 0)
            {
                timeSinceIgnited = 0;
            }
            if(timeSinceIgnited >= 15)
            {
                timeSinceIgnited = 15;
            }
        }
        super.onUpdate();
    }

    protected void attackEntity(Entity entity, float f)
    {
        int i = func_21091_q();
        if(i <= 0 && f < 3F || i > 0 && f < 7F)
        {
            if(timeSinceIgnited == 0)
            {
                worldObj.playSoundAtEntity(this, "random.fuse", 1.0F, 0.5F);
            }
            func_21090_e(1);
            timeSinceIgnited++;
            if(timeSinceIgnited >= 15)
            {
                worldObj.createExplosion(this, posX, posY, posZ, 2F);
                setEntityDead();
            }
            hasAttacked = true;
        } else
        {
            func_21090_e(-1);
            timeSinceIgnited--;
            if(timeSinceIgnited < 0)
            {
                timeSinceIgnited = 0;
            }
        }
    }

    public float func_440_b(float f)
    {
        return ((float)lastActiveTime + (float)(timeSinceIgnited - lastActiveTime) * f) / 28F;
    }

    private int func_21091_q()
    {
        return dataWatcher.getWatchableObjectByte(16);
    }

    private void func_21090_e(int i)
    {
        dataWatcher.updateObject(16, Byte.valueOf((byte)i));
    }
	
	public void onUpdate(World world, int i, int j, int k, Random random)
    {
		float f4 = (float)i + random.nextFloat();
        float f10 = (float)j + random.nextFloat();
        float f16 = (float)(k + 1) - random.nextFloat() * 0.1F;
		world.spawnParticle("largesmoke", f4, f10, f16, 0.0D, 0.0D, 0.0D);
        super.onUpdate();
    }
	
	public void onDeath(Entity entity)
    {
        if(scoreValue > 0)
        {
            ModLoader.getMinecraftInstance().thePlayer.addToPlayerScore(this, scoreValue);
			worldObj.zombieKills++;
			worldObj.totalZombieKills++;
        }
        field_9327_S = true;
        if(!worldObj.multiplayerWorld)
        {
            func_21066_o();
        }
        worldObj.func_9425_a(this, (byte)3);
    }
	
	protected String getLivingSound()
    {
        return "";
    }

    protected String getHurtSound()
    {
        return "";
    }

    protected String getDeathSound()
    {
        return "";
    }
    
	int timeSinceIgnited;
    int lastActiveTime;
}
