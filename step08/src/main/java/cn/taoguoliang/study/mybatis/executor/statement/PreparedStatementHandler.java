package cn.taoguoliang.study.mybatis.executor.statement;

import cn.taoguoliang.study.mybatis.executor.Executor;
import cn.taoguoliang.study.mybatis.mapping.BoundSql;
import cn.taoguoliang.study.mybatis.mapping.MappedStatement;
import cn.taoguoliang.study.mybatis.session.ResultHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * 预处理语句处理器.
 *
 * @author taogl
 * @date 2022/5/5 22:22
 **/
public class PreparedStatementHandler extends BaseStatementHandler {

    public PreparedStatementHandler(Executor executor, MappedStatement ms, BoundSql boundSql, ResultHandler resultHandler, Object parameterObject) {
        super(executor, ms, boundSql, resultHandler, parameterObject);
    }

    @Override
    protected Statement instantiateStatement(Connection connection) throws SQLException {
        String sql = boundSql.getSql();
        return connection.prepareStatement(sql);
    }

    @Override
    public void parameterize(Statement statement) throws SQLException {
        PreparedStatement ps = (PreparedStatement) statement;
        parameterHandler.setParameters(ps);
    }

    @Override
    public <E> List<E> query(Statement statement, ResultHandler resultHandler) throws SQLException {
        PreparedStatement ps = (PreparedStatement) statement;
        ps.execute();
        return resultSetHandler.handleResultSets(ps);
    }

}
