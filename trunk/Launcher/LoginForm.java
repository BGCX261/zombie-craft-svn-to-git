// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) fieldsfirst nonlb safe
// Source File Name:   LoginForm.java

package net.minecraft;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.URL;
import java.util.Random;
import javax.crypto.*;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import javax.swing.*;
import javax.swing.border.MatteBorder;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import javax.swing.text.html.StyleSheet;
import javax.swing.text.html.*;

// Referenced classes of package net.minecraft:
//            TransparentPanel, TransparentCheckbox, TransparentButton, TransparentLabel,
//            Util, TexturedPanel, LogoPanel, LauncherFrame,
//            OptionsPanel

public class LoginForm extends TransparentPanel {

    private static final int PANEL_SIZE = 100;
    private static final long serialVersionUID = 1L;
    private static final Color LINK_COLOR = new Color(0x8080ff);
    private JTextField userName;
    private JPasswordField password;
    private TransparentCheckbox rememberBox;
    private TransparentButton launchButton;
    private TransparentButton optionsButton;
    private TransparentButton retryButton;
    private TransparentButton offlineButton;
    private TransparentLabel errorLabel;
    private LauncherFrame launcherFrame;
    private boolean outdated;
    private JScrollPane scrollPane;

    public LoginForm(final LauncherFrame launcherFrame) {
        userName = new JTextField(20);
        password = new JPasswordField(20);
        rememberBox = new TransparentCheckbox("Remember password");
        launchButton = new TransparentButton("Login");
        optionsButton = new TransparentButton("Options");
        retryButton = new TransparentButton("Try again");
        offlineButton = new TransparentButton("Play offline");
        errorLabel = new TransparentLabel("", 0);
        outdated = false;
        this.launcherFrame = launcherFrame;
        userName.setBackground(Color.BLACK);
        password.setBackground(Color.BLACK);
        userName.setForeground(Color.WHITE);
        password.setForeground(Color.WHITE);
        userName.setCaretColor(Color.WHITE);
        password.setCaretColor(Color.WHITE);
        BorderLayout gbl = new BorderLayout();
        setLayout(((java.awt.LayoutManager) (gbl)));
        add(((Component) (buildMainLoginPanel())), "Center");
        readUsername();
        retryButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                errorLabel.setText("");
                removeAll();
                add(((Component) (buildMainLoginPanel())), "Center");
                validate();
            }
        }
                                     );
        offlineButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                launcherFrame.playCached(userName.getText());
            }
        }
                                       );
        launchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                setLoggingIn();
                ((new Thread() {
                    public void run() {
                        try {
                            launcherFrame.login(userName.getText(), new String(password.getPassword()));
                        } catch(Exception e) {
                            setError(e.toString());
                        }
                    }
                }
                         )).start();
            }
        }
                                      );
        
        /*this.addWindowListener(((java.awt.event.WindowListener) (new WindowAdapter() {
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
                                                           )));*/
        
        optionsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                (new OptionsPanel(((java.awt.Window) (launcherFrame)))).setVisible(true);
            }
        }
                                       );
    }

    private void readUsername() {
        try {
            File lastLogin = new File(Util.getWorkingDirectory(), "lastlogin");
            Cipher cipher = getCipher(2, "passwordfile");
            DataInputStream dis;

            if(cipher != null)
                dis = new DataInputStream(((java.io.InputStream) (new CipherInputStream(((java.io.InputStream) (new FileInputStream(lastLogin))), cipher))));
            else
                dis = new DataInputStream(((java.io.InputStream) (new FileInputStream(lastLogin))));

            userName.setText(dis.readUTF());
            password.setText(dis.readUTF());
            rememberBox.setSelected(password.getPassword().length > 0);
            dis.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    private void writeUsername() {
        try {
            File lastLogin = new File(Util.getWorkingDirectory(), "lastlogin");
            Cipher cipher = getCipher(1, "passwordfile");
            DataOutputStream dos;

            if(cipher != null)
                dos = new DataOutputStream(((java.io.OutputStream) (new CipherOutputStream(((java.io.OutputStream) (new FileOutputStream(lastLogin))), cipher))));
            else
                dos = new DataOutputStream(((java.io.OutputStream) (new FileOutputStream(lastLogin))));

            dos.writeUTF(userName.getText());
            dos.writeUTF(rememberBox.isSelected() ? new String(password.getPassword()) : "");
            dos.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    private Cipher getCipher(int mode, String password) throws Exception {
        Random random = new Random(0x29482c2L);
        byte salt[] = new byte[8];
        random.nextBytes(salt);
        PBEParameterSpec pbeParamSpec = new PBEParameterSpec(salt, 5);
        javax.crypto.SecretKey pbeKey = SecretKeyFactory.getInstance("PBEWithMD5AndDES").generateSecret(((java.security.spec.KeySpec) (new PBEKeySpec(password.toCharArray()))));
        Cipher cipher = Cipher.getInstance("PBEWithMD5AndDES");
        cipher.init(mode, ((java.security.Key) (pbeKey)), ((java.security.spec.AlgorithmParameterSpec) (pbeParamSpec)));
        return cipher;
    }

    private JScrollPane getUpdateNews() {
        if(scrollPane != null)
            return scrollPane;

        try {
            final JTextPane editorPane = ((JTextPane) (new JTextPane() {
                private static final long serialVersionUID = 1L;
            }
                                                      ));
            editorPane.setText("<html><body><font color=\"#808080\"><br><br><br><br><br><br><br><center>Loading update news..</center></font></body></html>");
            editorPane.addHyperlinkListener(new HyperlinkListener() {
                public void hyperlinkUpdate(HyperlinkEvent he) {
                    if(he.getEventType() == javax.swing.event.HyperlinkEvent.EventType.ACTIVATED)
                        try {
                            Desktop.getDesktop().browse(he.getURL().toURI());
                        } catch(Exception e) {
                            e.printStackTrace();
                        }
                }
            }
                                           );
            ((new Thread() {
                public void run() {
                    try {
                    	//StyleSheet ss = new StyleSheet();
                    	//ss.importStyleSheet(new URL("http://s4.zetaboards.com/c/24897/59/css.css"));
                    	//HTMLDocument htmldoc = new HTMLDocument(ss);
                    	//htmldoc.set
                    	//HTMLEditorKit kit = (HTMLEditorKit)jEditorPane.getEditorKit();
                    	//kit.setStyleSheet(ss);

                    	//editorPane.setDocument(htmldoc);
                        editorPane.setPage(new URL("http://modzilla.zymichost.com/zc/"));
                    	
                        
                    } catch(Exception e) {
                        e.printStackTrace();
                        editorPane.setText((new StringBuilder("<html><body><font color=\"#808080\"><br><br><br><br><br><br><br><center>Failed to update news<br>")).append(e.toString()).append("</center></font></body></html>").toString());
                    }
                }
            }
                     )).start();
            editorPane.setBackground(Color.BLACK);
            editorPane.setEditable(false);
            scrollPane = new JScrollPane(((Component) (editorPane)));
            scrollPane.setBorder(((javax.swing.border.Border) (null)));
            editorPane.setMargin(((java.awt.Insets) (null)));
            scrollPane.setBorder(((javax.swing.border.Border) (new MatteBorder(0, 0, 2, 0, Color.BLACK))));
        } catch(Exception e2) {
            e2.printStackTrace();
        }

        return scrollPane;
    }

    private JPanel buildMainLoginPanel() {
        JPanel p = ((JPanel) (new TransparentPanel(((java.awt.LayoutManager) (new BorderLayout())))));
        p.add(((Component) (getUpdateNews())), "Center");
        JPanel southPanel = ((JPanel) (new TexturedPanel()));
        southPanel.setLayout(((java.awt.LayoutManager) (new BorderLayout())));
        southPanel.add(((Component) (new LogoPanel())), "West");
        southPanel.add(((Component) (new TransparentPanel())), "Center");
        southPanel.add(center(((Component) (buildLoginPanel()))), "East");
        southPanel.setPreferredSize(new Dimension(100, 100));
        p.add(((Component) (southPanel)), "South");
        return p;
    }

    private JPanel buildLoginPanel() {
        TransparentPanel panel = new TransparentPanel();
        panel.setInsets(4, 0, 4, 0);
        BorderLayout layout = new BorderLayout();
        layout.setHgap(0);
        layout.setVgap(8);
        panel.setLayout(((java.awt.LayoutManager) (layout)));
        GridLayout gl1 = new GridLayout(0, 1);
        gl1.setVgap(2);
        GridLayout gl2 = new GridLayout(0, 1);
        gl2.setVgap(2);
        GridLayout gl3 = new GridLayout(0, 1);
        gl3.setVgap(2);
        TransparentPanel titles = new TransparentPanel(((java.awt.LayoutManager) (gl1)));
        TransparentPanel values = new TransparentPanel(((java.awt.LayoutManager) (gl2)));
        titles.add(((Component) (new TransparentLabel("Username:", 4))));
        titles.add(((Component) (new TransparentLabel("Password:", 4))));
        titles.add(((Component) (new TransparentLabel("", 4))));
        values.add(((Component) (userName)));
        values.add(((Component) (password)));
        values.add(((Component) (rememberBox)));
        panel.add(((Component) (titles)), "West");
        panel.add(((Component) (values)), "Center");
        TransparentPanel loginPanel = new TransparentPanel(((java.awt.LayoutManager) (new BorderLayout())));
        TransparentPanel third = new TransparentPanel(((java.awt.LayoutManager) (gl3)));
        titles.setInsets(0, 0, 0, 4);
        third.setInsets(0, 10, 0, 10);

        try {
            if(outdated) {
                TransparentLabel accountLink = getUpdateLink();
                third.add(((Component) (accountLink)));
            } else {
                TransparentLabel accountLink = ((TransparentLabel) (new TransparentLabel("Need account?") {
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
                }
                                                                   ));
                accountLink.setCursor(Cursor.getPredefinedCursor(12));
                accountLink.addMouseListener(((java.awt.event.MouseListener) (new MouseAdapter() {
                    public void mousePressed(MouseEvent arg0) {
                        try {
                            Desktop.getDesktop().browse((new URL("http://www.minecraft.net/register.jsp")).toURI());
                        } catch(Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
                                                                             )));
                accountLink.setForeground(LINK_COLOR);
                third.add(((Component) (accountLink)));
            }
        } catch(Error error) { }

        third.add(((Component) (launchButton)));
        third.add(((Component) (optionsButton)));
        loginPanel.add(((Component) (third)), "Center");
        panel.add(((Component) (loginPanel)), "East");
        errorLabel.setFont(new Font(((String) (null)), 2, 16));
        errorLabel.setForeground(new Color(0xff4040));
        errorLabel.setText("");
        panel.add(((Component) (errorLabel)), "North");
        return ((JPanel) (panel));
    }

    private TransparentLabel getUpdateLink() {
        TransparentLabel accountLink = ((TransparentLabel) (new TransparentLabel("You need to update the launcher!") {
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
        }
                                                           ));
        accountLink.setCursor(Cursor.getPredefinedCursor(12));
        accountLink.addMouseListener(((java.awt.event.MouseListener) (new MouseAdapter() {
            public void mousePressed(MouseEvent arg0) {
                try {
                    Desktop.getDesktop().browse((new URL("http://www.minecraft.net/download.jsp")).toURI());
                } catch(Exception e) {
                    e.printStackTrace();
                }
            }
        }
                                                                     )));
        accountLink.setForeground(LINK_COLOR);
        return accountLink;
    }

    private JPanel buildMainOfflinePanel() {
        JPanel p = ((JPanel) (new TransparentPanel(((java.awt.LayoutManager) (new BorderLayout())))));
        p.add(((Component) (getUpdateNews())), "Center");
        JPanel southPanel = ((JPanel) (new TexturedPanel()));
        southPanel.setLayout(((java.awt.LayoutManager) (new BorderLayout())));
        southPanel.add(((Component) (new LogoPanel())), "West");
        southPanel.add(((Component) (new TransparentPanel())), "Center");
        southPanel.add(center(((Component) (buildOfflinePanel()))), "East");
        southPanel.setPreferredSize(new Dimension(100, 100));
        p.add(((Component) (southPanel)), "South");
        return p;
    }

    private Component center(Component c) {
        TransparentPanel tp = new TransparentPanel(((java.awt.LayoutManager) (new GridBagLayout())));
        tp.add(c);
        return ((Component) (tp));
    }

    private TransparentPanel buildOfflinePanel() {
        TransparentPanel panel = new TransparentPanel();
        panel.setInsets(0, 0, 0, 20);
        BorderLayout layout = new BorderLayout();
        panel.setLayout(((java.awt.LayoutManager) (layout)));
        TransparentPanel loginPanel = new TransparentPanel(((java.awt.LayoutManager) (new BorderLayout())));
        GridLayout gl = new GridLayout(0, 1);
        gl.setVgap(2);
        TransparentPanel pp = new TransparentPanel(((java.awt.LayoutManager) (gl)));
        pp.setInsets(0, 8, 0, 0);
        pp.add(((Component) (retryButton)));
        pp.add(((Component) (offlineButton)));
        loginPanel.add(((Component) (pp)), "East");
        boolean canPlayOffline = launcherFrame.canPlayOffline(userName.getText());
        offlineButton.setEnabled(canPlayOffline);

        if(!canPlayOffline)
            loginPanel.add(((Component) (new TransparentLabel("(Not downloaded)", 4))), "South");

        panel.add(((Component) (loginPanel)), "Center");
        TransparentPanel p2 = new TransparentPanel(((java.awt.LayoutManager) (new GridLayout(0, 1))));
        errorLabel.setFont(new Font(((String) (null)), 2, 16));
        errorLabel.setForeground(new Color(0xff4040));
        p2.add(((Component) (errorLabel)));

        if(outdated) {
            TransparentLabel accountLink = getUpdateLink();
            p2.add(((Component) (accountLink)));
        }

        loginPanel.add(((Component) (p2)), "Center");
        return panel;
    }

    public void setError(String errorMessage) {
        removeAll();
        add(((Component) (buildMainLoginPanel())), "Center");
        errorLabel.setText(errorMessage);
        validate();
    }

    public void loginOk() {
        writeUsername();
    }

    public void setLoggingIn() {
        removeAll();
        JPanel panel = new JPanel(((java.awt.LayoutManager) (new BorderLayout())));
        panel.add(((Component) (getUpdateNews())), "Center");
        JPanel southPanel = ((JPanel) (new TexturedPanel()));
        southPanel.setLayout(((java.awt.LayoutManager) (new BorderLayout())));
        southPanel.add(((Component) (new LogoPanel())), "West");
        southPanel.add(((Component) (new TransparentPanel())), "Center");
        JLabel label = ((JLabel) (new TransparentLabel("Logging in...                      ", 0)));
        label.setFont(new Font(((String) (null)), 1, 16));
        southPanel.add(center(((Component) (label))), "East");
        southPanel.setPreferredSize(new Dimension(100, 100));
        panel.add(((Component) (southPanel)), "South");
        add(((Component) (panel)), "Center");
        validate();
    }

    public void setNoNetwork() {
        removeAll();
        add(((Component) (buildMainOfflinePanel())), "Center");
        validate();
    }

    public void checkAutologin() {
        if(password.getPassword().length > 0)
            launcherFrame.login(userName.getText(), new String(password.getPassword()));
    }

    public void setOutdated() {
        outdated = true;
    }





}
