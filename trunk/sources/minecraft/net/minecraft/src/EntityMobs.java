package net.minecraft.src;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

import java.util.Random;
import java.util.List;

public class EntityMobs extends EntityCreature
    implements IMobs
{

	public int AIDelay = 0;
	public EntityNode navPath[];
	public int curNode = 0;
	public int noMoveTicks = 0;
	public int cantSeeTicks = 0;

    public boolean swingArm = false;
    public int swingTick = 0;
	
	public int lastNodeAction[] = { -1, -1 };
	
    public EntityMobs(World world)
    {
        super(world);
        attackStrength = 2;
        health = 20;
    }

    public void onLivingUpdate()
    {
        float f = getEntityBrightness(1.0F);
        if(f > 0.5F)
        {
            field_9344_ag += 2;
        }
        
        if(swingArm) {
        	if (swingTick > 3 && swingTick < 9) {
        		swingTick+=2;
        	} else {
        		swingTick++;
        	}

            if(swingTick >= 24) {
                swingTick = 0;
                swingArm = false;
            }
        } else {
            swingTick = 0;
        }

        if (swingTick <= 16) {
        	swingProgress = (float)swingTick / 8F;
        } else {
        	swingProgress = 0F;
        }
        
        super.onLivingUpdate();
    }
    
    
    
    public boolean notMoving(EntityLiving thisent, float tolerance) {
    	double d1 = thisent.prevPosX - thisent.posX;
        //double d2 = c - aw;
        double d3 = thisent.prevPosZ - thisent.posZ;
        float d4 = MathHelper.sqrt_double(d1 * d1 + d3 * d3);
        return d4 < tolerance ? true : false;
        
    }

    public void onUpdate()
    {
    	AIDelay--;
    	
    	if (notMoving(this,0.03F)) {
    		noMoveTicks++;
    	} else {
    		noMoveTicks = 0;
    	}
    	
    	if (playerToAttack != null) {
	    	if (!canSeeNode(playerToAttack)) {
	    		cantSeeTicks++;
	    	} else {
	    		cantSeeTicks = 0;
	    	}
    	}
    	
    	if (playerToGuard != null) {
	    	if (!canSeeNode(playerToGuard)) {
	    		cantSeeTicks++;
	    	} else {
	    		cantSeeTicks = 0;
	    	}
    	}
    	
    	EntityPlayer player = worldObj.getClosestPlayerToEntity(this, -1);
    	//System.out.println("0");
    	if (this instanceof EntityFriend || this instanceof EntityBunny) {
    		//System.out.println("1");
    		if (getDistanceToEntity(player) < guardDist && canEntityBeSeen4(player) && playerToAttack instanceof EntityPlayer) {
    			//System.out.println("2");
    			//if (playerToAttack instanceof EntityPlayer) {
    				//System.out.println("hhhm");
    				//System.out.println("11");
    				playerToAttack = null;
    				//System.out.println("close");
    				super.onUpdate();
    				return;
    			//}
    		} else {
    			if (playerToAttack instanceof EntityPlayer) {
    				//System.out.println("far");
    				playerToAttack = null;
    				super.onUpdate();
    				return;
    			}
    		}
    		
    	}
    	
    	if (getDistanceToEntity(player) < 8.0F && canEntityBeSeen4(player) && playerToAttack instanceof EntityNode) {
    		
    		if (mod_EntAI.dbg_AI) System.out.println("player close, set targ");
    		playerToAttack = player;
			super.onUpdate();
			return;
    		
    	}
    	
    	//if (this instanceof EntityFriend) { System.out.println("2"); }
    	if (playerToAttack instanceof EntityNode) {
    		//look 1 waypoint ahead code, needs to account for stairs
    		/*if (navPath != null) {
    			if (curNode+1 < navPath.length) {
    				if (canEntityBeSeen2(navPath[curNode+1])) {
    					playerToAttack = navPath[curNode+1];
            		}
    				//playerToAttack = player;
    				//curNode = 0;
    				//System.out.println("end of path");
    				
    				//super.onUpdate();
    				//return;
    			}
    			
    			
    		}*/
    		
    		if (getXZDistanceToEntity(playerToAttack) < 0.6F && (MathHelper.abs((float)(this.posY - playerToAttack.posY*1.0F)) < 8.0F) || (noMoveTicks > 90 || cantSeeTicks > 60)) {
    		//if (getXZDistanceToEntity(playerToAttack) < 0.5F || (!canEntityBeSeen(playerToAttack))) {
    			//System.out.println("1");
    			//EntityNode WPNode = (EntityNode)playerToAttack;
    			
    			
    			
    			if (navPath == null || (canEntityBeSeen4(player) && getDistanceToEntity(player) < mod_EntAI.maxDistToPlayer)) {
    				
    				if (this instanceof EntityFriend || this instanceof EntityBunny) {
    					//System.out.println("1");
    					playerToAttack = null;
    				} else {
    					playerToAttack = player;
    				}
    				
    				
    				curNode = 0;
    				if (mod_EntAI.dbg_AI) System.out.println("no path or what?!");
    				
    				super.onUpdate();
    				return;
    			}
    			
    			/*if (navPath[curNode] != null) {
    				if (navPath[curNode].pr)
    			}*/
    			
    			if (curNode >= navPath.length-1) {
    				if (this instanceof EntityFriend || this instanceof EntityBunny) {
    					//System.out.println("2");
    					playerToAttack = null;
    				} else {
    					playerToAttack = player;
    				}
    				curNode = 0;
    				if (mod_EntAI.dbg_AI) System.out.println("end of path");
    				
    				super.onUpdate();
    				return;
    			}
    			
    			if (noMoveTicks > 90 || cantSeeTicks > 60) {
    				
    				if (mod_EntAI.dbg_AI) {
    					System.out.print("noMoveTicks: ");
    					System.out.println(noMoveTicks);
    					System.out.print("cantSeeTicks: ");
    					System.out.println(cantSeeTicks);
    					System.out.println("stuck, falling back a node");
    				}
    				noMoveTicks = 0;
    				//if node and last 2 states weren't a fallback (endless loop detection)
    				if (curNode > 0 && lastNodeAction[0] != 1 && lastNodeAction[1] != 1) {
    					curNode--;
    					lastNodeAction[1] = lastNodeAction[0];
    	    			lastNodeAction[0] = 1;
    				} else {
    					if (this instanceof EntityFriend) {
    						//System.out.println("3");
        					playerToAttack = null;
        				} else {
        					playerToAttack = player;
        				}
    					super.onUpdate();
        				return;
    				}
    				if (navPath[curNode] != null) {
    					playerToAttack = navPath[curNode];
    					//System.out.println("prev node: ");
    					/*System.out.print("x: ");
    	            	System.out.print(playerToAttack.posX);
    	            	System.out.print(" - z: ");
    	            	System.out.print(playerToAttack.posZ);
    	            	System.out.print(" - y: ");
    	            	System.out.println(playerToAttack.posY);*/
    				}
    				
    				super.onUpdate();
    				return;
    			}
    			lastNodeAction[1] = lastNodeAction[0];
    			lastNodeAction[0] = 0;
    			curNode++;
    			/*System.out.println(curNode);
    			System.out.println(navPath.length);*/
    			playerToAttack = navPath[curNode];
    			if (mod_EntAI.dbg_AI) System.out.println("next node: ");
    			/*System.out.print("x: ");
            	System.out.print(playerToAttack.posX);
            	System.out.print(" - z: ");
            	System.out.print(playerToAttack.posZ);
            	System.out.print(" - y: ");
            	System.out.println(playerToAttack.posY);*/
    			
    			/*if (WPNode.losNodes != null) {
    				//System.out.print("2: ");
    				//System.out.println(WPNode.losNodeCount);
    				int chNode = rand.nextInt(WPNode.losNodeCount);
	    			if (WPNode.losNodes[chNode] != null) {
	    				//System.out.println("switch");
	    				playerToAttack = WPNode.losNodes[chNode];
	    			}
    			}*/
    		}
    			
    			
    		
    	} else {
    		if (canEntityBeSeen4(player) && getDistanceToEntity(player) < mod_EntAI.maxDistToPlayer) {
    			//System.out.println("sees player");
    			if (this instanceof EntityFriend || this instanceof EntityBunny) {
    				if (playerToAttack instanceof EntityPlayer) {
	    				//System.out.println("4");
						playerToAttack = null;
    				}
				} else {
					playerToAttack = player;
				}
    		}
    	}
    	
    	
        super.onUpdate();
        //if(worldObj.difficultySetting == 0)
        //{
            //setEntityDead();
        //}
    }
    
    protected Entity seekCloserTarget() {
    	
    	if (AIDelay < 0) {
    		AIDelay = 20+rand.nextInt(20);
    		return findPlayerToAttack();
    	} else {
    		return null;
    	}
    	
    }
    
    protected void updatePlayerActionState() {
    	
    	Entity tEnt = null;
    	
    	//if (!(playerToAttack instanceof EntityNode)) {
    		tEnt = seekCloserTarget();
    	//}
    	
    	
    	if (tEnt != null) {
    		playerToAttack = tEnt;
    	}
    	
    	super.updatePlayerActionState();
    }

    protected Entity findPlayerToAttack()
    {
    	
    	List entList = worldObj.getEntitiesWithinAABBExcludingEntity((Entity)this,this.boundingBox.expand(16D,16D,16D));
		Entity tEnt = null;
		
		Entity clEnt = null;
		Entity clEntWP = null;

		float clDist = 90000;
		float curDist;
		
		float clWP = 90000;
		
		boolean canSeePlayer = false;
		
		for (int i = 0; i < entList.size(); i++) {
			
			tEnt = (Entity)entList.get(i);
			
			if (tEnt instanceof EntityFriend || tEnt instanceof EntityPlayer || tEnt instanceof EntityChicken) {
			//if (tEnt instanceof EntityFriend) {
				
				if (canEntityBeSeen4(tEnt)) {
					curDist = getDistanceToEntity(tEnt);
					
					if (curDist < clDist) {
						clDist = curDist;
						clEnt = tEnt;
					}
					
					
				}
				
			}
			
			if (tEnt instanceof EntityNode) {
				
				if (canEntityBeSeen3(tEnt)) {
					curDist = getDistanceToEntity(tEnt);
					
					if (curDist < clWP) {
						clWP = curDist;
						clEntWP = tEnt;
					}
				}
				
			}
			
		}
		
		EntityPlayer entityplayer = worldObj.getClosestPlayerToEntity(this, -1);
		
		if (clEnt != null) {
			if (getDistanceToEntity(clEnt) < mod_EntAI.maxDistToPlayer && canEntityBeSeen4(clEnt)) {
		
				//if (mod_EntAI.dbg_AI) { System.out.println("close and visible"); }
				return clEnt;
			}
		}
		
		
		
		if (!(playerToAttack instanceof EntityNode)) {
			if (clEntWP != null) {
				if (mod_EntAI.dbg_AI) System.out.print("path get - ");
				navPath = mod_EntAI.getPathToTarget(this,entityplayer,(EntityNode)clEntWP);
				if (navPath != null) {
					//mod_EntAI.displayMessage(new StringBuilder().append(navPath.length).append("").toString());
					if (mod_EntAI.dbg_AI) { 
						if (navPath.length > 1) {
							System.out.print("NPL: ");
							System.out.println(navPath.length);
							System.out.print("found end: ");
							System.out.println(mod_EntAI.dbg_WPFP++);
						} else {
							System.out.print("no path: ");
							System.out.println(mod_EntAI.dbg_WPNP++);
						}
					}
				} else {
					//System.out.println(navPath);
					if (mod_EntAI.dbg_AI) { System.out.print("no path: ");
					System.out.println(mod_EntAI.dbg_WPNP++); }
				}
				if (mod_EntAI.dbg_AI) { System.out.print("nodes: ");
				System.out.println(mod_EntAI.nodeData.size());
				System.out.println(" "); }
				curNode = 0;
				return clEntWP;
			}
		}
    	
        
        if(entityplayer != null)
        {
        	if (canEntityBeSeen4(entityplayer) && getDistanceToEntity(entityplayer) < mod_EntAI.maxDistToPlayer) {
        		//System.out.println("aaaa!!");
        		return entityplayer;
        	}
        }
		return null;
    }
    
    

    public boolean attackEntityFrom(Entity entity, int i)
    {
        if(super.attackEntityFrom(entity, i))
        {
            if(riddenByEntity == entity || ridingEntity == entity)
            {
                return true;
            }
            if(entity != this && canEntityBeSeen4(entity))
            {
                playerToAttack = entity;
            }
            return true;
        } else
        {
            return false;
        }
    }

    protected void attackEntity(Entity entity, float f)
    {
        if((double)f < 2.0D && entity.boundingBox.maxY > boundingBox.minY && entity.boundingBox.minY < boundingBox.maxY && attackTime == 0)
        {
        	swingArm = true;
        	swingTick = 0;
            attackTime = 20;
            entity.attackEntityFrom(this, attackStrength);
        }
    }

    protected float getBlockPathWeight(int i, int j, int k)
    {
        return 0.5F - worldObj.getLightBrightness(i, j, k);
    }

    public void writeEntityToNBT(NBTTagCompound nbttagcompound)
    {
        super.writeEntityToNBT(nbttagcompound);
    }

    public void readEntityFromNBT(NBTTagCompound nbttagcompound)
    {
        super.readEntityFromNBT(nbttagcompound);
    }

    public boolean getCanSpawnHere()
    {
        int i = MathHelper.floor_double(posX);
        int j = MathHelper.floor_double(boundingBox.minY);
        int k = MathHelper.floor_double(posZ);
        if(worldObj.getSavedLightValue(EnumSkyBlock.Sky, i, j, k) > rand.nextInt(32))
        {
            return false;
        } else
        {
            int l = worldObj.getBlockLightValue(i, j, k);
            return l <= rand.nextInt(8) && super.getCanSpawnHere();
        }
    }

    protected int attackStrength;
    public int curBlockDamage;
    public boolean isBreaking;
}
