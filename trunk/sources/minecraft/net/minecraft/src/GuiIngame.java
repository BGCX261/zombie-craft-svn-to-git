package net.minecraft.src;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;
import net.minecraft.client.Minecraft;
import net.minecraft.src.GuiZombieMenu;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

public class GuiIngame extends Gui
{

    public GuiIngame(Minecraft minecraft)
    {
        chatMessageList = new ArrayList();
        rand = new Random();
        field_933_a = null;
        updateCounter = 0;
        field_9420_i = "";
        field_9419_j = 0;
        field_22065_l = false;
        field_931_c = 1.0F;
        mc = minecraft;
		count = 0;
		buyRandom = false;
		newWave = false;
		lastRandAdd = 0;
    }

    public void renderGameOverlay(float f, boolean flag, int i, int j)
    {
        ScaledResolution scaledresolution = new ScaledResolution(mc.displayWidth, mc.displayHeight);
        int k = scaledresolution.getScaledWidth();
        int l = scaledresolution.getScaledHeight();
        FontRenderer fontrenderer = mc.fontRenderer;
        mc.entityRenderer.func_905_b();
        GL11.glEnable(3042 /*GL_BLEND*/);
        if(Minecraft.func_22001_u())
        {
            func_4064_a(mc.thePlayer.getEntityBrightness(f), k, l);
        }
        ItemStack itemstack = mc.thePlayer.inventory.armorItemInSlot(3);
        if(!mc.gameSettings.thirdPersonView && itemstack != null && itemstack.itemID == Block.pumpkin.blockID)
        {
            func_4063_a(k, l);
        }
        float f1 = mc.thePlayer.prevTimeInPortal + (mc.thePlayer.timeInPortal - mc.thePlayer.prevTimeInPortal) * f;
        if(f1 > 0.0F)
        {
            func_4065_b(f1, k, l);
        }
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glBindTexture(3553 /*GL_TEXTURE_2D*/, mc.renderEngine.getTexture("/gui/gui.png"));
        InventoryPlayer inventoryplayer = mc.thePlayer.inventory;
        zLevel = -90F;
        
        
        //?!?!?
        int shiftLeft = 90;
        
        int startLeft = 16;
        
        
        //
        if (mc.theWorld.newGUI) {
	        drawTexturedModalRect(12, l - 22, 0, 0, 182, 22);
			drawTexturedModalRect(11 + inventoryplayer.currentItem * 20, l - 22 - 1, 0, 22, 24, 22);
			
			GL11.glBindTexture(3553 /*GL_TEXTURE_2D*/, mc.renderEngine.getTexture("/zc/zombiegui.png"));

			drawTexturedModalRect(0, 15, 0, 0, 28,28);
			
	        drawTexturedModalRect((k / 2 - 19), 15, 28, 0, 39,22);
        } else {
        	drawTexturedModalRect(k / 2 - 91, l - 22, 0, 0, 182, 22);
        	drawTexturedModalRect((k / 2 - 91 - 1) + inventoryplayer.currentItem * 20, l - 22 - 1, 0, 22, 24, 22);
    	}
		
		
		
        GL11.glBindTexture(3553 /*GL_TEXTURE_2D*/, mc.renderEngine.getTexture("/gui/icons.png"));
        GL11.glEnable(3042 /*GL_BLEND*/);
        GL11.glBlendFunc(775, 769);
        drawTexturedModalRect(k / 2 - 7, l / 2 - 7, 0, 0, 16, 16);
        GL11.glDisable(3042 /*GL_BLEND*/);
        boolean flag1 = (mc.thePlayer.field_9306_bj / 3) % 2 == 1;
        if(mc.thePlayer.field_9306_bj < 10)
        {
            flag1 = false;
        }
        int i1 = mc.thePlayer.health;
        int j1 = mc.thePlayer.prevHealth;
        rand.setSeed(updateCounter * 0x4c627);
		
        if(mc.playerController.shouldDrawHUD())
        {
            int k1 = mc.thePlayer.getPlayerArmorValue();
            for(int j2 = 0; j2 < 10; j2++)
            {
            	//edit for y change
            	int k3;
            	if (mc.theWorld.newGUI) {
            		k3 = 18;//l - 32;
            	} else {
            		k3 = l - 32;
            	}
                if(k1 > 0)
                {
                    int j5 = (k / 2 + 91) - j2 * 8 - 9;
                    if(j2 * 2 + 1 < k1)
                    {
                        drawTexturedModalRect(j5, k3, 34, 9, 9, 9);
                    }
                    if(j2 * 2 + 1 == k1)
                    {
                        drawTexturedModalRect(j5, k3, 25, 9, 9, 9);
                    }
                    if(j2 * 2 + 1 > k1)
                    {
                        drawTexturedModalRect(j5, k3, 16, 9, 9, 9);
                    }
                }
                int k5 = 0;
                if(flag1)
                {
                    k5 = 1;
                }
                //edit for x change
                int i6;
                if (mc.theWorld.newGUI) {
                	i6 = (k / 2 - 191) + j2 * 8;
                } else {
                	i6 = (k / 2 - 91) + j2 * 8;
                }
                if(i1 <= 4)
                {
                    k3 += rand.nextInt(2);
                }
                drawTexturedModalRect(i6, k3, 16 + k5 * 9, 0, 9, 9);
                if(flag1)
                {
                    if(j2 * 2 + 1 < j1)
                    {
                        drawTexturedModalRect(i6, k3, 70, 0, 9, 9);
                    }
                    if(j2 * 2 + 1 == j1)
                    {
                        drawTexturedModalRect(i6, k3, 79, 0, 9, 9);
                    }
                }
                if(j2 * 2 + 1 < i1)
                {
                    drawTexturedModalRect(i6, k3, 52, 0, 9, 9);
                }
                if(j2 * 2 + 1 == i1)
                {
                    drawTexturedModalRect(i6, k3, 61, 0, 9, 9);
                }
            }

            if(mc.thePlayer.isInsideOfMaterial(Material.water))
            {
                int k2 = (int)Math.ceil(((double)(mc.thePlayer.air - 2) * 10D) / 300D);
                int l3 = (int)Math.ceil(((double)mc.thePlayer.air * 10D) / 300D) - k2;
                for(int l5 = 0; l5 < k2 + l3; l5++)
                {
                    if(l5 < k2)
                    {
                        drawTexturedModalRect((k / 2 - 91) + l5 * 8, l - 32 - 9, 16, 18, 9, 9);
                    } else
                    {
                        drawTexturedModalRect((k / 2 - 91) + l5 * 8, l - 32 - 9, 25, 18, 9, 9);
                    }
                }

            }
        }
        GL11.glDisable(3042 /*GL_BLEND*/);
        GL11.glEnable(32826 /*GL_RESCALE_NORMAL_EXT*/);
        GL11.glPushMatrix();
        GL11.glRotatef(180F, 1.0F, 0.0F, 0.0F);
        RenderHelper.enableStandardItemLighting();
        GL11.glPopMatrix();
        for(int l1 = 0; l1 < 9; l1++)
        {
        	int i3;
            //moves item renders to the left
        	if (mc.theWorld.newGUI) {
        		i3 = 15 + l1 * 20;
        	} else {
        		i3 = (k / 2 - 90) + l1 * 20 + 2;
        	}
            int i4 = l - 16 - 3;
            func_554_a(l1, i3, i4, f);
        }

        RenderHelper.disableStandardItemLighting();
        GL11.glDisable(32826 /*GL_RESCALE_NORMAL_EXT*/);
        //if(mc.thePlayer.func_22060_M() > 0)
		
		try {
			ModLoader.RunOSDHooks(mc);
		} catch (Exception ex) {}
		
		int tmp = 0;
		for(int l33 = 0; l33 < mc.theWorld.loadedTileEntityList.size(); l33++)
        {		
            TileEntity tileentity = (TileEntity)mc.theWorld.loadedTileEntityList.get(l33);
			//System.out.println(tileentity);
			if (tileentity instanceof TileEntityMobSpawnerWave)			
			{
				if (tileentity.isActivated){
					tmp++;
				}
			}
			mc.theWorld.spawnerCount = tmp;
        }	
		
		GL11.glDisable(2929 /*GL_DEPTH_TEST*/);
		GL11.glDisable(3008 /*GL_ALPHA_TEST*/);
		int i2 = mc.thePlayer.func_22060_M();
		float f3 = (float)i2 / 100F;
		if(f3 > 1.0F)
		{
			f3 = 1.0F - (float)(i2 - 100) / 10F;
		}
		int j4 = (int)(220F * f3) << 24 | 0x101020;
		drawRect(0, 0, k, l, j4);
		GL11.glEnable(3008 /*GL_ALPHA_TEST*/);
		GL11.glEnable(2929 /*GL_DEPTH_TEST*/);
		
		if(mc.gameSettings.field_22276_A)
        {
            fontrenderer.drawStringWithShadow((new StringBuilder()).append("ZombieCraft ").append(mod_ZombieCraft.getVersion()).append(" (").append(mc.debug).append(")").toString(), 2, 2, 0xffffff);
            fontrenderer.drawStringWithShadow(mc.func_6241_m(), 2, 12, 0xffffff);
            fontrenderer.drawStringWithShadow(mc.func_6262_n(), 2, 22, 0xffffff);
            fontrenderer.drawStringWithShadow(mc.func_6245_o(), 2, 32, 0xffffff);
            fontrenderer.drawStringWithShadow(mc.func_21002_o(), 2, 42, 0xffffff);
            long l2 = Runtime.getRuntime().maxMemory();
            long l4 = Runtime.getRuntime().totalMemory();
            long l6 = Runtime.getRuntime().freeMemory();
            long l7 = l4 - l6;
            String s = (new StringBuilder()).append("Used memory: ").append((l7 * 100L) / l2).append("% (").append(l7 / 1024L / 1024L).append("MB) of ").append(l2 / 1024L / 1024L).append("MB").toString();
            drawString(fontrenderer, s, k - fontrenderer.getStringWidth(s) - 2, 2, 0xe0e0e0);
            s = (new StringBuilder()).append("Allocated memory: ").append((l4 * 100L) / l2).append("% (").append(l4 / 1024L / 1024L).append("MB)").toString();
            drawString(fontrenderer, s, k - fontrenderer.getStringWidth(s) - 2, 12, 0xe0e0e0);
            drawString(fontrenderer, (new StringBuilder()).append("x: ").append(mc.thePlayer.posX).toString(), 2, 64, 0xe0e0e0);
            drawString(fontrenderer, (new StringBuilder()).append("y: ").append(mc.thePlayer.posY).toString(), 2, 72, 0xe0e0e0);
            drawString(fontrenderer, (new StringBuilder()).append("z: ").append(mc.thePlayer.posZ).toString(), 2, 80, 0xe0e0e0);
        } else
        {
            fontrenderer.drawStringWithShadow((new StringBuilder()).append("ZombieCraft ").append(mod_ZombieCraft.getVersion()).toString(), 2, 2, 0xffffff);
			//if (mc.theWorld.zombHUD) 
			{		
				fontrenderer.drawStringWithShadow("ZombieCraft - " + difficulties[mc.gameSettings.difficulty], 2, 40, 0xE52626);
				fontrenderer.drawStringWithShadow("----------------", 2, 48, 0xE52626);
				fontrenderer.drawStringWithShadow("Wave: "+String.valueOf(mc.theWorld.zombieWave)+" Mobs: "+String.valueOf(mc.theWorld.mobArr.size())+" Points: "+String.valueOf(mc.thePlayer.getScore()), 2, 56, 0xE52626);
				fontrenderer.drawStringWithShadow("ZombieKills: "+String.valueOf(mc.theWorld.zombieKills)/*+" MaxSpawns: "+String.valueOf(mc.theWorld.waveSpawnMax)+" KillsNeeded: "+String.valueOf(mc.theWorld.zombieWave*mc.theWorld.spawnerCount)+" SpawnCount: "+String.valueOf(mc.theWorld.waveSpawnCount)*/, 2, 64, 0xE52626);
				//fontrenderer.drawStringWithShadow("newWave: "+String.valueOf(newWave), 2, 72, 0xE52626);
				/*if (mc.theWorld.zombieWave==0)
				{
					fontrenderer.drawStringWithShadow("Start the game using the ZombieMod submenu when you press esc!", 2, 80, 0xE52626);	
				}*/
				
				
			}
        }
		
		
		
		//Wave logic code start \
		
		//wave auto init
		if (mc.theWorld.zombieWave == 0) {
			/*mc.theWorld.zombieWave++;			
			mc.theWorld.zombieKills=0;
			mc.theWorld.waveSpawnCount = 0;
			mc.theWorld.waveSpawnMax=(mc.theWorld.zombieWave*3)+7;*/
			//mc.theWorld.playSoundEffect(mc.thePlayer.posX, mc.thePlayer.posY, mc.thePlayer.posZ, "sdkzc.round_over", 2F, 1.0F);
		}
		
		//First Wave Init Logic - Random spawning
		
		if (!mc.theWorld.doneWaveInit && mc.theWorld.zombieWave == 1) {
			if (mc.theWorld.field_22145_q.startPosCount > 0) {
				int randomSpawn = rand.nextInt(mc.theWorld.field_22145_q.startPosCount);
				mc.thePlayer.setPosition(mc.theWorld.field_22145_q.startPosX[randomSpawn], mc.theWorld.field_22145_q.startPosY[randomSpawn], mc.theWorld.field_22145_q.startPosZ[randomSpawn]);
				/*mc.thePlayer.posX = mc.theWorld.field_22145_q.startPosX[randomSpawn];
				mc.thePlayer.posY = mc.theWorld.field_22145_q.startPosY[randomSpawn];
				mc.thePlayer.posZ = mc.theWorld.field_22145_q.startPosZ[randomSpawn];
				mc.thePlayer.prevPosX = mc.theWorld.field_22145_q.startPosX[randomSpawn];
				mc.thePlayer.prevPosY = mc.theWorld.field_22145_q.startPosY[randomSpawn];
				mc.thePlayer.prevPosZ = mc.theWorld.field_22145_q.startPosZ[randomSpawn];*/
				
			}
			mc.theWorld.doneWaveInit = true;
		}
		
		if (!(newWave) && (mc.theWorld.zombieKills>=mc.theWorld.waveSpawnMax) && mc.theWorld.zombieWave != 0)
		{
			waveDelay = System.currentTimeMillis();
			newWave = true;
			mc.theWorld.playSoundEffect(mc.thePlayer.posX, mc.thePlayer.posY, mc.thePlayer.posZ, "sdkzc.round_over", 2F, 1.0F);
			//fontrenderer.drawStringWithShadow("You can move on to the next wave now!", 2, 80, 0xE52626);	
		}
		
		//Code to keep it night
		if (mc.theWorld.field_22145_q.func_22304_f() > 22000) {
			mc.theWorld.field_22145_q.func_22307_a((long)14000);
		}
		
		for(int k5 = 0; k5 < mc.theWorld.mobArr.size(); k5++)
		{
			if (mc.theWorld.mobArr.get(k5).isDead) {
				mc.theWorld.mobArr.remove(k5);
			}
		}
		
		if (newWave) {
			if(System.currentTimeMillis() - waveDelay >= 10000) {
				if (mc.theWorld.zombieKills >= mc.theWorld.waveSpawnMax)
				{
					mc.theWorld.zombieWave++;			
					mc.theWorld.zombieKills=0;
					mc.theWorld.waveSpawnCount=0;
					mc.theWorld.waveSpawnMax=(mc.theWorld.zombieWave*3)+7;
					//mc.displayGuiScreen(null);
					//mc.func_6259_e();
					newWave = false;
				}
			}
		}
		//Wave logic code end \

		//Purchasing code
		if(canBuy == false && System.currentTimeMillis() - lastBuy >= 1500) {
			canBuy = true;
			lastBuy = System.currentTimeMillis();
		}
		if(System.currentTimeMillis() - lastRandAdd >= 100) {
			randAnimTimer+=100;
			lastRandAdd = System.currentTimeMillis();
		}
		if (randAnim) {	
			if(randAnimTimer % 100 == 0) {
				if (oldEntItem != null) {
					oldEntItem.setEntityDead();
				}
				randWeap = randItem();
				EntityItem entityitem = new EntityItem(mc.theWorld, chestX, chestY+1, chestZ, new ItemStack(randWeap, 1, 0));
                entityitem.delayBeforeCanPickup = 5000;
                mc.theWorld.entityJoinedWorld(entityitem);
				oldEntItem = entityitem;
				if(randAnimTimer == 4000) {
					randAnim = false;
					buyRandom = true;
					randTimer = System.currentTimeMillis();
				}
			}
		}
		
		if (buyRandom) {	
			if(System.currentTimeMillis() - randTimer >= 15000) {
				buyRandom = false;
				if (oldEntItem != null) {
					oldEntItem.setEntityDead();
				}
			}
		}
		if(mc.thePlayer.inventory.getCurrentItem() != null)
			fontrenderer.drawStringWithShadow(StringTranslate.getInstance().translateNamedKey(mc.thePlayer.inventory.getCurrentItem().func_20109_f()), (k / 2 - 91) + 1, l - 40, 0xffffff);		
		
		boolean showingMessage = false;
		
		if (mc.theWorld.showNoPower)
		{
			fontrenderer.drawStringWithShadow("The power must be activated first.", 2, 128, 0xffffff);		
		}

		if (mc.theWorld.powerMenu && !mc.theWorld.field_22145_q.getPower())
		{
			fontrenderer.drawStringWithShadow("Press E to activate power.", 2, 128, 0xffffff);
			showingMessage = true;
			if (Keyboard.isKeyDown(18))
			{
				mod_ZombieCraft.triggerPower(triggerX, triggerY, triggerZ);
			}
		}
		
		
		if (mc.theWorld.showBuyMenu)
		{	
			Item itemtype = mc.theWorld.itemToBuy;
			
			Block blocktype = mc.theWorld.blockToBuy;
			String s = "";
			int cost = mc.theWorld.weapCost;
			int count = mc.theWorld.weapCount;
			if (!randAnim)
			{
				//If mysterybox and random item is ready
				if (buyRandom && cost == 950) {		
					itemtype = randWeap;
					String s3 = itemtype.getItemName();
					String s4 = (StringTranslate.getInstance().translateNamedKey(s3));
					
					fontrenderer.drawStringWithShadow("Press E for a "+s4+".", 2, 136, 0xffffff);	
					showingMessage = true;
					if (Keyboard.isKeyDown(18))
					{
						if (canBuy)
						{
							
							int tryID = -1;
							tryID = mc.thePlayer.inventory.getOptimalBuySlot(mc.thePlayer.inventory, itemtype, blocktype);

				    		if (tryID > -1) {
							
				    			
								
								if (itemtype instanceof SdkZcItemGun) 
								{										
									mc.thePlayer.inventory.mainInventory[9+tryID] = new ItemStack(((SdkZcItemGun)itemtype).requiredBullet, ((SdkZcItemGun)itemtype).requiredBullet.maxStackSize);
									mc.thePlayer.inventory.mainInventory[18+tryID] = new ItemStack(((SdkZcItemGun)itemtype).requiredBullet, ((SdkZcItemGun)itemtype).requiredBullet.maxStackSize);
									mc.thePlayer.inventory.mainInventory[27+tryID] = new ItemStack(((SdkZcItemGun)itemtype).requiredBullet, ((SdkZcItemGun)itemtype).requiredBullet.maxStackSize);
									if (tryID < 2) {
										mc.thePlayer.inventory.setInventorySlotContents(tryID, new ItemStack(itemtype, randCount));
									}
								} else {
									if (tryID < 4) {
										mc.thePlayer.inventory.setInventorySlotContents(tryID, new ItemStack(itemtype, randCount));
									}
								}
								
								//mc.thePlayer.dropPlayerItem(new ItemStack(itemtype, 1));	
								canBuy = false;
								lastBuy = System.currentTimeMillis();
								buyRandom = false;
								if (oldEntItem != null) {
									oldEntItem.setEntityDead();
								}
				    		}
						}
					}
				} else {			
					if (cost!=950) {
						if (itemtype==null) {
							s = blocktype.getBlockName();
						} else {							
							s = itemtype.getItemName();		
							
						}
					}
					String s2 = (StringTranslate.getInstance().translateNamedKey(s));			
					
					if (count > 1) {			
					fontrenderer.drawStringWithShadow("Press E to buy "+String.valueOf(count)+" "+s2+" for "+String.valueOf(cost)+" points.", 2, 128, 0xffffff);
					} else if (cost == 950) {
					fontrenderer.drawStringWithShadow("Press E to buy a random weapon for 950 points.", 2, 128, 0xffffff);
					} else {
					fontrenderer.drawStringWithShadow("Press E to buy "+s2+" for "+String.valueOf(cost)+" points.", 2, 128, 0xffffff);
					}
					showingMessage = true;
					
					if (Keyboard.isKeyDown(18))
					{
						if (canBuy)
						{
							if (mc.thePlayer.getScore()>=cost)
								{
									//If mystery box, start random item animation
									if (cost == 950) {
										if (mc.theWorld.getBlockId(triggerX+1,triggerY,triggerZ)==Block.crate.blockID)
										{
											chestX = triggerX+1;
											chestY = triggerY;
											chestZ = triggerZ;										
											if (mc.theWorld.getBlockId((int)chestX,(int)chestY,(int)chestZ+1)==Block.crate.blockID)
											{
												chestZ = chestZ + 1;
											}										
											chestX+=0.5;
										} else if (mc.theWorld.getBlockId(triggerX,triggerY,triggerZ+1)==Block.crate.blockID)
										{
											chestX = triggerX;
											chestY = triggerY;
											chestZ = triggerZ+1;
											if (mc.theWorld.getBlockId((int)chestX+1,(int)chestY,(int)chestZ)==Block.crate.blockID)
											{
												chestX = chestX + 1;
											}
											chestZ+=0.5;										
										} else if (mc.theWorld.getBlockId(triggerX,triggerY,triggerZ-1)==Block.crate.blockID)
										{
											chestX = triggerX;
											chestY = triggerY;
											chestZ = triggerZ-1;
											if (mc.theWorld.getBlockId((int)chestX+1,(int)chestY,(int)chestZ)==Block.crate.blockID)
											{
												chestX = chestX + 1;
											}			
											chestZ+=0.5;	
										} else if (mc.theWorld.getBlockId(triggerX-1,triggerY,triggerZ)==Block.crate.blockID)
										{
											chestX = triggerX-1;
											chestY = triggerY;
											chestZ = triggerZ;																				
											if (mc.theWorld.getBlockId((int)chestX,(int)chestY,(int)chestZ+1)==Block.crate.blockID)
											{
												chestZ = chestZ + 1;
											} 										
											chestX+=0.5;
										}
										
										mc.thePlayer.score = mc.thePlayer.score - cost;
										mc.theWorld.playSoundEffect(mc.thePlayer.posX, mc.thePlayer.posY, mc.thePlayer.posZ, "mysterybox", 2F, 1.0F);
										randAnim = true;
										randAnimTimer = 0;
										canBuy=false;
										
									//If any other item to buy
									} else {
										
										int tryID = -1;
										tryID = mc.thePlayer.inventory.getOptimalBuySlot(mc.thePlayer.inventory, itemtype, blocktype);
										
										if (tryID > -1) {
											
											mc.thePlayer.score = mc.thePlayer.score - cost;
											
											if (mc.theWorld.buyItem) {
												
												
												
												if (itemtype instanceof SdkZcItemGun) 
												{
													mc.thePlayer.inventory.mainInventory[9+tryID] = new ItemStack(((SdkZcItemGun)itemtype).requiredBullet, ((SdkZcItemGun)itemtype).requiredBullet.maxStackSize);
													mc.thePlayer.inventory.mainInventory[18+tryID] = new ItemStack(((SdkZcItemGun)itemtype).requiredBullet, ((SdkZcItemGun)itemtype).requiredBullet.maxStackSize);
													mc.thePlayer.inventory.mainInventory[27+tryID] = new ItemStack(((SdkZcItemGun)itemtype).requiredBullet, ((SdkZcItemGun)itemtype).requiredBullet.maxStackSize);
												}
												if (itemtype == mod_ZombieCraft.frienddrink || itemtype == mod_ZombieCraft.jugg || itemtype == mod_ZombieCraft.speed || itemtype instanceof ItemAbility/* || blocktype == mod_ZombieCraft.betty*/) {
													
													//auto use item
													
													if ((itemtype.onItemRightClick(new ItemStack(itemtype, count), mc.thePlayer.worldObj, mc.thePlayer)).stackSize > 0) {
														mc.thePlayer.score = mc.thePlayer.score + cost;
													}
													//if (mod_EntAI.enhance && tryID != -1) {
														//mc.thePlayer.inventory.mainInventory[tryID] = new ItemStack(itemtype, count);
													//} else {
														//mc.thePlayer.inventory.mainInventory[tryID] = new ItemStack(itemtype, count);
														
													//}
												} else {
													/*System.out.print("id: ");
													System.out.println(tryID);*/
													if (tryID < 2) {
														mc.thePlayer.inventory.setInventorySlotContents(tryID, new ItemStack(itemtype, count));
													}
												}
												
											} else {
												mc.thePlayer.inventory.setInventorySlotContents(tryID, new ItemStack(blocktype, count));
											}
										
											//mc.thePlayer.dropPlayerItem(new ItemStack(itemtype, 1));	
											canBuy=false;
											lastBuy = System.currentTimeMillis();
										}
									}
								}
						}
					}
				}
			}
		}
		
		if (mc.theWorld.instaKill)
		{
			fontrenderer.drawStringWithShadow("INSTAKILL", 2, 168, 0x3ba425);
		}
		
		if (mc.thePlayer.doubleScore)
		{
			fontrenderer.drawStringWithShadow("DOUBLE POINTS", 2, 176, 0x2432d2);
		}
		
		if (mc.thePlayer.canCharge && mc.thePlayer.chargeCooldown == 0)
		{
			fontrenderer.drawStringWithShadow("CHARGE READY", 2, 184, 0xFF0000);
		}
		
		if (mc.thePlayer.canShock && mc.thePlayer.shockCooldown == 0)
		{
			fontrenderer.drawStringWithShadow("EXSTATIC READY", 2, 192, 0xFFFF00);
		}
		
		if (mc.theWorld.showSwitchTrap) {
			fontrenderer.drawStringWithShadow("Hit switch to activate trap for "+String.valueOf(((BlockTriggerTrap)mod_ZombieCraft.trapPlate_trig).cost)+" points.", 2, 128, 0xffffff);
			mc.theWorld.showSwitchTrap = false;
			
			showingMessage = true;
		}
		
		if (mc.theWorld.showSwitchPower) {
			fontrenderer.drawStringWithShadow("Hit switch to activate power.", 2, 128, 0xffffff);
			mc.theWorld.showSwitchPower = false;
			
			showingMessage = true;
		}
		
		//Debris clear
		if (mc.theWorld.showBuyMenu2)
		{	
			//Item itemtype = mc.theWorld.weapToBuy;//Item.swordSteel;
			//String s = itemtype.getItemName();
			//String s2 = (StringTranslate.getInstance().translateNamedKey(s));
			int cost = mc.theWorld.weapCost;
			fontrenderer.drawStringWithShadow("Press E to clear debris for "+String.valueOf(cost)+" points.", 2, 128, 0xffffff);
			showingMessage = true;

			if (Keyboard.isKeyDown(18))
			{
				if (canBuy)
				{
					if (mc.thePlayer.getScore()>=cost)
						{
							mc.thePlayer.score = mc.thePlayer.score - cost;
							if (mc.theWorld.getBlockId(mc.theWorld.debrisX+1,mc.theWorld.debrisY,mc.theWorld.debrisZ)==Block.fence.blockID || mc.theWorld.getBlockId(mc.theWorld.debrisX+1,mc.theWorld.debrisY,mc.theWorld.debrisZ)==mod_ZombieCraft.debris1000Plate.blockID)
							{
								mc.theWorld.setBlockWithNotify(mc.theWorld.debrisX+1,mc.theWorld.debrisY,mc.theWorld.debrisZ,0);
							}
							if (mc.theWorld.getBlockId(mc.theWorld.debrisX,mc.theWorld.debrisY,mc.theWorld.debrisZ+1)==Block.fence.blockID || mc.theWorld.getBlockId(mc.theWorld.debrisX,mc.theWorld.debrisY,mc.theWorld.debrisZ+1)==mod_ZombieCraft.debris1000Plate.blockID)
							{
								mc.theWorld.setBlockWithNotify(mc.theWorld.debrisX,mc.theWorld.debrisY,mc.theWorld.debrisZ+1,0);
							}
							if (mc.theWorld.getBlockId(mc.theWorld.debrisX-1,mc.theWorld.debrisY,mc.theWorld.debrisZ)==Block.fence.blockID || mc.theWorld.getBlockId(mc.theWorld.debrisX-1,mc.theWorld.debrisY,mc.theWorld.debrisZ)==mod_ZombieCraft.debris1000Plate.blockID)
							{
								mc.theWorld.setBlockWithNotify(mc.theWorld.debrisX-1,mc.theWorld.debrisY,mc.theWorld.debrisZ,0);	
							}
							if (mc.theWorld.getBlockId(mc.theWorld.debrisX,mc.theWorld.debrisY,mc.theWorld.debrisZ-1)==Block.fence.blockID || mc.theWorld.getBlockId(mc.theWorld.debrisX,mc.theWorld.debrisY,mc.theWorld.debrisZ-1)==mod_ZombieCraft.debris1000Plate.blockID)
							{
								mc.theWorld.setBlockWithNotify(mc.theWorld.debrisX,mc.theWorld.debrisY,mc.theWorld.debrisZ-1,0);	
							}			
							if (mc.theWorld.getBlockId(mc.theWorld.debrisX,mc.theWorld.debrisY+1,mc.theWorld.debrisZ)==Block.fence.blockID || mc.theWorld.getBlockId(mc.theWorld.debrisX,mc.theWorld.debrisY+1,mc.theWorld.debrisZ)==mod_ZombieCraft.debris1000Plate.blockID)
							{
								mc.theWorld.setBlockWithNotify(mc.theWorld.debrisX,mc.theWorld.debrisY+1,mc.theWorld.debrisZ,0);	
							}		
							if (mc.theWorld.getBlockId(mc.theWorld.debrisX,mc.theWorld.debrisY-1,mc.theWorld.debrisZ)==Block.fence.blockID || mc.theWorld.getBlockId(mc.theWorld.debrisX,mc.theWorld.debrisY-1,mc.theWorld.debrisZ)==mod_ZombieCraft.debris1000Plate.blockID)
							{
								mc.theWorld.setBlockWithNotify(mc.theWorld.debrisX,mc.theWorld.debrisY-1,mc.theWorld.debrisZ,0);	
							}		
							
							mc.theWorld.setBlockWithNotify(mc.theWorld.debrisX,mc.theWorld.debrisY,mc.theWorld.debrisZ,0);
							mc.theWorld.showBuyMenu2=false;
							canBuy=false;
							lastBuy = System.currentTimeMillis();
							
							//System.out.println("Debris down");
							mod_EntAI.QueueWPNodesInit = true;
						}
				}
			}
		}
		
		//Barricade repair
		if (mc.thePlayer.nearDoor && !showingMessage) {
			if (System.currentTimeMillis() > mc.thePlayer.lastRepairTime + 1000) {
				int points = 10;
				int cost = 750;
				
				
				//if (mc.thePlayer.getScore() >= cost) {
					
					
						
						int blockID = mc.thePlayer.worldObj.getBlockId(mc.thePlayer.barrierX, mc.thePlayer.barrierY, mc.thePlayer.barrierZ);
						//System.out.println(blockID);
						if (((BlockBarricade)mod_ZombieCraft.barricadeS5).isBarricade(mc.thePlayer.worldObj,mc.thePlayer.barrierX,mc.thePlayer.barrierY,mc.thePlayer.barrierZ)) {
							if (blockID == 71 && (mc.thePlayer.getScore() >= cost)) {
								fontrenderer.drawStringWithShadow("Press E to open door for 750 points.", 2, 128, 0xffffff);
							} else if (blockID != 64) {
								fontrenderer.drawStringWithShadow("Press E to repair barrier.", 2, 128, 0xffffff);
							}
							showingMessage = true;
							if (Keyboard.isKeyDown(18))
							{
								
								
								if (blockID == 71) {
									if ((mc.thePlayer.getScore() >= cost)) {
										mc.thePlayer.lastRepairTime = System.currentTimeMillis();
										((BlockDoor)Block.blocksList[blockID]).onBlockClicked(mc.thePlayer.worldObj, mc.thePlayer.barrierX, mc.thePlayer.barrierY, mc.thePlayer.barrierZ, mc.thePlayer);
										mc.thePlayer.score = mc.thePlayer.score - cost;
									}
								} else if (blockID != 64) {
									mc.thePlayer.lastRepairTime = System.currentTimeMillis();
									((BlockDoor)Block.blocksList[blockID]).onBlockClicked(mc.thePlayer.worldObj, mc.thePlayer.barrierX, mc.thePlayer.barrierY, mc.thePlayer.barrierZ, mc.thePlayer);
									mc.thePlayer.addToPlayerScore(mc.thePlayer,points);
									mc.theWorld.playSoundEffect(mc.thePlayer.posX, mc.thePlayer.posY, mc.thePlayer.posZ, "repair", 1F, 1.0F);
									mc.theWorld.playSoundEffect(mc.thePlayer.posX, mc.thePlayer.posY, mc.thePlayer.posZ, "chaching", 1F, 0.8F);
								}
							}
							
						}
						
						
						//((BlockDoor)mod_ZombieCraft.barricadeS0).onBlockClicked(mc.thePlayer.worldObj, mc.thePlayer.barrierX, mc.thePlayer.barrierY, mc.thePlayer.barrierZ, mc.thePlayer);
						
					
				//}
				
			}
		}
		
		//Trap enabling (global counter for now)
		if (mc.theWorld.showTrapUse) {
			int cost = mc.theWorld.weapCost;
			fontrenderer.drawStringWithShadow("Press E to activate trap for "+String.valueOf(cost)+" points.", 2, 128, 0xffffff);
			

			if (Keyboard.isKeyDown(18))
			{
				if (canBuy)// && mc.thePlayer.worldObj.trapTimer <= 0)
				{
					if (mc.thePlayer.getScore()>=cost)
					{
							//mc.thePlayer.score = mc.thePlayer.score - cost;
							
							//mc.thePlayer.worldObj.trapTimer = 80*20;
							
							mod_ZombieCraft.triggerTrap(triggerX, triggerY, triggerZ);
							
							//mc.theWorld.showBuyMenu2=false;
							//canBuy=false;
							//lastBuy = System.currentTimeMillis();
							
					}
					
				}
			}
			
		}
		
		/*if (mc.thePlayer.worldObj.trapTimer > 0) {
			mod_ZombieCraft.animateTraps();
			mc.thePlayer.worldObj.trapTimer--;
		}*/
			
        if(field_9419_j > 0)
        {
            float f2 = (float)field_9419_j - f;
            int j3 = (int)((f2 * 256F) / 20F);
            if(j3 > 255)
            {
                j3 = 255;
            }
            if(j3 > 0)
            {
                GL11.glPushMatrix();
                GL11.glTranslatef(k / 2, l - 48, 0.0F);
                GL11.glEnable(3042 /*GL_BLEND*/);
                GL11.glBlendFunc(770, 771);
                int k4 = 0xffffff;
                if(field_22065_l)
                {
                    k4 = Color.HSBtoRGB(f2 / 50F, 0.7F, 0.6F) & 0xffffff;
                }
                fontrenderer.drawString(field_9420_i, -fontrenderer.getStringWidth(field_9420_i) / 2, -4, k4 + (j3 << 24));
                GL11.glDisable(3042 /*GL_BLEND*/);
                GL11.glPopMatrix();
            }
        }
        byte byte0 = 10;
        boolean flag2 = false;
        if(mc.currentScreen instanceof GuiChat)
        {
            byte0 = 20;
            flag2 = true;
        }
        GL11.glEnable(3042 /*GL_BLEND*/);
        GL11.glBlendFunc(770, 771);
        GL11.glDisable(3008 /*GL_ALPHA_TEST*/);
        GL11.glPushMatrix();
        GL11.glTranslatef(0.0F, l - 48, 0.0F);
        for(int i5 = 0; i5 < chatMessageList.size() && i5 < byte0; i5++)
        {
            if(((ChatLine)chatMessageList.get(i5)).updateCounter >= 200 && !flag2)
            {
                continue;
            }
            double d = (double)((ChatLine)chatMessageList.get(i5)).updateCounter / 200D;
            d = 1.0D - d;
            d *= 10D;
            if(d < 0.0D)
            {
                d = 0.0D;
            }
            if(d > 1.0D)
            {
                d = 1.0D;
            }
            d *= d;
            int j6 = (int)(255D * d);
            if(flag2)
            {
                j6 = 255;
            }
            if(j6 > 0)
            {
                byte byte1 = 2;
                int k6 = -i5 * 9;
                String s1 = ((ChatLine)chatMessageList.get(i5)).message;
                drawRect(byte1, k6 - 1, byte1 + 320, k6 + 8, j6 / 2 << 24);
                GL11.glEnable(3042 /*GL_BLEND*/);
                fontrenderer.drawStringWithShadow(s1, byte1, k6, 0xffffff + (j6 << 24));
            }
        }

        GL11.glPopMatrix();
        GL11.glEnable(3008 /*GL_ALPHA_TEST*/);
        GL11.glDisable(3042 /*GL_BLEND*/);
    }

    private void func_4063_a(int i, int j)
    {
        GL11.glDisable(2929 /*GL_DEPTH_TEST*/);
        GL11.glDepthMask(false);
        GL11.glBlendFunc(770, 771);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glDisable(3008 /*GL_ALPHA_TEST*/);
        GL11.glBindTexture(3553 /*GL_TEXTURE_2D*/, mc.renderEngine.getTexture("%blur%/misc/pumpkinblur.png"));
        Tessellator tessellator = Tessellator.instance;
        tessellator.startDrawingQuads();
        tessellator.addVertexWithUV(0.0D, j, -90D, 0.0D, 1.0D);
        tessellator.addVertexWithUV(i, j, -90D, 1.0D, 1.0D);
        tessellator.addVertexWithUV(i, 0.0D, -90D, 1.0D, 0.0D);
        tessellator.addVertexWithUV(0.0D, 0.0D, -90D, 0.0D, 0.0D);
        tessellator.draw();
        GL11.glDepthMask(true);
        GL11.glEnable(2929 /*GL_DEPTH_TEST*/);
        GL11.glEnable(3008 /*GL_ALPHA_TEST*/);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    }

    private void func_4064_a(float f, int i, int j)
    {
        f = 1.0F - f;
        if(f < 0.0F)
        {
            f = 0.0F;
        }
        if(f > 1.0F)
        {
            f = 1.0F;
        }
        field_931_c += (double)(f - field_931_c) * 0.01D;
        GL11.glDisable(2929 /*GL_DEPTH_TEST*/);
        GL11.glDepthMask(false);
        GL11.glBlendFunc(0, 769);
        GL11.glColor4f(field_931_c, field_931_c, field_931_c, 1.0F);
        GL11.glBindTexture(3553 /*GL_TEXTURE_2D*/, mc.renderEngine.getTexture("%blur%/misc/vignette.png"));
        Tessellator tessellator = Tessellator.instance;
        tessellator.startDrawingQuads();
        tessellator.addVertexWithUV(0.0D, j, -90D, 0.0D, 1.0D);
        tessellator.addVertexWithUV(i, j, -90D, 1.0D, 1.0D);
        tessellator.addVertexWithUV(i, 0.0D, -90D, 1.0D, 0.0D);
        tessellator.addVertexWithUV(0.0D, 0.0D, -90D, 0.0D, 0.0D);
        tessellator.draw();
        GL11.glDepthMask(true);
        GL11.glEnable(2929 /*GL_DEPTH_TEST*/);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glBlendFunc(770, 771);
    }

    private void func_4065_b(float f, int i, int j)
    {
        f *= f;
        f *= f;
        f = f * 0.8F + 0.2F;
        GL11.glDisable(3008 /*GL_ALPHA_TEST*/);
        GL11.glDisable(2929 /*GL_DEPTH_TEST*/);
        GL11.glDepthMask(false);
        GL11.glBlendFunc(770, 771);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, f);
        GL11.glBindTexture(3553 /*GL_TEXTURE_2D*/, mc.renderEngine.getTexture("/terrain.png"));
        float f1 = (float)(Block.portal.blockIndexInTexture % 16) / 16F;
        float f2 = (float)(Block.portal.blockIndexInTexture / 16) / 16F;
        float f3 = (float)(Block.portal.blockIndexInTexture % 16 + 1) / 16F;
        float f4 = (float)(Block.portal.blockIndexInTexture / 16 + 1) / 16F;
        Tessellator tessellator = Tessellator.instance;
        tessellator.startDrawingQuads();
        tessellator.addVertexWithUV(0.0D, j, -90D, f1, f4);
        tessellator.addVertexWithUV(i, j, -90D, f3, f4);
        tessellator.addVertexWithUV(i, 0.0D, -90D, f3, f2);
        tessellator.addVertexWithUV(0.0D, 0.0D, -90D, f1, f2);
        tessellator.draw();
        GL11.glDepthMask(true);
        GL11.glEnable(2929 /*GL_DEPTH_TEST*/);
        GL11.glEnable(3008 /*GL_ALPHA_TEST*/);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    }

    private void func_554_a(int i, int j, int k, float f)
    {
        ItemStack itemstack = mc.thePlayer.inventory.mainInventory[i];
        if(itemstack == null)
        {
            return;
        }
        float f1 = (float)itemstack.animationsToGo - f;
        if(f1 > 0.0F)
        {
            GL11.glPushMatrix();
            float f2 = 1.0F + f1 / 5F;
            GL11.glTranslatef(j + 8, k + 12, 0.0F);
            GL11.glScalef(1.0F / f2, (f2 + 1.0F) / 2.0F, 1.0F);
            GL11.glTranslatef(-(j + 8), -(k + 12), 0.0F);
        }
        itemRenderer.renderItemIntoGUI(mc.fontRenderer, mc.renderEngine, itemstack, j, k);
        if(f1 > 0.0F)
        {
            GL11.glPopMatrix();
        }
        itemRenderer.renderItemOverlayIntoGUI(mc.fontRenderer, mc.renderEngine, itemstack, j, k);
    }

    public void func_555_a()
    {
        if(field_9419_j > 0)
        {
            field_9419_j--;
        }
        updateCounter++;
        for(int i = 0; i < chatMessageList.size(); i++)
        {
            ((ChatLine)chatMessageList.get(i)).updateCounter++;
        }

    }

    public void addChatMessage(String s)
    {
        int i;
        for(; mc.fontRenderer.getStringWidth(s) > 320; s = s.substring(i))
        {
            for(i = 1; i < s.length() && mc.fontRenderer.getStringWidth(s.substring(0, i + 1)) <= 320; i++) { }
            addChatMessage(s.substring(0, i));
        }

        chatMessageList.add(0, new ChatLine(s));
        for(; chatMessageList.size() > 50; chatMessageList.remove(chatMessageList.size() - 1)) { }
    }

    public void func_553_b(String s)
    {
        field_9420_i = (new StringBuilder()).append("Now playing: ").append(s).toString();
        field_9419_j = 60;
        field_22065_l = true;
    }

    public void func_22064_c(String s)
    {
        StringTranslate stringtranslate = StringTranslate.getInstance();
        String s1 = stringtranslate.translateKey(s);
        addChatMessage(s1);
    }
    
    //private  Item[] myArray = { Item.swordWood,mod_ZombieCraft.chickenNade};//, Block.betty};
	
	private  Item[] myArray = { Item.swordWood,Item.swordWood,Item.swordWood,
								Item.swordStone,Item.swordStone,Item.swordStone,
								mod_ZombieCraft.lsword,
								mod_ZombieCraft.bigax,
								mod_ZombieCraft.nail,
								mod_SdkZombieCraft.itemGunM1911,
								mod_SdkZombieCraft.itemGunKar98k,mod_SdkZombieCraft.itemGunKar98k,
								mod_SdkZombieCraft.itemGunM1Garand,mod_SdkZombieCraft.itemGunM1Garand,
								mod_SdkZombieCraft.itemGunMp40,mod_SdkZombieCraft.itemGunMp40,
								mod_SdkZombieCraft.itemGunTrenchgun,mod_SdkZombieCraft.itemGunTrenchgun,
								mod_SdkZombieCraft.itemGunFlamethrower,mod_SdkZombieCraft.itemGunFlamethrower,
								mod_SdkZombieCraft.itemGunRayGun,
								mod_SdkZombieCraft.itemGunUzi,mod_SdkZombieCraft.itemGunUzi,
								mod_SdkZombieCraft.itemGunMagum,mod_SdkZombieCraft.itemGunMagum,
								mod_SdkZombieCraft.itemGunRifle,mod_SdkZombieCraft.itemGunRifle,
								mod_SdkZombieCraft.itemGunPanzerfaust,mod_SdkZombieCraft.itemGunPanzerfaust,
								mod_SdkZombieCraft.itemGrenadeStun, mod_ZombieCraft.chickenNade};//, Block.betty};
	private int randomNum;
	public Item randItem()
	{
		Item random = Item.swordWood;
		Random rand = new Random(); 
		for (int i = 0; i < myArray.length; i++){
			randomNum = rand.nextInt(myArray.length);//(int)(2 * (myArray.length));
			if (myArray[randomNum] instanceof SdkZcItemCustomStackSize) 
			{
				randCount = myArray[randomNum].maxStackSize;
			} else {
				randCount = 1;
			}
			//System.out.println(randomNum);
			random = myArray[randomNum];
		}
		return random;
	}
	
	public static final String difficulties[] = {
        "Peaceful", "Easy", "Normal", "Hard"
    };

    private static RenderItem itemRenderer = new RenderItem();
    private java.util.List chatMessageList;
    private Random rand;
    private Minecraft mc;
    public String field_933_a;
    private int updateCounter;
    private String field_9420_i;
    private int field_9419_j;
    private boolean field_22065_l;
    public float field_6446_b;
    float field_931_c;
    public int count;
	public int randCount;	
	public boolean canBuy;
	public boolean buyRandom;
	public boolean randAnim;
	public Item randWeap;
	public long lastBuy = 0;
	public long randTimer = 0;
	public long randAnimTimer = 0;
	private boolean newWave;
	private long waveDelay = 0;
	private double chestX;
	private double chestY;
	private double chestZ;
	public int triggerX;
	public int triggerY;
	public int triggerZ;
	private EntityItem oldEntItem;
	private int axis;
	private long lastRandAdd;
}
