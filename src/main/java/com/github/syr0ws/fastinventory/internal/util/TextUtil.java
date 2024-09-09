package com.github.syr0ws.fastinventory.internal.util;

import net.md_5.bungee.api.ChatColor;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextUtil {

    private static final char COLOR_CHAR = '&';
    private static final Pattern HEX_PATTERN = Pattern.compile("&#([A-Fa-f\\d]{6})");

    public static String parseColors(String string) {

        string = ChatColor.translateAlternateColorCodes(COLOR_CHAR, string);
        string = parseHexColors(string);

        return string;
    }

    public static List<String> parseColors(List<String> strings) {
        return strings.stream().map(TextUtil::parseColors).toList();
    }

    public static String parseHexColors(String message) {

        char colorChar = ChatColor.COLOR_CHAR;

        Matcher matcher = HEX_PATTERN.matcher(message);
        StringBuilder builder = new StringBuilder(message.length() + 4 * 8);

        while (matcher.find()) {

            String group = matcher.group(1);

            matcher.appendReplacement(builder, colorChar + "x"
                    + colorChar + group.charAt(0) + colorChar + group.charAt(1)
                    + colorChar + group.charAt(2) + colorChar + group.charAt(3)
                    + colorChar + group.charAt(4) + colorChar + group.charAt(5));
        }

        return matcher.appendTail(builder).toString();
    }
}
