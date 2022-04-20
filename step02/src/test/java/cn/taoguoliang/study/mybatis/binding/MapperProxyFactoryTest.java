package cn.taoguoliang.study.mybatis.binding;

import cn.taoguoliang.study.mybatis.dao.IUserDao;
import cn.taoguoliang.study.mybatis.session.SqlSession;
import cn.taoguoliang.study.mybatis.session.SqlSessionFactory;
import cn.taoguoliang.study.mybatis.session.defaults.DefaultSqlSessionFactory;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *
 * </p>
 *
 * @author taogl
 * @since 2022/4/19 14:50
 **/
@Slf4j
class MapperProxyFactoryTest {

    @Test
    public void test() {
        MapperRegistry mapperRegistry = new MapperRegistry();
        mapperRegistry.addMappers("cn.taoguoliang.study.mybatis.dao");

        SqlSessionFactory sqlSessionFactory = new DefaultSqlSessionFactory(mapperRegistry);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        IUserDao mapper = sqlSession.getMapper(IUserDao.class);
        String res = mapper.queryUserName("123");
        log.info("Result:{}", res);
    }
}