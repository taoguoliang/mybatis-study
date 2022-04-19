package cn.taoguoliang.study.mybatis.binding;

import cn.taoguoliang.study.mybatis.dao.IUserDao;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

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
        MapperProxyFactory<IUserDao> iUserDaoMapperProxyFactory = new MapperProxyFactory<>(IUserDao.class);
        Map<String, String> sqlSqlSession = new HashMap<String, String>() {{
            put("cn.taoguoliang.study.mybatis.dao.IUserDao.queryUserNameById", "模拟执行 Mapper.xml 中 SQL 语句的操作：查询用户姓名");
        }};

        IUserDao iUserDao = iUserDaoMapperProxyFactory.newInstance(sqlSqlSession);
        String queryUserNameById = iUserDao.queryUserNameById("124");
        log.info("Result:{}", queryUserNameById);
    }
}