package cn.taoguoliang.study.mybatis.binding;

import cn.taoguoliang.study.mybatis.dao.IUserDao;
import cn.taoguoliang.study.mybatis.io.Resources;
import cn.taoguoliang.study.mybatis.pojo.dto.User;
import cn.taoguoliang.study.mybatis.session.SqlSession;
import cn.taoguoliang.study.mybatis.session.SqlSessionFactory;
import cn.taoguoliang.study.mybatis.session.SqlSessionFactoryBuilder;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.Reader;

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

    private IUserDao userDao;

    @BeforeEach
    public void before() {
        Reader reader = Resources.getResourceAsReader("mybatis-mappers.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        userDao = sqlSession.getMapper(IUserDao.class);
    }

    @Test
    void test() {
        User user = userDao.queryUser(1L);
        log.info("result:{}", user);
    }

    @Test
    void test1() {
        User userParam = new User();
        userParam.setId("1");
        userParam.setUserId("10001");
        User user = userDao.queryUserInfo(userParam);
        log.info("result2:{}", user);
    }
}