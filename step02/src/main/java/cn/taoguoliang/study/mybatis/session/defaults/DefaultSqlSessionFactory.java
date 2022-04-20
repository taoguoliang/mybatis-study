package cn.taoguoliang.study.mybatis.session.defaults;

import cn.taoguoliang.study.mybatis.binding.MapperRegistry;
import cn.taoguoliang.study.mybatis.session.SqlSession;
import cn.taoguoliang.study.mybatis.session.SqlSessionFactory;

/**
 * <p>
 * session factory
 * </p>
 *
 * @author taogl
 * @since 2022/4/19 21:45
 **/
public class DefaultSqlSessionFactory implements SqlSessionFactory {

    private final MapperRegistry mapperRegistry;

    public DefaultSqlSessionFactory(MapperRegistry mapperRegistry) {
        this.mapperRegistry = mapperRegistry;
    }

    /**
     * 打开一个 session
     * @return SqlSession
     */
    @Override
    public SqlSession openSession() {
        return new DefaultSqlSession(mapperRegistry);
    }
}
