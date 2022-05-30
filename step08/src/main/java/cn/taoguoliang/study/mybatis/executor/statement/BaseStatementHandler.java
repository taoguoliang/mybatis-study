package cn.taoguoliang.study.mybatis.executor.statement;

import cn.taoguoliang.study.mybatis.executor.Executor;
import cn.taoguoliang.study.mybatis.executor.resultset.ResultSetHandler;
import cn.taoguoliang.study.mybatis.mapping.BoundSql;
import cn.taoguoliang.study.mybatis.mapping.MappedStatement;
import cn.taoguoliang.study.mybatis.session.Configuration;
import cn.taoguoliang.study.mybatis.session.ResultHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 基础语句处理器
 *
 * @author taogl
 * @date 2022/5/5 22:16
 */
public abstract class BaseStatementHandler implements StatementHandler {

    protected final Configuration configuration;
    protected final Executor executor;

    protected final MappedStatement mappedStatement;
    protected final BoundSql boundSql;
    protected final ResultSetHandler resultSetHandler;

    protected final Object parameterObject;

    public BaseStatementHandler(Executor executor, MappedStatement ms, BoundSql boundSql, ResultHandler resultHandler, Object parameterObject) {
        this.executor = executor;
        this.mappedStatement = ms;
        this.boundSql = boundSql;
        this.parameterObject = parameterObject;

        this.configuration = ms.getConfiguration();
        this.resultSetHandler = this.configuration.newResultSetHandler(boundSql, ms);
    }

    @Override
    public Statement prepare(Connection connection) {
        Statement statement = null;
        try {
            // 实例化 Statement
            statement = instantiateStatement(connection);
            // 参数设置，可以被抽取，提供配置 todo
            statement.setQueryTimeout(350);
            statement.setFetchSize(10000);
            return statement;
        } catch (Exception e) {
            throw new RuntimeException("Error preparing statement.  Cause: " + e, e);
        }
    }

    /**
     * 实例化语句.
     *
     * @param connection 连接
     * @return java.sql.Statement
     * @throws SQLException sql异常
     * @author taogl
     * @date 2022/5/5 22:21
     **/
    protected abstract Statement instantiateStatement(Connection connection) throws SQLException;

}
