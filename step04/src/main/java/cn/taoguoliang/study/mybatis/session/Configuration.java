package cn.taoguoliang.study.mybatis.session;

import cn.taoguoliang.study.mybatis.binding.MapperRegistry;
import cn.taoguoliang.study.mybatis.datasource.druid.DruidDataSourceFactory;
import cn.taoguoliang.study.mybatis.mapping.Environment;
import cn.taoguoliang.study.mybatis.mapping.MappedStatement;
import cn.taoguoliang.study.mybatis.transaction.jdbc.JdbcTransactionFactory;
import cn.taoguoliang.study.mybatis.type.TypeAliasRegistry;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

/**
 * mybatis 最重要的类，贯穿整个注册使用阶段
 *
 * @author taogl
 * @since 2022-4-20 8:43 PM
 */
public class Configuration {

    /**
     * 映射注册机
     */
    protected MapperRegistry mapperRegistry = new MapperRegistry(this);

    /**
     * 映射的语句，存在Map里
     */
    protected final Map<String, MappedStatement> mappedStatements = new HashMap<>();

    @Setter
    @Getter
    protected Environment environment;

    @Getter
    protected boolean useActualParamName = true;

    /**
     * 类型别名注册机
     **/
    @Getter
    protected final TypeAliasRegistry typeAliasRegistry = new TypeAliasRegistry();


    public Configuration() {
        typeAliasRegistry.registerAlias("Jdbc", JdbcTransactionFactory.class);
        typeAliasRegistry.registerAlias("Druid", DruidDataSourceFactory.class);
    }

    public <T> void addMapper(Class<T> type) {
        mapperRegistry.addMapper(type);
    }

    public void addMappedStatement(MappedStatement ms) {
        mappedStatements.put(ms.getId(), ms);
    }

    public MappedStatement getMappedStatement(String statementId) {
        return mappedStatements.get(statementId);
    }

    public <T> T getMapper(Class<T> cls, SqlSession sqlSession) {
        return mapperRegistry.getMapper(cls, sqlSession);
    }

    public boolean hasStatement(String statementId) {
        return mappedStatements.containsKey(statementId);
    }
}
