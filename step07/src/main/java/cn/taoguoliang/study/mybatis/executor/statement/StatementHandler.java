package cn.taoguoliang.study.mybatis.executor.statement;

import cn.taoguoliang.study.mybatis.session.ResultHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * 语句处理器
 *
 * @author taogl
 * @date 2022/5/5 22:10
 */
public interface StatementHandler {
    /**
     * 准备语句
     *
     * @param connection  连接
     * @throws SQLException sql异常
     * @return 根据连接初始化查询语句
     * */
    Statement prepare(Connection connection) throws SQLException;

    /**
     * 参数化
     *
     * @param statement sql语句
     * @throws SQLException sql异常
     * */
    void parameterize(Statement statement) throws SQLException;

    /**
     * 执行查询
     *
     * @param statement sql语句
     * @param resultHandler 结果处理器
     * @param <E> 实体泛型
     * @return 结果list
     * @throws SQLException sql异常
     * */
    <E> List<E> query(Statement statement, ResultHandler resultHandler) throws SQLException;
}
