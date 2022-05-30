package cn.taoguoliang.study.mybatis.binding;

/**
 * <p>
 * 异常
 * </p>
 *
 * @author taogl
 * @since 2022/4/20 21:54
 **/
public class BindingException extends RuntimeException {
    public BindingException(String message) {
        super(message);
    }
}
