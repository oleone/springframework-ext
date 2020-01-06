package org.springframework.ext.common.helper;

import java.util.HashMap;
import java.util.Map;

/**
 * 枚举辅助类
 *
 * @author only
 * @since 2020-01-06
 */
public abstract class EnumHelper {
    /**
     * @param <T>      枚举类型
     * @param enumType 枚举类型的class
     * @return 枚举映射：名词为key，枚举值为value
     */
    public static <T extends Enum<T>> Map<String, T> enum2Map(Class<T> enumType) {
        Map<String, T> map = new HashMap<String, T>();
        T[] enums = enumType.getEnumConstants();
        for (T item : enums) {
            map.put(item.name(), item);
        }
        return map;
    }
}
