package cn.taoguoliang.study.mybatis.executor.resultset;

import cn.taoguoliang.study.mybatis.mapping.BoundSql;
import cn.taoguoliang.study.mybatis.mapping.MappedStatement;
import cn.taoguoliang.study.mybatis.reflection.ResultSetUtil;
import lombok.extern.slf4j.Slf4j;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collections;
import java.util.List;

/**
 * 默认结果集处理
 *
 * @author taogl
 * @date 2022/5/5 22:34
 */
@Slf4j
public class DefaultResultSetHandler implements ResultSetHandler {
    private final BoundSql boundSql;
    private final MappedStatement ms;

    public DefaultResultSetHandler(BoundSql boundSql, MappedStatement ms) {
        this.boundSql = boundSql;
        this.ms = ms;
    }

    @Override
    public <E> List<E> handleResultSets(Statement stmt) throws SQLException {
        ResultSet resultSet = stmt.getResultSet();
        try {
            return (List<E>) ResultSetUtil.get(resultSet, ms.getResultTypeClass());
        } catch (Exception e) {
            log.error("Error extract ResultSet.", e);
        }
        return Collections.emptyList();
    }
}
