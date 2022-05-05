package cn.taoguoliang.study.mybatis.executor.resultset;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * <p>
 *    结果集处理器
 * </p>
 * @author taogl
 */
public interface ResultSetHandler {

  /**
   * 处理结果集.
   *
   * @param stmt  语句
   * @return java.util.List<E>
   * @author taogl
   * @throws SQLException sql异常
   * @date 2022/5/5 22:34
   **/
  <E> List<E> handleResultSets(Statement stmt) throws SQLException;

}
