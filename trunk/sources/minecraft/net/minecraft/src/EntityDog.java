package net.minecraft.src;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 
import java.util.Random;

public class EntityDog extends EntityMobs
{

    public EntityDog(World world)
    {
        super(world);
        texture = "/mob/pig.png";
        setSize(0.9F, 0.9F);
		moveSpeed = 0.8F;
        attackStrength = 3;
		scoreValue = 100;
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
}
