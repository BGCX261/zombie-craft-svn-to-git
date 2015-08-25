package net.minecraft.src;

import net.minecraft.client.Minecraft;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;


public class mod_ZombieCraft extends BaseMod {
	public static Minecraft mc = ModLoader.getMinecraftInstance();
    
    public mod_ZombieCraft() {
		// Blocks Init
		mobSpawnerWave  = (new BlockMobSpawnerWave(100, 65)).setHardness(0.1F).setStepSound(Block.soundMetalFootstep).setBlockName("spawnblock");
		betty = (new BlockBetty(107, 15)).setHardness(0.5F).setStepSound(Block.soundWoodFootstep).setBlockName("betty");
        mbPlate = (new BlockTrigger(109, 48, EnumMobType.players, null, null,950,1)).setHardness(0.5F).setStepSound(Block.soundWoodFootstep).setBlockName("mysterybox");
        
		woodSwordPlate = (new BlockTrigger(101, 48, EnumMobType.players,Item.swordWood,null,600,1)).setHardness(0.5F).setStepSound(Block.soundWoodFootstep).setBlockName("woodSwordPlate");
        stoneSwordPlate = (new BlockTrigger(102, 48, EnumMobType.players,Item.swordStone,null,1200,1)).setHardness(0.5F).setStepSound(Block.soundWoodFootstep).setBlockName("swordSwordPlate");
        ironSwordPlate = (new BlockTrigger(103, 48, EnumMobType.players,Item.swordSteel,null,2000,1)).setHardness(0.5F).setStepSound(Block.soundWoodFootstep).setBlockName("ironSwordPlate");
        
        bettyPlate = (new BlockTrigger(108, 48, EnumMobType.players, null, betty,1000,4)).setHardness(0.5F).setStepSound(Block.soundWoodFootstep).setBlockName("bettyPlate");
		debris1000Plate = (new BlockTrigger(104, 48, EnumMobType.players,1000,false)).setHardness(0.5F).setStepSound(Block.soundWoodFootstep).setBlockName("1000debrisPlate");
		
        juggPerkPlate = (new BlockTrigger(105, 48, EnumMobType.players,jugg,null,2500,1,true)).setHardness(0.5F).setStepSound(Block.soundWoodFootstep).setBlockName("juggPerkPlate");
        speedPerkPlate = (new BlockTrigger(106, 48, EnumMobType.players,speed,null,3000,1,true)).setHardness(0.5F).setStepSound(Block.soundWoodFootstep).setBlockName("speedPerkPlate");
		friendPerkPlate = (new BlockTrigger(110, 48, EnumMobType.players,frienddrink,null,3000,1,true)).setHardness(0.5F).setStepSound(Block.soundWoodFootstep).setBlockName("friendPerkPlate");
		exstaticPerkPlate = (new BlockTrigger(130, 48, EnumMobType.players,exstaticdrink,null,3000,1,true)).setHardness(0.5F).setStepSound(Block.soundWoodFootstep).setBlockName("exstaticPerkPlate");
		chargePerkPlate = (new BlockTrigger(131, 48, EnumMobType.players,chargedrink,null,3000,1,true)).setHardness(0.5F).setStepSound(Block.soundWoodFootstep).setBlockName("chargePerkPlate");
		
		exstatic_top = (new Block(132, 211, Material.rock)).setHardness(0.5F).setStepSound(Block.soundWoodFootstep).setBlockName("exstatic_top");;
		exstatic_btm = (new Block(133, 227, Material.rock)).setHardness(0.5F).setStepSound(Block.soundWoodFootstep).setBlockName("exstatic_btm");;
		charge_top = (new Block(134, 212, Material.rock)).setHardness(0.5F).setStepSound(Block.soundWoodFootstep).setBlockName("charge_top");;
		charge_btm = (new Block(135, 228, Material.rock)).setHardness(0.5F).setStepSound(Block.soundWoodFootstep).setBlockName("charge_btm");;
		
		Kar98kPlate = (new BlockTrigger(111, 48, EnumMobType.players, mod_SdkZombieCraft.itemGunKar98k, null,200,1)).setHardness(0.5F).setStepSound(Block.soundWoodFootstep).setBlockName("kar98kplate");
		M1GarandPlate = (new BlockTrigger(112, 48, EnumMobType.players, mod_SdkZombieCraft.itemGunM1Garand, null,600,1)).setHardness(0.5F).setStepSound(Block.soundWoodFootstep).setBlockName("m1plate");
		TrenchgunPlate = (new BlockTrigger(113, 48, EnumMobType.players, mod_SdkZombieCraft.itemGunTrenchgun,null,1500,1)).setHardness(0.5F).setStepSound(Block.soundWoodFootstep).setBlockName("trenchplate");
		FlamethrowerPlate = (new BlockTrigger(114, 48, EnumMobType.players, mod_SdkZombieCraft.itemGunFlamethrower, null,2000,1)).setHardness(0.5F).setStepSound(Block.soundWoodFootstep).setBlockName("flameplate");
		PanzerfaustPlate = (new BlockTrigger(115, 48, EnumMobType.players, mod_SdkZombieCraft.itemGunPanzerfaust, null,2000,1)).setHardness(0.5F).setStepSound(Block.soundWoodFootstep).setBlockName("panzerplate");
		Mp40Plate = (new BlockTrigger(116, 48, EnumMobType.players, mod_SdkZombieCraft.itemGunMp40, null,1000,1)).setHardness(0.5F).setStepSound(Block.soundWoodFootstep).setBlockName("mp40plate");
		UziPlate = (new BlockTrigger(124, 48, EnumMobType.players, mod_SdkZombieCraft.itemGunUzi, null,750,1)).setHardness(0.5F).setStepSound(Block.soundWoodFootstep).setBlockName("uziplate");
		MagnumPlate = (new BlockTrigger(128, 48, EnumMobType.players, mod_SdkZombieCraft.itemGunMagum, null,1750,1,false)).setHardness(0.5F).setStepSound(Block.soundWoodFootstep).setBlockName("magnumplate");
		AR35Plate = (new BlockTrigger(129, 48, EnumMobType.players, mod_SdkZombieCraft.itemGunRifle, null,2000,1,false)).setHardness(0.5F).setStepSound(Block.soundWoodFootstep).setBlockName("AR35plate");
		
		barricadeS0 = (new BlockBarricade(117, Material.wood, 0)).setHardness(3F).setStepSound(Block.soundWoodFootstep).setBlockName("barricadeBroken");
		barricadeS1 = (new BlockBarricade(118, Material.wood, 1)).setHardness(3F).setStepSound(Block.soundWoodFootstep).setBlockName("barricadeS1");
		barricadeS2 = (new BlockBarricade(119, Material.wood, 2)).setHardness(3F).setStepSound(Block.soundWoodFootstep).setBlockName("barricadeS2");
		barricadeS3 = (new BlockBarricade(120, Material.wood, 3)).setHardness(3F).setStepSound(Block.soundWoodFootstep).setBlockName("barricadeS3");
		barricadeS4 = (new BlockBarricade(121, Material.wood, 4)).setHardness(3F).setStepSound(Block.soundWoodFootstep).setBlockName("barricadeS4");
		barricadeS5 = Block.doorWood;//(new BlockDoor(121, Material.wood, 5)).setHardness(3F).setStepSound(Block.soundWoodFootstep).setBlockName("doorWood");
		
		cobbleBreak = (new Block(122, 16, Material.rock)).setHardness(2.0F).setResistance(10F).setStepSound(Block.soundStoneFootstep).setBlockName("stonebrick");
		
		radio = (new BlockRadio(123)).setHardness(0.5F).setResistance(10F).setStepSound(Block.soundStoneFootstep).setBlockName("radio");
		
		trapPlate_trig = (new BlockTriggerTrap(125, 48, EnumMobType.players, null, null,2500,0,true)).setHardness(0.5F).setStepSound(Block.soundWoodFootstep).setBlockName("trapTriggerPlate");
		trapPlate_kill = (new BlockTriggerTrap(126, 48, EnumMobType.everything, null, null,0,0,true)).setHardness(0.5F).setStepSound(Block.soundWoodFootstep).setBlockName("trapKillPlate");
		
		powerTrigger = (new BlockTriggerPower(127, 48, EnumMobType.players)).setHardness(0.5F).setStepSound(Block.soundWoodFootstep).setBlockName("triggerpower");
		lightToggle = (new BlockLightToggle(136, 48)).setHardness(0.0F).setLightValue(1.0F).setStepSound(Block.soundWoodFootstep).setBlockName("lighttoggle");
		
		invisBlock = (new BlockBreakable(137, 48, Material.rock, true)).setHardness(0.5F).setStepSound(Block.soundWoodFootstep).setBlockName("invisBlock");
		zombiesOnlyBlock = (new BlockZombiesOnly(138, 48, Material.rock)).setHardness(0.5F).setStepSound(Block.soundWoodFootstep).setBlockName("zombiesOnlyBlock");
		
		chickenNadePlate = (new BlockTrigger(139, 48, EnumMobType.players, chickenNade, null,2000,16,false)).setHardness(0.5F).setStepSound(Block.soundWoodFootstep).setBlockName("Chicken gun");
		
		//tv = (new BlockLightToggle(140, 48)).setHardness(0.0F).setLightValue(1.0F).setStepSound(Block.soundWoodFootstep).setBlockName("flickering_tv");
		
		ModLoader.RegisterBlock(mobSpawnerWave);
		ModLoader.RegisterBlock(betty);
		ModLoader.RegisterBlock(mbPlate);
		ModLoader.RegisterBlock(woodSwordPlate);
		ModLoader.RegisterBlock(stoneSwordPlate);
		ModLoader.RegisterBlock(ironSwordPlate);
		ModLoader.RegisterBlock(bettyPlate);
		ModLoader.RegisterBlock(debris1000Plate);
		ModLoader.RegisterBlock(juggPerkPlate);
		ModLoader.RegisterBlock(speedPerkPlate);;
		ModLoader.RegisterBlock(friendPerkPlate);
		ModLoader.RegisterBlock(Kar98kPlate);
		ModLoader.RegisterBlock(M1GarandPlate);
		ModLoader.RegisterBlock(Mp40Plate);
		ModLoader.RegisterBlock(TrenchgunPlate);
		ModLoader.RegisterBlock(FlamethrowerPlate);
		ModLoader.RegisterBlock(PanzerfaustPlate);
		ModLoader.RegisterBlock(cobbleBreak);
		ModLoader.RegisterBlock(radio);
		ModLoader.RegisterBlock(UziPlate);
		ModLoader.RegisterBlock(trapPlate_trig);
		ModLoader.RegisterBlock(trapPlate_kill);
		ModLoader.RegisterBlock(powerTrigger);
		ModLoader.RegisterBlock(exstaticPerkPlate);
		ModLoader.RegisterBlock(chargePerkPlate);
		ModLoader.RegisterBlock(MagnumPlate);
		ModLoader.RegisterBlock(AR35Plate);
		ModLoader.RegisterBlock(exstatic_top);
		ModLoader.RegisterBlock(exstatic_btm);
		ModLoader.RegisterBlock(charge_top);
		ModLoader.RegisterBlock(charge_btm);
		ModLoader.RegisterBlock(lightToggle);
		ModLoader.RegisterBlock(invisBlock);
		ModLoader.RegisterBlock(zombiesOnlyBlock);
		ModLoader.RegisterBlock(chickenNadePlate);
		
		// Items
		ModLoader.AddName(betty,"Bouncing Betty");		
		ModLoader.AddName(jugg,"Juggernaut Soda");		
		ModLoader.AddName(speed,"Speed Soda");		
		ModLoader.AddName(frienddrink,"Support Soda");
		ModLoader.AddName(exstaticdrink,"Exstatic Soda");
		ModLoader.AddName(chargedrink,"Charge Soda");
		ModLoader.AddName(lsword,"Wonder Sword");
		ModLoader.AddName(bigax,"Battle Axe");
		ModLoader.AddName(nail,"Board with a nail in it!");
		ModLoader.AddName(chickenNade,"Chicken Gun");
		
		
		ModLoader.AddName(Item.swordStone,"Mace");
		ModLoader.AddName(Item.swordWood,"Sword");
		
		// Trigger names
		ModLoader.AddName(UziPlate,"Mini Uzi trigger");
    	ModLoader.AddName(Kar98kPlate,"Kar98k trigger");		
		ModLoader.AddName(M1GarandPlate, "M1Garand trigger");
		ModLoader.AddName(Mp40Plate, "MP40 trigger");
		ModLoader.AddName(TrenchgunPlate, "Trenchgun trigger");
		ModLoader.AddName(FlamethrowerPlate, "Flamethrower trigger");
		ModLoader.AddName(PanzerfaustPlate, "Panzerfaust trigger");		
		ModLoader.AddName(woodSwordPlate, "Wood sword trigger");
		ModLoader.AddName(stoneSwordPlate, "Stone sword trigger");
		ModLoader.AddName(ironSwordPlate, "Iron sword trigger");
		ModLoader.AddName(juggPerkPlate, "Juggernaut trigger");
		ModLoader.AddName(speedPerkPlate, "SpeedSoda trigger");
		ModLoader.AddName(friendPerkPlate, "1-UP Fizzie trigger");
		ModLoader.AddName(bettyPlate, "Betty trigger");
		ModLoader.AddName(debris1000Plate, "Debris trigger (1000)");
		ModLoader.AddName(mbPlate, "Mystery box trigger");
		
		ModLoader.AddName(exstaticPerkPlate,"exstaticPerkPlate");
		ModLoader.AddName(chargePerkPlate,"chargePerkPlate");
		
		ModLoader.AddName(MagnumPlate,"MagnumPlate");
		ModLoader.AddName(AR35Plate,"AR35Plate");
		ModLoader.AddName(chickenNade,"x Chicken Gun shots");
		
		ModLoader.AddName(trapPlate_trig,"trapTriggerPlate");
		ModLoader.AddName(trapPlate_kill,"trapKillPlate");
		ModLoader.AddName(powerTrigger,"Power trigger");
		
		//Misc
		ModLoader.AddName(mobSpawnerWave, "SpawnBlock");
		ModLoader.AddName(magicStick,"SpawnLinker");
		ModLoader.AddName(nodeStick,"Waypoint Node Stick");
		ModLoader.AddName(cobbleBreak,"Explodable cobble");
		ModLoader.AddName(radio,"Radio");
		ModLoader.AddName(exstatic_top,"exstatic_top");
		ModLoader.AddName(exstatic_btm,"exstatic_btm");
		ModLoader.AddName(charge_top,"charge_top");
		ModLoader.AddName(charge_btm,"charge_btm");
		ModLoader.AddName(lightToggle,"lightToggle");
		ModLoader.AddName(invisBlock,"invisBlock");
		ModLoader.AddName(zombiesOnlyBlock,"zombiesOnlyBlock");
		ModLoader.AddName(tv,"flickering tv");
		
		Block.waterMoving.setResistance(1F);
		Block.waterStill.setResistance(1F);
	}
    
    public static String verZC = "v2.3";
    
    public static String getVersion() {
    	return verZC;
    }
     
    public String Version() {
    	return verZC;
    }
    
    public static boolean triggerPower(int x, int y, int z) {
    	if (!mc.theWorld.field_22145_q.getPower())
		{
			mc.theWorld.field_22145_q.setPower(true);
			mc.theWorld.playSoundEffect(mc.thePlayer.posX, mc.thePlayer.posY, mc.thePlayer.posZ, "sdkzc.poweron", 2F, 1.0F);
			if (mc.theWorld.getBlockId(x,y,z) == mod_ZombieCraft.powerTrigger.blockID) {
				mc.theWorld.setBlockMetadata(x,y,z,1);
			}
			mc.theWorld.showBuyMenu2=false;
			mc.ingameGUI.canBuy=false;
			mc.ingameGUI.lastBuy = System.currentTimeMillis();
			if (mc.theWorld.getBlockId(x,y+1,z) == 69) {
	    		if ((mc.theWorld.getBlockMetadata(x,y+1,z) & 8) == 0) {
	    			Block.lever.blockActivated(mc.theWorld, x, y+1, z, mc.thePlayer);
	    		}
	    	}
			return true;
		}
    	return false;
    }
    
    public static boolean triggerTrap(int x, int y, int z) {
    	int cost = ((BlockTriggerTrap)trapPlate_trig).cost;
    	if (mc.theWorld.getBlockId(x,y,z) == trapPlate_trig.blockID) {
    		if (mc.thePlayer.getScore()>=cost && mc.theWorld.getBlockMetadata(x,y,z) == 0 && ModLoader.getMinecraftInstance().theWorld.field_22145_q.getPower()) {
		    	int XZsize = 6;
		    	int Ysize = 1;
		    	
		    	mc.thePlayer.score = mc.thePlayer.score - cost;
				
				mc.thePlayer.worldObj.trapTimer = System.currentTimeMillis() + 20000;
				
				//mod_ZombieCraft.triggerTrap(triggerX, triggerY, triggerZ);
				
				mc.theWorld.showBuyMenu2=false;
				mc.ingameGUI.canBuy=false;
				mc.ingameGUI.lastBuy = System.currentTimeMillis();
				mc.theWorld.setBlockMetadata(x,y,z,1);
		    	if (mc.theWorld.getBlockId(x,y+1,z) == 69) {
		    		if ((mc.theWorld.getBlockMetadata(x,y+1,z) & 8) == 0) {
		    			Block.lever.blockActivated(mc.theWorld, x, y+1, z, mc.thePlayer);
		    		}
		    	}
		    	//find a trap kill block to activate
		    	for (int yy = y-Ysize; yy <= y+Ysize; yy++) {
		    		for (int xx = x-XZsize; xx <= x+XZsize; xx++) {
		    			for (int zz = z-XZsize; zz <= z+XZsize; zz++) {
		    				if (mc.theWorld.getBlockId(xx,yy,zz) == trapPlate_kill.blockID) {
		    					//System.out.println("found a trap");
		    					mc.theWorld.setBlockMetadata(xx,yy,zz,1);
		    					updateNearbyTraps(xx,yy,zz,1);
		    					return true;
		    				}
		    			}
		    		}
		    	}
    		}
    	}
    	return false;
    }
    
    public static void updateNearbyTraps(int x, int y, int z, int meta) {
    	int XZsize = 1;
    	int Ysize = 1;
    	for (int yy = y-Ysize; yy <= y+Ysize; yy++) {
    		for (int xx = x-XZsize; xx <= x+XZsize; xx++) {
    			for (int zz = z-XZsize; zz <= z+XZsize; zz++) {
    				if (mc.theWorld.getBlockId(xx,yy,zz) == trapPlate_kill.blockID && mc.theWorld.getBlockMetadata(xx,yy,zz) != 1) {
    					mc.theWorld.setBlockMetadata(xx,yy,zz,1);
    					
    					//System.out.println("found trapkill, setting meta");
    					updateNearbyTraps(xx,yy,zz,meta);
    				}
    			}
    		}
    	}
    }
    
    public static void animateTraps() {
    	if (mc.theWorld.trapTimer > 0) {
    		
    		
    		
    	}
    }
    
    public static int curTrapX;
    public static int curTrapY;
    public static int curTrapZ;
	
	private static final int ITEM_ID_OFFSET = 20300;
    
	public static final Item magicStick = (new ItemMagicStick(ITEM_ID_OFFSET + 1)).setIconCoord(5, 3).setFull3D().setItemName("magicstick");    
	
	public static final Item jugg = (new ItemJugg(ITEM_ID_OFFSET + 2, 2, 2, 2, 3)).setIconCoord(2,15).setItemName("jugg");
    public static final Item speed = (new ItemSpeed(ITEM_ID_OFFSET + 3)).setIconCoord(3,15).setItemName("speed");
	public static final Item frienddrink = (new ItemFriend(ITEM_ID_OFFSET + 4)).setIconCoord(4,15).setItemName("frienddrink");
	public static final Item exstaticdrink = (new ItemAbility(ITEM_ID_OFFSET + 12,1)).setIconCoord(4,15).setItemName("exstaticdrink");
	public static final Item chargedrink = (new ItemAbility(ITEM_ID_OFFSET + 13,0)).setIconCoord(4,15).setItemName("chargedrink");
	
	public static final Item lsword = (new ItemLSword(ITEM_ID_OFFSET + 5, EnumToolMaterial.WOOD)).setIconCoord(2, 4).setItemName("lsword");
	public static final Item bigax = (new ItemBigAx(ITEM_ID_OFFSET + 10, EnumToolMaterial.IRON)).setIconIndex(ModLoader.addOverride("/gui/items.png", "/sdkzc/itemAxe.png")).setItemName("bigax");
	public static final Item nail = (new ItemNail(ITEM_ID_OFFSET + 11, EnumToolMaterial.STONE)).setIconIndex(ModLoader.addOverride("/gui/items.png", "/sdkzc/nail.png")).setItemName("nail");
	
	public static final Item instaKill = (new Item(ITEM_ID_OFFSET + 6)).setIconIndex(ModLoader.addOverride("/gui/items.png", "/sdkzc/instakill.png")).setItemName("instakill");
	public static final Item nuke = (new Item(ITEM_ID_OFFSET + 7)).setIconIndex(ModLoader.addOverride("/gui/items.png", "/sdkzc/nuke.png")).setItemName("nuke");
	public static final Item maxAmmo = (new Item(ITEM_ID_OFFSET + 15)).setIconIndex(ModLoader.addOverride("/gui/items.png", "/sdkzc/maxammo.png")).setItemName("maxammo");
	public static final Item doublePoints = (new Item(ITEM_ID_OFFSET + 8)).setIconIndex(ModLoader.addOverride("/gui/items.png", "/sdkzc/doublepoints.png")).setItemName("doublepoints");	
	public static final Item nodeStick = (new ItemNodeStick(ITEM_ID_OFFSET + 9)).setIconCoord(5, 3).setFull3D().setItemName("nodestick");
	
	//public static final Item chickenNade = (new ItemChickenNade(ITEM_ID_OFFSET + 10)).setIconCoord(5, 3).setFull3D().setItemName("chickengun");
	public static final Item chickenNade = (new ItemChickenGun(ITEM_ID_OFFSET + 14, 16)).setIconIndex(ModLoader.addOverride("/gui/items.png", "/sdkzc/itemGunChickenGun.png")).setFull3D().setItemName("chickengun");
	
	public static final Item egg1 = (new ItemCandy(ITEM_ID_OFFSET + 16)).setIconIndex(ModLoader.addOverride("/gui/items.png", "/sdkzc/egg1.png")).setFull3D().setItemName("eegg");
	public static final Item egg2 = (new ItemCandy(ITEM_ID_OFFSET + 17)).setIconIndex(ModLoader.addOverride("/gui/items.png", "/sdkzc/egg2.png")).setFull3D().setItemName("eegg");
	public static final Item egg3 = (new ItemCandy(ITEM_ID_OFFSET + 18)).setIconIndex(ModLoader.addOverride("/gui/items.png", "/sdkzc/egg3.png")).setFull3D().setItemName("eegg");
	public static final Item egg4 = (new ItemCandy(ITEM_ID_OFFSET + 19)).setIconIndex(ModLoader.addOverride("/gui/items.png", "/sdkzc/egg4.png")).setFull3D().setItemName("eegg");
	
	public static Block lightToggle;
	
	public static Block ironSwordPlate;
	public static Block woodSwordPlate;
	public static Block stoneSwordPlate;
	
	public static Block Kar98kPlate;
	public static Block M1GarandPlate;
	public static Block Mp40Plate;
	public static Block TrenchgunPlate;
	public static Block FlamethrowerPlate;
	public static Block PanzerfaustPlate;
	public static Block UziPlate;
	public static Block MagnumPlate;
	public static Block AR35Plate;
	public static Block chickenNadePlate;
	
	public static Block juggPerkPlate;
	public static Block speedPerkPlate;
    public static Block friendPerkPlate;	
	
    public static Block debris1000Plate;	
    public static Block bettyPlate;
	
	public static Block betty;		
	public static Block mbPlate;
	public static Block mobSpawnerWave;
	
	public static Block barricadeS0;
	public static Block barricadeS1;
	public static Block barricadeS2;
	public static Block barricadeS3;
	public static Block barricadeS4;
	public static Block barricadeS5;
		
	public static Block cobbleBreak;	
	public static Block radio;		
	
	public static Block powerTrigger;		
	
	public static Block trapPlate_trig;
	public static Block trapPlate_kill;
	
	public static Block exstatic_top;
	public static Block exstatic_btm;
	public static Block charge_top;
	public static Block charge_btm;
	
	public static Block exstaticPerkPlate;
	public static Block chargePerkPlate;
	public static Block invisBlock;
	public static Block zombiesOnlyBlock;
	public static Block tv;
	
	
	
}







