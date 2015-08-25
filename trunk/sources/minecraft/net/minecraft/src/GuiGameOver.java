package net.minecraft.src;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

import java.util.List;
import net.minecraft.client.Minecraft;
import org.lwjgl.opengl.GL11;
import org.lwjgl.input.Keyboard;

import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.Toolkit;
import java.io.*;

//import javax.crypto.*;
//import javax.crypto.spec.*;
import java.security.spec.*;
import java.security.*;

public class GuiGameOver extends GuiScreen implements ClipboardOwner
{

    public GuiGameOver()
    {
    }
	
	public void lostOwnership( Clipboard aClipboard, Transferable aContents) {
     //do nothing
	}

    public void initGui()
    {
        controlList.clear();
        if (mc.theWorld.devMode || Keyboard.isKeyDown(73)) { controlList.add(new GuiButton(1, width / 2 - 100, height / 4 + 72, "Retry")); } //"Get webcode"));
        controlList.add(new GuiButton(2, width / 2 - 100, height / 4 + 96, "Back to menu"));
        if(mc.session == null)
        {
            ((GuiButton)controlList.get(1)).enabled = false;
        }
    }

    protected void keyTyped(char c, int i)
    {
    }
	
	protected void actionPerformed(GuiButton guibutton)
    {
        if(guibutton.id != 0);
        if(guibutton.id == 1)
        {
		
			mc.theWorld.zombieWave=0;
			mc.theWorld.zombieKills=0;
			mc.theWorld.waveSpawnCount = 0;
			//mc.theWorld.waveSpawnMax=(mc.theWorld.zombieWave*5)+10;
			mc.thePlayer.score = 0;
			
			for(int k = 0; k < mc.theWorld.loadedEntityList.size(); k++)
			{
				Entity tEnt = (Entity)mc.theWorld.loadedEntityList.get(k);
				if (tEnt instanceof EntityMobs) {
					tEnt.setEntityDead();
				}
			} 
            mc.thePlayer.respawnPlayer();
            mc.displayGuiScreen(null);
			
			String str = new String(mc.theWorld.zombieWave+";"+mc.theWorld.totalZombieKills+";"+mc.thePlayer.score+";"+mc.thePlayer.username+";"+System.currentTimeMillis()+";"+"("+ModLoader.modList+")");
			setClipboardContents(str);
        }
        if(guibutton.id == 2)
        {
			mc.theWorld.zombieWave=0;
			mc.theWorld.zombieKills=0;
			for(int k = 0; k < mc.theWorld.mobArr.size(); k++)
			{
			mc.theWorld.mobArr.get(k).setEntityDead();
			}            
            mc.changeWorld1(null);
            mc.displayGuiScreen(new GuiMainMenu());
        }
    }
	
	public void setClipboardContents( String aString ){
		StringSelection stringSelection = new StringSelection( aString );
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		clipboard.setContents( stringSelection, this );
	}

    public void drawScreen(int i, int j, float f)
    {
        drawGradientRect(0, 0, width, height, 0x60500000, 0xa0803030);
        GL11.glPushMatrix();
        GL11.glScalef(1.5F, 1.5F, 1.5F);
        drawCenteredString(fontRenderer, getString(mc.theWorld.zombieWave),width / 3, 30, 0xffffff);
        GL11.glPopMatrix();
        drawCenteredString(fontRenderer, (new StringBuilder()).append("Points: ").append(mc.thePlayer.getScore()).append(" Wave: ").append(mc.theWorld.zombieWave).toString(), width / 2, 100, 0xffffff);
        super.drawScreen(i, j, f);
    }
	
	public String getString(int wave)
	{
		if (wave==1){ return "Pro Tip 1: Right click to shoot!"; }
		if (wave==2){ return "You're just as bad as Kane...";}
		if (wave==3){  return "You beat Kane's record!"; }
		if (wave==4){  return "Wow stop making Kane look bad..."; }
		if (wave==5){  return "Try Hello Kitty instead."; }
		if (wave==6){  return "You're Getting there! Just try harder."; }		
		if (wave==7){  return "Protip 2: Hugging zombies, your doing it wrong"; }
		if (wave==8){  return "Ya, that was a zombie behind you..."; }
		if (wave==9){ return "MD65's record!"; }
		if (wave==10){ return "At least you reached double digits...."; }
		if (wave>=11 && wave<=20){ return "Are you even trying?"; }
		if (wave>=21 && wave<=30){ return "Keep trying, you might be good some day."; }
		if (wave==31){ return "Are you camping?"; }
		if (wave>=32 && wave<=40){ return "Cheater...."; }
		if (wave>=41  && wave<=999){ return "Hax? MLG? Notch?"; }
		
		
		return "Zombies ate your brain!";
	}

    public boolean doesGuiPauseGame()
    {
        return false;
    }
}
