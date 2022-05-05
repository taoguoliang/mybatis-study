package cn.taoguoliang.study.mybatis.transaction;

import cn.taoguoliang.study.mybatis.session.TransactionIsolationLevel;

import javax.sql.DataSource;
import java.sql.Connection;

/**
 * <p>
 *     事务工厂
 * </p>
 *
 * @author taogl
 * @since 2022/4/20 19:59
 */
public interface TransactionFactory {

    /**
     * 根据 Connection 创建 Transaction
     *
     * @param conn Existing database connection
     * @return Transaction
     */
    Transaction newTransaction(Connection conn);

    /**
     * 根据数据源和事务隔离级别创建 Transaction
     *
     * @param dataSource DataSource to take the connection from
     * @param level Desired isolation level
     * @param autoCommit Desired autocommit
     * @return Transaction
     */
    Transaction newTransaction(DataSource dataSource, TransactionIsolationLevel level, boolean autoCommit);

}