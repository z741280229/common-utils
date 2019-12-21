package com.zeno.utils;

import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: common-utils
 * @description: 参数工具类
 * @author: Zeno
 * @create: 2019-12-21 16:46
 **/
public class ParameterUtil {

    /**
     * 如果传入对象直接为空，那么直接返回true，如果对象当中只要存在某个字段为null，就返回true，其余返回false
     *
     * @param obj
     * @return
     */
    public static boolean parameterIsNull(Object obj) {
        if (obj == null) {
            return true;
        }
        try {
            for (Field f : obj.getClass().getDeclaredFields()) {
                if (isNull(obj, f)) {
                    return true;
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return false;
    }


    /**
     * 判断指定参数是否为空
     *
     * @param obj
     * @param params
     * @return
     */
    public static boolean parameterIsNull(Object obj, String... params) {
        if (obj == null) {
            return true;
        }
        try {
            Field field = null;
            for (String param : params) {
                field = obj.getClass().getDeclaredField(param);
                if (isNull(obj, field)) {
                    return true;
                }
            }
        } catch (IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 返回参数为空的所有字段
     *
     * @param obj
     * @return
     */
    public static List<String> findNullParam(Object obj) {
        List<String> params = new ArrayList<>();
        for (Field f : obj.getClass().getDeclaredFields()) {
            try {
                f.setAccessible(true);
                if (f.get(obj) == null) {
                    params.add(f.getName());
                    continue;
                }
                if (StringUtils.isBlank(f.get(obj).toString())) {
                    params.add(f.getName());
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return params;
    }

    /**
     * 判断指定参数是否为空，返回为空的字段名
     *
     * @param obj
     * @param params
     * @return
     */
    public static List<String> findNullParam(Object obj, String... params) {
        List<String> ps = new ArrayList<>();
        try {
            Field field = null;
            for (String param : params) {
                field = obj.getClass().getDeclaredField(param);
                field.setAccessible(true);
                if (field.get(obj) == null) {
                    ps.add(param);
                    continue;
                }
                if (StringUtils.isBlank(field.get(obj).toString())) {
                    ps.add(param);
                }
            }
        } catch (IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
        }
        return ps;
    }


    /**
     * 判断单个参数是否为空
     *
     * @param obj
     * @param f
     * @return
     * @throws IllegalAccessException
     */
    private static boolean isNull(Object obj, Field f) throws IllegalAccessException {
        f.setAccessible(true);
        if (f.get(obj) == null) {
            //System.out.print(f.getName() + ":");
            //System.out.println(f.get(obj));
            return true;
        }
        if (StringUtils.isBlank(f.get(obj).toString())) {
            return true;
        }
        return false;
    }

}
