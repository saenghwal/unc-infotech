package netcracker.unc;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Method;

import netcracker.unc.annotation.AutoInject;
import netcracker.unc.annotation.TIgnore;
import netcracker.unc.annotation.TMarker;
import netcracker.unc.logic.EmployeeDTO;
import netcracker.unc.logic.FinanceInformation;
import netcracker.unc.logic.Null;
import netcracker.unc.logic.SomeBean;
import netcracker.unc.logic.TestAnnotation;
import netcracker.unc.logic.interfaces.FinanceInfoBuilder;
import netcracker.unc.processor.BenchmarkAnnotationProcessor;
import netcracker.unc.processor.GenericAnnotations;
import netcracker.unc.processor.InjectRandomIntBeanProcessor;
import netcracker.unc.processor.Injector;
import netcracker.unc.processor.FinanceInfoBuilderFactory;
import netcracker.unc.processor.TestAnnotationAnalyzer;

public class ObjectInjectionTest {

    public static EmployeeDTO employeeDTO;

    public EmployeeDTO getEmployeeDTO() {
        return employeeDTO;
    }

    @AutoInject
    public void setEmployeeDTO(EmployeeDTO employeeDTO) {
        this.employeeDTO = employeeDTO;
    }

    public static void main(String[] args) {
        Injector injector = new Injector();

        Object someClass = injector.injector(new SomeBean());
        ((SomeBean)someClass).doSome();

        ObjectInjectionTest injectionTest = new ObjectInjectionTest();
        GenericAnnotations.injectDependencies(injectionTest);
        InjectRandomIntBeanProcessor iribp = new InjectRandomIntBeanProcessor();

        try {
            iribp.processAnnotation(employeeDTO, employeeDTO.getClass().getName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(injectionTest.getEmployeeDTO().getFirstName()
                + injectionTest.getEmployeeDTO().getLastName() + " ("
                + injectionTest.getEmployeeDTO().getAge() +" years old)" );
        System.out.println( );

        // Получаем нужный объект
        FinanceInfoBuilder fib = FinanceInfoBuilderFactory.getFinanceInfoBuilder();

        FinanceInformation info = fib.buildFinanceInformation();
        BenchmarkAnnotationProcessor bap = new BenchmarkAnnotationProcessor();
        try {
            bap.processAnnotation(FinanceInfoBuilderFactory.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Вызов getClass() позволяет получить описание класса у объекта
        System.out.println("Имя нового класса:" + fib.getClass().getCanonicalName());
        // Дальше можем делать с полученной информацией все, что захотим

        TestAnnotationAnalyzer taa = new TestAnnotationAnalyzer();
        taa.processAnnotation();



    }
}
