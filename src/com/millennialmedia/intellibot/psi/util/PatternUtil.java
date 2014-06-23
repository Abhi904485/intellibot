package com.millennialmedia.intellibot.psi.util;

import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.TreeSet;
import java.util.regex.Pattern;

/**
 * @author mrubino
 * @since 2014-06-19
 */
public class PatternUtil {

    private static final Collection<String> VARIABLE_SETTERS;

    static {
        VARIABLE_SETTERS = new TreeSet<String>(String.CASE_INSENSITIVE_ORDER);
        VARIABLE_SETTERS.add("set test variable");
        VARIABLE_SETTERS.add("set suite variable");
        VARIABLE_SETTERS.add("set global variable");
    }
    
    private static final String SPACE = " ";
    private static final String UNDERSCORE = "_";
    
    private PatternUtil() {}
    
    @NotNull
    public static String getVariablePattern(@NotNull String text) {
        text = text.trim();
        if (text.length() == 0) {
            return text;
        }
        if (text.endsWith("=")) {
            text = text.substring(0, text.length() - 1);
        }
        text = text.trim();
        if (text.startsWith("${")) {
            text = text.substring(2);
        }
        if (text.endsWith("}")) {
            text = text.substring(0, text.length() - 1);
        }
        return "\\$\\{" + Pattern.quote(text) + "((\\..*?)*?(\\[.*?\\])*?)*?\\}";
    }
    
    public static boolean isVariableSettingKeyword(String keyword) {
        return VARIABLE_SETTERS.contains(functionToKeyword(keyword));
    }
    
    public static String functionToKeyword(String function) {
        return function == null ? null : function.replaceAll(UNDERSCORE, SPACE).trim();
    }
}
