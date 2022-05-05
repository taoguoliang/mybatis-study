package cn.taoguoliang.study.mybatis.reflection;

import cn.taoguoliang.study.mybatis.reflection.factory.DefaultObjectFactory;
import cn.taoguoliang.study.mybatis.reflection.factory.ObjectFactory;
import cn.taoguoliang.study.mybatis.reflection.wrapper.DefaultObjectWrapperFactory;
import cn.taoguoliang.study.mybatis.reflection.wrapper.ObjectWrapperFactory;

public final class SystemMetaObject {

  public static final ObjectFactory DEFAULT_OBJECT_FACTORY = new DefaultObjectFactory();
  public static final ObjectWrapperFactory DEFAULT_OBJECT_WRAPPER_FACTORY = new DefaultObjectWrapperFactory();
  public static final MetaObject NULL_META_OBJECT = MetaObject.forObject(NullObject.class, DEFAULT_OBJECT_FACTORY, DEFAULT_OBJECT_WRAPPER_FACTORY, new DefaultReflectorFactory());

  private SystemMetaObject() {
    // Prevent Instantiation of Static Class
  }

  private static class NullObject {
  }

  public static MetaObject forObject(Object object) {
    return MetaObject.forObject(object, DEFAULT_OBJECT_FACTORY, DEFAULT_OBJECT_WRAPPER_FACTORY, new DefaultReflectorFactory());
  }

}
