// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) fieldsfirst nonlb safe
// Source File Name:   GameUpdater.java

package net.minecraft;

import java.io.FilePermission;
import java.lang.reflect.Method;
import java.net.*;
import java.security.*;

// Referenced classes of package net.minecraft:
//            GameUpdater

class GameUpdater$2 extends URLClassLoader {

    protected PermissionCollection getPermissions(CodeSource codesource) {
        PermissionCollection perms = null;

        try {
            Method method = (java.security.SecureClassLoader.class).getDeclaredMethod("getPermissions", new Class[] {
                                java.security.CodeSource.class
                            });
            method.setAccessible(true);
            perms = (PermissionCollection)method.invoke(((Object) (((Object)this).getClass().getClassLoader())), new Object[] {
                        codesource
                    });
            String host = "www.minecraft.net";

            if(host != null && host.length() > 0)
                perms.add(((java.security.Permission) (new SocketPermission(host, "connect,accept"))));
            else
                codesource.getLocation().getProtocol().equals("file");

            perms.add(((java.security.Permission) (new FilePermission("<<ALL FILES>>", "read"))));
        } catch(Exception e) {
            e.printStackTrace();
        }

        return perms;
    }

    GameUpdater$2(URL $anonymous0[]) {
        super($anonymous0);
    }
}
