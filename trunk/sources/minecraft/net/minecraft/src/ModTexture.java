package net.minecraft.src;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class ModTexture extends TextureFX
{

    public ModTexture(int i, int j, BufferedImage bufferedimage)
    {
        super(i);
        field_22003_pixels = null;
        tileImage = j;
        int k = bufferedimage.getWidth();
        int l = bufferedimage.getHeight();
        int i1 = (int)Math.sqrt(imageData.length / 4);
        field_22003_pixels = new int[i1 * i1];
        if(k != l || k != i1)
        {
            BufferedImage bufferedimage1 = new BufferedImage(i1, i1, bufferedimage.getType());
            Graphics2D graphics2d = bufferedimage1.createGraphics();
            graphics2d.drawImage(bufferedimage, 0, 0, i1, i1, 0, 0, k, l, null);
            bufferedimage1.getRGB(0, 0, i1, i1, field_22003_pixels, 0, i1);
        } else
        {
            bufferedimage.getRGB(0, 0, k, l, field_22003_pixels, 0, k);
        }
        func_22001_u();
    }

    public void func_22001_u()
    {
        for(int i = 0; i < field_22003_pixels.length; i++)
        {
            int j = field_22003_pixels[i] >> 24 & 0xff;
            int k = field_22003_pixels[i] >> 16 & 0xff;
            int l = field_22003_pixels[i] >> 8 & 0xff;
            int i1 = field_22003_pixels[i] >> 0 & 0xff;
            if(anaglyphEnabled)
            {
                int j1 = (k + l + i1) / 3;
                k = l = i1 = j1;
            }
            imageData[i * 4 + 0] = (byte)k;
            imageData[i * 4 + 1] = (byte)l;
            imageData[i * 4 + 2] = (byte)i1;
            imageData[i * 4 + 3] = (byte)j;
        }

        field_22002_oldanaglyph = anaglyphEnabled;
    }

    public void onTick()
    {
        if(field_22002_oldanaglyph != anaglyphEnabled)
        {
            func_22001_u();
        }
    }

    private boolean field_22002_oldanaglyph;
    private int field_22003_pixels[];
}
