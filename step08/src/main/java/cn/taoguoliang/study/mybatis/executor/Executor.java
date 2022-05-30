package cn.taoguoliang.study.mybatis.executor;

import cn.taoguoliang.study.mybatis.mapping.BoundSql;
import cn.taoguoliang.study.mybatis.mapping.MappedStatement;
import cn.taoguoliang.study.mybatis.session.ResultHandler;
import cn.taoguoliang.study.mybatis.transaction.Transaction;

import java.sql.SQLException;
import java.util.List;

/**
 * sql执行器
 *
 * @author taogl
 * @date 2022/5/5 21:50
 */
public interface Executor {

    Transaction getTransaction();

    <E> List<E> query(MappedStatement ms, Object parameter, ResultHandler resultHandler, BoundSql boundSql);

    void commit(boolean required) throws SQLException;

    void rollback(boolean required) throws SQLException;

    void close(boolean forceRollback);

}
