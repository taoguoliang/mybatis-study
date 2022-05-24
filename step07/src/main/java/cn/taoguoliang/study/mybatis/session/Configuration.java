package cn.taoguoliang.study.mybatis.session;

import cn.taoguoliang.study.mybatis.binding.MapperRegistry;
import cn.taoguoliang.study.mybatis.datasource.druid.DruidDataSourceFactory;
import cn.taoguoliang.study.mybatis.datasource.pooled.PooledDataSourceFactory;
import cn.taoguoliang.study.mybatis.datasource.unpooled.UnPooledDataSourceFactory;
import cn.taoguoliang.study.mybatis.executor.Executor;
import cn.taoguoliang.study.mybatis.executor.SimpleExecutor;
import cn.taoguoliang.study.mybatis.executor.resultset.DefaultResultSetHandler;
import cn.taoguoliang.study.mybatis.executor.resultset.ResultSetHandler;
import cn.taoguoliang.study.mybatis.executor.statement.PreparedStatementHandler;
import cn.taoguoliang.study.mybatis.executor.statement.StatementHandler;
import cn.taoguoliang.study.mybatis.mapping.BoundSql;
import cn.taoguoliang.study.mybatis.mapping.Environment;
import cn.taoguoliang.study.mybatis.mapping.MappedStatement;
import cn.taoguoliang.study.mybatis.reflection.DefaultReflectorFactory;
import cn.taoguoliang.study.mybatis.reflection.MetaObject;
import cn.taoguoliang.study.mybatis.reflection.ReflectorFactory;
import cn.taoguoliang.study.mybatis.reflection.factory.DefaultObjectFactory;
import cn.taoguoliang.study.mybatis.reflection.factory.ObjectFactory;
import cn.taoguoliang.study.mybatis.reflection.wrapper.DefaultObjectWrapperFactory;
import cn.taoguoliang.study.mybatis.reflection.wrapper.ObjectWrapperFactory;
import cn.taoguoliang.study.mybatis.scripting.LanguageDriverRegistry;
import cn.taoguoliang.study.mybatis.scripting.xmltags.XMLLanguageDriver;
import cn.taoguoliang.study.mybatis.transaction.Transaction;
import cn.taoguoliang.study.mybatis.transaction.jdbc.JdbcTransactionFactory;
import cn.taoguoliang.study.mybatis.type.TypeAliasRegistry;
import cn.taoguoliang.study.mybatis.type.TypeHandlerRegistry;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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
     * 类型别名注册机
     **/
    @Getter
    protected final TypeAliasRegistry typeAliasRegistry = new TypeAliasRegistry();
    /**
     * 类型处理注册机
     **/
    @Getter
    protected final TypeHandlerRegistry typeHandlerRegistry = new TypeHandlerRegistry();
    /**
     * 语言驱动注册机
     */
    @Getter
    protected final LanguageDriverRegistry languageRegistry = new LanguageDriverRegistry();

    /**
     * 映射的语句，存在Map里
     */
    protected final Map<String, MappedStatement> mappedStatements = new HashMap<>();

    @Setter
    @Getter
    protected Environment environment;

    @Getter
    protected boolean useActualParamName = true;

    @Getter
    @Setter
    protected ObjectFactory objectFactory = new DefaultObjectFactory();
    @Getter
    @Setter
    protected ObjectWrapperFactory objectWrapperFactory = new DefaultObjectWrapperFactory();
    @Getter
    @Setter
    protected ReflectorFactory reflectorFactory = new DefaultReflectorFactory();


    protected final Set<String> loadedResources = new HashSet<>();

    public Configuration() {
        typeAliasRegistry.registerAlias("Jdbc", JdbcTransactionFactory.class);
        typeAliasRegistry.registerAlias("Druid", DruidDataSourceFactory.class);
        typeAliasRegistry.registerAlias("UNPOOLED", UnPooledDataSourceFactory.class);
        typeAliasRegistry.registerAlias("POOLED", PooledDataSourceFactory.class);

        languageRegistry.setDefaultDriverClass(XMLLanguageDriver.class);
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

    public StatementHandler newStatementHandler(Executor executor, MappedStatement ms, Object parameter, ResultHandler resultHandler, BoundSql boundSql) {
        return new PreparedStatementHandler(executor, ms, boundSql, resultHandler, parameter);
    }

    public ResultSetHandler newResultSetHandler(BoundSql boundSql, MappedStatement ms) {
        return new DefaultResultSetHandler(boundSql, ms);
    }

    public Executor newExecutor(Transaction tx) {
        return new SimpleExecutor(this, tx);
    }

    public boolean isResourceLoaded(String resource) {
        return loadedResources.contains(resource);
    }

    public void addLoadedResource(String resource) {
        loadedResources.add(resource);
    }

    public MetaObject newMetaObject(Object object) {
        return MetaObject.forObject(object, objectFactory, objectWrapperFactory, reflectorFactory);
    }
}
