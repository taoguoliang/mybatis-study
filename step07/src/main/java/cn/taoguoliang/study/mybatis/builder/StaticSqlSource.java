package cn.taoguoliang.study.mybatis.builder;

import cn.taoguoliang.study.mybatis.mapping.BoundSql;
import cn.taoguoliang.study.mybatis.mapping.ParameterMapping;
import cn.taoguoliang.study.mybatis.mapping.SqlSource;
import cn.taoguoliang.study.mybatis.session.Configuration;

import java.util.List;

/**
 * <p>
 *    静态sql数据源
 * </p>
 *
 * @author taogl
 * @since 2022/5/24 13:41
 **/
public class StaticSqlSource implements SqlSource {

  private final String sql;
  private final List<ParameterMapping> parameterMappings;
  private final Configuration configuration;

  public StaticSqlSource(Configuration configuration, String sql) {
    this(configuration, sql, null);
  }

  public StaticSqlSource(Configuration configuration, String sql, List<ParameterMapping> parameterMappings) {
    this.sql = sql;
    this.parameterMappings = parameterMappings;
    this.configuration = configuration;
  }

  @Override
  public BoundSql getBoundSql(Object parameterObject) {
    return new BoundSql(configuration, sql, parameterMappings, parameterObject);
  }
}
