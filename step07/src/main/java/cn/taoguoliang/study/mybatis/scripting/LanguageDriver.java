package cn.taoguoliang.study.mybatis.scripting;

import cn.taoguoliang.study.mybatis.mapping.SqlSource;
import cn.taoguoliang.study.mybatis.session.Configuration;
import org.dom4j.Element;

/**
 * <p>
 *   脚本语言驱动器
 * </p>
 * @author taogl
 * @since 2022/5/24 10:33
 **/
public interface LanguageDriver {

    SqlSource createSqlSource(Configuration configuration, Element script, Class<?> parameterType);

}