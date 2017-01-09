package netcracker.unc.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/*
* Аннотация, которая помечает класс как устаревший и подменяет его новым, который подается в аннотации
*
* */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface MyDeprecated {
    public Class newImpl() default Class.class;
}
