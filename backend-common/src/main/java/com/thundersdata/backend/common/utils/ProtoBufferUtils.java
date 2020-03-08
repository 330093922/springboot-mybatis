package com.thundersdata.backend.common.utils;

import com.google.protobuf.GeneratedMessageV3;
import com.thundersdata.backend.common.enums.BoolEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @Description: grpc消息工具类
 */
public class ProtoBufferUtils {
    private static final Logger log = LoggerFactory.getLogger(ProtoBufferUtils.class);

    private ProtoBufferUtils() {

    }

    private static HashSet<String> gRpcMethodNameSet = new HashSet<>();

    static {
        gRpcMethodNameSet.add("com.google.protobuf.DoubleValue");
        gRpcMethodNameSet.add("com.google.protobuf.FloatValue");
        gRpcMethodNameSet.add("com.google.protobuf.Int64Value");
        gRpcMethodNameSet.add("com.google.protobuf.UInt64Value");
        gRpcMethodNameSet.add("com.google.protobuf.Int32Value");
        gRpcMethodNameSet.add("com.google.protobuf.UInt32Value");
        gRpcMethodNameSet.add("com.google.protobuf.BoolValue");
        gRpcMethodNameSet.add("com.google.protobuf.StringValue");
        gRpcMethodNameSet.add("com.google.protobuf.BytesValue");
    }

    /**
     * java对象转消息对象
     *
     * @param model   java对象
     * @param pbClass 消息类型class
     * @param <T>     消息类型
     * @return 消息对象
     */
    public static <T> T toProtoBuffer(Object model, Class<T> pbClass) {
        if (!GeneratedMessageV3.class.isAssignableFrom(pbClass)) {
            throw new RuntimeException("Not ProtoBuffer message type");
        }

        T pbObject = null;

        if (model != null) {
            try {
                Object pbBuilder = pbClass.getDeclaredMethod("newBuilder").invoke(null);
                Field[] pbFields = pbClass.getDeclaredFields();
                if (pbFields != null && pbFields.length > 0) {
                    for (Field pbField : pbFields) {
                        String fieldName = pbField.getName().substring(0, pbField.getName().length() - 1);
                        String name = fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
                        Class<?> fieldType = pbField.getType();
                        if (pbField.getType() == Object.class) {
                            fieldType = String.class;
                        }

                        try {
                            Method modelGetMethod = model.getClass().getMethod("get" + name);
                            Object value = modelGetMethod.invoke(model);
                            if (value != null) {
                                Method pbBuilderSetMethod = pbBuilder.getClass().getMethod("set" + name, fieldType);
                                if (value instanceof BigDecimal) {
                                    value = ((BigDecimal) value).floatValue();
                                }

                                if (value.equals(true)) {
                                    value = 1;
                                } else if (value.equals(false)) {
                                    value = 0;
                                }

                                pbBuilderSetMethod.invoke(pbBuilder, value);
                            }
                        } catch (NoSuchMethodException | IllegalArgumentException ignored) {
                        }
                    }
                }
                pbObject = (T) pbBuilder.getClass().getDeclaredMethod("build").invoke(pbBuilder);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return pbObject;
    }

    private static String upperFirstChar(String name) {
        if (name != null && name.length() != 0) {
            String firstUpperStr = name.substring(0, 1).toUpperCase(Locale.ENGLISH);
            return firstUpperStr + name.substring(1);
        } else {
            return name;
        }
    }

    private static String humpMethodName(String prefix, String... methodParam) {
        Objects.requireNonNull(prefix);
        List<String> methodParamList = methodParam != null ? Arrays.asList(methodParam) : null;
        if (methodParam == null) {
            return prefix;
        }

        StringBuilder result = new StringBuilder(prefix);
        for (String param : methodParamList) {
            result.append(upperFirstChar(param));
        }

        return result.toString();
    }

    private static Map<String, FieldProperty> fieldPropertyMap(Class c) {
        Field[] fields = c.getDeclaredFields();
        return Arrays.stream(fields).map(f -> {
            FieldProperty fieldProperty = new FieldProperty();

            fieldProperty.setFieldName(f.getName());
            fieldProperty.setHasMethodName(humpMethodName("has", f.getName()));
            fieldProperty.setGetMethodName(humpMethodName("get", f.getName()));
            fieldProperty.setSetMethodName(humpMethodName("set", f.getName()));

            return fieldProperty;
        }).collect(Collectors.toMap(FieldProperty::getGetMethodName, Function.identity()));
    }

    private static List<Method> filterMethodStartsWithGet(Method... requestMethods) {
        if (requestMethods == null) {
            return Collections.emptyList();
        }

        return Arrays.stream(requestMethods).filter(method -> method.getName().startsWith("get")).collect(Collectors.toList());
    }

    private static Object invokeStaticMethod(String className, String methodName, Object... methodParam) {
        List<Object> methodParamList = methodParam != null ? Arrays.asList(methodParam) : null;
        Class<?>[] paramTypeClass = null;

        if (methodParamList != null) {
            paramTypeClass = new Class[methodParamList.size()];
            List<Class<?>> paramClassList = methodParamList.stream().map(Object::getClass).collect(Collectors.toList());
            paramClassList.toArray(paramTypeClass);
        }

        try {
            Class<?> c = Class.forName(className);
            return c.getDeclaredMethod(methodName, paramTypeClass).invoke(null, methodParam);
        } catch (Exception e) {
            log.error("invoke static method fail");
            return null;
        }
    }

    /**
     * 业务类字段类型  ==> rpc类实例域定义的类型
     *
     * @param rpcGetMethod        rpc Method类
     * @param businessMethodValue 业务字段值
     * @return 符合rpc字段类型的值
     */
    private static Object handleValueType(Method rpcGetMethod, Object businessMethodValue) {
        if (!rpcGetMethod.getReturnType().isPrimitive()) {
            String methodReturnTypeName = rpcGetMethod.getGenericReturnType().getTypeName();
            if (gRpcMethodNameSet.contains(methodReturnTypeName)) {
                businessMethodValue = invokeStaticOfMethod(methodReturnTypeName, businessMethodValue);
            }
        }

        return businessMethodValue;
    }

    private static Object invokeStaticOfMethod(String className, Object value) {

        try {
            Class<?> c = Class.forName(className);
            Method getValueMethod = c.getDeclaredMethod("getValue");
            value = convertPackTypeToBasicType(value.getClass(), getValueMethod.getReturnType(), value);
            return c.getDeclaredMethod("of", getValueMethod.getReturnType()).invoke(null, value);
        } catch (Exception e) {
            log.error("invoke static method fail", e);
            return null;
        }
    }

    public static <T extends GeneratedMessageV3.Builder> void copyFromBusinessModel(Object businessModel, T rpcModel) {
        Objects.requireNonNull(businessModel);
        Objects.requireNonNull(rpcModel);
        Class<?> rpcClass = rpcModel.getClass();
        Class<?> businessClass = businessModel.getClass();

        Map<String, FieldProperty> fieldMap = fieldPropertyMap(businessClass);
        for (Method rpcMethod : filterMethodStartsWithGet(rpcClass.getMethods())) {
            if (fieldMap.containsKey(rpcMethod.getName())) {
                try {
                    Method businessMethod = businessClass.getMethod(rpcMethod.getName());
                    if (businessMethod != null && businessMethod.invoke(businessModel) != null) {
                        Object businessValue = handleValueType(rpcMethod, businessMethod.invoke(businessModel));
                        if (businessValue != null) {
                            Method setFieldMethod = rpcClass.getMethod(fieldMap.get(rpcMethod.getName()).getSetMethodName(), rpcMethod.getReturnType());
                            businessValue = convertBusinessModelValueType(rpcMethod.getReturnType(), businessMethod.getReturnType(), businessValue);
                            setFieldMethod.invoke(rpcModel, businessValue);
                        }
                    }
                } catch (Exception e) {
                    log.error("copy properties failure", e);
                }
            }
        }
    }

    public static <T extends GeneratedMessageV3.Builder> void copyFromRpcModel(T rpcModel, Object businessModel) {
        Objects.requireNonNull(businessModel);
        Objects.requireNonNull(rpcModel);
        Class<?> rpcClass = rpcModel.getClass();
        Class<?> businessClass = businessModel.getClass();

        Map<String, FieldProperty> map = fieldPropertyMap(businessClass);
        for (Method method : filterMethodStartsWithGet(rpcClass.getDeclaredMethods())) {
            if (map.containsKey(method.getName())) {
                Object value = null;
                if (gRpcMethodNameSet.contains(method.getGenericReturnType().getTypeName())) {
                    if (hasSetFieldValue(map.get(method.getName()).getHasMethodName(), rpcModel)) {
                        value = handleRpcModelType(rpcModel, method.getName());
                    }
                } else {
                    value = invokeMethod(method, rpcModel);
                }

                if (value != null && !"".equals(value)) {
                    try {
                        Method businessModelGetMethod = businessClass.getDeclaredMethod(method.getName());
                        Method businessSetMethod = businessClass.getDeclaredMethod(map.get(method.getName()).getSetMethodName(), businessModelGetMethod.getReturnType());
                        value = convertRpcModelValueType(businessModelGetMethod.getReturnType(), method.getReturnType(), value);
                        businessSetMethod.invoke(businessModel, value);
                    } catch (Exception e) {
                        log.error("copy properties failure", e);
                    }
                }
            }
        }
    }

    private static Object convertBusinessModelValueType(Class<?> targetClass, Class<?> sourceClass, Object value) {
        if (BigDecimal.class.equals(sourceClass)) {
            if (matchFloatClass(targetClass)) {
                return ((BigDecimal) value).floatValue();
            }

            if (matchDoubleClass(targetClass)) {
                return ((BigDecimal) value).doubleValue();
            }
        }

        if (matchIntegerClass(targetClass) && matchBooleanClass(sourceClass)) {
            return Boolean.TRUE.equals(value) ? BoolEnum.YES.getValue() : BoolEnum.NO.getValue();
        }

        return value;
    }

    /**
     * 包装类 ==> 基本类型
     * 针对{@link BigDecimal} --> {@link Float} or {@link Double}
     *
     * @param packClass  包装类
     * @param basicClass 基本类型
     * @param value      要操作的值
     * @return 转换类型后的值
     */
    private static Object convertPackTypeToBasicType(Class<?> packClass, Class<?> basicClass, Object value) {
        if (BigDecimal.class.equals(packClass)) {
            if (matchFloatClass(basicClass)) {
                return ((BigDecimal) value).floatValue();
            }

            if (matchDoubleClass(basicClass)) {
                return ((BigDecimal) value).doubleValue();
            }
        }

        return value;
    }

    private static Object convertRpcModelValueType(Class<?> targetClass, Class<?> sourceClass, Object value) {
        if (BigDecimal.class.equals(targetClass)) {
            if (matchFloatClass(sourceClass)) {
                return BigDecimal.valueOf((Float) value);
            }

            if (matchDoubleClass(sourceClass)) {
                return BigDecimal.valueOf((Double) value);
            }
        }

        if (matchBooleanClass(targetClass) && matchIntegerClass(sourceClass)) {
            return BoolEnum.YES.getValue().equals(value);
        }

        return value;
    }

    private static Object invokeMethod(Method method, Object obj, Object... args) {
        Object result = null;
        try {
            result = method.invoke(obj, args);
        } catch (Exception e) {
            log.error("invoke method failure", e);
        }

        return result;
    }

    /**
     * rpc 类型  ===>  java类型
     *
     * @param rpcModel   rpc类
     * @param methodName 方法名称
     * @return 适合java类型的值
     */
    private static Object handleRpcModelType(Object rpcModel, String methodName) {
        try {
            Method rpcModelGetValueMethod = rpcModel.getClass().getDeclaredMethod(methodName);
            Object fieldValue = rpcModelGetValueMethod.invoke(rpcModel);
            Method fieldMethod = Class.forName(rpcModelGetValueMethod.getGenericReturnType().getTypeName()).getDeclaredMethod("getValue");
            return fieldMethod.invoke(fieldValue);
        } catch (Exception e) {
            log.error("invoke get rpc value failure", e);
            return null;
        }
    }

    private static boolean hasSetFieldValue(String methodName, Object rpcModel) {
        try {
            return (boolean) rpcModel.getClass().getDeclaredMethod(methodName).invoke(rpcModel);
        } catch (Exception e) {
            log.error("invoke has type method failure", e);
            return false;
        }
    }

    private static boolean matchFloatClass(Class c) {
        return Float.class.equals(c) || float.class.equals(c);
    }

    private static boolean matchBooleanClass(Class c) {
        return Boolean.class.equals(c) || boolean.class.equals(c);
    }

    private static boolean matchDoubleClass(Class c) {
        return Double.class.equals(c) || double.class.equals(c);
    }

    private static boolean matchIntegerClass(Class c) {
        return Integer.class.equals(c) || int.class.equals(c);
    }
}
