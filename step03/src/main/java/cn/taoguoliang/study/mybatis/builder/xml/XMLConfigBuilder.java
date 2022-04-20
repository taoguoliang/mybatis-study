package cn.taoguoliang.study.mybatis.builder.xml;

import cn.taoguoliang.study.mybatis.builder.BaseBuilder;
import cn.taoguoliang.study.mybatis.io.Resources;
import cn.taoguoliang.study.mybatis.mapping.MappedStatement;
import cn.taoguoliang.study.mybatis.mapping.SqlCommandType;
import cn.taoguoliang.study.mybatis.session.Configuration;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.xml.sax.InputSource;

import java.io.Reader;
import java.util.List;

/**
 * <p>
 * xml builder
 * </p>
 *
 * @author taogl
 * @since 2022/4/20 11:00
 **/
public class XMLConfigBuilder extends BaseBuilder {

    private Element root;

    public XMLConfigBuilder(Reader reader) {
        // 1. 调用父类初始化Configuration
        super(new Configuration());
        // 2. dom4j 处理 xml
        SAXReader saxReader = new SAXReader();
        try {
            Document document = saxReader.read(new InputSource(reader));
            root = document.getRootElement();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    public Configuration parse() {
        try {
            // 解析映射器
            mapperElement(root.element("mappers"));
        } catch (Exception e) {
            throw new RuntimeException("Error parsing SQL Mapper Configuration. Cause: " + e, e);
        }
        return configuration;
    }

    private void mapperElement(Element mappers) throws Exception {
        List<Element> mapperList = mappers.elements("mapper");
        SAXReader saxReader = new SAXReader();
        for (Element e : mapperList) {
            String location = e.attribute("location").getText();
            Document document = saxReader.read(Resources.getResourceAsReader(location));
            String namespace = document.getRootElement().attribute("namespace").getValue();
            for (Element element : document.getRootElement().elements()) {
                String id = element.attribute("id").getText();
                String type = element.getName();
                SqlCommandType sqlCommandType = SqlCommandType.valueOfIgnoreCase(type);
                // 解析处理，具体参照源码
                MappedStatement mappedStatement = new MappedStatement.Builder(configuration, namespace + "." + id, sqlCommandType).source(element.getText()).build();
                // 添加解析 SQL
                configuration.addMappedStatement(mappedStatement);
            }

            // 注册Mapper映射器
            configuration.addMapper(Resources.classForName(namespace));
        }
    }
}
