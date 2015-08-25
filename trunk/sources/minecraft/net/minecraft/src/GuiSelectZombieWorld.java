package net.minecraft.src;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

import java.io.*;
import java.util.*;
import net.minecraft.client.Minecraft;
import org.lwjgl.input.Keyboard;

class GuiSelectZombieWorld extends GuiSelectWorld
{

    public GuiSelectZombieWorld(GuiScreen guiscreen)
    {
        super(guiscreen);
        selectWorldGui = null;
        worldList = new ArrayList();
    }

    public void initGui()
    {
        StringTranslate stringtranslate = StringTranslate.getInstance();
        screenTitle = stringtranslate.translateKey("Choose map");
        //field_22098_o = stringtranslate.translateKey("multiplayer.server");
        func_22084_k(); // load worlds
        field_22099_n = new GuiZombieWorldSlot(this);
        field_22099_n.func_22240_a(controlList, 4, 5);
        initGui2();
    }

    public void initGui2()
    {
        StringTranslate stringtranslate = StringTranslate.getInstance();
        /*controlList.add(field_22104_s = new GuiButton(1, width / 2 - 154, height - 52, 150, 20, stringtranslate.translateKey("multiplayer.connect")));
        controlList.add(field_22095_r = new GuiButton(6, width / 2 - 154, height - 28, 70, 20, stringtranslate.translateKey("selectWorld.rename")));
        controlList.add(field_22103_t = new GuiButton(2, width / 2 - 74, height - 28, 70, 20, stringtranslate.translateKey("selectWorld.delete")));
        controlList.add(new GuiButton(3, width / 2 + 4, height - 52, 150, 20, stringtranslate.translateKey("multiplayer.add")));*/
        controlList.add(new GuiButton(0, width / 2 - 100, height - 28,stringtranslate.translateKey("gui.cancel")));/*
        field_22104_s.enabled = false;
        field_22095_r.enabled = false;
        field_22103_t.enabled = false;*/
    }

    public void func_22084_k()
    {
		Minecraft mc = ModLoader.getMinecraftInstance();
        worldList.clear();
		ArrayList arraylist = new ArrayList();
		File zombieWorldDir = new File(mc.getMinecraftDir(),"zcmaps");
		if(zombieWorldDir.exists() && zombieWorldDir.isDirectory())
        {
            File afile[] = zombieWorldDir.listFiles();
            File afile1[] = afile;
            int i = afile1.length;
            for(int j = 0; j < i; j++)
            {
                File file = afile1[j];
                if(!file.isFile() || !file.getName().toLowerCase().endsWith(".zip"))
                {
                    continue;
                }
                String s = (new StringBuilder()).append(file.getName()).append(":").append(file.length()).append(":").append(file.lastModified()).toString();
                try
                {
                    worldList.add(new ZombieSaveRecord(zombieWorldDir,file.getName()));
                }
                catch(Exception exception)
                {
                    exception.printStackTrace();
                }
            }

        }
       
        ZombieSaveRecord zombiesaverecord;
        for(Iterator iterator = worldList.iterator(); iterator.hasNext();)
        {
            zombiesaverecord = (ZombieSaveRecord)iterator.next();
            zombiesaverecord.load();
        }
    }

    public void selectWorld(int i)
    {
	
    }

    private int func_22003_b(String s, int i)
    {
        try
        {
            return Integer.parseInt(s.trim());
        }
        catch(Exception exception)
        {
            return i;
        }
    }

    protected String func_22094_d(int i)
    {
        StringTranslate stringtranslate = StringTranslate.getInstance();
        Keyboard.enableRepeatEvents(true);
        ZombieSaveRecord zombiesaverecord = (ZombieSaveRecord)worldList.get(i);
        return zombiesaverecord == null ? (new StringBuilder()).append(stringtranslate.translateKey("multiplayer.server")).append(i).toString() : zombiesaverecord.getText();
    }

    protected void actionPerformed(GuiButton guibutton)
    {
        if(!guibutton.enabled)
        {
            return;
        }
        if(guibutton.id == 2)
        {
            String s = func_22094_d(field_22101_l);
            if(s != null)
            {
                field_22096_q = true;
                StringTranslate stringtranslate = StringTranslate.getInstance();
                String s1 = stringtranslate.translateKey("multiplayer.deleteQuestion");
                String s2 = (new StringBuilder()).append("'").append(s).append("' ").append(stringtranslate.translateKey("multiplayer.deleteWarning")).toString();
                String s3 = stringtranslate.translateKey("selectWorld.deleteButton");
                String s4 = stringtranslate.translateKey("gui.cancel");
                GuiYesNo guiyesno = new GuiYesNo(this, s1, s2, s3, s4, field_22101_l);
                mc.displayGuiScreen(guiyesno);
            }
        } else
        if(guibutton.id == 1)
        {
            selectWorld(field_22101_l);
        } else
        if(guibutton.id == 3)
        {			
			//worldList.add(new ZombieSaveRecord("Nacht der Untoten","Replica of the original WaW map.","Nacht.zip"));			
			func_22004_c(worldList);
            //mc.displayGuiScreen(new GuiAddServer(this));
        } else/*
        if(guibutton.id == 6)
        {
            mc.displayGuiScreen(new GuiRenameMPWorld(this, field_22101_l));
        } else*/
        if(guibutton.id == 0)
        {
            mc.displayGuiScreen(parentScreen);
        }/* else
        {
            field_22099_n.func_22241_a(guibutton);
        }*/
    }

    public static void func_22004_c(List list)
    {
        try
        {
            File file = new File(Minecraft.getMinecraftDir(), "resources/");
            File file1 = new File(file, "map.list");
            file1.createNewFile();
            FileOutputStream fileoutputstream = new FileOutputStream(file1);
            ObjectOutputStream objectoutputstream = new ObjectOutputStream(fileoutputstream);
            objectoutputstream.writeObject(list);
            objectoutputstream.close();
            fileoutputstream.close();
        }
        catch(Exception exception)
        {
            System.out.println(exception.getMessage());
        }
    }

    public void deleteWorld(boolean flag, int i)
    {
        worldList.remove(i);
        func_22004_c(worldList);
        mc.displayGuiScreen(this);
    }

    private GuiSelectWorld selectWorldGui;
	public List worldList;
}
