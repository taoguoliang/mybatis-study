package cn.taoguoliang.study.mybatis.scripting.xmltags;

import cn.taoguoliang.study.mybatis.session.Configuration;

import java.util.StringJoiner;

/**
 * <p>
 * 动态sql 上下文
 * </p>
 *
 * @author taogl
 * @since 2022/5/24 10:42
 **/
public class DynamicContext {

    private final StringJoiner sqlBuilder = new StringJoiner(" ");

    public DynamicContext(Configuration configuration, Object parameterObject) {
    }

    public void appendSql(String sql) {
        sqlBuilder.add(sql);
    }

    public String getSql() {
        return sqlBuilder.toString().trim();
    }
}
