package net.minecraft.src;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

import java.util.List;
import net.minecraft.client.Minecraft;

public class GuiZombieMenu extends GuiScreen
{

    public GuiZombieMenu()
    {
        updateCounter2 = 0;
        updateCounter = 0;
    }

    public void initGui()
    {
        updateCounter2 = 0;
        controlList.clear();
        controlList.add(new GuiButton(11, width / 2 -100, height / 4 + 24, 100, 20, "Next wave"));
        controlList.add(new GuiButton(2, width / 2, height / 4 + 24, 100, 20, "Reset game"));
		if(mc.theWorld.zombieWave==0)
        {
            ((GuiButton)controlList.get(0)).displayString = "Start game";
			((GuiButton)controlList.get(0)).id = 1;
        }
		controlList.add(new GuiButton(5, width / 2 - 100, height / 4 + 48,100,20,"Toggle HUD"));
		controlList.add(new GuiButton(6, width / 2 , height / 4 + 48,100,20,"Dev Mode: " + mc.theWorld.devMode));
		
		controlList.add(new GuiButton(3, width / 2 - 100, height / 4 + 72,100,20,"Give spawnblocks"));
		controlList.add(new GuiButton(7, width / 2, height / 4 + 72,100,20,"Give 10000p"));		
		//controlList.add(new GuiButton(5, width / 2 - 100, height / 4 + 72, "ZombieMod"));
		controlList.add(new GuiButton(8, width / 2 - 100, height / 4 + 96,100,20,"Door noclip: " + mod_EntAI.doorNoClip));
		//controlList.add(new GuiButton(7, width / 2, height / 4 + 96,100,20,"Give 10000p"));
        controlList.add(new GuiButton(4, width / 2 - 100, height / 4 + 120, "Back to game"));
        //controlList.add(new GuiButton(8, width / 2 - 100, height / 4 + 120, "Toggle door noclip"));
        //controlList.add(new GuiButton(0, width / 2 - 100, height / 4 + 96, "Options..."));
    }

    protected void actionPerformed(GuiButton guibutton)
    {
		if(guibutton.id == 11)
        {
			/*for(int k = 0; k < mc.theWorld.loadedEntityList.size(); k++)
			{
				Entity tEnt = (Entity)mc.theWorld.loadedEntityList.get(k);
				if (tEnt instanceof EntityMobs) {
					tEnt.setEntityDead();
				}
			}*/ 
			mc.theWorld.zombieWave++;			
			mc.theWorld.zombieKills=0;
			mc.theWorld.waveSpawnCount = 0;
			mc.theWorld.waveSpawnMax=(mc.theWorld.zombieWave*3)+7;
			//mc.displayGuiScreen(null);
            //mc.func_6259_e();
		}
		
		if(guibutton.id == 8)
        {	
			mod_EntAI.doorNoClip = !mod_EntAI.doorNoClip;
			initGui();
			//mc.displayGuiScreen(null);
            //mc.func_6259_e();
        }
	
        if(guibutton.id == 1)
        {	
			//if (mc.theWorld.zombieKills>=mc.theWorld.waveSpawnMax)
			//{
			mc.theWorld.zombieWave++;			
			mc.theWorld.zombieKills=0;
			mc.theWorld.waveSpawnCount = 0;
			mc.theWorld.waveSpawnMax=(mc.theWorld.zombieWave*3)+7;
			//mc.displayGuiScreen(null);
            //mc.func_6259_e();
			//}
        }	        
		if(guibutton.id == 5)
		{
			mc.theWorld.zombHUD = !mc.theWorld.zombHUD;
			mc.displayGuiScreen(null);
            mc.func_6259_e();
		}
		if(guibutton.id == 6)
		{
			mc.theWorld.devMode = !mc.theWorld.devMode;
			
			if (mc.theWorld.devMode) {
				mod_EntAI.setNodeSize(0.1F);
			} else {
				mod_EntAI.setNodeSize(0.001F);
			}
			initGui();
			//mc.displayGuiScreen(null);
            //mc.func_6259_e();
		}
		if(guibutton.id == 3)
        {
			//mc.thePlayer.dropPlayerItem(new ItemStack(mod_ZombieCraft.pressurePlateWave, 64));
			mc.thePlayer.dropPlayerItem(new ItemStack(mod_ZombieCraft.mobSpawnerWave, 64));
			mc.thePlayer.dropPlayerItem(new ItemStack(mod_ZombieCraft.woodSwordPlate, 64));
			mc.thePlayer.dropPlayerItem(new ItemStack(mod_ZombieCraft.ironSwordPlate, 64));
			mc.thePlayer.dropPlayerItem(new ItemStack(mod_ZombieCraft.stoneSwordPlate, 64));
			mc.thePlayer.dropPlayerItem(new ItemStack(mod_ZombieCraft.Kar98kPlate, 64));
			mc.thePlayer.dropPlayerItem(new ItemStack(mod_ZombieCraft.M1GarandPlate, 64));
			mc.thePlayer.dropPlayerItem(new ItemStack(mod_ZombieCraft.Mp40Plate, 64));
			mc.thePlayer.dropPlayerItem(new ItemStack(mod_ZombieCraft.TrenchgunPlate, 64));
			mc.thePlayer.dropPlayerItem(new ItemStack(mod_ZombieCraft.FlamethrowerPlate, 64));
			mc.thePlayer.dropPlayerItem(new ItemStack(mod_ZombieCraft.PanzerfaustPlate, 64));			
			mc.thePlayer.dropPlayerItem(new ItemStack(mod_ZombieCraft.debris1000Plate, 64));			
			mc.thePlayer.dropPlayerItem(new ItemStack(mod_ZombieCraft.juggPerkPlate, 64));		
			mc.thePlayer.dropPlayerItem(new ItemStack(mod_ZombieCraft.speedPerkPlate, 64));	
			mc.thePlayer.dropPlayerItem(new ItemStack(mod_ZombieCraft.bettyPlate, 64));		
			mc.thePlayer.dropPlayerItem(new ItemStack(mod_ZombieCraft.mbPlate, 64));			
			mc.thePlayer.dropPlayerItem(new ItemStack(mod_ZombieCraft.friendPerkPlate, 64));			
			mc.thePlayer.dropPlayerItem(new ItemStack(Block.fence, 64));
			mc.thePlayer.dropPlayerItem(new ItemStack(mod_ZombieCraft.magicStick, 1));
			mc.thePlayer.dropPlayerItem(new ItemStack(mod_ZombieCraft.nodeStick, 1));
			mc.thePlayer.dropPlayerItem(new ItemStack(Block.cobblestone, 64));
			mc.thePlayer.dropPlayerItem(new ItemStack(mod_ZombieCraft.radio, 64));
			mc.thePlayer.dropPlayerItem(new ItemStack(mod_ZombieCraft.powerTrigger, 64));
			mc.thePlayer.dropPlayerItem(new ItemStack(mod_ZombieCraft.invisBlock, 64));
			mc.thePlayer.dropPlayerItem(new ItemStack(mod_ZombieCraft.zombiesOnlyBlock, 64));
			mc.displayGuiScreen(null);
            mc.func_6259_e();
		}
		if(guibutton.id == 7)
		{
			mc.thePlayer.addToPlayerScore(null,10000);
			//mc.displayGuiScreen(null);
            //mc.func_6259_e();
		}        
        if(guibutton.id == 2)
        {			
			/*for(int k = 0; k < mc.theWorld.mobArr.size(); k++)
			{
			mc.theWorld.mobArr.get(k).setEntityDead();
			}*/
			
			for(int k = 0; k < mc.theWorld.loadedEntityList.size(); k++)
			{
				Entity tEnt = (Entity)mc.theWorld.loadedEntityList.get(k);
				if (tEnt instanceof EntityMobs) {
					tEnt.setEntityDead();
				}
			} 
			
			mc.theWorld.zombieWave=0;						
			mc.theWorld.zombieKills=0;
			mc.theWorld.waveSpawnCount = 0;
			mc.theWorld.waveSpawnMax=(mc.theWorld.zombieWave*3)+7;
			mc.thePlayer.score = 0;
			//mc.displayGuiScreen(null);
            //mc.func_6259_e();
        }			
		if(guibutton.id == 4)
        {
            mc.displayGuiScreen(null);
            mc.func_6259_e();
        }		
    }

    public void updateScreen()
    {
        super.updateScreen();
        updateCounter++;
    }

    public void drawScreen(int i, int j, float f)
    {
        drawDefaultBackground();

		drawCenteredString(fontRenderer, "ZombieMod. Wave: "+String.valueOf(mc.theWorld.zombieWave), width / 2, 40, 0xffffff);
        
        super.drawScreen(i, j, f);
    }

    private int updateCounter2;
    private int updateCounter;
}
