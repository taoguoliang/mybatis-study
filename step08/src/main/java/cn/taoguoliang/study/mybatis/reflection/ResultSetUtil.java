package cn.taoguoliang.study.mybatis.reflection;

import lombok.experimental.UtilityClass;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.Collection;

/**
 * <p>
 * 结果集工具类
 * </p>
 *
 * @author taogl
 * @since 2022/4/21 13:41
 **/
@UtilityClass
public class ResultSetUtil {
    public <T> Collection<T> get(final ResultSet result, final Class<T> clazz) throws Exception {
        ResultSetMetaData rsmd = result.getMetaData();
        // 获得数据列数
        int cols = rsmd.getColumnCount();
        // 创建等同数据列数的arraylist类型collection实例
        Collection<T> collection = new ArrayList<>(cols);
        // 遍历结果集
        while (result.next()) {
            // 创建对象
            T object = clazz.newInstance();

            // 循环每条记录
            for (int i = 1; i <= cols; i++) {
                beanRegister(object, rsmd.getColumnName(i), result.getString(i));
            }
            // 将数据插入collection
            collection.add(object);
        }

        return collection;
    }

    private Object[] beanMatch(Class clazz, String beanProperty) {
        Object[] result = new Object[2];
        char beanPropertyChars[] = beanProperty.toCharArray();
        beanPropertyChars[0] = Character.toUpperCase(beanPropertyChars[0]);
        String s = new String(beanPropertyChars);
        String names[] = {("set" + s).intern(), ("get" + s).intern(),
                ("is" + s).intern(), ("write" + s).intern(),
                ("read" + s).intern()};
        Method getter = null;
        Method setter = null;
        Method methods[] = clazz.getMethods();
        for (int i = 0; i < methods.length; i++) {
            Method method = methods[i];
            // 只取公共字段
            if (!Modifier.isPublic(method.getModifiers()))
                continue;
            String methodName = method.getName().intern();
            for (int j = 0; j < names.length; j++) {
                String name = names[j];
                if (!name.equals(methodName))
                    continue;
                if (methodName.startsWith("set")
                        || methodName.startsWith("read"))
                    setter = method;
                else
                    getter = method;
            }
        }
        result[0] = getter;
        result[1] = setter;
        return result;
    }

    private void beanRegister(Object object, String beanProperty, String value) {
        Object[] beanObject = beanMatch(object.getClass(), beanProperty);
        Object[] cache = new Object[1];
        Method getter = (Method) beanObject[0];
        Method setter = (Method) beanObject[1];
        try {
            // 通过get获得方法类型
            String methodType = getter.getReturnType().getName();
            if (methodType.equalsIgnoreCase("long")) {
                cache[0] = new Long(value);
                setter.invoke(object, cache);
            } else if (methodType.equalsIgnoreCase("int")
                    || methodType.equalsIgnoreCase("integer")) {
                cache[0] = new Integer(value);
                setter.invoke(object, cache);
            } else if (methodType.equalsIgnoreCase("short")) {
                cache[0] = new Short(value);
                setter.invoke(object, cache);
            } else if (methodType.equalsIgnoreCase("float")) {
                cache[0] = new Float(value);
                setter.invoke(object, cache);
            } else if (methodType.equalsIgnoreCase("double")) {
                cache[0] = new Double(value);
                setter.invoke(object, cache);
            } else if (methodType.equalsIgnoreCase("boolean")) {
                cache[0] = new Boolean(value);
                setter.invoke(object, cache);
            } else if (methodType.equalsIgnoreCase("java.lang.String")) {
                cache[0] = value;
                setter.invoke(object, cache);
            } else if (methodType.equalsIgnoreCase("java.io.InputStream")) {
            } else if (methodType.equalsIgnoreCase("char")) {
                cache[0] = (Character.valueOf(value.charAt(0)));
                setter.invoke(object, cache);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
