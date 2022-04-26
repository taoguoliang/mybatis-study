package cn.taoguoliang.study.mybatis.datasource.druid;

import cn.taoguoliang.study.mybatis.datasource.DataSourceFactory;
import lombok.extern.slf4j.Slf4j;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * <p>
 * druid 数据源工厂
 * </p>
 *
 * @author taogl
 * @since 2022/4/20 20:30
 **/
@Slf4j
public class DruidDataSourceFactory implements DataSourceFactory {

    private DataSource druidDataSource;

    @Override
    public DataSource getDataSource() {
        return druidDataSource;
    }

    @Override
    public void setProperties(Properties properties) {
        if (druidDataSource == null) {
            try {
                druidDataSource = com.alibaba.druid.pool.DruidDataSourceFactory.createDataSource(properties);
            } catch (Exception e) {
                log.error("error to creat datasource.", e);
            }
        }
    }
}
