package net.minecraft.src;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

import java.util.Random;
import net.minecraft.client.Minecraft;

public class EntityZombie extends EntityMobs
{

    public EntityZombie(World world)
    {
        super(world);
        texture = "/mob/zombie.png";
        moveSpeed = 0.4F;
        attackStrength = 5;
		scoreValue = 100;
    }

    public void onLivingUpdate()
    {
        if(worldObj.isDaytime())
        {
            float f = getEntityBrightness(1.0F);
            if(f > 0.5F && worldObj.canBlockSeeTheSky(MathHelper.floor_double(posX), MathHelper.floor_double(posY), MathHelper.floor_double(posZ)) && rand.nextFloat() * 30F < (f - 0.4F) * 2.0F)
            {
                //fire = 300;
            }
        }
		super.onLivingUpdate();
    }

    protected String getLivingSound()
    {
        return "mob.zombie";
    }

    protected String getHurtSound()
    {
        return "mob.zombiehurt";
    }

    protected String getDeathSound()
    {
        return "mob.zombiedeath";
    }

    protected int getDropItemId()
    {
		Random rand = new Random(); 		
		int i = rand.nextInt(4);
		if (rand.nextInt(40)==0)
		{
			if (i==0) {
				return mod_ZombieCraft.nuke.shiftedIndex;
			} else if (i==1) {
				return mod_ZombieCraft.doublePoints.shiftedIndex;		
			} else if (i==2) {		
				return mod_ZombieCraft.instaKill.shiftedIndex;
			} else if (i==3) {		
				return mod_ZombieCraft.maxAmmo.shiftedIndex;
			}
		}
        return 0;//Item.feather.shiftedIndex;
    }
	
	public void onUpdate()
    {
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
}
