package cn.taoguoliang.study.mybatis.datasource;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * <p>
 * 数据源工厂
 * </p>
 *
 * @author taogl
 * @since 2022/4/20 20:29
 **/
public interface DataSourceFactory {

    DataSource getDataSource();

    void setProperties(Properties properties);
}
