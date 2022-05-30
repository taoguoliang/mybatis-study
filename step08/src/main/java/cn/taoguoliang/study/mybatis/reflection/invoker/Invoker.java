package cn.taoguoliang.study.mybatis.reflection.invoker;

import java.lang.reflect.InvocationTargetException;

/**
 * <p>
 *     Invoker
 * </p>
 *
 * @author taogl
 * @since 2022/4/26 21:15
 **/
public interface Invoker {
  Object invoke(Object target, Object[] args) throws IllegalAccessException, InvocationTargetException;

  Class<?> getType();
}