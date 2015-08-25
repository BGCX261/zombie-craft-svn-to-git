// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) fieldsfirst nonlb safe
// Source File Name:   GameUpdater.java

package net.minecraft;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;

// Referenced classes of package net.minecraft:
//            GameUpdater

class GameUpdater$3 extends Thread {

    public void run() {
        try {
            val$is[0] = val$urlconnection.getInputStream();
        } catch(IOException ioexception) { }
    }

    GameUpdater$3() {
    }
}
