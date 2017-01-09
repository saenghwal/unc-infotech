package netcracker.unc.processor;


import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Random;

import netcracker.unc.annotation.InjectRandomInt;

public class InjectRandomIntBeanProcessor {
    private Random random = new Random();

    public Object processAnnotation(Object bean, String beanName) throws Exception {
        Field[] fields = bean.getClass().getDeclaredFields();

        for (Field field : fields) {

            InjectRandomInt annotation = field.getAnnotation(InjectRandomInt.class);

            if (annotation != null) {

                if(!field.getType().equals(Integer.class))

                    throw new RuntimeException("don't put @InjectRandomInt above " + field.getType());

                if (Modifier.isFinal(field.getModifiers())) {

                    throw new RuntimeException("can't inject to final fields");

                }

                int min = annotation.min();

                int max = annotation.max();

                int randomInt = min + random.nextInt(max - min);

                try {

                    field.setAccessible(true);

                    field.set(bean,randomInt);

                } catch (IllegalAccessException e) {

                    throw new RuntimeException(e);

                }

            }

        }

        return bean;
    }
}
