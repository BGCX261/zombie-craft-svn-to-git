// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) fieldsfirst nonlb safe
// Source File Name:   LoginForm.java

package net.minecraft;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

// Referenced classes of package net.minecraft:
//            LoginForm, LauncherFrame

class LoginForm$3$1 extends Thread {

    public void run() {
        try {
            val$launcherFrame.login(LoginForm.access$2(this$0).getText(), new String(LoginForm.access$3(this$0).getPassword()));
        } catch(Exception e) {
            setError(e.toString());
        }
    }

    LoginForm$3$1() {
    }

    // Unreferenced inner class net/minecraft/LoginForm$3

    /* anonymous class */
    class LoginForm._cls3
        implements ActionListener {

        final LoginForm this$0;
        private final LauncherFrame val$launcherFrame;

        public void actionPerformed(ActionEvent ae) {
            setLoggingIn();
            (((LoginForm._cls3._cls1) (launcherFrame)). new LoginForm._cls3._cls1()).start();
        }


        {
            this$0 = final_loginform;
            launcherFrame = LauncherFrame.this;
            super();
        }
    }

}
