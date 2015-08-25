package net.minecraft.src;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode

// SDK
public abstract class SdkZcEntityGrenade extends EntityItem
{

    public SdkZcEntityGrenade(World world)
    {
        super(world);
        setSize(0.25F, 0.25F);
        exploded = false;
        entityWalks = false;
        fuse = FUSE_LENGTH;
        yOffset = 0.0F;
    }
    
    public SdkZcEntityGrenade(World world, double d, double d1, double d2)
    {
        this(world);
        setPosition(d, d1, d2);
    }

    public SdkZcEntityGrenade(World world, Entity entity)
    {
        this(world);

        func_346_d(entity.rotationYaw, 0);
        // Set the velocity
        double xHeading = -MathHelper.sin((entity.rotationYaw * 3.141593F) / 180F);
        double zHeading = MathHelper.cos((entity.rotationYaw * 3.141593F) / 180F);
        motionX = initialVelocity*xHeading*MathHelper.cos((entity.rotationPitch / 180F) * 3.141593F);
        motionY = -initialVelocity*MathHelper.sin((entity.rotationPitch / 180F) * 3.141593F);
        motionZ = initialVelocity*zHeading*MathHelper.cos((entity.rotationPitch / 180F) * 3.141593F);
       
        // Set the position
        setPosition(entity.posX+xHeading*0.8, entity.posY + (double)entity.getYOffset(), entity.posZ+zHeading*0.8);
        prevPosX = posX;
        prevPosY = posY;
        prevPosZ = posZ;
    }
    
    @Override
    public boolean isInRangeToRenderDist(double d)
    {
        return true;
    }

    @Override
    public void onUpdate()
    {
        double prevVelX = motionX;
        double prevVelY = motionY;
        double prevVelZ = motionZ;
        prevPosX = posX;
        prevPosY = posY;
        prevPosZ = posZ;
        moveEntity(motionX, motionY, motionZ);
       
        // Take into account bouncing (normal displacement just sets them to 0)
        boolean bounced = false;
        if(motionX == 0 && prevVelX != 0)
        {
            motionX = -bounceFactor*prevVelX;
            motionY = bounceSlowFactor*prevVelY;
            motionZ = bounceSlowFactor*prevVelZ;
            
        	if(Math.abs(prevVelX) > MIN_BOUNCE_SOUND_VELOCITY)
        		bounced = true;
        }
        if(motionY == 0 && prevVelY != 0)
        {
            motionX = bounceSlowFactor*prevVelX;
            motionY = -bounceFactor*prevVelY;
            motionZ = bounceSlowFactor*prevVelZ;
            
        	if(Math.abs(prevVelY) > MIN_BOUNCE_SOUND_VELOCITY)
        		bounced = true;
        }
        if(motionZ == 0 && prevVelZ != 0)
        {
            motionX = bounceSlowFactor*prevVelX;
            motionY = bounceSlowFactor*prevVelY;
            motionZ = -bounceFactor*prevVelZ;
            
        	if(Math.abs(prevVelZ) > MIN_BOUNCE_SOUND_VELOCITY)
        		bounced = true;
        }
        
        if(bounced)
        {
            handleBounce();
        }

        motionY -= 0.04;
       
        // Air friction
        motionX *= 0.99;
        motionY *= 0.99;
        motionZ *= 0.99;
       
        handleExplode();
    }

	protected void handleBounce()
	{
		// Play sound
		worldObj.playSoundAtEntity(this, bounceSound, 0.25F, 1.0F / (rand.nextFloat() * 0.1F + 0.95F));
	}

	protected void handleExplode()
	{
		// Are we going to explode?
        if(fuse-- <= 0)
        {
            explode();
        }
	}

    protected abstract void explode();
    
    @Override
    public boolean canBeCollidedWith()
    {
        return true;
    }

    @Override
    public boolean attackEntityFrom(Entity entity, int i)
    {
        super.attackEntityFrom(entity, i);
        explode();
        return false;
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound nbttagcompound)
    {
        super.writeEntityToNBT(nbttagcompound);
        nbttagcompound.setByte("Fuse", (byte)fuse);
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound nbttagcompound)
    {
    	super.readEntityFromNBT(nbttagcompound);
        fuse = nbttagcompound.getByte("Fuse");
    }

    @Override
    public void onCollideWithPlayer(EntityPlayer entityplayer) { }
    
	protected String bounceSound = "sdkzc.grenadebounce";
   
    protected double bounceFactor = 0.15D;
    protected double bounceSlowFactor = 0.80D;
    protected int fuse;
    protected boolean exploded;
    protected double initialVelocity = 1D;
    
    protected static final int FUSE_LENGTH = 50;
    protected static final double MIN_BOUNCE_SOUND_VELOCITY = 0.1D;
}
