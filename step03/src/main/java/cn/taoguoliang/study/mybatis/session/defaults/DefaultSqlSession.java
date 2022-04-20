package cn.taoguoliang.study.mybatis.session.defaults;

import cn.taoguoliang.study.mybatis.session.Configuration;
import cn.taoguoliang.study.mybatis.session.SqlSession;

/**
 * <p>
 * DefaultSqlSession
 * </p>
 *
 * @author taogl
 * @since 2022/4/19 21:43
 **/
public class DefaultSqlSession implements SqlSession {

    /**
     * 映射器注册机
     */
    private Configuration configuration;

    public DefaultSqlSession(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public <T> T selectOne(String statement) {
        return (T) ("你被代理了！" + "方法：" + statement);
    }

    @Override
    public <T> T selectOne(String statement, Object args) {
        return (T) ("你被代理了！" + "方法：" + statement + " 入参：" + args);
    }

    @Override
    public <T> T getMapper(Class<T> cls) {
        return configuration.getMapper(cls, this);
    }

    @Override
    public Configuration getConfiguration() {
        return configuration;
    }
}
