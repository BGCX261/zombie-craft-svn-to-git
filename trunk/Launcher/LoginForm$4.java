// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) fieldsfirst nonlb safe
// Source File Name:   LoginForm.java

package net.minecraft;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Referenced classes of package net.minecraft:
//            OptionsPanel, LoginForm, LauncherFrame

class LoginForm$4
    implements ActionListener {

    public void actionPerformed(ActionEvent ae) {
        (new OptionsPanel(((java.awt.Window) (val$launcherFrame)))).setVisible(true);
    }

    LoginForm$4() {
    }
}
