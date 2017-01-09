package netcracker.unc.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/*
* FinanceAnnotation позволяет установить имя нужного класса
*
* */
@Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface FinanceAnnotation {
    // Объявляем параметр для имени класса со значением по умолчанию
    String financeBuilder() default "netcracker.unc.logic.impl.DbFinanceInfoBuilder";
}
