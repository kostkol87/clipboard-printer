package org.kakolesie.clipboardprinter;

import lombok.experimental.UtilityClass;

import java.awt.*;
import java.awt.event.KeyEvent;

@UtilityClass
public class KeyTool {

    public static void sendKeys(String str, Robot robot) {
        if (str == null) {
            return;
        }
        char[] chars = str.toCharArray();

        for (char aChar : chars) {
            sendKey(aChar, robot);
        }
    }

    private static void sendKey(char aChar, Robot robot) {
        try {
            if (Symbol.SYMBOLS.indexOf(aChar) >= 0) {
                printSymbol(aChar, robot);
                return;
            }
            printLetter(aChar, robot);
        } catch (Exception e) {
            robot.keyRelease(KeyEvent.VK_SHIFT);
            robot.keyRelease(KeyEvent.VK_CONTROL);
        }
    }

    private static void printSymbol(char aChar, Robot robot) {
        Symbol symbol = Symbol.findSymbol(aChar);
        if (symbol.getModKey() != -1) {
            robot.keyPress(symbol.getModKey());
        }
        robot.keyPress(symbol.getKey());
        if (symbol.getModKey() != -1) {
            robot.keyRelease(symbol.getModKey());
        }
        robot.keyRelease(symbol.getKey());
    }

    private static void printLetter(char aChar, Robot robot) {
        if (Character.isUpperCase(aChar)) {
            robot.keyPress(KeyEvent.VK_SHIFT);
        }
        int keyCode = KeyEvent.getExtendedKeyCodeForChar(aChar);
        robot.keyPress(keyCode);
        robot.keyRelease(keyCode);
        if (Character.isUpperCase(aChar)) {
            robot.keyRelease(KeyEvent.VK_SHIFT);
        }
    }
}


