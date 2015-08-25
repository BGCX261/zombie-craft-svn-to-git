// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) fieldsfirst nonlb safe
// Source File Name:   OptionsPanel.java

package net.minecraft;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.net.URL;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

// Referenced classes of package net.minecraft:
//            Util, TransparentLabel, GameUpdater

public class OptionsPanel extends JDialog {

    private static final long serialVersionUID = 1L;

    public OptionsPanel(Window parent) {
        super(parent);
        setModal(true);
        JPanel panel = new JPanel(((java.awt.LayoutManager) (new BorderLayout())));
        JLabel label = new JLabel("Launcher options", 0);
        label.setBorder(((javax.swing.border.Border) (new EmptyBorder(0, 0, 16, 0))));
        label.setFont(new Font("Default", 1, 16));
        panel.add(((java.awt.Component) (label)), "North");
        JPanel optionsPanel = new JPanel(((java.awt.LayoutManager) (new BorderLayout())));
        JPanel labelPanel = new JPanel(((java.awt.LayoutManager) (new GridLayout(0, 1))));
        JPanel fieldPanel = new JPanel(((java.awt.LayoutManager) (new GridLayout(0, 1))));
        optionsPanel.add(((java.awt.Component) (labelPanel)), "West");
        optionsPanel.add(((java.awt.Component) (fieldPanel)), "Center");
        final JButton forceButton = new JButton("Force update!");
        forceButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                GameUpdater.forceUpdate = true;
                forceButton.setText("Will force!");
                forceButton.setEnabled(false);
            }
        }
                                     );
        labelPanel.add(((java.awt.Component) (new JLabel("Force game update: ", 4))));
        fieldPanel.add(((java.awt.Component) (forceButton)));
        labelPanel.add(((java.awt.Component) (new JLabel("Game location on disk: ", 4))));
        TransparentLabel dirLink = ((TransparentLabel) (new TransparentLabel(Util.getWorkingDirectory().toString()) {
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
        dirLink.setCursor(Cursor.getPredefinedCursor(12));
        dirLink.addMouseListener(((java.awt.event.MouseListener) (new MouseAdapter() {
            public void mousePressed(MouseEvent arg0) {
                try {
                    Desktop.getDesktop().browse((new URL((new StringBuilder("file://")).append(Util.getWorkingDirectory().getAbsolutePath()).toString())).toURI());
                } catch(Exception e) {
                    e.printStackTrace();
                }
            }
        }
                                                                 )));
        dirLink.setForeground(new Color(0x2020ff));
        fieldPanel.add(((java.awt.Component) (dirLink)));
        panel.add(((java.awt.Component) (optionsPanel)), "Center");
        JPanel buttonsPanel = new JPanel(((java.awt.LayoutManager) (new BorderLayout())));
        buttonsPanel.add(((java.awt.Component) (new JPanel())), "Center");
        JButton doneButton = new JButton("Done");
        doneButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                setVisible(false);
            }
        }
                                    );
        buttonsPanel.add(((java.awt.Component) (doneButton)), "East");
        buttonsPanel.setBorder(((javax.swing.border.Border) (new EmptyBorder(16, 0, 0, 0))));
        panel.add(((java.awt.Component) (buttonsPanel)), "South");
        add(((java.awt.Component) (panel)));
        panel.setBorder(((javax.swing.border.Border) (new EmptyBorder(16, 24, 24, 24))));
        pack();
        setLocationRelativeTo(((java.awt.Component) (parent)));
    }
}
