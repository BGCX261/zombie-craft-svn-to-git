package net.minecraft.src;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

import java.util.List;
import java.util.Random;

public class EntityBunny extends EntityMobs
{

    public EntityBunny(World world)
    {
        super(world);
        field_22059_a = false;
        field_22058_b = 0.0F;
        field_22057_c = 0.0F;
        field_22056_f = 1.0F;
        moveSpeed = 1.5F;
        texture = "/mob/bunny.png";
        yOffset = -0.16F;
        setSize(0.4F, 0.4F);
        health = 1;
		attackStrength = 1;  
		playerToGuard = ModLoader.getMinecraftInstance().thePlayer;
		
    }

    public void onLivingUpdate()
    {
        super.onLivingUpdate();
		field_22057_q = field_22058_b;
        field_22057_z = field_22057_c;
        field_22057_c = (float)((double)field_22057_c + (double)(onGround ? -1 : 4) * 0.29999999999999999D);
        if(field_22057_c < 0.0F)
        {
            field_22057_c = 0.0F;
        }
        if(field_22057_c > 1.0F)
        {
            field_22057_c = 1.0F;
        }
        if(!onGround && field_22056_f < 1.0F)
        {
            field_22056_f = 1.0F;
        }
        field_22056_f = (float)((double)field_22056_f * 0.90000000000000002D);
        field_22058_b += field_22056_f * 2.0F;
    }
	
	public void onDeath(Entity entity)
    {
		Explosion explosion = new Explosion(worldObj, entity, posX, posY, posZ, 1f, true);
        explosion.field_12257_a = false;
        explosion.func_12248_a();
        explosion.func_12247_b();
		worldObj.playSoundAtEntity(this, "random.pop", 1.0F, (rand.nextFloat() - rand.nextFloat()) * 0.2F + 1.0F);
        dropItem((mod_ZombieCraft.egg1.shiftedIndex + worldObj.rand.nextInt(3)), 1 + worldObj.rand.nextInt(2));
    }
	
	protected void attackEntity(Entity entity, float f)
    {
	}
	
	protected Entity findPlayerToAttack() {
		
		Entity clEnt = null;
		
		Entity player = worldObj.getClosestPlayerToEntity(this, -1);
		
		if (clEnt == null) { clEnt = super.findPlayerToAttack(); }
		
		if (clEnt instanceof EntityPlayer || clEnt instanceof EntityFriend) {
			//System.out.println("null out clEnt");
			clEnt = null;
		}
		
		if (super.playerToGuard != null) {
			if (getDistanceToEntity(super.playerToGuard) > guardDist) {
				//playerToAttack = null;
				//clEnt = null;
			} else {
				/*if (clEnt instanceof EntityNode || (clEnt instanceof EntityPlayer)) {
					clEnt = null;
					playerToAttack = null;
				}*/
			}
		} else {
			//super.playerToGuard = worldObj.getClosestPlayerToEntity(this, -1);
			
		}
		
		return clEnt;		
	}

    public void onUpdate()
    {        
        super.onUpdate();
    }

    protected void fall(float f)
    {
    }

    protected void updatePlayerActionState()
    {
        if(onGround && (motionX > 0.050000000745058101D || motionZ > 0.050000000745058101D || motionX < -0.050000000745058101D || motionZ < -0.050000000745058101D))
        {
            motionY = 0.44999998807907099D;
        }
        if(!field_22055_h)
        {
            super.updatePlayerActionState();
        } else
        if(onGround)
        {
            field_22055_h = false;
            List list = worldObj.getEntitiesWithinAABBExcludingEntity(this, boundingBox.expand(12D, 12D, 12D));
            for(int i = 0; i < list.size(); i++)
            {
                Entity entity = (Entity)list.get(i);
                if(entity instanceof EntityMobs)
                {
                    EntityMobs entitymobs = (EntityMobs)entity;
                    entitymobs.playerToAttack = this;
                }
            }

        }
    }

    public boolean interact(EntityPlayer entityplayer)
    {
        rotationYaw = entityplayer.rotationYaw;
        mountEntity(entityplayer);
        motionX = entityplayer.motionX * 5D;
        motionY = entityplayer.motionY / 2D + 0.5D;
        motionZ = entityplayer.motionZ * 5D;
        return true;
    }

    public double getYOffset()
    {
        if(ridingEntity instanceof EntityPlayer)
        {
            return (double)(yOffset - 1.15F);
        } else
        {
            return (double)yOffset;
        }
    }

    protected String getLivingSound()
    {
        return null;
    }

    protected String getHurtSound()
    {
        return "rabbithurt";
    }

    protected String getDeathSound()
    {
        return "rabbitdeath";
    }

    public boolean field_22059_a;
    public float field_22058_b;
    public float field_22057_c;
    public float field_22057_z;
    public float field_22052_d1;
    public float field_22057_q;
    public float field_22056_f;
    public boolean field_22055_h;
}
