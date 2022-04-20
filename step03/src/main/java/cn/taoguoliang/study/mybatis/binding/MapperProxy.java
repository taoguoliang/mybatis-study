package cn.taoguoliang.study.mybatis.binding;

import cn.taoguoliang.study.mybatis.mapping.MappedStatement;
import cn.taoguoliang.study.mybatis.session.SqlSession;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * <p>
 * mapper代理
 * </p>
 *
 * @author taogl
 * @since 2022/4/19 14:47
 **/
public class MapperProxy<T> implements InvocationHandler {

    private final SqlSession sqlSession;
    private final Class<T> mapperInterface;

    public MapperProxy(SqlSession sqlSession, Class<T> mapperInterface) {
        this.sqlSession = sqlSession;
        this.mapperInterface = mapperInterface;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (Object.class.equals(method.getDeclaringClass())) {
            return method.invoke(this, args);
        } else {
            String methodKey = this.mapperInterface.getName() + "." + method.getName();
            MappedStatement mappedStatement = sqlSession.getConfiguration().getMappedStatement(methodKey);
            return "你被代理了!\n" +
                    "方法:" + methodKey +
                    "\n入参:" + Arrays.toString(args) +
                    "\n待执行SQL:" +
                    mappedStatement.getSource();
        }
    }
}
