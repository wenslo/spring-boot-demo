package com.github.wenslo.springbootdemo.util;

import java.util.regex.Pattern;

/**
 * @author wenhailin
 * @version 0.0.1
 * @createTime 2019-06-10-10:14
 * @description LocalDateTime
 */
public class LocalDateTimeUtil {
    /**
     * populate time to yyyy-MM-dd HH:mm:ss
     * 
     * @param text
     *            date str
     * @return populated str
     */
    public static String populateLocalDateTime(String text) {
        if (text.isEmpty())
            return null;
        StringBuilder sb = new StringBuilder(text.trim());
        // yyyy-MM-dd HH:mm
        String yyyyMMddHHmmRegex = "^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}?$";
        Pattern yyyyMMddHHmmPattern = Pattern.compile(yyyyMMddHHmmRegex);

        // yyyy-MM-dd HH
        String yyyyMMddHHRegex = "^\\d{4}-\\d{2}-\\d{2} \\d{2}?$";
        Pattern yyyyMMddHHPattern = Pattern.compile(yyyyMMddHHRegex);
        // yyyy-MM-dd
        String yyyyMMddRegex = "^\\d{4}-\\d{2}-\\d{2}$";
        Pattern yyyyMMddPattern = Pattern.compile(yyyyMMddRegex);

        // yyyy-MM
        String yyyyMMRegex = "^\\d{4}-\\d{2}$";
        Pattern yyyyMMPattern = Pattern.compile(yyyyMMRegex);

        if (yyyyMMddHHmmPattern.matcher(text).matches()) {
            sb.append(":00");
        }
        if (yyyyMMddHHPattern.matcher(text).matches()) {
            sb.append(":00:00");
        }
        if (yyyyMMddPattern.matcher(text).matches()) {
            sb.append(" 00:00:00");
        }
        if (yyyyMMPattern.matcher(text).matches()) {
            sb.append("-00 00:00:00");
        }
        return sb.toString();
    }
}
