package cn.taoguoliang.study.mybatis.mapping;

/**
 * <p>
 *     sql 命令类型
 * </p>
 *
 * @author taogl
 * @since 2022/4/20 11:22
 **/
public enum SqlCommandType {
  /**
   * 这步只关注这些
   */
  INSERT, UPDATE, DELETE, SELECT;

  public static SqlCommandType valueOfIgnoreCase(String command) {
    return SqlCommandType.valueOf(command.toUpperCase());
  }
}