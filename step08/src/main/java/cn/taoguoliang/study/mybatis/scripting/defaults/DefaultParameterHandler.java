package cn.taoguoliang.study.mybatis.scripting.defaults;

import cn.hutool.json.JSONUtil;
import cn.taoguoliang.study.mybatis.executor.parameter.ParameterHandler;
import cn.taoguoliang.study.mybatis.mapping.BoundSql;
import cn.taoguoliang.study.mybatis.mapping.MappedStatement;
import cn.taoguoliang.study.mybatis.mapping.ParameterMapping;
import cn.taoguoliang.study.mybatis.reflection.MetaObject;
import cn.taoguoliang.study.mybatis.session.Configuration;
import cn.taoguoliang.study.mybatis.type.JdbcType;
import cn.taoguoliang.study.mybatis.type.TypeHandler;
import cn.taoguoliang.study.mybatis.type.TypeHandlerRegistry;
import lombok.extern.slf4j.Slf4j;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * <p>
 * 默认参数处理器
 * </p>
 *
 * @author taogl
 * @since 2022/5/30 21:02
 **/
@Slf4j
public class DefaultParameterHandler implements ParameterHandler {

    private final BoundSql boundSql;

    private final TypeHandlerRegistry typeHandlerRegistry;

    private final Object parameterObject;

    private final Configuration configuration;

    public DefaultParameterHandler(MappedStatement mappedStatement, Object parameterObject, BoundSql boundSql) {
        this.boundSql = boundSql;
        this.typeHandlerRegistry = mappedStatement.getConfiguration().getTypeHandlerRegistry();
        this.parameterObject = parameterObject;
        this.configuration = mappedStatement.getConfiguration();
    }

    @Override
    public Object getParameterObject() {
        return parameterObject;
    }

    @Override
    public void setParameters(PreparedStatement ps) throws SQLException {
        List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
        if (null != parameterMappings) {
            for (int i = 0; i < parameterMappings.size(); i++) {
                ParameterMapping parameterMapping = parameterMappings.get(i);
                String propertyName = parameterMapping.getProperty();
                Object value;
                if (typeHandlerRegistry.hasTypeHandler(parameterObject.getClass())) {
                    value = parameterObject;
                } else {
                    // 通过 MetaObject.getValue 反射取得值设进去
                    MetaObject metaObject = configuration.newMetaObject(parameterObject);
                    value = metaObject.getValue(propertyName);
                }
                JdbcType jdbcType = parameterMapping.getJdbcType();

                // 设置参数
                log.info("根据每个ParameterMapping中的TypeHandler设置对应的参数信息 value：{}", JSONUtil.toJsonStr(value));
                TypeHandler typeHandler = parameterMapping.getTypeHandler();
                typeHandler.setParameter(ps, i + 1, value, jdbcType);
            }
        }
    }
}
