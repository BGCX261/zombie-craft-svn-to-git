// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) fieldsfirst nonlb safe
// Source File Name:   OptionsPanel.java

package net.minecraft;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

// Referenced classes of package net.minecraft:
//            GameUpdater, OptionsPanel

class OptionsPanel$1
    implements ActionListener {

    public void actionPerformed(ActionEvent ae) {
        GameUpdater.forceUpdate = true;
        val$forceButton.setText("Will force!");
        val$forceButton.setEnabled(false);
    }

    OptionsPanel$1() {
    }
}
