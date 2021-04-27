package com.lls.common.utils;


import com.lls.common.annotation.ConfigResolve;
import com.lls.common.annotation.LoadProperty;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.Properties;

public class AnnotationUtil {

    public static <T> void loadProperty(T t) {
        Class<? extends Object> cls = t.getClass();
        boolean hasLoadPropertyAnno = cls.isAnnotationPresent(LoadProperty.class);
        if (hasLoadPropertyAnno) {
            //为属性赋值
            try {
                configField(cls, t);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static <T> void configField(Class<? extends Object> cls, T t) throws IOException {
        String filePath = cls.getAnnotation(LoadProperty.class).value();
        Properties properties = new Properties();
        InputStream is = AnnotationUtil.class.getClassLoader().getResourceAsStream(filePath);
        try {
            properties.load(is);
            Field[] fields = cls.getDeclaredFields();
            for (Field field : fields) {
                boolean hasConfigField = field.isAnnotationPresent(ConfigResolve.class);
                String fieldValue = null;
                if (hasConfigField) {
                    //获取注解的值
                    Object annoValue = field.getAnnotation(ConfigResolve.class).value();
                    fieldValue = properties.getProperty(annoValue.toString());
                } else {
                    //若属性上没有注解，则使用属性名作为key去配置文件中查找
                    fieldValue = properties.getProperty(field.getName());
                }
                field.setAccessible(true);
                field.set(t, fieldValue);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } finally {
            is.close();
        }
    }
}
