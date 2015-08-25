// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) fieldsfirst nonlb safe
// Source File Name:   TexturedPanel.java

package net.minecraft;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

// Referenced classes of package net.minecraft:
//            LoginForm

public class TexturedPanel extends JPanel {

    private static final long serialVersionUID = 1L;
    private Image img;
    private Image bgImage;

    public TexturedPanel() {
        setOpaque(true);

        try {
            bgImage = ImageIO.read((net.minecraft.LoginForm.class).getResource("dirt.png")).getScaledInstance(32, 32, 16);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void update(Graphics g) {
        paint(g);
    }

    public void paintComponent(Graphics g2) {
        int w = getWidth() / 2 + 1;
        int h = getHeight() / 2 + 1;

        if(img == null || img.getWidth(((java.awt.image.ImageObserver) (null))) != w || img.getHeight(((java.awt.image.ImageObserver) (null))) != h) {
            img = createImage(w, h);
            Graphics g = img.getGraphics();

            for(int x = 0; x <= w / 32; x++) {
                for(int y = 0; y <= h / 32; y++)
                    g.drawImage(bgImage, x * 32, y * 32, ((java.awt.image.ImageObserver) (null)));
            }

            if(g instanceof Graphics2D) {
                Graphics2D gg = (Graphics2D)g;
                int gh = 1;
                gg.setPaint(((java.awt.Paint) (new GradientPaint(((Point2D) (new java.awt.geom.Point2D.Float(0.0F, 0.0F))), new Color(0x20ffffff, true), ((Point2D) (new java.awt.geom.Point2D.Float(0.0F, gh))), new Color(0, true)))));
                gg.fillRect(0, 0, w, gh);
                gh = h;
                gg.setPaint(((java.awt.Paint) (new GradientPaint(((Point2D) (new java.awt.geom.Point2D.Float(0.0F, 0.0F))), new Color(0, true), ((Point2D) (new java.awt.geom.Point2D.Float(0.0F, gh))), new Color(0x60000000, true)))));
                gg.fillRect(0, 0, w, gh);
            }

            g.dispose();
        }

        g2.drawImage(img, 0, 0, w * 2, h * 2, ((java.awt.image.ImageObserver) (null)));
    }
}
