package cn.taoguoliang.study.mybatis.session.defaults;

import cn.taoguoliang.study.mybatis.executor.Executor;
import cn.taoguoliang.study.mybatis.mapping.Environment;
import cn.taoguoliang.study.mybatis.session.Configuration;
import cn.taoguoliang.study.mybatis.session.SqlSession;
import cn.taoguoliang.study.mybatis.session.SqlSessionFactory;
import cn.taoguoliang.study.mybatis.session.TransactionIsolationLevel;
import cn.taoguoliang.study.mybatis.transaction.Transaction;
import cn.taoguoliang.study.mybatis.transaction.TransactionFactory;

/**
 * <p>
 * session factory
 * </p>
 *
 * @author taogl
 * @since 2022/4/19 21:45
 **/
public class DefaultSqlSessionFactory implements SqlSessionFactory {

    private final Configuration configuration;

    public DefaultSqlSessionFactory(Configuration configuration) {
        this.configuration = configuration;
    }

    /**
     * 打开一个 session
     * @return SqlSession
     */
    @Override
    public SqlSession openSession() {
        final Environment environment = configuration.getEnvironment();
        TransactionFactory transactionFactory = environment.getTransactionFactory();
        Transaction tx = transactionFactory.newTransaction(configuration.getEnvironment().getDataSource(), TransactionIsolationLevel.READ_COMMITTED, false);
        // 创建执行器
        final Executor executor = configuration.newExecutor(tx);
        // 创建DefaultSqlSession
        return new DefaultSqlSession(configuration, executor);
    }
}
