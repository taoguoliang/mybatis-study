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
public class MappedStatement {

    private String source;
    private Configuration configuration;
    private String id;
    private SqlCommandType sqlCommandType;

    public static class Builder {
        private final MappedStatement mappedStatement = new MappedStatement();

        public Builder(Configuration configuration, String id, SqlCommandType sqlCommandType) {
            mappedStatement.configuration = configuration;
            mappedStatement.id = id;
            mappedStatement.sqlCommandType = sqlCommandType;
        }

        public Builder source(String source) {
            mappedStatement.source = source;
            return this;
        }

        public MappedStatement build() {
            assert mappedStatement.configuration != null;
            assert mappedStatement.id != null;
            assert mappedStatement.source != null;
            return mappedStatement;
        }
    }
}
