package cn.taoguoliang.study.mybatis.session;

import cn.taoguoliang.study.mybatis.builder.xml.XMLConfigBuilder;
import cn.taoguoliang.study.mybatis.session.defaults.DefaultSqlSessionFactory;

import java.io.Reader;

/**
 * <p>
 * builder
 * </p>
 *
 * @author taogl
 * @since 2022/4/20 10:59
 **/
public class SqlSessionFactoryBuilder {

    public SqlSessionFactory build(Reader reader) {
        XMLConfigBuilder xmlConfigBuilder = new XMLConfigBuilder(reader);
        return build(xmlConfigBuilder.parse());
    }

    public SqlSessionFactory build(Configuration config) {
        return new DefaultSqlSessionFactory(config);
    }
}
