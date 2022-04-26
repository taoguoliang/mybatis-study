package cn.taoguoliang.study.mybatis.reflection;

import lombok.experimental.UtilityClass;

import java.lang.reflect.Constructor;
import java.lang.reflect.Executable;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *   参数名称工具类
 * </p>
 *
 * @since 2022/4/21 10:49
 **/
@UtilityClass
public class ParamNameUtil {

  public static List<String> getParamNames(Method method) {
    return getParameterNames(method);
  }

  public static List<String> getParamNames(Constructor<?> constructor) {
    return getParameterNames(constructor);
  }

  private static List<String> getParameterNames(Executable executable) {
    return Arrays.stream(executable.getParameters()).map(Parameter::getName).collect(Collectors.toList());
  }
}