// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) fieldsfirst nonlb safe
// Source File Name:   Util.java

package net.minecraft;


// Referenced classes of package net.minecraft:
//            Util

private static final class Util$OS extends Enum {

    public static final Util$OS linux;
    public static final Util$OS solaris;
    public static final Util$OS windows;
    public static final Util$OS macos;
    public static final Util$OS unknown;
    private static final Util$OS ENUM$VALUES[];

    public static Util$OS[] values() {
        Util$OS autil$os[];
        int i;
        Util$OS autil$os1[];
        System.arraycopy(((Object) (autil$os = ENUM$VALUES)), 0, ((Object) (autil$os1 = new Util$OS[i = autil$os.length])), 0, i);
        return autil$os1;
    }

    public static Util$OS valueOf(String s) {
        return (Util$OS)Enum.valueOf(net.minecraft.Util$OS.class, s);
    }

    static  {
        linux = new Util$OS("linux", 0);
        solaris = new Util$OS("solaris", 1);
        windows = new Util$OS("windows", 2);
        macos = new Util$OS("macos", 3);
        unknown = new Util$OS("unknown", 4);
        ENUM$VALUES = (new Util$OS[] {
                           linux, solaris, windows, macos, unknown
                       });
    }

    private Util$OS(String s, int i) {
        super(s, i);
    }
}
