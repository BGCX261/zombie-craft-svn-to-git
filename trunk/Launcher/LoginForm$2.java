// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) fieldsfirst nonlb safe
// Source File Name:   LoginForm.java

package net.minecraft;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextField;

// Referenced classes of package net.minecraft:
//            LoginForm, LauncherFrame

class LoginForm$2
    implements ActionListener {

    public void actionPerformed(ActionEvent ae) {
        val$launcherFrame.playCached(LoginForm.access$2(LoginForm.this).getText());
    }

    LoginForm$2() {
    }
}
