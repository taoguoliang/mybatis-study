package cn.taoguoliang.study.mybatis.mapping;

/**
 * 表示从 XML 文件或注释读取的映射语句的内容。它根据从用户接收到的输入参数创建将传递给数据库的 SQL。
 * @author taogl
 * @since 2022/5/24 10:39
 **/
public interface SqlSource {

  BoundSql getBoundSql(Object parameterObject);

}