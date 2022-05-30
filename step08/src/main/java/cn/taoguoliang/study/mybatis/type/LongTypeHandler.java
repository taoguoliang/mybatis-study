package cn.taoguoliang.study.mybatis.type;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Long类型参数处理器
 *
 * @author taogl
 */
public class LongTypeHandler extends BaseTypeHandler<Long> {

    @Override
    protected void setNonNullParameter(PreparedStatement ps, int i, Long parameter, JdbcType jdbcType) throws SQLException {
        ps.setLong(i, parameter);
    }

}