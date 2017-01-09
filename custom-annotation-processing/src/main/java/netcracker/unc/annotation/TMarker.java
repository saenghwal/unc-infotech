package netcracker.unc.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import netcracker.unc.logic.Null;

/*
 * Аннотирует указатель на тестовый метод.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface TMarker {
    Class<? extends Throwable> expected() default Null.class;
}