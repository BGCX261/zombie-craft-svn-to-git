// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) fieldsfirst nonlb safe
// Source File Name:   OptionsPanel.java

package net.minecraft;

import java.awt.Desktop;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.net.URL;

// Referenced classes of package net.minecraft:
//            Util, OptionsPanel

class OptionsPanel$3 extends MouseAdapter {

    public void mousePressed(MouseEvent arg0) {
        try {
            Desktop.getDesktop().browse((new URL((new StringBuilder("file://")).append(Util.getWorkingDirectory().getAbsolutePath()).toString())).toURI());
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    OptionsPanel$3() {
    }
}
