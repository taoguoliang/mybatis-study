package cn.taoguoliang.study.mybatis.reflection;

/**
 * <p>
 *     反射工厂
 * </p>
 *
 * @author taogl
 * @since 2022/4/26 13:54
 **/
public interface ReflectorFactory {

  boolean isClassCacheEnabled();

  void setClassCacheEnabled(boolean classCacheEnabled);

  Reflector findForClass(Class<?> type);
}