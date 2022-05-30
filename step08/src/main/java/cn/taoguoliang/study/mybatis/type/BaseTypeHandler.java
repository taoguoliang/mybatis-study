package cn.taoguoliang.study.mybatis.type;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * <p>
 * 基础参数类型处理器
 * </p>
 *
 * @author taogl
 * @since 2022/5/30 20:43
 **/
public abstract class BaseTypeHandler<T> implements TypeHandler<T> {

    @Override
    public void setParameter(PreparedStatement ps, int i, T parameter, JdbcType jdbcType) throws SQLException {
        // 定义抽象方法，由子类实现不同类型的属性设置
        setNonNullParameter(ps, i, parameter, jdbcType);
    }

    protected abstract void setNonNullParameter(PreparedStatement ps, int i, T parameter, JdbcType jdbcType) throws SQLException;
}
