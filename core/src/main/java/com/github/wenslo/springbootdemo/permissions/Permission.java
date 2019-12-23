package com.github.wenslo.springbootdemo.permissions;

import org.apache.commons.lang3.StringUtils;

public interface Permission {

    String SEPARATOR = "_";

    String getAction();

    String getDescribe();

    String getGroup();

    String getGroupDescribe();

    /**
     * use by perAuthority
     */
    default String getAuthority() {
        return StringUtils.join(getGroup(), SEPARATOR, getAction());
    }

    @SuppressWarnings("unchecked")
    default <T extends Permission> T restore(Class<? extends Enum> clazz, String authority) {
        String[] split = authority.split(SEPARATOR);
        if (split.length > 1) {
            return (T) Enum.valueOf(clazz, split[1].toUpperCase());
        }
        return null;
    }
}
