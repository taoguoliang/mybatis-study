package cn.taoguoliang.study.mybatis.scripting.xmltags;

import cn.taoguoliang.study.mybatis.builder.BaseBuilder;
import cn.taoguoliang.study.mybatis.mapping.SqlSource;
import cn.taoguoliang.study.mybatis.scripting.defaults.RawSqlSource;
import cn.taoguoliang.study.mybatis.session.Configuration;
import org.dom4j.Element;

import java.util.ArrayList;
import java.util.List;

public class XMLScriptBuilder extends BaseBuilder {

    private final Element element;

    private final Class<?> parameterType;

    public XMLScriptBuilder(Configuration configuration, Element element, Class<?> parameterType) {
        super(configuration);
        this.element = element;
        this.parameterType = parameterType;
    }

    public SqlSource parseScriptNode() {
        List<SqlNode> contents = parseDynamicTags(element);
        MixedSqlNode rootSqlNode = new MixedSqlNode(contents);
        return new RawSqlSource(configuration, rootSqlNode, parameterType);
    }

    List<SqlNode> parseDynamicTags(Element element) {
        List<SqlNode> contents = new ArrayList<>();
        // element.getText 拿到 SQL
        String data = element.getText();
        contents.add(new StaticTextSqlNode(data));
        return contents;
    }

}