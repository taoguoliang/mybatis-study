package cn.taoguoliang.study.mybatis.binding;

import cn.taoguoliang.study.mybatis.dao.IUserDao;
import cn.taoguoliang.study.mybatis.io.Resources;
import cn.taoguoliang.study.mybatis.pojo.dto.User;
import cn.taoguoliang.study.mybatis.session.SqlSession;
import cn.taoguoliang.study.mybatis.session.SqlSessionFactory;
import cn.taoguoliang.study.mybatis.session.SqlSessionFactoryBuilder;
import lombok.extern.slf4j.Slf4j;
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

    @Test
    public void test() {
        Reader reader = Resources.getResourceAsReader("mybatis-mappers.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);

        SqlSession sqlSession = sqlSessionFactory.openSession();

        IUserDao schoolDao = sqlSession.getMapper(IUserDao.class);

        for (int i = 0; i < 50; i++) {
            User user = schoolDao.queryUser("1");
            log.info("result:{}", user);
        }
    }
}