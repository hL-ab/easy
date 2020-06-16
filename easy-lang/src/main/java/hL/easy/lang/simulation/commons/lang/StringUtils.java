package hL.easy.lang.simulation.commons.lang;


import hL.easy.lang.commons.Classes;

/**
 * @author @HL
 * @since 0.1.001
 */
public class StringUtils {

    public static final String SIMULATED_1 = "org.apache.commons.lang3.StringUtils";
    public static final String SIMULATED_2 = "org.apache.commons.lang.StringUtils";
    public static final boolean EXISTS_SIMULATED_1;
    public static final boolean EXISTS_SIMULATED_2;

    public static final String SPACE = " ";
    private static final int PAD_LIMIT = 8192;
    public static final String EMPTY = "";

    static {
        EXISTS_SIMULATED_1 = Classes.isExists(SIMULATED_1);
        EXISTS_SIMULATED_2 = Classes.isExists(SIMULATED_2);
    }


    public static boolean isEmpty(CharSequence cs) {
        if (EXISTS_SIMULATED_1) {
            return org.apache.commons.lang3.StringUtils.isEmpty(cs);
        } else if (EXISTS_SIMULATED_2 && cs instanceof String) {
            return org.apache.commons.lang.StringUtils.isEmpty((String) cs);
        } else {
            return cs == null || cs.length() == 0;
        }
    }

    public static boolean isBlank(CharSequence cs) {
        if (EXISTS_SIMULATED_1) {
            return org.apache.commons.lang3.StringUtils.isBlank(cs);
        } else if (EXISTS_SIMULATED_2 && cs instanceof String) {
            return org.apache.commons.lang.StringUtils.isBlank((String) cs);
        } else {
            if (isEmpty(cs)) {
                return true;
            } else {
                int l = cs.length();
                for (int i = 0; i < l; ++i) {
                    if (!Character.isWhitespace(cs.charAt(i))) {
                        return false;
                    }
                }
                return true;
            }
        }
    }

    public static String leftPad(final String str, final int size, String padStr) {
        if (EXISTS_SIMULATED_1) {
            return org.apache.commons.lang3.StringUtils.leftPad(str, size, padStr);
        } else if (EXISTS_SIMULATED_2) {
            return org.apache.commons.lang.StringUtils.leftPad(str, size, padStr);
        } else {
            if (str == null) {
                return null;
            }
            if (isEmpty(padStr)) {
                padStr = SPACE;
            }
            final int padLen = padStr.length();
            final int strLen = str.length();
            final int pads = size - strLen;
            if (pads <= 0) {
                return str;
            }
            if (padLen == 1 && pads <= PAD_LIMIT) {
                return leftPad(str, size, padStr.charAt(0));
            }
            if (pads == padLen) {
                return padStr.concat(str);
            } else if (pads < padLen) {
                return padStr.substring(0, pads).concat(str);
            } else {
                final char[] padding = new char[pads];
                final char[] padChars = padStr.toCharArray();
                for (int i = 0; i < pads; i++) {
                    padding[i] = padChars[i % padLen];
                }
                return new String(padding).concat(str);
            }
        }
    }

    public static String leftPad(final String str, final int size, final char padChar) {
        if (EXISTS_SIMULATED_1) {
            return org.apache.commons.lang3.StringUtils.leftPad(str, size, padChar);
        } else if (EXISTS_SIMULATED_2) {
            return org.apache.commons.lang.StringUtils.leftPad(str, size, padChar);
        } else {
            if (str == null) {
                return null;
            }
            final int pads = size - str.length();
            if (pads <= 0) {
                return str;
            }
            if (pads > PAD_LIMIT) {
                return leftPad(str, size, String.valueOf(padChar));
            }
            return repeat(padChar, pads).concat(str);
        }
    }

    public static String repeat(final char ch, final int repeat) {
        if (EXISTS_SIMULATED_1) {
            return org.apache.commons.lang3.StringUtils.repeat(ch, repeat);
        } else if (EXISTS_SIMULATED_2) {
            return org.apache.commons.lang.StringUtils.repeat(String.valueOf(ch), repeat);
        } else {
            if (repeat <= 0) {
                return EMPTY;
            }
            final char[] buf = new char[repeat];
            for (int i = repeat - 1; i >= 0; i--) {
                buf[i] = ch;
            }
            return new String(buf);
        }
    }


    public static String rightPad(final String str, final int size, String padStr) {
        if (EXISTS_SIMULATED_1) {
            return org.apache.commons.lang3.StringUtils.rightPad(str, size, padStr);
        } else if (EXISTS_SIMULATED_2) {
            return org.apache.commons.lang.StringUtils.rightPad(str, size, padStr);
        } else {
            if (str == null) {
                return null;
            }
            if (isEmpty(padStr)) {
                padStr = SPACE;
            }
            final int padLen = padStr.length();
            final int strLen = str.length();
            final int pads = size - strLen;
            if (pads <= 0) {
                return str;
            }
            if (padLen == 1 && pads <= PAD_LIMIT) {
                return rightPad(str, size, padStr.charAt(0));
            }

            if (pads == padLen) {
                return str.concat(padStr);
            } else if (pads < padLen) {
                return str.concat(padStr.substring(0, pads));
            } else {
                final char[] padding = new char[pads];
                final char[] padChars = padStr.toCharArray();
                for (int i = 0; i < pads; i++) {
                    padding[i] = padChars[i % padLen];
                }
                return str.concat(new String(padding));
            }
        }
    }

    public static String rightPad(final String str, final int size, final char padChar) {
        if (EXISTS_SIMULATED_1) {
            return org.apache.commons.lang3.StringUtils.rightPad(str, size, padChar);
        } else if (EXISTS_SIMULATED_2) {
            return org.apache.commons.lang.StringUtils.rightPad(str, size, padChar);
        } else {
            if (str == null) {
                return null;
            }
            final int pads = size - str.length();
            if (pads <= 0) {
                return str;
            }
            if (pads > PAD_LIMIT) {
                return rightPad(str, size, String.valueOf(padChar));
            }
            return str.concat(repeat(padChar, pads));
        }
    }

    public static boolean isNumeric(CharSequence cs) {
        if (EXISTS_SIMULATED_1) {
            return org.apache.commons.lang3.StringUtils.isNumeric(cs);
        } else if (EXISTS_SIMULATED_2 && cs instanceof String) {
            return org.apache.commons.lang.StringUtils.isNumeric((String) cs);
        } else {
            if (isEmpty(cs)) {
                return false;
            } else {
                int sz = cs.length();
                for (int i = 0; i < sz; ++i) {
                    if (!Character.isDigit(cs.charAt(i))) {
                        return false;
                    }
                }
                return true;
            }
        }
    }
}
