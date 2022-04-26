package cn.taoguoliang.study.mybatis.binding;

import cn.taoguoliang.study.mybatis.annotations.Flush;
import cn.taoguoliang.study.mybatis.mapping.MappedStatement;
import cn.taoguoliang.study.mybatis.mapping.SqlCommandType;
import cn.taoguoliang.study.mybatis.reflection.ParamNameResolver;
import cn.taoguoliang.study.mybatis.session.Configuration;
import cn.taoguoliang.study.mybatis.session.SqlSession;

import java.lang.reflect.Method;

/**
 * <p>
 * mapper,处理参数
 * </p>
 *
 * @author taogl
 * @since 2022/4/20 21:45
 **/
public class MapperMethod {
    private final SqlCommand command;
    private final MethodSignature method;

    public MapperMethod(Class<?> mapperInterface, Method method, Configuration config) {
        this.command = new SqlCommand(config, mapperInterface, method);
        this.method = new MethodSignature(config, mapperInterface, method);
    }

    public Object execute(SqlSession sqlSession, Object[] args) {
        Object result = null;
        switch (command.getType()) {
            case INSERT: {
                Object param = method.convertArgsToSqlCommandParam(args);
//                result = rowCountResult(sqlSession.insert(command.getName(), param));
                break;
            }
            case UPDATE: {
                Object param = method.convertArgsToSqlCommandParam(args);
//                result = rowCountResult(sqlSession.update(command.getName(), param));
                break;
            }
            case DELETE: {
                Object param = method.convertArgsToSqlCommandParam(args);
//                result = rowCountResult(sqlSession.delete(command.getName(), param));
                break;
            }
            case SELECT:
//                if (method.returnsVoid() && method.hasResultHandler()) {
//                    executeWithResultHandler(sqlSession, args);
//                    result = null;
//                } else if (method.returnsMany()) {
//                    result = executeForMany(sqlSession, args);
//                } else if (method.returnsMap()) {
//                    result = executeForMap(sqlSession, args);
//                } else if (method.returnsCursor()) {
//                    result = executeForCursor(sqlSession, args);
//                } else {
                    Object param = method.convertArgsToSqlCommandParam(args);
                    result = sqlSession.selectOne(command.getName(), param);
//                    if (method.returnsOptional()
//                            && (result == null || !method.getReturnType().equals(result.getClass()))) {
//                        result = Optional.ofNullable(result);
//                    }
//                }
                break;
            case FLUSH:
//                result = sqlSession.flushStatements();
                break;
            default:
                throw new BindingException("Unknown execution method for: " + command.getName());
        }
//        if (result == null && method.getReturnType().isPrimitive() && !method.returnsVoid()) {
//            throw new BindingException("Mapper method '" + command.getName()
//                    + " attempted to return null from a method with a primitive return type (" + method.getReturnType() + ").");
//        }
        return result;
    }

    public static class SqlCommand {

        private final String name;
        private final SqlCommandType type;

        public SqlCommand(Configuration configuration, Class<?> mapperInterface, Method method) {
            final String methodName = method.getName();
            final Class<?> declaringClass = method.getDeclaringClass();
            MappedStatement ms = resolveMappedStatement(mapperInterface, methodName, declaringClass,
                    configuration);
            if (ms == null) {
                if (method.getAnnotation(Flush.class) != null) {
                    name = null;
                    type = SqlCommandType.FLUSH;
                } else {
                    throw new BindingException("Invalid bound statement (not found): "
                            + mapperInterface.getName() + "." + methodName);
                }
            } else {
                name = ms.getId();
                type = ms.getSqlCommandType();
                if (type == SqlCommandType.UNKNOWN) {
                    throw new BindingException("Unknown execution method for: " + name);
                }
            }
        }

        public String getName() {
            return name;
        }

        public SqlCommandType getType() {
            return type;
        }

        private MappedStatement resolveMappedStatement(Class<?> mapperInterface, String methodName,
                                                       Class<?> declaringClass, Configuration configuration) {
            String statementId = mapperInterface.getName() + "." + methodName;
            if (configuration.hasStatement(statementId)) {
                return configuration.getMappedStatement(statementId);
            } else if (mapperInterface.equals(declaringClass)) {
                return null;
            }
            for (Class<?> superInterface : mapperInterface.getInterfaces()) {
                if (declaringClass.isAssignableFrom(superInterface)) {
                    MappedStatement ms = resolveMappedStatement(superInterface, methodName,
                            declaringClass, configuration);
                    if (ms != null) {
                        return ms;
                    }
                }
            }
            return null;
        }
    }

    public static class MethodSignature {
        private final ParamNameResolver paramNameResolver;

        public MethodSignature(Configuration configuration, Class<?> mapperInterface, Method method) {
            this.paramNameResolver = new ParamNameResolver(configuration, method);
        }

        public Object convertArgsToSqlCommandParam(Object[] args) {
            return paramNameResolver.getNamedParams(args);
        }
    }
}
