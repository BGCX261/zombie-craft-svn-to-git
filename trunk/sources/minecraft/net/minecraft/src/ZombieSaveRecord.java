package net.minecraft.src;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

import java.io.*;
import java.util.zip.ZipFile;

class ZombieSaveRecord
    implements Serializable
{

    public ZombieSaveRecord(File f1, String s2)
    {
		zipname = s2;
		worldFile = new File(f1, s2);
    }

    public boolean func_22015_a()
    {
        return true;
    }

    public String getSubText()
    {
        return subText;
    }

    public String getSubText2()
    {
        return subText2;
    }

    public String getText()
    {
        return text;
    }
	
	public String getZipname()
    {
        return zipname;
    }

    public void setText(String s)
    {
        text = s;
    }
	
	public void setZipname(String s)
    {
        zipname = s;
    }

    public void setSubText(String s)
    {
        subText = s;
    }   
	
	public void setSubText2(String s)
    {
        subText2 = s;
    }
	
	public void load()
	{
		ZipFile zipfile = null;
        InputStream inputstream = null;
        try
        {
            zipfile = new ZipFile(worldFile);
            try
            {
                inputstream = zipfile.getInputStream(zipfile.getEntry("map.txt"));
                BufferedReader bufferedreader = new BufferedReader(new InputStreamReader(inputstream));
                text = bufferedreader.readLine();
                subText = bufferedreader.readLine();
				subText2 = bufferedreader.readLine();
                bufferedreader.close();
                inputstream.close();
            }
            catch(Exception exception) { }
            zipfile.close();
        }
        catch(Exception exception2)
        {
            exception2.printStackTrace();
        }
        finally
        {
            try
            {
                inputstream.close();
            }
            catch(Exception exception4) { }
            try
            {
                zipfile.close();
            }
            catch(Exception exception5) { }
        }
	}

    private String text;
    private String subText;	
    private String subText2;	
    private String zipname;
	private File worldFile;
}
