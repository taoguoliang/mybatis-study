package cn.taoguoliang.study.mybatis.executor;

import cn.taoguoliang.study.mybatis.mapping.BoundSql;
import cn.taoguoliang.study.mybatis.mapping.MappedStatement;
import cn.taoguoliang.study.mybatis.session.Configuration;
import cn.taoguoliang.study.mybatis.session.ResultHandler;
import cn.taoguoliang.study.mybatis.transaction.Transaction;
import lombok.extern.slf4j.Slf4j;

import java.sql.SQLException;
import java.util.List;

/**
 * 执行器抽象类，共性代码
 *
 * @author taogl
 * @date 2022/5/5 21:51
 */
@Slf4j
public abstract class BaseExecutor implements Executor {

    protected Configuration configuration;

    protected Transaction transaction;

    protected Executor wrapper;

    private boolean closed;

    protected BaseExecutor(Configuration configuration, Transaction transaction) {
        this.configuration = configuration;
        this.transaction = transaction;
        this.wrapper = this;
    }

    /**
     * 查询结果集.
     *
     * @param ms MappedStatement
     * @param parameter 查询参数
     * @param resultHandler 结果处理
     * @param boundSql boundSql
     * @return java.util.List<E>
     * @author taogl
     * @date 2022/5/5 21:53
     **/
    protected abstract <E> List<E> doQuery(MappedStatement ms, Object parameter, ResultHandler resultHandler, BoundSql boundSql);

    @Override
    public <E> List<E> query(MappedStatement ms, Object parameter, ResultHandler resultHandler, BoundSql boundSql) {
        if (closed) {
            throw new RuntimeException("Executor was closed.");
        }
        return doQuery(ms, parameter, resultHandler, boundSql);
    }

    @Override
    public void commit(boolean required) throws SQLException {
        if (closed) {
            throw new RuntimeException("Cannot commit, transaction is already closed");
        }
        if (required) {
            transaction.commit();
        }
    }

    @Override
    public Transaction getTransaction() {
        if (closed) {
            throw new RuntimeException("Executor was closed.");
        }
        return transaction;
    }

    @Override
    public void rollback(boolean required) throws SQLException {
        if (closed) {
            throw new RuntimeException("Cannot rollback, transaction is already closed");
        }
        if (required) {
            transaction.rollback();
        }
    }

    @Override
    public void close(boolean forceRollback) {
        try {
            try {
                rollback(forceRollback);
            } finally {
                if (transaction != null) {
                    transaction.close();
                }
            }
        } catch (SQLException e) {
            log.warn("Unexpected exception on closing transaction.  Cause: " + e);
        } finally {
            transaction = null;
            closed = true;
        }
    }

}
