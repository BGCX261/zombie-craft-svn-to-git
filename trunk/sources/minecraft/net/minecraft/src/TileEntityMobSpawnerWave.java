package net.minecraft.src;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

import java.util.List;
import java.util.Random;
import java.util.Calendar;
					
public class TileEntityMobSpawnerWave extends TileEntity
{

    public TileEntityMobSpawnerWave()
    {
		
        delay = -1;
        yaw2 = 0.0D;
        mobID = "Zombie";
        delay = 20;
		spawnsthiswave = 0;
		watchX = 0;
		watchY = 0;
		watchZ = 0;
		isActivated = false;
    }

    public String getMobID()
    {
        return mobID;
    }

    public void setMobID(String s)
    {
        mobID = s;
    }

    public boolean anyPlayerInRange()
    {
        return worldObj.getClosestPlayer((double)xCoord + 0.5D, (double)yCoord + 0.5D, (double)zCoord + 0.5D, 64D) != null;
    }

    public void updateEntity()
    {
        yaw2 = yaw;
        
		for(yaw += 1000F / ((float)delay + 200F); yaw > 360D;)
        {
            yaw -= 360D;
            yaw2 -= 360D;
        }
		//System.out.println(delay);
        if(delay == -1)
        {
            updateDelay();
        }
        if(delay > 0)
        {
            delay--;
            return;
        }
        byte byte0 = 4;
				
		if (lastwave != worldObj.zombieWave)
		{
			
			//System.out.println("new wave init");
			delay = worldObj.rand.nextInt(600) + 50;// - (worldObj.zombieWave*10);
			spawnsthiswave=0;
			lastwave = worldObj.zombieWave;
			return;
		}
		
		if (watchX != 0 || watchY != 0 || watchZ != 0)
		{
			if (worldObj.getBlockId((int)watchX,(int)watchY,(int)watchZ) == 0)
			{
				isActivated = true;
				
				//if (worldObj.zombieKills < worldObj.waveSpawnMax && worldObj.zombieWave > 0)
				if ((worldObj.mobArr.size()+worldObj.zombieKills < worldObj.waveSpawnMax && worldObj.zombieWave > 0) || worldObj.waveSpawnCount < worldObj.waveSpawnMax && worldObj.zombieWave > 0)
				{
					EntityLiving entityliving = null;
					if (worldObj.zombieWave % 6 == 0 && worldObj.rand.nextInt(100) > 50) {
						entityliving = (EntityLiving)EntityList.createEntityInWorld("HellSheep", worldObj);
					} else {
						entityliving = (EntityLiving)EntityList.createEntityInWorld("Zombie", worldObj);
					}
					
					Calendar calendar = Calendar.getInstance();
					calendar.setTimeInMillis(System.currentTimeMillis());
					if(worldObj.zombieWave % 3 == 0 && (calendar.get(Calendar.DAY_OF_YEAR) == 114 || calendar.get(Calendar.DAY_OF_YEAR) == 115) && worldObj.rand.nextInt(2) == 0) {
						entityliving = (EntityLiving)EntityList.createEntityInWorld("Bunny", worldObj);
					}
					
					if(entityliving == null)
					{
						return;
					}
					/*int j = worldObj.getEntitiesWithinAABB(entityliving.getClass(), AxisAlignedBB.getBoundingBoxFromPool(xCoord, yCoord, zCoord, xCoord + 1, yCoord + 1, zCoord + 1).expand(8D, 4D, 8D)).size();
					if(j >= 6)
					{
						updateDelay();
						return;
					}*/
					double d6 = (double)xCoord;// + (worldObj.rand.nextDouble() - worldObj.rand.nextDouble()) * 4D;
					double d7 = (double)yCoord; //+ worldObj.rand.nextInt(3)) - 1;
					double d8 = (double)zCoord;// + (worldObj.rand.nextDouble() - worldObj.rand.nextDouble()) * 4D;
					entityliving.setLocationAndAngles(d6, d7, d8, worldObj.rand.nextFloat() * 360F, 0.0F);
								
					//while (!entityliving.getCanSpawnHere())
					float dFactor = 1.5F;
					if (worldObj.difficultySetting == 1) dFactor = 1.0F;
					if (worldObj.difficultySetting == 3) dFactor = 2.0F;
					//System.out.println(entityliving.getCanSpawnHere());
					if (worldObj.zombieWave < 15) {
						entityliving.health=(int)((worldObj.zombieWave*dFactor)+1);			
						if (entityliving instanceof EntityHellSheep) {
							entityliving.health /= 2;
						}
						if (entityliving instanceof EntityBunny) {
							entityliving.health = 1;
						}
					}
					dFactor = 0.010F;
					if (worldObj.difficultySetting == 1) dFactor = 0.005F;
					if (worldObj.difficultySetting == 3) dFactor = 0.015F;
					entityliving.moveSpeed+=worldObj.zombieWave*(int)dFactor;
					//temp
					//entityliving.moveSpeed+=5.0F;
					
					
					worldObj.mobArr.add(entityliving);
					worldObj.entityJoinedWorld(entityliving);
					spawnsthiswave++;
					worldObj.waveSpawnCount++;
					/* FUCK THIS PARTICLE EFFECT!!!!
					entityliving.spawnExplosionParticle();*/
					updateDelay();
								
					/*for(int k = 0; k < 20; k++)
					{
						double d1 = (double)xCoord + 0.5D + ((double)worldObj.rand.nextFloat() - 0.5D) * 2D;
						double d3 = (double)yCoord + 0.5D + ((double)worldObj.rand.nextFloat() - 0.5D) * 2D;
						double d5 = (double)zCoord + 0.5D + ((double)worldObj.rand.nextFloat() - 0.5D) * 2D;
						worldObj.spawnParticle("smoke", d1, d3, d5, 0.0D, 0.0D, 0.0D);
						worldObj.spawnParticle("flame", d1, d3, d5, 0.0D, 0.0D, 0.0D);
					}*/


				}
			} else {
			isActivated = false;
			}
		}
        super.updateEntity();
    }

    private void updateDelay()
    {
    	float dFactor = 10.0F;
		if (worldObj.difficultySetting == 1) dFactor = 15F;
		if (worldObj.difficultySetting == 3) dFactor = 7.5F;
        delay = (worldObj.rand.nextInt(300) + 150 - (worldObj.zombieWave*(int)dFactor))+50;
    }

    public void readFromNBT(NBTTagCompound nbttagcompound)
    {
        super.readFromNBT(nbttagcompound);
        mobID = nbttagcompound.getString("EntityId");
        //TEMP!!!
        //mobID = "Creeper";
        delay = nbttagcompound.getShort("Delay");
		spawnsthiswave = nbttagcompound.getInteger("SpawnsThisWave");
		watchX = nbttagcompound.getDouble("watchX");
		watchY = nbttagcompound.getDouble("watchY");
		watchZ = nbttagcompound.getDouble("watchZ");
    }

    public void writeToNBT(NBTTagCompound nbttagcompound)
    {
        super.writeToNBT(nbttagcompound);
        nbttagcompound.setString("EntityId", mobID);
        nbttagcompound.setShort("Delay", (short)delay);
		nbttagcompound.setInteger("SpawnsThisWave", (int)spawnsthiswave);		
		nbttagcompound.setDouble("watchX", (double)watchX);		
		nbttagcompound.setDouble("watchY", (double)watchY);			
		nbttagcompound.setDouble("watchZ", (double)watchZ);
    }

    public int delay;
    private String mobID;
    public double yaw;
    public double yaw2;
	public int spawnsthiswave;	
	public int lastwave;
}
