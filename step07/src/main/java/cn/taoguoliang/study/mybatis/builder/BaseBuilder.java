package cn.taoguoliang.study.mybatis.builder;

import cn.taoguoliang.study.mybatis.session.Configuration;
import cn.taoguoliang.study.mybatis.type.TypeAliasRegistry;
import cn.taoguoliang.study.mybatis.type.TypeHandlerRegistry;

/**
 * <p>
 * base builder
 * </p>
 *
 * @author taogl
 * @since 2022/4/20 11:01
 **/
public abstract class BaseBuilder {

    protected final Configuration configuration;

    protected final TypeAliasRegistry typeAliasRegistry;

    protected final TypeHandlerRegistry typeHandlerRegistry;

    public BaseBuilder(Configuration configuration) {
        this.configuration = configuration;
        this.typeAliasRegistry = configuration.getTypeAliasRegistry();
        this.typeHandlerRegistry = configuration.getTypeHandlerRegistry();
    }

    protected <T> Class<? extends T> resolveAlias(String alias) {
        return typeAliasRegistry.resolveAlias(alias);
    }

}
