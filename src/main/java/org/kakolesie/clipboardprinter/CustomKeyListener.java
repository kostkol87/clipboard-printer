package org.kakolesie.clipboardprinter;

import lombok.RequiredArgsConstructor;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

@RequiredArgsConstructor
public class CustomKeyListener implements NativeKeyListener {

    private final Robot robot;
    private final AtomicInteger hotKeyFlag = new AtomicInteger();
    private final ClipboardReader clipboardReader = new ClipboardReader();

    public static void initShortcutListener() {
        Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
        logger.setLevel(Level.OFF);

        try {
            GlobalScreen.registerNativeHook();
            GlobalScreen.addNativeKeyListener(new CustomKeyListener(new Robot()));
        } catch (NativeHookException | AWTException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void nativeKeyTyped(NativeKeyEvent e) {
    }

    @Override
    public void nativeKeyReleased(NativeKeyEvent e) {
        hotKeyFlag.set(0x00);
    }

    @Override
    public void nativeKeyPressed(NativeKeyEvent e) {

        if (hotKeyFlag.get() == e.getKeyCode()) {
            return;
        }

        if (e.getKeyCode() == NativeKeyEvent.VC_CONTROL || e.getKeyCode() == NativeKeyEvent.VC_I) {
            if (hotKeyFlag.get() == 0x00) {
                hotKeyFlag.set(e.getKeyCode());
            }
            if (hotKeyFlag.get() != e.getKeyCode()) {
                String clipBoardString = clipboardReader.getClipBoardString();
                if (clipBoardString != null) {
                    robot.keyRelease(KeyEvent.VK_CONTROL);
                    robot.keyRelease(KeyEvent.VK_I);
                    KeyTool.sendKeys(clipBoardString, robot);
                }
            }
        }
    }
}
