package net.minecraft.src;

import java.util.List;
import java.util.Random;

public class EntityNode extends Entity
{
	
	public EntityNode losNodes[];
	public int losNodeCount = 0;
	public boolean isLoaded = false;
	public boolean isUsed = false;
	public boolean isTempUsed = false;
	public boolean render = false;
	
	

    public EntityNode(World world)
    {
        super(world);

        isImmuneToFire = true;
        setSize(0.1F,0.1F);
        
        if (mod_EntAI.nodeRef == null) { 
        	mod_EntAI.nodeRef = this;
        }
        
        mod_EntAI.entNodes[mod_EntAI.entNodeCount] = this;
        mod_EntAI.entNodeCount++;
        
        /*if (mod_EntAI.ticksRan > 10) {
			
		}*/
        
    }
    
    public void setEntityDead()
    {
    	mod_EntAI.entNodeCount--;
        super.setEntityDead();
    }
    
    
    public boolean isInRangeToRenderDist(double d)
    {
    	return this.worldObj.devMode;
        //return true;
    }
    
    public void getLOSNodes() {
    	//stair node = a node that connects to a close Y differing node
    	//check for other stair nodes first to see if self qualifies, mark self and those as stair nodes if found others
    	//stair nodes with 2 other connected stair nodes can ONLY look for other stair nodes
    	//stair nodes with 1 other connected nodes are the top or bottom of stairs, can connect to normal nodes in normal ranges
    	
    	//normal/end of stair nodes in future will require a walkable path check, to fix future problems such as flat open areas with pits
    	
    	
    	
		
		if (!isLoaded) {
		
			List entList = worldObj.getEntitiesWithinAABBExcludingEntity((Entity)this,this.boundingBox.expand(64D,64D,64D));
			Entity tEnt = null;
			
			losNodeCount = 0;
			losNodes = new EntityNode[64];
		
		
			isLoaded = true;
			for (int i = 0; i < entList.size(); i++) {
				
				tEnt = (Entity)entList.get(i);
				
				if (tEnt instanceof EntityNode && tEnt != this) {
					
					if (canEntityBeSeen(tEnt)) {
						//System.out.println(MathHelper.abs((float)(this.posY - tEnt.posY*1.0F)));
						if (MathHelper.abs((float)(this.posY - tEnt.posY*1.0F)) > mod_EntAI.maxNodeLOSYDiff) {
							if (getXZDistanceToEntity(tEnt) < mod_EntAI.maxStairNodeLOSXYDiff) {
						
								
								losNodes[losNodeCount] = (EntityNode)tEnt;
								losNodeCount++;
								continue;
								
							}
						} else {
							if (getXZDistanceToEntity(tEnt) < mod_EntAI.maxNodeLOSDist) {
								
								
								losNodes[losNodeCount] = (EntityNode)tEnt;
								losNodeCount++;
								continue;
								
							}
						}
					}
				}
			}
			//System.out.print("WP Found: ");
			//System.out.println(losNodeCount);
		}
    }
    
    public boolean canEntityBeSeen(Entity entity)
    {
    	return worldObj.rayTraceBlocks_do(Vec3D.createVector(posX, posY-yOffset + (double)getEyeHeight(), posZ), Vec3D.createVector(entity.posX, entity.posY-entity.yOffset + (double)entity.getEyeHeight(), entity.posZ), false) == null;
        //return worldObj.rayTraceBlocks_doorEx(Vec3D.createVector(posX, posY + (double)getEyeHeight(), posZ), Vec3D.createVector(entity.posX, entity.posY + (double)entity.getEyeHeight(), entity.posZ), false) == null;
    }
    
    public boolean canEntityBeSeenmDoors(Entity entity)
    {
        return worldObj.rayTraceBlocks_def(Vec3D.createVector(posX, posY + (double)getEyeHeight(), posZ), Vec3D.createVector(entity.posX, entity.posY + (double)entity.getEyeHeight(), entity.posZ), false) == null;
    }
    
    public void entityInit() {
    	
    }
    
	public void onUpdate()
    {
        //super.onUpdate();
		
		render = false;
       
        if (worldObj.devMode) {
			List entList = worldObj.getEntitiesWithinAABBExcludingEntity((Entity)this,this.boundingBox.expand(0.3D,1D,0.3D));
			Entity tEnt = null;
			
			Entity clEnt = null;
			Entity clEntWP = null;
	
			float clDist = 90000;
			float curDist;
			
			float clWP = 90000;
			
			boolean canSeePlayer = false;
			
			for (int i = 0; i < entList.size(); i++) {
				
				tEnt = (Entity)entList.get(i);
				
				if (tEnt instanceof EntityPlayer) {
					
					for (int j = 0; j < this.losNodeCount; j++) {
						//if (!entNodes[i].isLoaded) {
							
							
							/*System.out.print(losNodes[j].posX);
							System.out.print(" - ");
							System.out.print(losNodes[j].posY);
							System.out.print(" - ");
							System.out.print(losNodes[j].posZ);
							System.out.println("");*/
							
						
					}
					//System.out.println(" ------------ ");
					mod_EntAI.displayMessage(new StringBuilder().append("LOS Nodes: ").append(losNodeCount).toString());
					render = true;
				
				}
				
			}
        }
        
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
	
	@Override
    public void writeEntityToNBT(NBTTagCompound nbttagcompound)
    {
        /*nbttagcompound.setShort("xTile", (short)xTile);
        nbttagcompound.setShort("yTile", (short)yTile);
        nbttagcompound.setShort("zTile", (short)zTile);
        nbttagcompound.setByte("inTile", (byte)inTile);
        nbttagcompound.setByte("inGround", (byte)(inGround ? 1 : 0));*/
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound nbttagcompound)
    {
        /*xTile = nbttagcompound.getShort("xTile");
        yTile = nbttagcompound.getShort("yTile");
        zTile = nbttagcompound.getShort("zTile");
        inTile = nbttagcompound.getByte("inTile") & 0xff;
        inGround = nbttagcompound.getByte("inGround") == 1;*/
    }


}
