package cn.taoguoliang.study.mybatis.mapping;

import cn.taoguoliang.study.mybatis.transaction.TransactionFactory;
import lombok.Builder;
import lombok.Getter;

import javax.sql.DataSource;

/**
 * <p>
 * mybatis环境
 * </p>
 *
 * @author taogl
 * @since 2022/4/20 20:44
 **/
@Builder
@Getter
public final class Environment {
    private final String id;
    private final TransactionFactory transactionFactory;
    private final DataSource dataSource;
}
