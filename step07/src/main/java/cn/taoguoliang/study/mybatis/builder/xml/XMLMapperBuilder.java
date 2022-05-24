package cn.taoguoliang.study.mybatis.builder.xml;

import cn.taoguoliang.study.mybatis.builder.BaseBuilder;
import cn.taoguoliang.study.mybatis.io.Resources;
import cn.taoguoliang.study.mybatis.session.Configuration;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.Reader;
import java.util.List;

/**
 * <p>
 * xml
 * </p>
 *
 * @author taogl
 * @since 2022/5/24 10:12
 **/
public class XMLMapperBuilder extends BaseBuilder {

    private final Element element;

    private final String resource;

    private String currentNamespace;

    private static final SAXReader SAX_READER = new SAXReader();

    public XMLMapperBuilder(Reader reader, Configuration configuration, String resource) throws DocumentException {
        super(configuration);
        this.resource = resource;
        this.element = SAX_READER.read(reader).getRootElement();
    }

    /**
     * 解析
     */
    public void parse() throws Exception {
        // 如果当前资源没有加载过再加载，防止重复加载
        if (!configuration.isResourceLoaded(resource)) {
            configurationElement(element);
            // 标记一下，已经加载过了
            configuration.addLoadedResource(resource);
            // 绑定映射器到namespace
            configuration.addMapper(Resources.classForName(currentNamespace));
        }
    }

    // 配置mapper元素
    // <mapper namespace="org.mybatis.example.BlogMapper">
    //   <select id="selectBlog" parameterType="int" resultType="Blog">
    //    select * from Blog where id = #{id}
    //   </select>
    // </mapper>
    private void configurationElement(Element element) {
        // 1.配置namespace
        currentNamespace = element.attributeValue("namespace");
        if ("".equals(currentNamespace)) {
            throw new RuntimeException("Mapper's namespace cannot be empty");
        }

        // 2.配置select|insert|update|delete
        buildStatementFromContext(element.elements("select"));
    }

    // 配置select|insert|update|delete
    private void buildStatementFromContext(List<Element> list) {
        for (Element element : list) {
            final XMLStatementBuilder statementParser = new XMLStatementBuilder(configuration, element, currentNamespace);
            statementParser.parseStatementNode();
        }
    }
}
