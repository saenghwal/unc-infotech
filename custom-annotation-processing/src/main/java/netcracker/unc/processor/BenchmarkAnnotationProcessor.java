package netcracker.unc.processor;

import java.lang.reflect.Method;

import netcracker.unc.annotation.Benchmark;

public class BenchmarkAnnotationProcessor {
    public void processAnnotation(Class<?> clazz) throws Exception {
        /*
         * Time of the test.
         */
        double time = 0;
        double newTime = 0;

        Method[] methods = clazz.getMethods();
        for (Method method : methods) {
            if (method.isAnnotationPresent(Benchmark.class)) {
                try {
                    long before = System.nanoTime();
                    time = System.nanoTime();
                    method.invoke(null);
                    long after = System.nanoTime();
                    newTime = System.nanoTime() - time;
                    time = newTime/1000000;
                    System.out.println("\r\nMETHOD " + method.getName() + "\r\nrun-time: " + time + " ms");
                } catch (Exception e) {

                }
            }
        }
    }
}
