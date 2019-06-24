package com.seewo.spider.util;

import com.seewo.spider.vo.MongoDBConfig;
import com.seewo.spider.vo.common.PropertiesPair;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 属性文件工具类
 *
 * @author qiuwuhui
 * @date 2019/6/6
 */
public class PropertiesUtils {

    private static Map<String, Object> beanMap = new HashMap<>();
    
    public static Properties getProperties(String filePath) {
        Properties p = new Properties();
        InputStream is = null;
        try {
            is = PropertiesUtils.class.getResourceAsStream(filePath);
            p.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return p;
    }

    public static <T> T propertiesToBean(String filepath, Class<T> clazz) {
        if (beanMap.containsKey(filepath)) {
            return (T) beanMap.get(filepath);
        }
        Properties properties = getProperties(filepath);
        Field[] fields = clazz.getDeclaredFields();
        T bean = null;
        try {
            bean = clazz.newInstance();
            for (Field field : fields) {
                PropertiesPair propertiesPair = field.getAnnotation(PropertiesPair.class);
                if (propertiesPair == null) {
                    continue;
                }
                field.setAccessible(true);
                String key = propertiesPair.key();
                String typeName = field.getGenericType().getTypeName();
                if ("java.lang.String".equals(typeName)) {
                    field.set(bean, properties.getProperty(key));
                } else if ("java.lang.Integer".equals(typeName) || "int".equals(typeName)){
                    field.set(bean, Integer.parseInt(properties.getProperty(key)));
                } else if ("java.lang.Boolean".equals(typeName) || "boolean".equals(typeName)) {
                    field.set(bean, Boolean.parseBoolean(properties.getProperty(key)));
                } else {
                    throw new RuntimeException("类型转换错误，不支持的类型：" + typeName);
                }
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        beanMap.put(filepath, bean);
        return bean;
    }

    public static void main(String[] args){
        MongoDBConfig mongoDBConfig = propertiesToBean("/mongodb.properties", MongoDBConfig.class);
        System.out.println(mongoDBConfig);
    }
}
