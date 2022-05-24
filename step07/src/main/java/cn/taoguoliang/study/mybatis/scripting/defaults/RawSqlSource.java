package cn.taoguoliang.study.mybatis.scripting.defaults;

import cn.taoguoliang.study.mybatis.builder.SqlSourceBuilder;
import cn.taoguoliang.study.mybatis.mapping.BoundSql;
import cn.taoguoliang.study.mybatis.mapping.SqlSource;
import cn.taoguoliang.study.mybatis.scripting.xmltags.DynamicContext;
import cn.taoguoliang.study.mybatis.scripting.xmltags.SqlNode;
import cn.taoguoliang.study.mybatis.session.Configuration;

import java.util.HashMap;

/**
 * 原始sql
 *
 * @author taogl
 * @since 2022/5/24 13:36
 **/
public class RawSqlSource implements SqlSource {

  private final SqlSource sqlSource;

  public RawSqlSource(Configuration configuration, SqlNode rootSqlNode, Class<?> parameterType) {
    this(configuration, getSql(configuration, rootSqlNode), parameterType);
  }

  public RawSqlSource(Configuration configuration, String sql, Class<?> parameterType) {
    SqlSourceBuilder sqlSourceParser = new SqlSourceBuilder(configuration);
    Class<?> clazz = parameterType == null ? Object.class : parameterType;
    sqlSource = sqlSourceParser.parse(sql, clazz, new HashMap<>());
  }

  private static String getSql(Configuration configuration, SqlNode rootSqlNode) {
    DynamicContext context = new DynamicContext(configuration, null);
    rootSqlNode.apply(context);
    return context.getSql();
  }

  @Override
  public BoundSql getBoundSql(Object parameterObject) {
    return sqlSource.getBoundSql(parameterObject);
  }

}