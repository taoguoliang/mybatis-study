package cn.taoguoliang.study.mybatis.datasource.pooled;

import cn.taoguoliang.study.mybatis.datasource.unpooled.UnPooledDataSourceFactory;

/**
 * <p>
 * 池化数据源工厂
 * </p>
 *
 * @author taogl
 * @since 2022/4/26 20:30
 **/
public class PooledDataSourceFactory extends UnPooledDataSourceFactory {

    public PooledDataSourceFactory() {
        this.dataSource = new PooledDataSource();
    }
}
