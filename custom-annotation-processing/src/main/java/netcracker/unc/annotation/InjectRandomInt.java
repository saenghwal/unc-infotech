package netcracker.unc.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/*
*  Аннотация, позволяющая в поле типа Integer инжектить рандомное число от min до max;
*
*  */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface InjectRandomInt {
    int min() default 0;
    int max() default 10;
}
