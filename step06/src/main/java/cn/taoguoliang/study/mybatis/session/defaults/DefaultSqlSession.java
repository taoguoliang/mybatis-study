package cn.taoguoliang.study.mybatis.session.defaults;

import cn.taoguoliang.study.mybatis.executor.Executor;
import cn.taoguoliang.study.mybatis.mapping.MappedStatement;
import cn.taoguoliang.study.mybatis.session.Configuration;
import cn.taoguoliang.study.mybatis.session.SqlSession;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * <p>
 * DefaultSqlSession
 * </p>
 *
 * @author taogl
 * @since 2022/4/19 21:43
 **/
@Slf4j
public class DefaultSqlSession implements SqlSession {

    /**
     * 映射器注册机
     */
    private final Configuration configuration;

    private final Executor executor;

    public DefaultSqlSession(Configuration configuration, Executor executor) {
        this.configuration = configuration;
        this.executor = executor;
    }


    @Override
    public <T> T selectOne(String statement) {
        MappedStatement mappedStatement = configuration.getMappedStatement(statement);
        final List<T> list = executor.query(mappedStatement, null, null, mappedStatement.getBoundSql());
        return list.iterator().next();
    }

    @Override
    public <T> T selectOne(String statement, Object args) {
        MappedStatement mappedStatement = configuration.getMappedStatement(statement);
        final List<T> list = executor.query(mappedStatement, args, null, mappedStatement.getBoundSql());
        return list.iterator().next();
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
