package net.minecraft.src;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

import java.lang.reflect.Constructor;
import java.util.*;

public final class SpawnerAnimals
{

    public SpawnerAnimals()
    {
    }

    protected static ChunkPosition getRandomSpawningPointInChunk(World world, int i, int j)
    {
        int k = i + world.rand.nextInt(16);
        int l = world.rand.nextInt(128);
        int i1 = j + world.rand.nextInt(16);
        return new ChunkPosition(k, l, i1);
    }

    public static final int performSpawning(World var0, boolean var1, boolean var2) {
        if(!var1 && !var2) {
           return 0;
        } else {
           eligibleChunksForSpawning.clear();

           int var3;
           int var6;
           for(var3 = 0; var3 < var0.playerEntities.size(); ++var3) {
              EntityPlayer var4 = (EntityPlayer)var0.playerEntities.get(var3);
              int var5 = MathHelper.floor_double(var4.posX / 16.0D);
              var6 = MathHelper.floor_double(var4.posZ / 16.0D);
              byte var7 = 8;

              for(int var8 = -var7; var8 <= var7; ++var8) {
                 for(int var9 = -var7; var9 <= var7; ++var9) {
                    eligibleChunksForSpawning.add(new ChunkCoordIntPair(var8 + var5, var9 + var6));
                 }
              }
           }

           var3 = 0;
           ChunkCoordinates var33 = var0.func_22137_s();
           EnumCreatureType[] var34 = EnumCreatureType.values();
           var6 = var34.length;

           for(int var35 = 0; var35 < var6; ++var35) {
              EnumCreatureType var36 = var34[var35];
              if((!var36.func_21168_d() || var2) && (var36.func_21168_d() || var1) && var0.countEntities(var36.getCreatureClass()) <= var36.getMaxNumberOfCreature() * eligibleChunksForSpawning.size() / 256) {
                 Iterator var37 = eligibleChunksForSpawning.iterator();

                 label91:
                 while(var37.hasNext()) {
                    ChunkCoordIntPair var10 = (ChunkCoordIntPair)var37.next();
                    MobSpawnerBase var11 = var0.getWorldChunkManager().func_4074_a(var10);
                    Class[] var12 = var11.getEntitiesForType(var36);
                    if(var12 != null && var12.length != 0) {
                       int var13 = var0.rand.nextInt(var12.length);
                       ChunkPosition var14 = getRandomSpawningPointInChunk(var0, var10.chunkXPos * 16, var10.chunkZPos * 16);
                       int var15 = var14.x;
                       int var16 = var14.y;
                       int var17 = var14.z;
                       if(!var0.isBlockOpaqueCube(var15, var16, var17) && var0.getBlockMaterial(var15, var16, var17) == var36.getCreatureMaterial()) {
                          int var18 = 0;

                          for(int var19 = 0; var19 < 3; ++var19) {
                             int var20 = var15;
                             int var21 = var16;
                             int var22 = var17;
                             byte var23 = 6;

                             for(int var24 = 0; var24 < 4; ++var24) {
                                var20 += var0.rand.nextInt(var23) - var0.rand.nextInt(var23);
                                var21 += var0.rand.nextInt(1) - var0.rand.nextInt(1);
                                var22 += var0.rand.nextInt(var23) - var0.rand.nextInt(var23);
                                if(func_21203_a(var36, var0, var20, var21, var22)) {
                                   float var25 = (float)var20 + 0.5F;
                                   float var26 = (float)var21;
                                   float var27 = (float)var22 + 0.5F;
                                   if(var0.getClosestPlayer((double)var25, (double)var26, (double)var27, 24.0D) == null) {
                                      float var28 = var25 - (float)var33.field_22395_a;
                                      float var29 = var26 - (float)var33.field_22394_b;
                                      float var30 = var27 - (float)var33.field_22396_c;
                                      float var31 = var28 * var28 + var29 * var29 + var30 * var30;
                                      if(var31 >= 576.0F) {
                                         EntityLiving var38;
                                         try {
                                            var38 = (EntityLiving)var12[var13].getConstructor(new Class[]{World.class}).newInstance(new Object[]{var0});
                                         } catch (Exception var32) {
                                            var32.printStackTrace();
                                            return var3;
                                         }

                                         var38.setLocationAndAngles((double)var25, (double)var26, (double)var27, var0.rand.nextFloat() * 360.0F, 0.0F);
                                         if(var38.getCanSpawnHere()) {
                                            ++var18;
                                            var0.entityJoinedWorld(var38);
                                            func_21204_a(var38, var0, var25, var26, var27);
                                            if(var18 >= var38.getMaxSpawnedInChunk()) {
                                               continue label91;
                                            }
                                         }

                                         var3 += var18;
                                      }
                                   }
                                }
                             }
                          }
                       }
                    }
                 }
              }
           }

           return var3;
        }
     }

    private static boolean func_21203_a(EnumCreatureType enumcreaturetype, World world, int i, int j, int k)
    {
        if(enumcreaturetype.getCreatureMaterial() == Material.water)
        {
            return world.getBlockMaterial(i, j, k).getIsLiquid() && !world.isBlockOpaqueCube(i, j + 1, k);
        } else
        {
            return world.isBlockOpaqueCube(i, j - 1, k) && !world.isBlockOpaqueCube(i, j, k) && !world.getBlockMaterial(i, j, k).getIsLiquid() && !world.isBlockOpaqueCube(i, j + 1, k);
        }
    }

    private static void func_21204_a(EntityLiving entityliving, World world, float f, float f1, float f2)
    {
        if((entityliving instanceof EntitySpider) && world.rand.nextInt(100) == 0)
        {
            EntitySkeleton entityskeleton = new EntitySkeleton(world);
            entityskeleton.setLocationAndAngles(f, f1, f2, entityliving.rotationYaw, 0.0F);
            world.entityJoinedWorld(entityskeleton);
            entityskeleton.mountEntity(entityliving);
        } else
        if(entityliving instanceof EntitySheep)
        {
            ((EntitySheep)entityliving).setFleeceColor(EntitySheep.func_21070_a(world.rand));
        }
    }

    public static boolean func_22390_a(World world, List list)
    {
        boolean flag = false;
        Pathfinder pathfinder = new Pathfinder(world);
        Iterator iterator = list.iterator();
        do
        {
            if(!iterator.hasNext())
            {
                break;
            }
            EntityPlayer entityplayer = (EntityPlayer)iterator.next();
            Class aclass[] = field_22391_a;
            if(aclass != null && aclass.length != 0)
            {
                boolean flag1 = false;
                int i = 0;
                while(i < 20 && !flag1) 
                {
                    int j = (MathHelper.floor_double(entityplayer.posX) + world.rand.nextInt(32)) - world.rand.nextInt(32);
                    int k = (MathHelper.floor_double(entityplayer.posZ) + world.rand.nextInt(32)) - world.rand.nextInt(32);
                    int l = (MathHelper.floor_double(entityplayer.posY) + world.rand.nextInt(16)) - world.rand.nextInt(16);
                    if(l < 1)
                    {
                        l = 1;
                    } else
                    if(l > 128)
                    {
                        l = 128;
                    }
                    int i1 = world.rand.nextInt(aclass.length);
                    int j1;
                    for(j1 = l; j1 > 2 && !world.isBlockOpaqueCube(j, j1 - 1, k); j1--) { }
                    for(; !func_21203_a(EnumCreatureType.monster, world, j, j1, k) && j1 < l + 16 && j1 < 128; j1++) { }
                    if(j1 >= l + 16 || j1 >= 128)
                    {
                        j1 = l;
                    } else
                    {
                        float f = (float)j + 0.5F;
                        float f1 = j1;
                        float f2 = (float)k + 0.5F;
                        EntityLiving entityliving;
                        try
                        {
                            entityliving = (EntityLiving)aclass[i1].getConstructor(new Class[] {
                                World.class
                            }).newInstance(new Object[] {
                                world
                            });
                        }
                        catch(Exception exception)
                        {
                            exception.printStackTrace();
                            return flag;
                        }
                        entityliving.setLocationAndAngles(f, f1, f2, world.rand.nextFloat() * 360F, 0.0F);
                        if(entityliving.getCanSpawnHere())
                        {
                            PathEntity pathentity = pathfinder.createEntityPathTo(entityliving, entityplayer, 32F);
                            if(pathentity != null && pathentity.pathLength > 1)
                            {
                                PathPoint pathpoint = pathentity.func_22328_c();
                                if(Math.abs((double)pathpoint.xCoord - entityplayer.posX) < 1.5D && Math.abs((double)pathpoint.zCoord - entityplayer.posZ) < 1.5D && Math.abs((double)pathpoint.yCoord - entityplayer.posY) < 1.5D)
                                {
                                    ChunkCoordinates chunkcoordinates = BlockBed.func_22028_g(world, MathHelper.floor_double(entityplayer.posX), MathHelper.floor_double(entityplayer.posY), MathHelper.floor_double(entityplayer.posZ), 1);
                                    entityliving.setLocationAndAngles((float)chunkcoordinates.field_22395_a + 0.5F, chunkcoordinates.field_22394_b, (float)chunkcoordinates.field_22396_c + 0.5F, 0.0F, 0.0F);
                                    world.entityJoinedWorld(entityliving);
                                    func_21204_a(entityliving, world, (float)chunkcoordinates.field_22395_a + 0.5F, chunkcoordinates.field_22394_b, (float)chunkcoordinates.field_22396_c + 0.5F);
                                    entityplayer.func_22056_a(true, false);
                                    entityliving.func_22050_O();
                                    flag = true;
                                    flag1 = true;
                                }
                            }
                        }
                    }
                    i++;
                }
            }
        } while(true);
        return flag;
    }

    private static Set eligibleChunksForSpawning = new HashSet();
    protected static final Class field_22391_a[];

    static 
    {
        field_22391_a = (new Class[] {
            EntitySpider.class, EntityZombie.class, EntitySkeleton.class
        });
    }
}
