package com.jenwis.base.util;

import java.lang.reflect.Method;

/**
 * Created by zhengyuji on 15/10/31.
 */
public class FindMethodUtil {
    public static Method getMethod(String className, String methodName, Class<?>... parameterTypes) {
        Method method = null;
        try {
            Class<?> c = Class.forName(className);
            method = c.getDeclaredMethod(methodName, parameterTypes);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        return method;
    }
}
