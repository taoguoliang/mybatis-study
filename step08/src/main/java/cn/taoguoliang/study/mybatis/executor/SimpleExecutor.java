package cn.taoguoliang.study.mybatis.executor;

import cn.taoguoliang.study.mybatis.executor.statement.StatementHandler;
import cn.taoguoliang.study.mybatis.mapping.BoundSql;
import cn.taoguoliang.study.mybatis.mapping.MappedStatement;
import cn.taoguoliang.study.mybatis.session.Configuration;
import cn.taoguoliang.study.mybatis.session.ResultHandler;
import cn.taoguoliang.study.mybatis.transaction.Transaction;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collections;
import java.util.List;

/**
 * 简单执行器
 *
 * @author taogl
 * @date 2022/5/5 21:57
 */
@Slf4j
public class SimpleExecutor extends BaseExecutor {

    public SimpleExecutor(Configuration configuration, Transaction transaction) {
        super(configuration, transaction);
    }

    @Override
    protected <E> List<E> doQuery(MappedStatement ms, Object parameter, ResultHandler resultHandler, BoundSql boundSql) {
        Configuration configuration = ms.getConfiguration();
        StatementHandler handler = configuration.newStatementHandler(this, ms, parameter, resultHandler, boundSql);
        try (Connection connection = transaction.getConnection();
                Statement stmt = handler.prepare(connection)) {
            handler.parameterize(stmt);
            return handler.query(stmt, resultHandler);
        } catch (SQLException e) {
            log.error("Query Error.", e);
        }
        return Collections.emptyList();
    }

}
