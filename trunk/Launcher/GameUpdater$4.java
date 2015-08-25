// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) fieldsfirst nonlb safe
// Source File Name:   GameUpdater.java

package net.minecraft;

import java.io.File;
import java.security.PrivilegedExceptionAction;

// Referenced classes of package net.minecraft:
//            Util, GameUpdater

class GameUpdater$4
    implements PrivilegedExceptionAction {

    public Object run() throws Exception {
        return ((Object) ((new StringBuilder()).append(((Object) (Util.getWorkingDirectory()))).append(File.separator).append("bin").append(File.separator).toString()));
    }

    GameUpdater$4() {
    }
}
