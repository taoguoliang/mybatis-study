package cn.taoguoliang.study.mybatis.scripting.xmltags;

import cn.taoguoliang.study.mybatis.mapping.SqlSource;
import cn.taoguoliang.study.mybatis.scripting.LanguageDriver;
import cn.taoguoliang.study.mybatis.session.Configuration;
import org.dom4j.Element;

/**
 * <p>
 *     xml语言驱动
 * </p>
 *
 * @author taogl
 * @since 2022/5/24 10:35
 **/
public class XMLLanguageDriver implements LanguageDriver {

    @Override
    public SqlSource createSqlSource(Configuration configuration, Element script, Class<?> parameterType) {
        XMLScriptBuilder builder = new XMLScriptBuilder(configuration, script, parameterType);
        return builder.parseScriptNode();
    }

}