package cn.taoguoliang.study.mybatis.builder;

import java.util.HashMap;

/**
 * <p>
 * 参数表达式
 * </p>
 *
 * @author taogl
 * @since 2022/5/24 11:12
 **/
public class ParameterExpression extends HashMap<String, String> {

    public ParameterExpression(String content) {
        parse(content);
    }

    private void parse(String content) {
        int p = skipWS(content, 0);
        if (content.charAt(p) == '(') {
            expression(content, p + 1);
        } else {
            property(content, p);
        }
    }

    private int skipWS(String expression, int p) {
        for (int i = p; i < expression.length(); i++) {
            if (expression.charAt(i) > 0x20) {
                return i;
            }
        }
        return expression.length();
    }

    private void expression(String expression, int left) {
        int match = 1;
        int right = left + 1;
        while (match > 0) {
            if (expression.charAt(right) == ')') {
                match--;
            } else if (expression.charAt(right) == '(') {
                match++;
            }
            right++;
        }
        put("expression", expression.substring(left, right - 1));
        jdbcTypeOpt(expression, right);
    }

    private void property(String expression, int left) {
        if (left < expression.length()) {
            int right = skipUntil(expression, left, ",:");
            put("property", trimmedStr(expression, left, right));
            jdbcTypeOpt(expression, right);
        }
    }

    private int skipUntil(String expression, int p, final String endChars) {
        for (int i = p; i < expression.length(); i++) {
            char c = expression.charAt(i);
            if (endChars.indexOf(c) > -1) {
                return i;
            }
        }
        return expression.length();
    }

    private void jdbcTypeOpt(String expression, int p) {
        p = skipWS(expression, p);
        if (p < expression.length()) {
            if (expression.charAt(p) == ':') {
                jdbcType(expression, p + 1);
            } else if (expression.charAt(p) == ',') {
                option(expression, p + 1);
            } else {
                throw new RuntimeException("Parsing error in {" + expression + "} in position " + p);
            }
        }
    }

    private void jdbcType(String expression, int p) {
        int left = skipWS(expression, p);
        int right = skipUntil(expression, left, ",");
        if (right > left) {
            put("jdbcType", trimmedStr(expression, left, right));
        } else {
            throw new RuntimeException("Parsing error in {" + expression + "} in position " + p);
        }
        option(expression, right + 1);
    }

    private void option(String expression, int p) {
        int left = skipWS(expression, p);
        if (left < expression.length()) {
            int right = skipUntil(expression, left, "=");
            String name = trimmedStr(expression, left, right);
            left = right + 1;
            right = skipUntil(expression, left, ",");
            String value = trimmedStr(expression, left, right);
            put(name, value);
            option(expression, right + 1);
        }
    }

    private String trimmedStr(String str, int start, int end) {
        while (str.charAt(start) <= 0x20) {
            start++;
        }
        while (str.charAt(end - 1) <= 0x20) {
            end--;
        }
        return start >= end ? "" : str.substring(start, end);
    }
}
