// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) fieldsfirst nonlb safe
// Source File Name:   OptionsPanel.java

package net.minecraft;

import java.awt.*;

// Referenced classes of package net.minecraft:
//            TransparentLabel, OptionsPanel

class OptionsPanel$2 extends TransparentLabel {

    private static final long serialVersionUID = 0L;

    public void paint(Graphics g) {
        super.paint(g);
        int x = 0;
        int y = 0;
        FontMetrics fm = g.getFontMetrics();
        int width = fm.stringWidth(getText());
        int height = fm.getHeight();

        if(getAlignmentX() == 2.0F)
            x = 0;
        else if(getAlignmentX() == 0.0F)
            x = getBounds().width / 2 - width / 2;
        else if(getAlignmentX() == 4F)
            x = getBounds().width - width;

        y = (getBounds().height / 2 + height / 2) - 1;
        g.drawLine(x + 2, y, (x + width) - 2, y);
    }

    public void update(Graphics g) {
        paint(g);
    }

    OptionsPanel$2(String $anonymous0) {
        super($anonymous0);
    }
}
