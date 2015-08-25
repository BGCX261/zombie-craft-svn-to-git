package net.minecraft.src;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

import java.util.List;
import net.minecraft.client.Minecraft;
import org.lwjgl.input.Keyboard;

public class GuiNotPremium extends GuiScreen
{

    public GuiNotPremium(GuiScreen guiscreen)
    {
        parentScreen = guiscreen;
    }

    public void initGui()
    {
        StringTranslate stringtranslate = StringTranslate.getInstance();
        Keyboard.enableRepeatEvents(true);
        controlList.clear();
		controlList.add(new GuiButton(0, width / 2 - 100, height / 4 + 96 + 12, "ZombieCraft Store"));
        controlList.add(new GuiButton(1, width / 2 - 100, height / 4 + 120 + 12, stringtranslate.translateKey("gui.cancel")));
    }

    public void onGuiClosed()
    {
        Keyboard.enableRepeatEvents(false);
    }

    protected void actionPerformed(GuiButton guibutton)
    {
        if(guibutton.id == 1)
        {
            mc.displayGuiScreen(parentScreen);
        }
		if(guibutton.id == 0)
        {
			java.awt.Desktop desktop = java.awt.Desktop.getDesktop();
			try {
                java.net.URI uri = new java.net.URI( "http://modzilla.zymichost.com/store/" );
                desktop.browse( uri );
            }
            catch ( Exception e ) { e.printStackTrace(); }
            mc.displayGuiScreen(parentScreen);
        }
    }

    public void drawScreen(int i, int j, float f)
    {
        StringTranslate stringtranslate = StringTranslate.getInstance();
        drawDefaultBackground();
        drawCenteredString(fontRenderer, stringtranslate.translateKey("Unauthorized access!"), width / 2, (height / 4 - 60) + 20, 0xffffff);
        drawString(fontRenderer, stringtranslate.translateKey("This map is not available to you because you have not     "), width / 2 - 140, (height / 4 - 60) + 60 + 0, 0xa0a0a0);
        drawString(fontRenderer, stringtranslate.translateKey("bought it, please visit our store for more info."), width / 2 - 140, (height / 4 - 60) + 60 + 9, 0xa0a0a0);
        
		drawString(fontRenderer, stringtranslate.translateKey("Click the button below to get to our store."), width / 2 - 140, (height / 4 - 60) + 60 + 36, 0xa0a0a0);
        super.drawScreen(i, j, f);
    }

    private GuiScreen parentScreen;
}
