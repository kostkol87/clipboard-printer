package org.kakolesie.clipboardprinter;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.awt.event.KeyEvent;
import java.util.EnumSet;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Getter
@RequiredArgsConstructor
public enum Symbol {

    SQUARE_BRACKET_LEFT(91, -1, KeyEvent.VK_OPEN_BRACKET),//[
    SQUARE_BRACKET_RIGHT(93, -1, KeyEvent.VK_CLOSE_BRACKET),//]
    BRACKET_LEFT(40, KeyEvent.VK_SHIFT, KeyEvent.VK_9),//(
    BRACKET_RIGHT(41, KeyEvent.VK_SHIFT, KeyEvent.VK_0),//)
    FIGURE_BRACKET_LEFT(123, KeyEvent.VK_SHIFT, KeyEvent.VK_OPEN_BRACKET),//{
    FIGURE_BRACKET_RIGHT(125, KeyEvent.VK_SHIFT, KeyEvent.VK_CLOSE_BRACKET),//}
    DOLLAR(36, KeyEvent.VK_SHIFT, KeyEvent.VK_4),//$
    AMP(38, KeyEvent.VK_SHIFT, KeyEvent.VK_7),//&
    PLUS(43, KeyEvent.VK_SHIFT, KeyEvent.VK_EQUALS),//+
    COMMA(44, -1, KeyEvent.VK_COMMA),//,
    COLON(58, KeyEvent.VK_SHIFT, KeyEvent.VK_SEMICOLON),//:
    SEMI_COLON(59, -1, KeyEvent.VK_SEMICOLON),//;
    EQ(61, -1, KeyEvent.VK_EQUALS),//=
    QUESTION(63, KeyEvent.VK_SHIFT, KeyEvent.VK_SLASH),//?
    AT(64, KeyEvent.VK_SHIFT, KeyEvent.VK_2),//@
    SHARP(35, KeyEvent.VK_SHIFT, KeyEvent.VK_3),//#
    OR(124, KeyEvent.VK_SHIFT, KeyEvent.VK_BACK_SLASH),//|
    SINGLE_QUOT(39, -1, KeyEvent.VK_QUOTE),//'
    DOUBLE_QUOT(34, KeyEvent.VK_SHIFT, KeyEvent.VK_QUOTE),//"
    LESS(60, KeyEvent.VK_SHIFT, KeyEvent.VK_COMMA),//<
    GREATER(62, KeyEvent.VK_SHIFT, KeyEvent.VK_PERIOD),//>
    DOT(46, -1, KeyEvent.VK_PERIOD),//.
    UP(94, KeyEvent.VK_SHIFT, KeyEvent.VK_6),//^
    STAR(42, KeyEvent.VK_SHIFT, KeyEvent.VK_8),//*
    PERCENT(37, KeyEvent.VK_SHIFT, KeyEvent.VK_5),//%
    EXCLAMATION(33, KeyEvent.VK_SHIFT, KeyEvent.VK_1),//!
    MINUS(45, -1, KeyEvent.VK_MINUS),//-
    UNDERSCORE(95, KeyEvent.VK_SHIFT, KeyEvent.VK_MINUS),//_
    TILDE(126, -1, KeyEvent.VK_DEAD_TILDE),//~ // TODO: 08.08.2023 Have no idea
    TILDE_QUOTE(96, -1, KeyEvent.VK_DEAD_TILDE),//` // TODO: 08.08.2023 Have no idea
    SLASH(47, -1, KeyEvent.VK_SLASH),// /
    BACKLSLASH(92, -1, KeyEvent.VK_BACK_SLASH);// \

    public static final String SYMBOLS = "[]$&+,:;=?@#|'<>.^*()%!-\\/\"{}_`~";
    private static final Map<Integer, Symbol> symbolMap = EnumSet.allOf(Symbol.class).stream().collect(Collectors.toMap(Symbol::getCode, Function.identity()));
    private final int code;
    private final int modKey;
    private final int key;

    public static Symbol findSymbol(int chr) {
        return symbolMap.get(chr);
    }
}
