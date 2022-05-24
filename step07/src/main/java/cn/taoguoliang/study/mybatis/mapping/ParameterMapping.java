package cn.taoguoliang.study.mybatis.mapping;

import cn.hutool.db.meta.JdbcType;
import cn.taoguoliang.study.mybatis.session.Configuration;
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

    public ParameterMapping build() {
      return parameterMapping;
    }
  }
}