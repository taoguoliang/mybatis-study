package cn.taoguoliang.study.mybatis.executor.parameter;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * 参数处理器
 *
 * @author taogl
 */
public interface ParameterHandler {

  Object getParameterObject();

  void setParameters(PreparedStatement ps) throws SQLException;
}
