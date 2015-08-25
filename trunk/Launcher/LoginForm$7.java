// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) fieldsfirst nonlb safe
// Source File Name:   LoginForm.java

package net.minecraft;

import java.net.URL;
import javax.swing.JTextPane;

// Referenced classes of package net.minecraft:
//            LoginForm

class LoginForm$7 extends Thread {

    public void run() {
        try {
            val$editorPane.setPage(new URL("http://mcupdate.tumblr.com/"));
        } catch(Exception e) {
            e.printStackTrace();
            val$editorPane.setText((new StringBuilder("<html><body><font color=\"#808080\"><br><br><br><br><br><br><br><center>Failed to update news<br>")).append(e.toString()).append("</center></font></body></html>").toString());
        }
    }

    LoginForm$7() {
    }
}
