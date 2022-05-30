package cn.taoguoliang.study.mybatis.scripting.xmltags;

/**
 * sql节点
 *
 * @author taogl
 * @since 2022/5/24 10:42
 **/
public interface SqlNode {
  boolean apply(DynamicContext context);
}