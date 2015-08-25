// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) fieldsfirst nonlb safe
// Source File Name:   Launcher.java

package net.minecraft;

import java.applet.Applet;
import java.applet.AppletStub;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.awt.image.VolatileImage;
import java.io.IOException;
import java.io.PrintStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;

// Referenced classes of package net.minecraft:
//            LoginForm, GameUpdater

public class Launcher extends Applet
    implements Runnable, AppletStub, MouseListener {

    private static final long serialVersionUID = 1L;
    public Map customParameters;
    private GameUpdater gameUpdater;
    private boolean gameUpdaterStarted;
    private Applet applet;
    private Image bgImage;
    private boolean active;
    private int context;
    private boolean hasMouseListener;
    private VolatileImage img;

    public Launcher() {
        customParameters = ((Map) (new HashMap()));
        gameUpdaterStarted = false;
        active = false;
        context = 0;
        hasMouseListener = false;
    }

    public boolean isActive() {
        if(context == 0) {
            context = -1;

            try {
                if(getAppletContext() != null)
                    context = 1;
            } catch(Exception exception) { }
        }

        if(context == -1)
            return active;
        else
            return super.isActive();
    }

    public void init(String userName, String latestVersion, String downloadTicket, String sessionId) {
        try {
            bgImage = ImageIO.read((net.minecraft.LoginForm.class).getResource("dirt.png")).getScaledInstance(32, 32, 16);
        } catch(IOException e) {
            e.printStackTrace();
        }

        customParameters.put("username", ((Object) (userName)));
        customParameters.put("sessionid", ((Object) (sessionId)));
        gameUpdater = new GameUpdater(latestVersion, (new StringBuilder("zombiecraft.jar?user=")).append(userName).append("&ticket=").append(downloadTicket).toString());
    }

    public boolean canPlayOffline() {
        return gameUpdater.canPlayOffline();
    }

    public void init() {
        if(applet != null) {
            applet.init();
            return;
        } else {
            init(getParameter("userName"), getParameter("latestVersion"), getParameter("downloadTicket"), getParameter("sessionId"));
            return;
        }
    }

    public void start() {
        if(applet != null) {
            applet.start();
            return;
        }

        if(gameUpdaterStarted) {
            return;
        } else {
            Thread t = ((Thread) (new Thread() {
                public void run() {
                    gameUpdater.run();

                    try {
                        if(!gameUpdater.fatalError)
                            replace(gameUpdater.createApplet());
                    } catch(ClassNotFoundException e) {
                        e.printStackTrace();
                    } catch(InstantiationException e) {
                        e.printStackTrace();
                    } catch(IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
                                 ));
            t.setDaemon(true);
            t.start();
            t = ((Thread) (new Thread() {
                public void run() {
                    while(applet == null)  {
                        repaint();

                        try {
                            Thread.sleep(10L);
                        } catch(InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
                          ));
            t.setDaemon(true);
            t.start();
            gameUpdaterStarted = true;
            return;
        }
    }

    public void stop() {
        if(applet != null) {
            active = false;
            applet.stop();
            return;
        } else {
            return;
        }
    }

    public void destroy() {
        if(applet != null) {
            applet.destroy();
            return;
        } else {
            return;
        }
    }

    public void replace(Applet applet) {
        this.applet = applet;
        applet.setStub(((AppletStub) (this)));
        applet.setSize(getWidth(), getHeight());
        setLayout(((java.awt.LayoutManager) (new BorderLayout())));
        add(((java.awt.Component) (applet)), "Center");
        applet.init();
        active = true;
        applet.start();
        validate();
    }

    public void update(Graphics g) {
        paint(g);
    }

    public void paint(Graphics g2) {
        if(applet != null)
            return;

        int w = getWidth() / 2;
        int h = getHeight() / 2;

        if(img == null || img.getWidth() != w || img.getHeight() != h)
            img = createVolatileImage(w, h);

        Graphics g = img.getGraphics();

        for(int x = 0; x <= w / 32; x++) {
            for(int y = 0; y <= h / 32; y++)
                g.drawImage(bgImage, x * 32, y * 32, ((java.awt.image.ImageObserver) (null)));
        }

        if(gameUpdater.pauseAskUpdate) {
            if(!hasMouseListener) {
                System.out.println("Adding mouse listener yay");
                hasMouseListener = true;
                addMouseListener(((MouseListener) (this)));
            }

            g.setColor(Color.LIGHT_GRAY);
            String msg = "New update available";
            g.setFont(new Font(((String) (null)), 1, 20));
            FontMetrics fm = g.getFontMetrics();
            g.drawString(msg, w / 2 - fm.stringWidth(msg) / 2, h / 2 - fm.getHeight() * 2);
            g.setFont(new Font(((String) (null)), 0, 12));
            fm = g.getFontMetrics();
            g.fill3DRect(w / 2 - 56 - 8, h / 2, 56, 20, true);
            g.fill3DRect(w / 2 + 8, h / 2, 56, 20, true);
            msg = "Would you like to update?";
            g.drawString(msg, w / 2 - fm.stringWidth(msg) / 2, h / 2 - 8);
            g.setColor(Color.BLACK);
            msg = "Yes";
            g.drawString(msg, (w / 2 - 56 - 8 - fm.stringWidth(msg) / 2) + 28, h / 2 + 14);
            msg = "Not now";
            g.drawString(msg, ((w / 2 + 8) - fm.stringWidth(msg) / 2) + 28, h / 2 + 14);
        } else {
            g.setColor(Color.LIGHT_GRAY);
            String msg = "Updating Minecraft";

            if(gameUpdater.fatalError)
                msg = "Failed to launch";

            g.setFont(new Font(((String) (null)), 1, 20));
            FontMetrics fm = g.getFontMetrics();
            g.drawString(msg, w / 2 - fm.stringWidth(msg) / 2, h / 2 - fm.getHeight() * 2);
            g.setFont(new Font(((String) (null)), 0, 12));
            fm = g.getFontMetrics();
            msg = gameUpdater.getDescriptionForState();

            if(gameUpdater.fatalError)
                msg = gameUpdater.fatalErrorDescription;

            g.drawString(msg, w / 2 - fm.stringWidth(msg) / 2, h / 2 + fm.getHeight() * 1);
            msg = gameUpdater.subtaskMessage;
            g.drawString(msg, w / 2 - fm.stringWidth(msg) / 2, h / 2 + fm.getHeight() * 2);

            if(!gameUpdater.fatalError) {
                g.setColor(Color.black);
                g.fillRect(64, h - 64, (w - 128) + 1, 5);
                g.setColor(new Color(32768));
                g.fillRect(64, h - 64, (gameUpdater.percentage * (w - 128)) / 100, 4);
                g.setColor(new Color(0x20a020));
                g.fillRect(65, (h - 64) + 1, (gameUpdater.percentage * (w - 128)) / 100 - 2, 1);
            }
        }

        g.dispose();
        g2.drawImage(((Image) (img)), 0, 0, w * 2, h * 2, ((java.awt.image.ImageObserver) (null)));
    }

    public void run() {
    }

    public String getParameter(String name) {
        String custom = (String)customParameters.get(((Object) (name)));

        if(custom != null)
            return custom;

        try {
            return super.getParameter(name);
        } catch(Exception e) {
            customParameters.put(((Object) (name)), ((Object) (null)));
        }

        return null;
    }

    public void appletResize(int i, int j) {
    }

    public URL getDocumentBase() {
        try {
            return new URL("http://www.minecraft.net/game/");
        } catch(MalformedURLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void mouseClicked(MouseEvent arg0) {
        System.out.println("OMG CLICK");
    }

    public void mouseEntered(MouseEvent mouseevent) {
    }

    public void mouseExited(MouseEvent mouseevent) {
    }

    public void mousePressed(MouseEvent me) {
        System.out.println("OMG CLICK");
        int x = me.getX() / 2;
        int y = me.getY() / 2;
        int w = getWidth() / 2;
        int h = getHeight() / 2;

        if(contains(x, y, w / 2 - 56 - 8, h / 2, 56, 20)) {
            removeMouseListener(((MouseListener) (this)));
            gameUpdater.shouldUpdate = true;
            gameUpdater.pauseAskUpdate = false;
            hasMouseListener = false;
        }

        if(contains(x, y, w / 2 + 8, h / 2, 56, 20)) {
            removeMouseListener(((MouseListener) (this)));
            gameUpdater.shouldUpdate = false;
            gameUpdater.pauseAskUpdate = false;
            hasMouseListener = false;
        }
    }

    private boolean contains(int x, int y, int xx, int yy, int w, int h) {
        return x >= xx && y >= yy && x < xx + w && y < yy + h;
    }

    public void mouseReleased(MouseEvent mouseevent) {
    }


}
