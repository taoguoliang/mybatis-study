package cn.taoguoliang.study.mybatis.mapping;

import cn.taoguoliang.study.mybatis.reflection.MetaObject;
import cn.taoguoliang.study.mybatis.session.Configuration;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * sql
 * </p>
 *
 * @author taogl
 * @since 2022/4/20 20:54
 **/
@Getter
@Setter
public final class BoundSql {
    private final String sql;
    private final List<ParameterMapping> parameterMappings;
    private final Object parameterObject;
    private final Map<String, Object> additionalParameters;
    private final MetaObject metaParameters;

    public BoundSql(Configuration configuration, String sql, List<ParameterMapping> parameterMappings, Object parameterObject) {
        this.sql = sql;
        this.parameterMappings = parameterMappings;
        this.parameterObject = parameterObject;
        this.additionalParameters = new HashMap<>();
        this.metaParameters = configuration.newMetaObject(additionalParameters);
    }
}
