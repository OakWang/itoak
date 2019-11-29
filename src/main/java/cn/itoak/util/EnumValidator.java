package cn.itoak.util;

import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Objects;

/**
 * 枚举校验器
 * 校验枚举项成员变量值是否合法，如code的值；
 * 校验枚举项是否合法，如RED是否属于ColorEnum中定义的。
 *
 * @author itoak
 */
public class EnumValidator {

    /**
     * 检测枚举项成员变量值是否合法
     *
     * @param targetField 目标变量
     * @param fieldValue  变量值
     * @param clazz       枚举类型Class
     * @param <T>         入参类型
     * @return 字段值是否合法
     */
    @SuppressWarnings("unchecked")
    public static <T> boolean checkFieldValue(String targetField, Object fieldValue, Class<T> clazz) {
        if (clazz == null || !clazz.isEnum() || Objects.isNull(fieldValue) || StringUtils.isEmpty(targetField))
            return false;
        try {
            Method method = clazz.getMethod("values");
            T[] items = (T[]) method.invoke(null);
            Field field = clazz.getDeclaredField(targetField);
            field.setAccessible(true);
            for (T t : items) {
                Object obj = field.get(t);
                if (obj.equals(fieldValue))
                    return true;
            }
        } catch (Exception ignored) {
        }
        return false;
    }

    /**
     * 校验枚举项是否合法
     *
     * @param targetItem 枚举项
     * @param clazz      枚举类型Class
     * @param <T>        入参类型
     * @return 字段是否合法
     */
    public static <T> boolean checkItem(String targetItem, Class<T> clazz) {
        if (clazz == null || !clazz.isEnum() || StringUtils.isEmpty(targetItem)) {
            return false;
        }
        try {
            Method method = clazz.getMethod("valueOf", String.class);
            Object item = method.invoke(null, targetItem);
            if (Objects.nonNull(item))
                return true;
        } catch (Exception ignored) {
        }
        return false;
    }

}
