// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) fieldsfirst nonlb safe
// Source File Name:   LauncherFrame.java

package net.minecraft;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.PrintStream;

// Referenced classes of package net.minecraft:
//            LauncherFrame, Launcher

class LauncherFrame$1 extends WindowAdapter {

    public void windowClosing(WindowEvent arg0) {
        ((_cls1) (new Thread() {
            public void run() {
                try {
                    Thread.sleep(30000L);
                } catch(InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("FORCING EXIT!");
                System.exit(0);
            }
        }
                 )).start();

        if(LauncherFrame.access$0(LauncherFrame.this) != null) {
            LauncherFrame.access$0(LauncherFrame.this).stop();
            LauncherFrame.access$0(LauncherFrame.this).destroy();
        }

        System.exit(0);
    }

    LauncherFrame$1() {
    }
}
