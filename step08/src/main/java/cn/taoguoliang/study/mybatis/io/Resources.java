package cn.taoguoliang.study.mybatis.io;

import cn.hutool.core.util.ClassLoaderUtil;
import lombok.experimental.UtilityClass;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

/**
 * <p>
 * Resources
 * </p>
 *
 * @author taogl
 * @since 2022/4/20 11:25
 **/
@UtilityClass
public class Resources {

    public Class<?> classForName(String name) throws ClassNotFoundException {
        return ClassLoader.getSystemClassLoader().loadClass(name);
    }

    public static Reader getResourceAsReader(String location) {
        InputStream resourceAsStream = ClassLoaderUtil.getClassLoader().getResourceAsStream(location);
        assert resourceAsStream != null;
        return new InputStreamReader(resourceAsStream);
    }
}
