package cn.taoguoliang.study.mybatis.binding;

import cn.taoguoliang.study.mybatis.dao.ISchoolDao;
import cn.taoguoliang.study.mybatis.io.Resources;
import cn.taoguoliang.study.mybatis.pojo.dto.Admin;
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

        ISchoolDao schoolDao = sqlSession.getMapper(ISchoolDao.class);

        for (int i = 0; i < 50; i++) {
            Admin schoolName = schoolDao.querySchoolName("1");
            log.info("result:{}", schoolName);
        }
    }
}