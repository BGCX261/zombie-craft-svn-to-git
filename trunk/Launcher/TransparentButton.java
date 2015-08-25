// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) fieldsfirst nonlb safe
// Source File Name:   TransparentButton.java

package net.minecraft;

import javax.swing.JButton;

public class TransparentButton extends JButton {

    private static final long serialVersionUID = 1L;

    public TransparentButton(String string) {
        super(string);
    }

    public boolean isOpaque() {
        return false;
    }
}
