package cn.taoguoliang.study.mybatis.transaction;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * <p>
 * 事务接口
 * </p>
 *
 * @author taogl
 * @since 2022/4/20 19:59
 **/
public interface Transaction {

    Connection getConnection() throws SQLException;

    void commit() throws SQLException;

    void rollback() throws SQLException;

    void close() throws SQLException;
}
