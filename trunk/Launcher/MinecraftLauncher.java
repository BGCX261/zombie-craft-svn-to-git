// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) fieldsfirst nonlb safe
// Source File Name:   MinecraftLauncher.java

package net.minecraft;

import java.net.URI;
import java.net.URL;
import java.security.CodeSource;
import java.security.ProtectionDomain;
import java.util.ArrayList;

// Referenced classes of package net.minecraft:
//            LauncherFrame

public class MinecraftLauncher {

    private static final int MIN_HEAP = 511;
    private static final int RECOMMENDED_HEAP = 1024;

    public MinecraftLauncher() {
    }

    public static void main(String args[]) throws Exception {
        float heapSizeMegs = Runtime.getRuntime().maxMemory() / 1024L / 1024L;

        if(heapSizeMegs > 511F)
            LauncherFrame.main(args);
        else
            try {
                String pathToJar = (net.minecraft.MinecraftLauncher.class).getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
                ArrayList params = new ArrayList();
                params.add("javaw");
                params.add("-Xmx1024m");
                params.add("-Dsun.java2d.noddraw=true");
                params.add("-Dsun.java2d.d3d=false");
                params.add("-Dsun.java2d.opengl=false");
                params.add("-Dsun.java2d.pmoffscreen=false");
                params.add("-classpath");
                params.add(((Object) (pathToJar)));
                params.add("net.minecraft.LauncherFrame");
                ProcessBuilder pb = new ProcessBuilder(((java.util.List) (params)));
                Process process = pb.start();

                if(process == null)
                    throw new Exception("!");

                System.exit(0);
            } catch(Exception e) {
                e.printStackTrace();
                LauncherFrame.main(args);
            }
    }
}
