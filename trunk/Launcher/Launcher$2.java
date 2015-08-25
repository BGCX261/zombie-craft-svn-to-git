// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) fieldsfirst nonlb safe
// Source File Name:   Launcher.java

package net.minecraft;


// Referenced classes of package net.minecraft:
//            Launcher

class Launcher$2 extends Thread {

    public void run() {
        while(Launcher.access$1(Launcher.this) == null)  {
            repaint();

            try {
                Thread.sleep(10L);
            } catch(InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    Launcher$2() {
    }
}
