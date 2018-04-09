package common;

import com.google.common.collect.Maps;
import domain.Book;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: wangzx
 * 实体类转Map
 * @Date: 2018-04-09-15:52
 */
public class BeanToMap {
        public BeanToMap() {
        }
        public static Map<String, Object> bean2map(Object bean) throws IntrospectionException, IllegalAccessException, InvocationTargetException {
            Class<? extends Object> type = bean.getClass();
            Map<String, Object> returnMap = Maps.newHashMap();
            BeanInfo beanInfo = Introspector.getBeanInfo(type);
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();

            for(int i = 0; i < propertyDescriptors.length; ++i) {
                PropertyDescriptor descriptor = propertyDescriptors[i];
                String propertyName = descriptor.getName();
                if (!"class".equals(propertyName)) {
                    Method readMethod = descriptor.getReadMethod();
                    Object result = readMethod.invoke(bean);
                    if (result != null) {
                        returnMap.put(propertyName, result);
                    } else {
                        returnMap.put(propertyName, "");
                    }
                }
            }

            return returnMap;
        }
    }

