package cn.taoguoliang.study.mybatis.mapping;

import cn.taoguoliang.study.mybatis.session.Configuration;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
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
    private final Map<String, Object> additionalParameters;
    private final String resultType;

    public BoundSql(Configuration configuration, String sql, String resultType) {
        this.sql = sql;
        this.additionalParameters = new HashMap<>();
        this.resultType = resultType;
    }
}
