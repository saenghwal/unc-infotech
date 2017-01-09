package netcracker.unc.processor;

import java.lang.annotation.Annotation;

import netcracker.unc.annotation.Benchmark;
import netcracker.unc.annotation.FinanceAnnotation;
import netcracker.unc.logic.interfaces.FinanceInfoBuilder;

/*
 * Класс для создания экземпляра нужного класса
 */
@FinanceAnnotation(financeBuilder = "netcracker.unc.logic.impl.WebFinanceInfoBuilder")
public class FinanceInfoBuilderFactory {
    @Benchmark
    public static FinanceInfoBuilder getFinanceInfoBuilder() {
        try {
            // Получаем аннотацию к классу. Т.к. это наш класс, то можно его приводить
            Annotation ann = FinanceInfoBuilderFactory.class.getAnnotation(FinanceAnnotation.class);
            FinanceAnnotation fa = (FinanceAnnotation) ann;
            // Загружаем класс по имени
            Class cl = Class.forName(fa.financeBuilder());
            // Т.к. наш класс должен имплементировать интерфейс FinanceInfoBuilder
            // то мы можем сделать приведение к интерфейсу
            FinanceInfoBuilder builder = (FinanceInfoBuilder) cl.newInstance();
            return builder;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

}

