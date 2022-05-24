package cn.taoguoliang.study.mybatis.mapping;

import cn.taoguoliang.study.mybatis.session.Configuration;
import lombok.Getter;

/**
 * <p>
 * 映射语句
 * </p>
 *
 * @author taogl
 * @since 2022/4/20 11:21
 **/
@Getter
public final class MappedStatement {

    private Configuration configuration;
    private String id;
    private SqlCommandType sqlCommandType;
    private SqlSource sqlSource;
    private Class<?> resultTypeClass;

    public static class Builder {
        private final MappedStatement mappedStatement = new MappedStatement();

        public Builder(Configuration configuration, String id, SqlCommandType sqlCommandType, SqlSource sqlSource, Class<?> resultTypeClass) {
            mappedStatement.configuration = configuration;
            mappedStatement.id = id;
            mappedStatement.sqlCommandType = sqlCommandType;
            mappedStatement.sqlSource = sqlSource;
            mappedStatement.resultTypeClass = resultTypeClass;
        }

        public MappedStatement build() {
            assert mappedStatement.configuration != null;
            assert mappedStatement.id != null;
            assert mappedStatement.sqlSource != null;
            return mappedStatement;
        }
    }
}
