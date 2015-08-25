package net.minecraft.src;

import java.util.ArrayList;

// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode

// SDK
public class SdkZcEntityGrenadeStun extends SdkZcEntityGrenade
{

	public SdkZcEntityGrenadeStun(World world)
    {
        super(world);
        item = new ItemStack(mod_SdkZombieCraft.itemGrenadeStun.shiftedIndex, 1, 0);
    }
    
    public SdkZcEntityGrenadeStun(World world, double d, double d1, double d2)
    {
        super(world, d, d1, d2);
        item = new ItemStack(mod_SdkZombieCraft.itemGrenadeStun.shiftedIndex, 1, 0);
    }

    public SdkZcEntityGrenadeStun(World world, Entity entity)
    {
        super(world, entity);
        item = new ItemStack(mod_SdkZombieCraft.itemGrenadeStun.shiftedIndex, 1, 0);
    }

    @Override
    protected void explode()
    {
       if(!exploded)
       {
          exploded = true;
          
          // Play sound
          worldObj.playSoundAtEntity(this, "sdkzc.stungrenade", 4F, 1.0F / (rand.nextFloat() * 0.1F + 0.95F));
          
          // Find entities
          ArrayList<EntityLiving> entityLivings = getEntityLivingsInRange(MAX_DISTANCE);
          
          for(int i = 0; i < entityLivings.size(); i++)
          {
        	  EntityLiving entityLiving = entityLivings.get(i);
        	  
        	  if(entityLiving.canEntityBeSeen(this))
        	  {
		          // Get distances
		  		  double deltaX = posX - entityLiving.posX;
		  		  double deltaY = posY - entityLiving.posY;
		  		  double deltaZ = posZ - entityLiving.posZ;
		  		  
		  		  // Get entity pitch
		  		  float entityPitch = entityLiving.rotationPitch;
					
		  		  // Get player-to-grenade pitch
		  		  float p2gPitch = (float)(Math.atan(Math.sqrt(deltaX * deltaX + deltaZ * deltaZ) / deltaY) * (180D / Math.PI));
		  		  if(deltaY >= 0F)
		  			  p2gPitch -= 90F;
		  		  else
		  			  p2gPitch += 90F;
					
		  		  // Get delta between entity and p2g pitch
		  		  float deltaPitch = entityPitch - p2gPitch;
		  		  
		  		  // Get entity yaw
		  		  float entityYaw = entityLiving.rotationYaw % 360;
		  		  if(entityYaw < -180F)
		  			  entityYaw += 360F;
		  		  if(entityYaw < 0F)
		  			  entityYaw *= -1;
		  		  else if(entityYaw < 180F)
		  			  entityYaw *= -1;
		  		  else
		  			  entityYaw = 360F - entityYaw;
					
		  		  // Get player-to-grenade yaw
		  		  float p2gYaw;
		  		  if(deltaX >= 0F && deltaZ >= 0F)
		  			  p2gYaw = (float)(Math.atan(Math.abs(deltaX / deltaZ)) * (180D / Math.PI));
		  		  else if(deltaX >= 0F && deltaZ <= 0F)
		  			  p2gYaw = 90F + (float)(Math.atan(Math.abs(deltaZ / deltaX)) * (180D / Math.PI));
		  		  else if(deltaX <= 0F && deltaZ >= 0F)
		  			  p2gYaw = -(90F - (float)(Math.atan(Math.abs(deltaZ / deltaX)) * (180D / Math.PI)));
		  		  else
		  			  p2gYaw = -(180F - (float)(Math.atan(Math.abs(deltaX / deltaZ)) * (180D / Math.PI)));
					
		  		  // Get delta between entity and p2g yaw
		  		  float deltaYaw = p2gYaw - entityYaw;
		  		  if (deltaYaw > 180F)
		  			  deltaYaw -= 360F;
		  		  else if (deltaYaw < -180F)
		  			  deltaYaw += 360F;
		  		  
		  		  // Get delta pitch factor
		  		  deltaPitch = Math.abs(deltaPitch);
		  		  float deltaPitchFactor;
		  		  if(deltaPitch < MIN_PITCH_ANGLE)
		  			  deltaPitchFactor = 1F;
		  		  else if(deltaPitch > MAX_ANGLE)
		  			  deltaPitchFactor = 0F;
		  		  else
		  			  deltaPitchFactor = 1F - (float)((deltaPitch - MIN_PITCH_ANGLE) / (MAX_ANGLE - MIN_PITCH_ANGLE));
		  		  
		  		  // Get delta yaw factor
		  		  deltaYaw = Math.abs(deltaYaw);
		  		  float deltaYawFactor;
		  		  if(deltaYaw < MIN_YAW_ANGLE)
		  			  deltaYawFactor = 1F;
		  		  else if(deltaYaw > MAX_ANGLE)
		  			deltaYawFactor = 0F;
		  		  else
		  			  deltaYawFactor = 1F - (float)((deltaYaw - MIN_YAW_ANGLE) / (MAX_ANGLE - MIN_YAW_ANGLE));
		  		  
		  		  // Get delta factor
		  		  float deltaFactor = Math.min(deltaPitchFactor, deltaYawFactor);
		  		  
		  		  // Get distance factor
		  		  float distance = getDistanceToEntity(entityLiving);
		  		  float distanceFactor;
		  		  if(distance < MIN_DISTANCE)
		  			  distanceFactor = 1F;
		  		  else
		  			  distanceFactor = 1F - (float)((distance - MIN_DISTANCE) / (MAX_DISTANCE - MIN_DISTANCE));
		  		  
		  		  // Get grenade flash time
	        	  int grenadeFlashTime;
	        	  if(entityLiving instanceof EntityPlayer)
	        		  grenadeFlashTime = (int)Math.round(MAX_FLASH_TIME_PLAYER * distanceFactor * deltaFactor);
	        	  else
	        		  grenadeFlashTime = (int)Math.round(MAX_FLASH_TIME_ENTITY * distanceFactor);
	        	  
	        	  // Set grenade flash time
	        	  if(!mod_SdkZombieCraft.stunGrenadeFlashTimes.containsKey(entityLiving)
	        			  || mod_SdkZombieCraft.stunGrenadeFlashTimes.get(entityLiving).getLeft() < grenadeFlashTime)
	        	  {
	        		  
	        		  mod_SdkZombieCraft.stunGrenadeFlashTimes.put(entityLiving, new SdkZcPair<Integer, Float>(grenadeFlashTime, entityLiving.moveSpeed));
	        	  }
	        	  
	        	  // Stop non-players from moving and attacking
	        	  if(!(entityLiving instanceof EntityPlayer))
	        	  {
	        		  entityLiving.moveSpeed = 0.0F;
	        		  entityLiving.attackTime = grenadeFlashTime;
	        	  }
        	  }
          }
          
    	  // Die
    	  isDead = true;
       }
    }
    
    public ArrayList<EntityLiving> getEntityLivingsInRange(double distance)
    {
    	ArrayList<EntityLiving> entityLivings = new ArrayList<EntityLiving>();
        for(int i = 0; i < worldObj.loadedEntityList.size(); i++)
        {
            Entity entity = (Entity)worldObj.loadedEntityList.get(i);
            if(entity instanceof EntityLiving && entity.isEntityAlive())
            {
	            if(getDistanceSqToEntity(entity) < distance * distance)
	            {
	            	entityLivings.add((EntityLiving)entity);
	            }
            }
        }

        return entityLivings;
    }
    
    public ArrayList<EntityPlayer> getPlayersInRange(double distance)
    {
    	ArrayList<EntityPlayer> players = new ArrayList<EntityPlayer>();
        for(int i = 0; i < worldObj.loadedEntityList.size(); i++)
        {
            Entity entity = (Entity)worldObj.loadedEntityList.get(i);
            if(entity instanceof EntityPlayer && entity.isEntityAlive())
            {
	            if(getDistanceSqToEntity(entity) < distance * distance)
	            {
	            	players.add((EntityPlayer)entity);
	            }
            }
        }

        return players;
    }
    
	protected String BOUNCE_SOUND = "sdkzc.stungrenadebounce";
    
    static final double MAX_DISTANCE = 32D;
    static final double MIN_DISTANCE = MAX_DISTANCE / 4;
    static final float MAX_ANGLE = 180F;
    static final float MIN_PITCH_ANGLE = 15F;
    static final float MIN_YAW_ANGLE = 15F;
    public static final int MAX_FLASH_TIME_PLAYER = 1000;
    public static final int MAX_FLASH_TIME_ENTITY = 200;
}
