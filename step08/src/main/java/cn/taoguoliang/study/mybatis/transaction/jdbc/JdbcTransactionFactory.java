package cn.taoguoliang.study.mybatis.transaction.jdbc;

import cn.taoguoliang.study.mybatis.session.TransactionIsolationLevel;
import cn.taoguoliang.study.mybatis.transaction.Transaction;
import cn.taoguoliang.study.mybatis.transaction.TransactionFactory;

import javax.sql.DataSource;
import java.sql.Connection;

/**
 * <p>
 * jdbc事务工厂
 * </p>
 *
 * @author taogl
 * @since 2022/4/20 20:11
 **/
public class JdbcTransactionFactory implements TransactionFactory {

    @Override
    public Transaction newTransaction(Connection conn) {
        return new JdbcTransaction(conn);
    }

    @Override
    public Transaction newTransaction(DataSource dataSource, TransactionIsolationLevel level, boolean autoCommit) {
        return new JdbcTransaction(dataSource, level, autoCommit);
    }
}
