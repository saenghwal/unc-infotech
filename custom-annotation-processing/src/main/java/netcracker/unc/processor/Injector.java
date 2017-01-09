package netcracker.unc.processor;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Properties;

/*
    Класс Injector, в котором метод injector, который принимает объект, залезает в его поля, находит все поля помеченные
    @аннотацией определяет их принадлежность к интерфейсам и из файла настроек выбирает нужную реализацию интерфейса.
* */
public class Injector<T> {
    public T injector(T object) {
        FileInputStream fis;
        Class<?> acClass = object.getClass();
        Field[] classFields = acClass.getDeclaredFields();

        try {
            Properties properties = new Properties();
            fis = new FileInputStream(new File("src/main/resources/injection.properties"));
            properties.load(fis);

            for (Field field : classFields) {
                Class cs = Class.forName(properties.getProperty(field.getType().getName().toString()));
                field.setAccessible(true);
                field.set(object,cs.newInstance());
            }


        } catch (IOException e) {
            System.err.println("File properties not exist!");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return object;
    }

}
