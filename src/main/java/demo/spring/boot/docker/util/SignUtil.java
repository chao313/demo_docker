package demo.spring.boot.docker.util;


import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 签名算法
 */
public class SignUtil {

    /**
     * 使用MD5对入参升序排序后签名
     *
     * @param t
     * @param key
     * @param <T>
     * @return
     */
    public static <T> String signMD5(T t, String key) {
        String stringBuilder;
        //升序
        Map<String, Object> fieldMap = new TreeMap<>(String::compareTo);
        List<Field> fieldList = SignUtil.getPrivateFieldList(t.getClass());
        fieldList.forEach(field -> fieldMap.put(field.getName(), SignUtil.getField(t, field)));
        stringBuilder = fieldMap.keySet().stream()
                .map(fieldMap::get)
                .filter(Objects::nonNull)
                .map(String::valueOf)
                .collect(Collectors.joining("", "", key));

        return MD5Utils.encodeByMD5(stringBuilder);
    }

    private static List<Field> getPrivateFieldList(Class clazz) {
        Field[] fields = clazz.getDeclaredFields();
        return Arrays.stream(fields).filter(field -> field.getModifiers() == Modifier.PRIVATE).collect(Collectors.toList());
    }

    private static Object getField(Object bean, Field field) {
        try {
            PropertyDescriptor pd = new PropertyDescriptor(field.getName(), bean.getClass());
            Method method = pd.getReadMethod();
            return method.invoke(bean);
        } catch (IllegalAccessException | IntrospectionException | InvocationTargetException e) {
            return null;
        }
    }
}
