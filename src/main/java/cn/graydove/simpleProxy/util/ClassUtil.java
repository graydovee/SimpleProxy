package cn.graydove.simpleProxy.util;

import cn.graydove.simpleProxy.pojo.Param;

import java.util.List;

public final class ClassUtil {
    private ClassUtil() {
    }

    public static Object[] analyseParams(List<Param> params) {
        if (params == null) {
            return null;
        }
        Object[] args = new Object[params.size()];
        int i = 0;
        for (Param param : params) {
            if ("String".equals(param.getClassName())) {
                args[i] = param.getValue();
            } else if ("Integer".equals(param.getClassName()) || "int".equals(param.getClassName())) {
                args[i] = Integer.parseInt(param.getValue());
            } else if ("Long".equals(param.getClassName()) || "long".equals(param.getClassName())) {
                args[i] = Long.parseLong(param.getValue());
            } else if ("Double".equals(param.getClassName()) || "double".equals(param.getClassName())) {
                args[i] = Double.parseDouble(param.getValue());
            } else if ("Float".equals(param.getClassName()) || "float".equals(param.getClassName())) {
                args[i] = Float.parseFloat(param.getValue());
            } else if ("Byte".equals(param.getClassName()) || "byte".equals(param.getClassName())) {
                args[i] = Byte.parseByte(param.getValue());
            } else if ("Boolean".equals(param.getClassName()) || "boolean".equals(param.getClassName())) {
                args[i] = Boolean.parseBoolean(param.getValue());
            } else if ("Short".equals(param.getClassName()) || "short".equals(param.getClassName())) {
                args[i] = Short.parseShort(param.getValue());
            }
            ++i;
        }
        return args;
    }

    public static Class getBasicClass(Class c) {
        if (c == null) {
            return null;
        }
        if (Integer.class == c) {
            return int.class;
        } else if (Double.class == c) {
            return double.class;
        } else if (Long.class == c) {
            return long.class;
        } else if (Float.class == c) {
            return float.class;
        } else if (Short.class == c) {
            return short.class;
        } else if (Boolean.class == c) {
            return boolean.class;
        } else if (Byte.class == c) {
            return byte.class;
        }
        return null;
    }

}
