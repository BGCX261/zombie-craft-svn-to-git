package net.minecraft.src;

import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import net.minecraft.client.Minecraft;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

// SDK
public class mod_SdkZombieCraft extends BaseMod
{
	// Items
	
	static float ammoFactor = 2;
	
	private static final int ITEM_ID_OFFSET = 23827;
	public static final Item itemBulletMagnum = (new SdkZcItemCustomStackSize(ITEM_ID_OFFSET + 20, (int)(6*ammoFactor))).setIconIndex(ModLoader.addOverride("/gui/items.png", "/sdkzc/itemBulletM1911.png")).setItemName("zcBulletMagnum");
    public static final Item itemBulletM1911 = (new SdkZcItemCustomStackSize(ITEM_ID_OFFSET, (int)(8*ammoFactor))).setIconIndex(ModLoader.addOverride("/gui/items.png", "/sdkzc/itemBulletM1911.png")).setItemName("zcBulletM1911");
    public static final Item itemBulletKar98k = (new SdkZcItemCustomStackSize(ITEM_ID_OFFSET + 1, (int)(5*ammoFactor))).setIconIndex(ModLoader.addOverride("/gui/items.png", "/sdkzc/itemBulletKar98k.png")).setItemName("zcBulletKar98k");
    public static final Item itemBulletM1Garand = (new SdkZcItemCustomStackSize(ITEM_ID_OFFSET + 2, (int)(6*ammoFactor))).setIconIndex(ModLoader.addOverride("/gui/items.png", "/sdkzc/itemBulletM1Garand.png")).setItemName("zcBulletM1Garand");
    public static final Item itemBulletMp40 = (new SdkZcItemCustomStackSize(ITEM_ID_OFFSET + 3, (int)(20*ammoFactor))).setIconIndex(ModLoader.addOverride("/gui/items.png", "/sdkzc/itemBulletMp40.png")).setItemName("zcBulletMp40");
    public static final Item itemBulletTrenchgun = (new SdkZcItemCustomStackSize(ITEM_ID_OFFSET + 4, (int)(4*ammoFactor))).setIconIndex(ModLoader.addOverride("/gui/items.png", "/sdkzc/itemBulletTrenchgun.png")).setItemName("zcBulletTrenchgun");
    public static final Item itemBulletFlamethrower = (new SdkZcItemBulletFlamethrower(ITEM_ID_OFFSET + 5)).setIconIndex(ModLoader.addOverride("/gui/items.png", "/sdkzc/itemBulletFlamethrower.png")).setItemName("zcBulletFlamethrower");
    public static final Item itemBulletRayGun = (new SdkZcItemCustomStackSize(ITEM_ID_OFFSET + 6, (int)(18*ammoFactor))).setIconIndex(ModLoader.addOverride("/gui/items.png", "/sdkzc/itemBulletRayGun.png")).setItemName("zcBulletRayGun");
    public static final Item itemBulletPanzerfaust = (new SdkZcItemCustomStackSize(ITEM_ID_OFFSET + 7, (int)(1*ammoFactor))).setIconIndex(ModLoader.addOverride("/gui/items.png", "/sdkzc/itemBulletPanzerfaust.png")).setItemName("zcBulletPanzerfaust");
    public static final Item itemGunM1911 = (new SdkZcItemGunM1911(ITEM_ID_OFFSET + 8)).setIconIndex(ModLoader.addOverride("/gui/items.png", "/sdkzc/itemGunM1911.png")).setItemName("zcGunM1911");
    public static final Item itemGunKar98k = (new SdkZcItemGunKar98k(ITEM_ID_OFFSET + 9)).setIconIndex(ModLoader.addOverride("/gui/items.png", "/sdkzc/itemGunKar98k.png")).setItemName("zcGunKar98k");
    public static final Item itemGunM1Garand = (new SdkZcItemGunM1Garand(ITEM_ID_OFFSET + 10)).setIconIndex(ModLoader.addOverride("/gui/items.png", "/sdkzc/itemGunM1Garand.png")).setItemName("zcGunM1Garand");
    public static final Item itemGunMp40 = (new SdkZcItemGunMp40(ITEM_ID_OFFSET + 11)).setIconIndex(ModLoader.addOverride("/gui/items.png", "/sdkzc/itemGunMp40.png")).setItemName("zcGunMp40");
    public static final Item itemGunTrenchgun = (new SdkZcItemGunTrenchgun(ITEM_ID_OFFSET + 12)).setIconIndex(ModLoader.addOverride("/gui/items.png", "/sdkzc/itemGunTrenchgun.png")).setItemName("zcGunTrenchgun");
    public static final Item itemGunFlamethrower = (new SdkZcItemGunFlamethrower(ITEM_ID_OFFSET + 13)).setIconIndex(ModLoader.addOverride("/gui/items.png", "/sdkzc/itemGunFlamethrower.png")).setItemName("zcGunFlamethrower");
    public static final Item itemGunRayGun = (new SdkZcItemGunRayGun(ITEM_ID_OFFSET + 14)).setIconIndex(ModLoader.addOverride("/gui/items.png", "/sdkzc/itemGunRayGun.png")).setItemName("zcGunRayGun");
    public static final Item itemGunPanzerfaust = (new SdkZcItemGunPanzerfaust(ITEM_ID_OFFSET + 15)).setIconIndex(ModLoader.addOverride("/gui/items.png", "/sdkzc/itemGunPanzerfaust.png")).setItemName("zcGunPanzerfaust");
    public static final Item itemGrenadeStun = (new SdkZcItemGrenadeStun(ITEM_ID_OFFSET + 16, 4)).setIconIndex(ModLoader.addOverride("/gui/items.png", "/sdkzc/itemGrenadeStun.png")).setItemName("zcGrenadeStun");
	public static final Item itemGunUzi = (new SdkZcItemGunUzi(ITEM_ID_OFFSET + 17)).setIconIndex(ModLoader.addOverride("/gui/items.png", "/sdkzc/itemGunUzi.png")).setItemName("zcGunUzi");
	public static final Item itemGunMagum = (new SdkZcItemGunMagum(ITEM_ID_OFFSET + 18)).setIconIndex(ModLoader.addOverride("/gui/items.png", "/sdkzc/itemGunMagnum.png")).setItemName("zcGunMagnum");
    public static final Item itemGunRifle = (new SdkZcItemGunRifle(ITEM_ID_OFFSET + 19)).setIconIndex(ModLoader.addOverride("/gui/items.png", "/sdkzc/itemGunAR35.png")).setItemName("zcGunRifle");
	
	// Reload
    public static Map<EntityPlayer, Integer> reloadTimes = new HashMap<EntityPlayer, Integer>();
    private static int lastReloadDecrementTime = 0;
    public static boolean reloadKeyDown = false;
    
    // Recoil
    public static final double RECOIL_FIX_FACTOR = 0.1F;
    public static final double MIN_RECOIL_FIX = 0.5F;
    public static double currentRecoilV = 0F;
    public static double currentRecoilH = 0F;
    private static int lastRecoilFixTime = 0;
    
    // Flash
    public static Map<EntityLiving, SdkZcPair<Integer, Float>> stunGrenadeFlashTimes = new HashMap<EntityLiving, SdkZcPair<Integer, Float>>();
    private static long lastFlashDecrementTime = 0;
    
    // Minecraft
    public static Minecraft mc = ModLoader.getMinecraftInstance();
    public static Field minecraftTicksRan = null;
    
    // Properties
    public static int reloadKey = Keyboard.KEY_R;
    
    // Unbreakable blocks
    public static boolean glassAlwaysDestructible = false;;
    public static boolean blocksMineable = true;
    public static boolean blocksExplodable = false;
	private static boolean areBlocksMineable = true;
	private static float[] blockHardness;
	private static boolean areBlocksExplodable = false;;
	private static float[] blockResistance;

    // Test
    public static int testKey = Keyboard.KEY_T;
    public static boolean testKeyDown = false;
    
    static
    {
    	loadProperties();
    }
    
    private static void loadProperties()
    {
    	Properties properties = new Properties();
    	try
    	{
    		String propertiesPath = mod_SdkZombieCraft.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
    		propertiesPath = propertiesPath.substring(0, propertiesPath.lastIndexOf('/') + 1);
    		propertiesPath += "mod_SdkZombieCraft.properties";
    		
    		properties.load(new FileInputStream(propertiesPath));
    		String property;
    		
    		property = properties.getProperty("reloadKey");
    		int tempReloadKey = Keyboard.getKeyIndex(property.trim().toUpperCase());
    		if(tempReloadKey == Keyboard.CHAR_NONE)
    		{
    			System.out.println("Error binding reload key. Bad key name: " + property.trim());
    		}
    		else
    		{
    			reloadKey = tempReloadKey;
    		}
        }
        catch(Exception e)
        {
        	System.out.println("Error loading properties.");
			e.printStackTrace();
        }
    }
	
	public mod_SdkZombieCraft()
	{
		ModLoader.AddName(itemBulletM1911, "M1911 Ammo");
		ModLoader.AddName(itemBulletMagnum, "Magnum Ammo");
		ModLoader.AddName(itemBulletKar98k, "Kar98k Ammo");
		ModLoader.AddName(itemBulletM1Garand, "M1 Garand Ammo");
		ModLoader.AddName(itemBulletMp40, "MP40 Ammo");
		ModLoader.AddName(itemBulletTrenchgun, "Trenchgun Ammo");
		ModLoader.AddName(itemBulletFlamethrower, "Flamethrower Ammo");
		ModLoader.AddName(itemBulletRayGun, "Ray Gun Ammo");
		ModLoader.AddName(itemBulletPanzerfaust, "Panzerfaust Ammo");
		ModLoader.AddName(itemGunUzi, "Mini Uzi");
		ModLoader.AddName(itemGunRifle, "AR35");
		ModLoader.AddName(itemGunMagum, "Magnum");
		ModLoader.AddName(itemGunM1911, "M1911");
		ModLoader.AddName(itemGunKar98k, "Kar98k");
		ModLoader.AddName(itemGunM1Garand, "M1 Garand");
		ModLoader.AddName(itemGunMp40, "MP40");
		ModLoader.AddName(itemGunTrenchgun, "Trenchgun");
		ModLoader.AddName(itemGunFlamethrower, "Flamethrower");
		ModLoader.AddName(itemGunRayGun, "Ray Gun");
		ModLoader.AddName(itemGunPanzerfaust, "Panzerfaust");
		ModLoader.AddName(itemGrenadeStun, "Stun Grenade");
	    
	    // For showing stack size instead of max damage
	    itemBulletM1911.maxDamage = 0;
	    itemBulletKar98k.maxDamage = 0;
	    itemBulletM1Garand.maxDamage = 0;
	    itemBulletMp40.maxDamage = 0;
	    itemBulletTrenchgun.maxDamage = 0;
	    itemBulletRayGun.maxDamage = 0;
	    itemBulletPanzerfaust.maxDamage = 0;
		
		ModLoader.RegisterEntityID(SdkZcEntityBullet.class, "Bullet", ModLoader.getUniqueEntityId());
		ModLoader.RegisterEntityID(SdkZcEntityBulletShot.class, "Shell", ModLoader.getUniqueEntityId());
		ModLoader.RegisterEntityID(SdkZcEntityBulletRocket.class, "Rocket", ModLoader.getUniqueEntityId());
		ModLoader.RegisterEntityID(SdkZcEntityBulletFlame.class, "Flame", ModLoader.getUniqueEntityId());
		ModLoader.RegisterEntityID(SdkZcEntityBulletRay.class, "Ray", ModLoader.getUniqueEntityId());
		ModLoader.RegisterEntityID(SdkZcEntityBulletCasing.class, "Casing", ModLoader.getUniqueEntityId());
		ModLoader.RegisterEntityID(SdkZcEntityBulletCasingShell.class, "ShellCasing", ModLoader.getUniqueEntityId());
		ModLoader.RegisterEntityID(SdkZcEntityGrenadeStun.class, "GrenadeStun", ModLoader.getUniqueEntityId());
		
		if (mc == null) {
			mc = ModLoader.getMinecraftInstance();
			System.out.println("sdkzc fetching mc instance");
		}
	}
	
	public String Version()
	{
		return "1.2_02v1";
	}

	public void AddRenderer(Map map)
    {
    	map.put(SdkZcEntityBullet.class, new SdkZcRenderBullet());
		map.put(SdkZcEntityBulletShot.class, new SdkZcRenderBulletShot());
		map.put(SdkZcEntityBulletCasing.class, new SdkZcRenderBulletCasing());
		map.put(SdkZcEntityBulletCasingShell.class, new SdkZcRenderBulletCasingShell());
		map.put(SdkZcEntityBulletRocket.class, new SdkZcRenderBulletRocket());
		map.put(SdkZcEntityBulletFlame.class, new SdkZcRenderBulletFlame());
		map.put(SdkZcEntityBulletRay.class, new SdkZcRenderBulletRay());
    }
	
	public void OSDHook(Minecraft minecraft, boolean menuOpen)
	{
	
		SdkZcTools.addSounds();
		handleDrawing(minecraft, menuOpen);
		handleBreakables();
		
		

		try
		{	
			tick(menuOpen);
		}
		catch (IllegalAccessException illegalaccessexception)
		{
			ModLoader.ThrowException("An impossible error has occured!", illegalaccessexception);
		}
	}

	private void handleDrawing(Minecraft minecraft, boolean menuOpen)
	{
		if(!menuOpen)
		{
			renderFlash(minecraft);		
			renderAmmo(minecraft);
		}
	}

	private void renderFlash(Minecraft minecraft)
	{
		if(getFlash() != 0F)
			SdkZcTools.renderTextureOverlay("%blur%/sdkzc/flash.png", getFlash());
	}
	
	public static float getFlash()
	{
		if(stunGrenadeFlashTimes.containsKey(mc.thePlayer))
    	{
    		return (float)stunGrenadeFlashTimes.get(mc.thePlayer).getLeft() / SdkZcEntityGrenadeStun.MAX_FLASH_TIME_PLAYER;
    	}
		else
		{
			return 0.0F;
		}
	}

	private void renderAmmo(Minecraft minecraft)
	{
		ItemStack itemStack = mc.thePlayer.getCurrentEquippedItem();
		if(itemStack != null)
	    {
	    	Item item = itemStack.getItem();
			if(item instanceof SdkZcItemGun)
	    	{
				//System.out.println("test3");
				int numStacks = getNumberInInventory(mc.thePlayer.inventory, ((SdkZcItemGun)item).requiredBullet.shiftedIndex);
	    		ScaledResolution scaledresolution = new ScaledResolution(mc.displayWidth, mc.displayHeight);
	    		int width = scaledresolution.getScaledWidth();
	            int height = scaledresolution.getScaledHeight();
	            
	            // Draw number of clips
	            String numStacksString = ((Integer)(numStacks > 0 ? numStacks - 1 : 0)).toString();
	            int numStacksStringWidth = minecraft.fontRenderer.getStringWidth(numStacksString);
	            minecraft.fontRenderer.drawString(numStacksString, (width / 2 + 91) - numStacksStringWidth, height - 32 - 8, 0xFFFFFF);
	            
	            // Draw number of bullets
	            GL11.glBindTexture(3553 /*GL_TEXTURE_2D*/, mc.renderEngine.getTexture("/sdkzc/icons.png"));
	            int ammoCount = mod_SdkZombieCraft.getNumberInFirstStackInInventory(mc.thePlayer.inventory, ((SdkZcItemGun)item).requiredBullet.shiftedIndex);
	        	for(int q = 0; q < ammoCount; q++)
		            if(item instanceof SdkZcItemGunFlamethrower)
		            	minecraft.ingameGUI.drawTexturedModalRect((width / 2 + 91) - q - 1 - 14, height - 32 - 9, 19, 27, 1, 9);
		            else
		            	minecraft.ingameGUI.drawTexturedModalRect((width / 2 + 91) - q * 2 - 3 - 14, height - 32 - 9, 16, 27, 3, 9);
	    	}
	    }
	}

	private void handleBreakables()
	{
		// Set mineable
		if(blocksMineable != areBlocksMineable)
		{
			areBlocksMineable = !areBlocksMineable;
			setBlocksMineable(areBlocksMineable);
		}

		// Set explodable
		if(blocksExplodable != areBlocksExplodable)
		{
			areBlocksExplodable = !areBlocksExplodable;
			setBlocksExplodable(areBlocksExplodable);
		}
	}
	
	private void setBlocksMineable(boolean setMineable)
	{
		try
		{
			if(!setMineable)
			{
				// Make unmineable
				blockHardness = new float[Block.blocksList.length];
				
				for(int i = 0; i < Block.blocksList.length; i++)
				{
					if(Block.blocksList[i] != null)
					{
						blockHardness[i] = Block.blocksList[i].blockHardness;
						
						if(i != Block.glass.blockID || !glassAlwaysDestructible)
							Block.blocksList[i].blockHardness = 6000000F;
					}
				}
			}
			else
			{
				// Make mineable
				for(int i = 0; i < Block.blocksList.length; i++)
				{
					if(Block.blocksList[i] != null)
					{
						Block.blocksList[i].blockHardness = blockHardness[i];
					}
				}
			}
		}
        catch(Exception e)
        {
        	System.out.println("Error setting blocks mineable: " + setMineable + ".");
			e.printStackTrace();
        }
	}
	
	private void setBlocksExplodable(boolean setExplodable)
	{
		try
		{
			if(!setExplodable)
			{
				// Make unexplodable
				blockResistance = new float[Block.blocksList.length];
				
				for(int i = 0; i < Block.blocksList.length; i++)
				{
					if(Block.blocksList[i] != null)
					{
						blockResistance[i] = Block.blocksList[i].blockResistance;
						
						if(i != Block.glass.blockID || !glassAlwaysDestructible)
							Block.blocksList[i].blockResistance = 6000000F;
					}
				}
			}
			else
			{
				// Make explodable
				for(int i = 0; i < Block.blocksList.length; i++)
				{
					if(Block.blocksList[i] != null)
					{
						Block.blocksList[i].blockResistance = blockResistance[i];
					}
				}
			}
		}
        catch(Exception e)
        {
        	System.out.println("Error setting blocks explodable: " + setExplodable + ".");
			e.printStackTrace();
        }
	}

	public void tick(boolean menuOpen) throws IllegalAccessException
	{
		
		if(minecraftTicksRan == null)
		{
			minecraftTicksRan = Minecraft.class.getDeclaredFields()[21];
			minecraftTicksRan.setAccessible(true);
		}
		if(!menuOpen)
		{
			// Get ticksRan
			int ticksRan = mc.ticksRan;// minecraftTicksRan.getInt(mc);
			
			// Fix recoil
			int sinceLastRecoilFix = ticksRan - lastRecoilFixTime;
			
			if(sinceLastRecoilFix > 0)
			{
				double recoilFixV = 0, lastRecoilV = currentRecoilV;
				if(mc.thePlayer != null && currentRecoilV > 0D)
				{
					recoilFixV = Math.min(Math.max(currentRecoilV * RECOIL_FIX_FACTOR, MIN_RECOIL_FIX), currentRecoilV);
					currentRecoilV -= recoilFixV;
					mc.thePlayer.rotationPitch += recoilFixV;
				}
				
				if(mc.thePlayer != null && Math.abs(currentRecoilH) > 0D)
				{
					double recoilFixH;
					if(currentRecoilH > 0D)
						recoilFixH = Math.min(Math.max(currentRecoilH * RECOIL_FIX_FACTOR / 2, MIN_RECOIL_FIX / 2), currentRecoilH);
					else
						recoilFixH = Math.max(Math.min(currentRecoilH * RECOIL_FIX_FACTOR / 2, -MIN_RECOIL_FIX / 2), currentRecoilH);
					
					if(recoilFixV != 0)
					{
						double synchedRecoilFix = recoilFixV / lastRecoilV * currentRecoilH;
						
						if(currentRecoilH > 0D)
							recoilFixH = Math.min(synchedRecoilFix, recoilFixH);
						else
							recoilFixH = Math.max(synchedRecoilFix, recoilFixH);
					}
					
					currentRecoilH -= recoilFixH;
					mc.thePlayer.rotationYaw -= recoilFixH;
				}
				
				lastRecoilFixTime = ticksRan;
			}
		
			// Handle reload
			int sinceLastReloadDecrement = ticksRan - lastReloadDecrementTime;
			
			if(sinceLastReloadDecrement > 0)
			{
				Iterator<Map.Entry<EntityPlayer,Integer>> it = reloadTimes.entrySet().iterator();
		    	while (it.hasNext()) {
		    	    Map.Entry<EntityPlayer,Integer> entry = it.next();
		    	    
		    	    int n = entry.getValue() - sinceLastReloadDecrement;
		    	    
		    	    if (n <= 0)
		    	        it.remove();
		    	    else
		    	    	entry.setValue(n);
		    	}
		    	
		    	lastReloadDecrementTime = ticksRan;
			}
			
			// Handle flash
			int sinceLastFlashDecrement = (int)(ticksRan - lastFlashDecrementTime);
			
			if(sinceLastFlashDecrement > 0)
			{
				Iterator<Map.Entry<EntityLiving,SdkZcPair<Integer, Float>>> it = stunGrenadeFlashTimes.entrySet().iterator();
		    	while (it.hasNext())
		    	{
		    	    Map.Entry<EntityLiving,SdkZcPair<Integer, Float>> entry = it.next();
		    	    
		    	    int n = entry.getValue().getLeft() - sinceLastFlashDecrement;
		    	    
		    	    if (n <= 0)
		    	    {
		    	    	entry.getKey().moveSpeed = entry.getValue().getRight();
		    	        it.remove();
		    	    }
		    	    else
		    	    {
		    	    	entry.setValue(new SdkZcPair<Integer, Float>(n, entry.getValue().getRight()));
		    	    }
		    	}
		    	
		    	lastFlashDecrementTime = ticksRan;
			}
			
			// Handle reload key
            if(!Keyboard.isKeyDown(reloadKey) && reloadKeyDown)
        		handleReload(mc.theWorld, mc.thePlayer, false);
            reloadKeyDown = Keyboard.isKeyDown(reloadKey);
            
            // Handle test key
			//System.out.println(Keyboard.isKeyDown(testKey));
            if(Keyboard.isKeyDown(testKey) && testKeyDown)
        		handleTestKey(mc.thePlayer);
            testKeyDown = Keyboard.isKeyDown(testKey);
		}
	}

	public static void handleReload(World world, EntityPlayer entityPlayer, boolean forcedReload)
	{
		if(!reloadTimes.containsKey(entityPlayer))
		{
			ItemStack itemStack = entityPlayer.getCurrentEquippedItem();
			if(itemStack != null && itemStack.getItem() instanceof SdkZcItemGun)
			{
				Item ammo = ((SdkZcItemGun)itemStack.getItem()).requiredBullet;
	
				int firstIndex = -1;
				int firstAmmoAmount = -1, secondAmmoAmount = -1;
				boolean hasReloaded = false;
				do
				{
					secondAmmoAmount = -1;
					
					InventoryPlayer inventory = entityPlayer.inventory;
					for(int j = firstIndex + 1; j < inventory.mainInventory.length; j++)
			            if(inventory.mainInventory[j] != null && inventory.mainInventory[j].itemID == ammo.shiftedIndex)
			            {
			            	if(ammo.maxDamage > 0)
			            	{
			            		int fullAmmoCount = ammo.maxDamage + 1;
			            		
			            		if(firstIndex == -1)
			            		{
			            			// Get size of first stack
			            			firstAmmoAmount = fullAmmoCount - getItemDamage(inventory.mainInventory[j]);
			    					
			    					// Break if full
			    					if(!forcedReload && ammo.maxDamage > 0 && firstAmmoAmount == ammo.maxDamage + 1)
			    						break;
			            		}
			            		else
			            		{
			            			// Reload
			            			if(!hasReloaded)
			            			{
			            				reload(world, entityPlayer);
			            				hasReloaded = true;
			            			}
			            			
			            			// Get size of second stack
			            			secondAmmoAmount = fullAmmoCount - getItemDamage(inventory.mainInventory[j]);
			            			
			            			// Find out how much ammo to shift
			            			int ammoToShift = Math.min(fullAmmoCount - firstAmmoAmount, secondAmmoAmount);
			            			
			            			// Shift ammo
			            			firstAmmoAmount += ammoToShift;
			            			secondAmmoAmount -= ammoToShift;
			            			setItemDamage(inventory.mainInventory[firstIndex], fullAmmoCount - firstAmmoAmount);
			            			setItemDamage(inventory.mainInventory[j], fullAmmoCount - secondAmmoAmount);
		            				
		            				// Nullify empty stack
		            				if(secondAmmoAmount == 0)
		            					inventory.mainInventory[j] = new ItemStack(Item.bucketEmpty);
		            				
		            				break;
			            		}
			            	}
			            	else
			            	{
			            		if(firstIndex == -1)
			            		{
			            			// Get size of first stack
			            			firstAmmoAmount = inventory.mainInventory[j].stackSize;
			    					
			    					// Break if full
			    					if(!forcedReload && ammo.maxDamage == 0 && firstAmmoAmount == ammo.maxStackSize)
			    						break;
			            		}
			            		else
			            		{
			            			// Reload
			            			if(!hasReloaded)
			            			{
			            				reload(world, entityPlayer);
			            				hasReloaded = true;
			            			}
			            			
			            			// Get size of second stack
			            			secondAmmoAmount = inventory.mainInventory[j].stackSize;
			            			
			            			// Find out how much ammo to shift
			            			int ammoToShift = Math.min(ammo.maxStackSize - firstAmmoAmount, secondAmmoAmount);
			            			
			            			// Shift ammo
			            			firstAmmoAmount += ammoToShift;
			            			secondAmmoAmount -= ammoToShift;
			            			inventory.mainInventory[firstIndex].stackSize = firstAmmoAmount;
		            				inventory.mainInventory[j].stackSize = secondAmmoAmount;
		            				
		            				// Nullify empty stack
		            				if(secondAmmoAmount == 0)
		            					inventory.mainInventory[j] = null;
		            				
		            				break;
			            		}
			            	}
			            	
			            	if(firstIndex == -1)
			            		firstIndex = j;
			            }
					
					// Break if no ammo found
					if(firstIndex == -1)
						break;
					
					// Reload and break if forced
					if(!hasReloaded && forcedReload)
					{
						reload(world, entityPlayer);
						break;
					}
					
					// Break if only one ammo stack found
					if(secondAmmoAmount == -1)
						break;
					
					// Break if full
					if(ammo.maxDamage == 0 && firstAmmoAmount == ammo.maxStackSize)
						break;
					
					// Break if full
					if(ammo.maxDamage > 0 && firstAmmoAmount == ammo.maxDamage + 1)
						break;
				}
				while(true);
			}
		}
	}
	
	public static int getNumberInFirstStackInInventory(InventoryPlayer inventory, int itemID)
    {
		
		int j = inventory.currentItem;
    	
    	j+=9;
    	if(inventory.mainInventory[j] != null && inventory.mainInventory[j].itemID == itemID)
        {
    		if(inventory.mainInventory[j].getItem().maxDamage > 0)
        	{
        		return inventory.mainInventory[j].getItem().maxDamage - getItemDamage(inventory.mainInventory[j]) + 1;
        	}
        	else
        	{
        		return inventory.mainInventory[j].stackSize;
        	}
        }
    	j+=9;
    	if(inventory.mainInventory[j] != null && inventory.mainInventory[j].itemID == itemID)
        {
    		if(inventory.mainInventory[j].getItem().maxDamage > 0)
        	{
        		return inventory.mainInventory[j].getItem().maxDamage - getItemDamage(inventory.mainInventory[j]) + 1;
        	}
        	else
        	{
        		return inventory.mainInventory[j].stackSize;
        	}
        }
    	j+=9;
    	if(inventory.mainInventory[j] != null && inventory.mainInventory[j].itemID == itemID)
        {
    		if(inventory.mainInventory[j].getItem().maxDamage > 0)
        	{
        		return inventory.mainInventory[j].getItem().maxDamage - getItemDamage(inventory.mainInventory[j]) + 1;
        	}
        	else
        	{
        		return inventory.mainInventory[j].stackSize;
        	}
        }
    	
    	for(j = 0; j < 9; j++)
        {
    		for (int k = 0; k < 4; k++) {
    			int ii = j + (k*9);
    			if(inventory.mainInventory[ii] != null && inventory.mainInventory[ii].itemID == itemID) {
	    			if(inventory.mainInventory[ii].getItem().maxDamage > 0)
	            	{
	            		return inventory.mainInventory[ii].getItem().maxDamage - getItemDamage(inventory.mainInventory[ii]) + 1;
	            	}
	            	else
	            	{
	            		return inventory.mainInventory[ii].stackSize;
	            	}
    			}
    		}
        }
    	
        //return -1;
		
        /*for(j = 0; j < inventory.mainInventory.length; j++)
        {
            if(inventory.mainInventory[j] != null && inventory.mainInventory[j].itemID == itemID)
            {
            	if(inventory.mainInventory[j].getItem().maxDamage > 0)
            	{
            		return inventory.mainInventory[j].getItem().maxDamage - getItemDamage(inventory.mainInventory[j]) + 1;
            	}
            	else
            	{
            		return inventory.mainInventory[j].stackSize;
            	}
            }
        }*/

        return -1;
    }
	
	public static int getNumberInInventory(InventoryPlayer inventory, int i)
    {
		int num = 0;
		
        for(int j = 0; j < inventory.mainInventory.length; j++)
        {
            if(inventory.mainInventory[j] != null && inventory.mainInventory[j].itemID == i)
            {
            	num++;
            }
        }

        return num;
    }
	
	public static int getItemDamage(ItemStack itemStack)
	{
		try
		{
			return (Integer)ModLoader.getPrivateValue(ItemStack.class, itemStack, 3);
		}
		catch (NoSuchFieldException ex)
		{
			System.out.println("Error getting item damage.");
		}
		
		return 0;
	}
	
	public static void setItemDamage(ItemStack itemStack, int itemDamage)
	{
		try
		{
			ModLoader.setPrivateValue(ItemStack.class, itemStack, 3, itemDamage);
		}
		catch (NoSuchFieldException ex)
		{
			System.out.println("Error setting item damage.");
		}
	}
	
	public static void reload(World world, EntityPlayer entityplayer)
	{
		world.playSoundAtEntity(entityplayer, "sdkzc.reload", 1.0F, 1.0F / (entityplayer.rand.nextFloat() * 0.1F + 0.95F));
		reloadTimes.put(entityplayer, 40);
	}
    
	public static boolean handleMouse0(boolean buttonDown)
	{
		return false;
	}

	public static boolean handleMouse1(boolean buttonDown)
	{
		return false;
	}

	public static boolean overrideMouseCheck0(int sinceLastUse)
	{
		return false;
	}

	public static boolean overrideMouseCheck1(int sinceLastUse)
	{
		if(mc.thePlayer != null && mc.thePlayer.inventory != null)
		{
			ItemStack itemStack = mc.thePlayer.inventory.getCurrentItem();
			
			if(itemStack != null
					&& itemStack.getItem() instanceof SdkZcItemCustomUseDelay
					&& sinceLastUse >= ((SdkZcItemCustomUseDelay)itemStack.getItem()).useDelay)
				return true;
		}
		
		return false;
	}
	
	private void handleTestKey(EntityPlayer entityPlayer)
	{
		//disabled
		if (true) { return; }
		entityPlayer.inventory.mainInventory[0] = new ItemStack(itemGunM1911);
		entityPlayer.inventory.mainInventory[1] = new ItemStack(itemGunKar98k);
		entityPlayer.inventory.mainInventory[2] = new ItemStack(itemGunM1Garand);
		entityPlayer.inventory.mainInventory[3] = new ItemStack(itemGunMp40);
		entityPlayer.inventory.mainInventory[4] = new ItemStack(itemGunTrenchgun);
		entityPlayer.inventory.mainInventory[5] = new ItemStack(itemGunFlamethrower);
		entityPlayer.inventory.mainInventory[6] = new ItemStack(itemGunRayGun);
		entityPlayer.inventory.mainInventory[7] = new ItemStack(itemGunPanzerfaust);
		entityPlayer.inventory.mainInventory[8] = new ItemStack(itemGrenadeStun, itemGrenadeStun.maxStackSize);

		for(int i = 9; i <= 27; i += 9)
		{
			entityPlayer.inventory.mainInventory[i + 0] = new ItemStack(itemBulletM1911, itemBulletM1911.maxStackSize);
			entityPlayer.inventory.mainInventory[i + 1] = new ItemStack(itemBulletKar98k, itemBulletKar98k.maxStackSize);
			entityPlayer.inventory.mainInventory[i + 2] = new ItemStack(itemBulletM1Garand, itemBulletM1Garand.maxStackSize);
			entityPlayer.inventory.mainInventory[i + 3] = new ItemStack(itemBulletMp40, itemBulletMp40.maxStackSize);
			entityPlayer.inventory.mainInventory[i + 4] = new ItemStack(itemBulletTrenchgun, itemBulletTrenchgun.maxStackSize);
			entityPlayer.inventory.mainInventory[i + 5] = new ItemStack(itemBulletFlamethrower, itemBulletFlamethrower.maxStackSize);
			entityPlayer.inventory.mainInventory[i + 6] = new ItemStack(itemBulletRayGun, itemBulletRayGun.maxStackSize);
			entityPlayer.inventory.mainInventory[i + 7] = new ItemStack(itemBulletPanzerfaust, itemBulletPanzerfaust.maxStackSize);
			entityPlayer.inventory.mainInventory[i + 8] = new ItemStack(itemGrenadeStun, itemGrenadeStun.maxStackSize);
		}
	}
}
