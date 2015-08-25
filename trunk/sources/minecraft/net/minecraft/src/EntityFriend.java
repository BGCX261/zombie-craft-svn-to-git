package net.minecraft.src;

import java.util.List;
import java.util.Random;

public class EntityFriend extends EntityMobs
{
/* not done at all so dont worry if you feel the need to edit it go ahead <3 Justin*/
	public EntityFriend(World world)
    {
        super(world);
        randomSoundDelay = 0;
        texture = getRandSkin();
        moveSpeed = 0.6F;
        attackStrength = 6;
        isImmuneToFire = true;
        super.playerToGuard = ModLoader.getMinecraftInstance().thePlayer;
    }
    
    public String getRandSkin() {
    	return new StringBuilder().append("/comrade/skin").append(rand.nextInt(20)).append(".png").toString();
    }
    
	public void onUpdate()
    {
		
        
        /*Entity player = worldObj.getClosestPlayerToEntity(this, -1);
        
        
		if (getDistanceToEntity(player) < guardDist) {
			if (playerToAttack instanceof EntityPlayer) {
				playerToAttack = null;
				//System.out.println("3");
			}
		} else {
			
		}*/
		
		/*if (playerToGuard != null) {
	    	if (!canEntityBeSeen(playerToGuard)) {
	    		cantSeeTicks++;
	    	} else {
	    		cantSeeTicks = 0;
	    	}
    	}*/
		
		
		if (playerToAttack == null) {
			
			playerToAttack = findPlayerToAttack();
			
		}
		
		/*if (playerToGuard != null) {
			mod_EntAI.displayMessage(new StringBuilder().append(getDistanceToEntity(playerToGuard)).append("").toString());
		}*/
		
		super.onUpdate();
       
		
        //BlockDoor = new 
        //((BlockDoor)mod_ZombieCraft.barricadeS5).isBarricade(this.worldObj,(int)posX,(int)posY,(int)posZ);
        
        
        /*for(int i = 0; i < worldObj.loadedEntityList.size(); i++)
        {
            Entity entity1 = (Entity)worldObj.loadedEntityList.get(i);
           
           
            if(!(entity1 instanceof EntityPlayer))
            {
                continue;
            }
           
            
            double d2 = entity1.getDistance(posX, posY, posZ);
            EntityFriend friend = (EntityFriend)entity1;
            if((d2 < 16) && friend.canEntityBeSeen(this) && friend.playerToAttack == null)
            {
                friend.playerToAttack = this;
            }
        }*/
    }
	
	protected Entity seekCloserTarget() {
    	
		/*if (playerToAttack != null) {
			if (!this.canEntityBeSeen(playerToAttack)) {
				playerToAttack = null;
			}
		}*/
		
		//if (true) return null;
		
    	if (AIDelay < 0) {
    		AIDelay = rand.nextInt(5);
    		return findPlayerToAttack();
    	} else {
    		return null;
    	}
    	
    }
	
	protected Entity findPlayerToAttack() {
		
		//System.out.println("find...");

		List entList = worldObj.getEntitiesWithinAABBExcludingEntity((Entity)this,this.boundingBox.expand(16D,16D,16D));
		Entity tEnt = null;
		Entity clEnt = null;

		float clDist = 90000;
		float curDist;
		
		for (int i = 0; i < entList.size(); i++) {
			
			tEnt = (Entity)entList.get(i);
			
			if (tEnt instanceof EntityMobs && !(tEnt instanceof EntityFriend)) {
				
				if (canEntityBeSeen3(tEnt)) {
					curDist = getDistanceToEntity(tEnt);
					
					if (curDist < clDist) {
						clDist = curDist;
						clEnt = tEnt;
					}
				}
				
			}
			
		}
		
		/*if (clEnt == null) {
			if (getDistanceToEntity(playerToGuard) > guardDist) {
				clEnt = playerToGuard;
			}
		}*/
		
		Entity player = worldObj.getClosestPlayerToEntity(this, -1);
		
		if (clEnt == null) { clEnt = super.findPlayerToAttack(); }
		
		if (clEnt instanceof EntityPlayer || clEnt instanceof EntityFriend) {
			//System.out.println("null out clEnt");
			clEnt = null;
		}
		
		//System.out.println(clEnt);
		
		/*if (playerToAttack instanceof EntityPlayer) {
			if (getDistanceToEntity(player) < guardDist) {
				playerToAttack = null;
				clEnt = null;
				//System.out.println("1");
			}
		}*/
		
		//if (playerToAttack instanceof EntityPlayer) {
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
		//}
		
		//super.playerToGuard = worldObj.getClosestPlayerToEntity(this, -1);
		
		//System.out.println(clEnt);
		//System.out.println(playerToAttack);
		return clEnt;
		
	}
	
	public boolean attackEntityFrom(Entity entity, int i) {
		if (entity instanceof EntityPlayer) {
			this.orders = this.orders == 1 ? 0 : 1;
			return false;
		}
		return super.attackEntityFrom(entity, i);
	}
	
	protected void attackEntity(Entity entity, float f)
    {
        if(f < 10F)
        {
        	
        	
        	
        	
        	//if (true) { return; }
        	
            //double d = entity.posX - posX;
            //double d1 = entity.posZ - posZ;
            if(attackTime == 0)
            {
            	Entity bulletEntity = ((SdkZcItemGun)mod_SdkZombieCraft.itemGunMp40).getBulletEntity(worldObj, this, 0);
            	//Entity bulletEntity = mod_SdkZombieCraft.itemGunMp40.getBulletEntity(worldObj, this, 0);
            	
            	if(bulletEntity != null)
            		worldObj.entityJoinedWorld(bulletEntity);
            	
            	
            	worldObj.playSoundAtEntity(this, ((SdkZcItemGun)mod_SdkZombieCraft.itemGunMp40).firingSound, 0.25F, 1.0F / (mod_SdkZombieCraft.itemGunMp40.itemRand.nextFloat() * 0.1F + 0.95F));
            	
            	((SdkZcItemGun)mod_SdkZombieCraft.itemGunMp40).lastSound = worldObj.func_22139_r();
        		
                /*EntityArrow entityarrow = new EntityArrow(worldObj, this);
                entityarrow.posY += 1.3999999761581421D;
                double d2 = entity.posY - 0.20000000298023224D - entityarrow.posY;
                float f1 = MathHelper.sqrt_double(d * d + d1 * d1) * 0.2F;
                worldObj.playSoundAtEntity(this, "random.bow", 1.0F, 1.0F / (rand.nextFloat() * 0.4F + 0.8F));
                worldObj.entityJoinedWorld(entityarrow);
                entityarrow.setArrowHeading(d, d2 + (double)f1, d1, 0.6F, 12F);*/
                attackTime = 10;
            }
            //rotationYaw = (float)((Math.atan2(d1, d) * 180D) / 3.1415927410125732D) - 90F;
            hasAttacked = true;
        }
    }
	
	public void writeEntityToNBT(NBTTagCompound nbttagcompound)
    {
        super.writeEntityToNBT(nbttagcompound);
        nbttagcompound.setString("texture", (String)texture);
        
        
    }

    public void readEntityFromNBT(NBTTagCompound nbttagcompound)
    {
        super.readEntityFromNBT(nbttagcompound);
        texture = nbttagcompound.getString("texture");
        
    }
	
    protected String getLivingSound()
    {
        return null;
    }

    protected String getHurtSound()
    {
        return null;
    }

    protected String getDeathSound()
    {
        return null;
    }

    protected int getDropItemId()
    {
        return 0;
    }

    public ItemStack getHeldItem()
    {
        return curHeldItem;
    }

    private int randomSoundDelay;
    private static final ItemStack defaultHeldItem;
    
    public static ItemStack curHeldItem;
    
    public int attackStrength;

    static 
    {
        defaultHeldItem = new ItemStack(mod_SdkZombieCraft.itemGunMp40,1);//new ItemStack(Item.swordSteel, 1);
        curHeldItem = defaultHeldItem;
        
    }
}
