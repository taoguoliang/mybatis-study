package cn.taoguoliang.study.mybatis.session.defaults;

import cn.hutool.core.collection.CollectionUtil;
import cn.taoguoliang.study.mybatis.mapping.BoundSql;
import cn.taoguoliang.study.mybatis.mapping.Environment;
import cn.taoguoliang.study.mybatis.mapping.MappedStatement;
import cn.taoguoliang.study.mybatis.reflection.ResultSetUtil;
import cn.taoguoliang.study.mybatis.session.Configuration;
import cn.taoguoliang.study.mybatis.session.SqlSession;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Collection;

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
    private final Configuration configuration;

    public DefaultSqlSession(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public <T> T selectOne(String statement) {
        return (T) ("你被代理了！" + "方法：" + statement);
    }

    @Override
    public <T> T selectOne(String statement, Object args) {
        MappedStatement mappedStatement = configuration.getMappedStatement(statement);
        Environment environment = configuration.getEnvironment();
        try(Connection connection = environment.getDataSource().getConnection()) {
            BoundSql boundSql = mappedStatement.getBoundSql();
            PreparedStatement preparedStatement = connection.prepareStatement(boundSql.getSql());
            preparedStatement.setLong(1, Long.parseLong(args.toString()));
            ResultSet resultSet = preparedStatement.executeQuery();

            Collection<T> objList = (Collection<T>) ResultSetUtil.get(resultSet, Class.forName(boundSql.getResultType()));
            return CollectionUtil.isNotEmpty(objList) ? objList.iterator().next() : null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
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
