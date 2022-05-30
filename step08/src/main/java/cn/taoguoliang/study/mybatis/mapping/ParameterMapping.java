package cn.taoguoliang.study.mybatis.mapping;

import cn.taoguoliang.study.mybatis.session.Configuration;
import cn.taoguoliang.study.mybatis.type.JdbcType;
import cn.taoguoliang.study.mybatis.type.TypeHandler;
import cn.taoguoliang.study.mybatis.type.TypeHandlerRegistry;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 *  参数映射
 * </p>
 *
 * @author taogl
 * @since 2022/4/20 21:01
 **/
@Getter
@Setter
public class ParameterMapping {

  private Configuration configuration;

  private TypeHandler<?> typeHandler;

  private String property;
  private Class<?> javaType = Object.class;
  private JdbcType jdbcType;
  private Integer numericScale;
  private String resultMapId;
  private String jdbcTypeName;
  private String expression;

  public static class Builder {
    private final ParameterMapping parameterMapping = new ParameterMapping();

    public Builder(Configuration configuration, String property, Class<?> propertyType) {
      parameterMapping.configuration = configuration;
      parameterMapping.property = property;
      parameterMapping.javaType = propertyType;
    }

    public Builder javaType(Class<?> javaType) {
      parameterMapping.javaType = javaType;
      return this;
    }

    public Builder jdbcType(JdbcType jdbcType) {
      parameterMapping.jdbcType = jdbcType;
      return this;
    }

    public Builder typeHandler(TypeHandler<?> typeHandler) {
      parameterMapping.typeHandler = typeHandler;
      return this;
    }

    public ParameterMapping build() {
      resolveTypeHandler();
      return parameterMapping;
    }

    private void resolveTypeHandler() {
      if (parameterMapping.typeHandler == null && parameterMapping.javaType != null) {
        Configuration configuration = parameterMapping.configuration;
        TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();
        parameterMapping.typeHandler = typeHandlerRegistry.getTypeHandler(parameterMapping.javaType, parameterMapping.jdbcType);
      }
    }
  }
}