// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) fieldsfirst nonlb safe
// Source File Name:   LauncherFrame.java

package net.minecraft;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.PrintStream;
import java.net.URLEncoder;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.UIManager;

// Referenced classes of package net.minecraft:
//            LoginForm, Launcher, Util

public class LauncherFrame extends Frame {

    public static final int VERSION = 12;
    private static final long serialVersionUID = 1L;
    private Launcher launcher;
    private LoginForm loginForm;

    public LauncherFrame() {
        super("ZombieCraft Launcher");
        setBackground(Color.BLACK);
        loginForm = new LoginForm(this);
        JPanel p = new JPanel();
        p.setLayout(((java.awt.LayoutManager) (new BorderLayout())));
        p.add(((java.awt.Component) (loginForm)), "Center");
        p.setPreferredSize(new Dimension(854, 480));
        setLayout(((java.awt.LayoutManager) (new BorderLayout())));
        add(((java.awt.Component) (p)), "Center");
        pack();
        setLocationRelativeTo(((java.awt.Component) (null)));

        try {
            setIconImage(((java.awt.Image) (ImageIO.read((net.minecraft.LauncherFrame.class).getResource("favicon.png")))));
        } catch(IOException e1) {
            e1.printStackTrace();
        }

        this.addWindowListener(((java.awt.event.WindowListener) (new WindowAdapter() {
            public void windowClosing(WindowEvent arg0) {
                ((new Thread() {
                    public void run() {
                        try {
                            Thread.sleep(30000L);
                        } catch(InterruptedException e) {
                            e.printStackTrace();
                        }

                        System.out.println("FORCING EXIT!");
                        System.exit(0);
                    }
                }
                         )).start();

                if(launcher != null) {
                    launcher.stop();
                    launcher.destroy();
                }

                System.exit(0);
            }
        }
                                                           )));
    }

    public void playCached(String userName) {
        try {
            if(userName == null || userName.length() <= 0)
                userName = "Player";

            launcher = new Launcher();
            launcher.customParameters.put("userName", ( (userName)));
            launcher.init();
            removeAll();
            add(((java.awt.Component) (launcher)), "Center");
            validate();
            launcher.start();
            loginForm = null;
            setTitle("Minecraft");
        } catch(Exception e) {
            e.printStackTrace();
            showError(e.toString());
        }
    }

    public void login(String userName, String password) {
        try {
            String parameters = (new StringBuilder("user=")).append(URLEncoder.encode(userName, "UTF-8")).append("&password=").append(URLEncoder.encode(password, "UTF-8")).append("&version=").append(12).toString();
            String result = Util.excutePost("https://login.minecraft.net/", parameters);

            if(result == null) {
                showError("Can't connect to minecraft.net");
                loginForm.setNoNetwork();
                return;
            }

            if(!result.contains(":")) {
                if(result.trim().equals("Bad login"))
                    showError("Login failed");
                else if(result.trim().equals("Old version")) {
                    loginForm.setOutdated();
                    showError("Outdated launcher");
                } else {
                    showError(result);
                }

                loginForm.setNoNetwork();
                return;
            }

            String values[] = result.split(":");
            launcher = new Launcher();
            launcher.customParameters.put("userName", ( (values[2].trim())));
            launcher.customParameters.put("latestVersion", ( (values[0].trim())));
            launcher.customParameters.put("downloadTicket", ( (values[1].trim())));
            launcher.customParameters.put("sessionId", (( (values[3].trim()))));
            launcher.init();
            removeAll();
            add(((java.awt.Component) (launcher)), "Center");
            validate();
            launcher.start();
            loginForm.loginOk();
            loginForm = null;
            setTitle("Minecraft");
        } catch(Exception e) {
            e.printStackTrace();
            showError(e.toString());
            loginForm.setNoNetwork();
        }
    }

    private void showError(String error) {
        removeAll();
        add(((java.awt.Component) (loginForm)));
        loginForm.setError(error);
        validate();
    }

    public boolean canPlayOffline(String userName) {
        Launcher launcher = new Launcher();
        launcher.init(userName, ((String) (null)), ((String) (null)), ((String) (null)));
        return launcher.canPlayOffline();
    }

    public static void main(String args[]) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch(Exception exception) { }

        LauncherFrame launcherFrame = new LauncherFrame();
        launcherFrame.setVisible(true);
    }

}
