package cn.taoguoliang.study.mybatis.session;

/**
 * <p>
 * SqlSession
 * </p>
 *
 * @author taogl
 * @since 2022/4/19 21:43
 **/
public interface SqlSession {

    <T> T selectOne(String statement);

    <T> T selectOne(String statement, Object args);

    <T> T getMapper(Class<T> cls);

    Configuration getConfiguration();
}
