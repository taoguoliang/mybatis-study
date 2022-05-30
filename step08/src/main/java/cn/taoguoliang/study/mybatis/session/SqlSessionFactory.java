package cn.taoguoliang.study.mybatis.session;

/**
 * <p>
 * session factory
 * </p>
 *
 * @author taogl
 * @since 2022/4/19 21:45
 **/
public interface SqlSessionFactory {

    /**
     * 打开一个 session
     * @return SqlSession
     */
    SqlSession openSession();
}
