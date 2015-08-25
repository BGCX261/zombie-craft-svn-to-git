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

class LoginForm$3
    implements ActionListener {

    public void actionPerformed(ActionEvent ae) {
        setLoggingIn();
        ((_cls1) (new Thread() {
            public void run() {
                try {
                    launcherFrame.login(LoginForm.access$2(this$0).getText(), new String(LoginForm.access$3(this$0).getPassword()));
                } catch(Exception e) {
                    setError(e.toString());
                }
            }
        }
                 )).start();
    }


    LoginForm$3() {
    }
}
