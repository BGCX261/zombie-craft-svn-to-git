// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) fieldsfirst nonlb safe
// Source File Name:   LoginForm.java

package net.minecraft;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Referenced classes of package net.minecraft:
//            LoginForm, TransparentLabel

class LoginForm$1
    implements ActionListener {

    public void actionPerformed(ActionEvent ae) {
        LoginForm.access$0(LoginForm.this).setText("");
        removeAll();
        add(((java.awt.Component) (LoginForm.access$1(LoginForm.this))), "Center");
        validate();
    }

    LoginForm$1() {
    }
}
