package netcracker.unc.processor;

import netcracker.unc.annotation.AutoInject;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class GenericAnnotations {

    public static <T> T injectDependencies(final T obj) {
        try {
            Class clazz = obj.getClass();
            BeanInfo beanInfo = Introspector.getBeanInfo(clazz);
            for (PropertyDescriptor pd : beanInfo.getPropertyDescriptors()) {
                Method writeMethod = pd.getWriteMethod();

                if (writeMethod == null) {
                    continue;
                }

                Object existingVal = pd.getReadMethod().invoke(obj);
                if (existingVal == null) {
                    for (Annotation annotation : writeMethod.getAnnotations()) {
                        if (annotation instanceof AutoInject) {
                            Class propertyType = pd.getPropertyType();
                            writeMethod.invoke(obj, propertyType.newInstance());
                        }
                    }
                }
            }
        } catch (Exception e) {
            // do something intelligent :)
        }
        return obj;
    }
}




