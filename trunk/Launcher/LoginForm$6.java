// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) fieldsfirst nonlb safe
// Source File Name:   LoginForm.java

package net.minecraft;

import java.awt.Desktop;
import java.net.URL;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;

// Referenced classes of package net.minecraft:
//            LoginForm

class LoginForm$6
    implements HyperlinkListener {

    public void hyperlinkUpdate(HyperlinkEvent he) {
        if(he.getEventType() == javax.swing.event.nt.EventType.ACTIVATED)
            try {
                Desktop.getDesktop().browse(he.getURL().toURI());
            } catch(Exception e) {
                e.printStackTrace();
            }
    }

    LoginForm$6() {
    }
}
