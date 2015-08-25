// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) fieldsfirst nonlb safe
// Source File Name:   LogoPanel.java

package net.minecraft;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

// Referenced classes of package net.minecraft:
//            LoginForm

public class LogoPanel extends JPanel {

    private static final long serialVersionUID = 1L;
    private Image bgImage;

    public LogoPanel() {
        setOpaque(true);

        try {
            BufferedImage src = ImageIO.read((net.minecraft.LoginForm.class).getResource("logo.png"));
            int w = src.getWidth();
            int h = src.getHeight();
            bgImage = src.getScaledInstance(w, h, 16);
            setPreferredSize(new Dimension(w + 32, h + 32));
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void update(Graphics g) {
        paint(g);
    }

    public void paintComponent(Graphics g2) {
        g2.drawImage(bgImage, 24, 24, ((java.awt.image.ImageObserver) (null)));
    }
}
