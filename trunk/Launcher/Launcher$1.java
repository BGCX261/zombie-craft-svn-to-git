// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) fieldsfirst nonlb safe
// Source File Name:   Launcher.java

package net.minecraft;


// Referenced classes of package net.minecraft:
//            Launcher, GameUpdater

class Launcher$1 extends Thread {

    public void run() {
        Launcher.access$0(Launcher.this).run();

        try {
            if(!Launcher.access$0(Launcher.this).fatalError)
                replace(Launcher.access$0(Launcher.this).createApplet());
        } catch(ClassNotFoundException e) {
            e.printStackTrace();
        } catch(InstantiationException e) {
            e.printStackTrace();
        } catch(IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    Launcher$1() {
    }
}
