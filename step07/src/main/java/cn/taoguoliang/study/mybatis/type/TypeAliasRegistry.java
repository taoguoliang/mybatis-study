package cn.taoguoliang.study.mybatis.type;

import cn.taoguoliang.study.mybatis.io.Resources;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * <p>
 *    类型别名注册器
 * </p>
 *
 * @author taogl
 * @since 2022/4/20 20:16
 **/
public class TypeAliasRegistry {

    private final Map<String, Class<?>> TYPE_ALIASES = new HashMap<>();

    public TypeAliasRegistry() {
        // 构造函数里注册系统内置的类型别名
        registerAlias("string", String.class);

        // 基本包装类型
        registerAlias("byte", Byte.class);
        registerAlias("long", Long.class);
        registerAlias("short", Short.class);
        registerAlias("int", Integer.class);
        registerAlias("integer", Integer.class);
        registerAlias("double", Double.class);
        registerAlias("float", Float.class);
        registerAlias("boolean", Boolean.class);
    }

    public void registerAlias(String alias, Class<?> value) {
        String key = alias.toLowerCase(Locale.ENGLISH);
        TYPE_ALIASES.put(key, value);
    }

    public <T> Class<T> resolveAlias(String string) {
        String key = string.toLowerCase(Locale.ENGLISH);
        Class<T> value;
        Class<?> aClass = TYPE_ALIASES.get(key);
        if (aClass != null) {
            value = (Class<T>) aClass;
        } else {
            try {
                value = (Class<T>) Resources.classForName(string);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException("Could not resolve type alias '" + string + "'.  Cause: " + e, e);
            }
        }
        return value;
    }

}
