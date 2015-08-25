package net.minecraft.src;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;
import net.minecraft.client.Minecraft;

public class CustomAnimation
{

    public CustomAnimation(Minecraft minecraft, String s, int i, byte abyte0[])
    {
        this(minecraft, s, i, abyte0, -1, -1);
    }

    public CustomAnimation(Minecraft minecraft, String s, int i, byte abyte0[], int j, int k)
    {
        field_22016_minScrollDelay = -1;
        field_22009_h = -1;
        field_22008_V = -1;
        field_22020_bi = minecraft;
        field_22021_bh = i;
        field_22018_aZZZZZZZ = abyte0;
        field_22016_minScrollDelay = j;
        field_22009_h = k;
        field_22011_isScrolling = j >= 0;
        BufferedImage bufferedimage;
        try
        {
            bufferedimage = ImageIO.read((net.minecraft.client.Minecraft.class).getResource("/terrain.png"));
        }
        catch(IOException ioexception)
        {
            ioexception.printStackTrace();
            return;
        }
        field_22017_tileWidth = bufferedimage.getWidth() / 16;
        field_22013_tileHeight = bufferedimage.getHeight() / 16;
        BufferedImage bufferedimage1 = null;
        try
        {
            java.net.URL url = (net.minecraft.client.Minecraft.class).getResource((new StringBuilder()).append("/custom_").append(s).append(".png").toString());
            if(url != null)
            {
                bufferedimage1 = ImageIO.read(url);
            }
        }
        catch(IOException ioexception1)
        {
            bufferedimage1 = null;
        }
        if(bufferedimage1 != null)
        {
            field_22012_numFrames = bufferedimage1.getHeight() / bufferedimage1.getWidth();
            if(bufferedimage1.getWidth() != field_22017_tileWidth)
            {
                BufferedImage bufferedimage2 = new BufferedImage(field_22017_tileWidth, field_22013_tileHeight * field_22012_numFrames, 2);
                Graphics2D graphics2d = bufferedimage2.createGraphics();
                graphics2d.drawImage(bufferedimage1, 0, 0, field_22017_tileWidth, field_22013_tileHeight * field_22012_numFrames, null);
                bufferedimage1 = bufferedimage2;
            }
            int ai[] = new int[bufferedimage1.getWidth() * bufferedimage1.getHeight()];
            bufferedimage1.getRGB(0, 0, bufferedimage1.getWidth(), bufferedimage1.getHeight(), ai, 0, field_22017_tileWidth);
            field_22007_src = new byte[ai.length * 4];
            func_22006_t(ai, field_22007_src);
            field_22014_isCustom = true;
        } else
        {
            int l = (i % 16) * field_22017_tileWidth;
            int i1 = (i / 16) * field_22013_tileHeight;
            int ai1[] = new int[field_22017_tileWidth * field_22013_tileHeight];
            bufferedimage.getRGB(l, i1, field_22017_tileWidth, field_22013_tileHeight, ai1, 0, field_22017_tileWidth);
            func_22006_t(ai1, abyte0);
            if(field_22011_isScrolling)
            {
                field_22019_aY = new byte[field_22017_tileWidth * 4];
            }
        }
    }

    private void func_22006_t(int ai[], byte abyte0[])
    {
        for(int i = 0; i < ai.length; i++)
        {
            int j = ai[i];
            abyte0[i * 4 + 3] = (byte)(j >> 24 & 0xff);
            abyte0[i * 4 + 0] = (byte)(j >> 16 & 0xff);
            abyte0[i * 4 + 1] = (byte)(j >> 8 & 0xff);
            abyte0[i * 4 + 2] = (byte)(j >> 0 & 0xff);
        }

    }

    public void func_22005_v()
    {
        if(field_22007_src != null)
        {
            if(++field_22010_a >= field_22012_numFrames)
            {
                field_22010_a = 0;
            }
            System.arraycopy(field_22007_src, field_22010_a * (field_22013_tileHeight * field_22017_tileWidth * 4), field_22018_aZZZZZZZ, 0, field_22013_tileHeight * field_22017_tileWidth * 4);
        } else
        if(field_22011_isScrolling && (field_22009_h <= 0 || --field_22008_V <= 0))
        {
            if(field_22009_h > 0)
            {
                field_22008_V = field_22015_rand.nextInt((field_22009_h - field_22016_minScrollDelay) + 1) + field_22016_minScrollDelay;
            }
            System.arraycopy(field_22018_aZZZZZZZ, (field_22013_tileHeight - 1) * field_22017_tileWidth * 4, field_22019_aY, 0, field_22017_tileWidth * 4);
            System.arraycopy(field_22018_aZZZZZZZ, 0, field_22018_aZZZZZZZ, field_22017_tileWidth * 4, field_22017_tileWidth * (field_22013_tileHeight - 1) * 4);
            System.arraycopy(field_22019_aY, 0, field_22018_aZZZZZZZ, 0, field_22017_tileWidth * 4);
        }
    }

    Minecraft field_22020_bi;
    int field_22021_bh;
    int field_22017_tileWidth;
    int field_22013_tileHeight;
    int field_22010_a;
    int field_22012_numFrames;
    byte field_22007_src[];
    byte field_22018_aZZZZZZZ[];
    byte field_22019_aY[];
    int field_22016_minScrollDelay;
    int field_22009_h;
    int field_22008_V;
    boolean field_22011_isScrolling;
    boolean field_22014_isCustom;
    private static Random field_22015_rand = new Random();

}
