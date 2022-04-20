package cn.taoguoliang.study.mybatis.builder;

import cn.taoguoliang.study.mybatis.session.Configuration;

/**
 * <p>
 * base builder
 * </p>
 *
 * @author taogl
 * @since 2022/4/20 11:01
 **/
public abstract class BaseBuilder {

    protected Configuration configuration;

    public BaseBuilder(Configuration configuration) {
        this.configuration = configuration;
    }
}
