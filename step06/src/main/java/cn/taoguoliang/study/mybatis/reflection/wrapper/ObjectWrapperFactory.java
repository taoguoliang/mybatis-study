package cn.taoguoliang.study.mybatis.reflection.wrapper;

import cn.taoguoliang.study.mybatis.reflection.MetaObject;

public interface ObjectWrapperFactory {

  boolean hasWrapperFor(Object object);

  ObjectWrapper getWrapperFor(MetaObject metaObject, Object object);

}