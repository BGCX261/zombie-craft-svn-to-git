// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) fieldsfirst nonlb safe
// Source File Name:   LoginForm.java

package net.minecraft;

import java.awt.Desktop;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;

// Referenced classes of package net.minecraft:
//            LoginForm

class LoginForm$9 extends MouseAdapter {

    public void mousePressed(MouseEvent arg0) {
        try {
            Desktop.getDesktop().browse((new URL("http://www.minecraft.net/register.jsp")).toURI());
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    LoginForm$9() {
    }
}
