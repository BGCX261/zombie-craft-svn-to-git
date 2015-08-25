// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) fieldsfirst nonlb safe
// Source File Name:   TransparentCheckbox.java

package net.minecraft;

import java.awt.Color;
import javax.swing.JCheckBox;

public class TransparentCheckbox extends JCheckBox {

    private static final long serialVersionUID = 1L;

    public TransparentCheckbox(String string) {
        super(string);
        setForeground(Color.WHITE);
    }

    public boolean isOpaque() {
        return false;
    }
}
