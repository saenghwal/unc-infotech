package netcracker.unc.processor;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Method;

import netcracker.unc.annotation.MyTest;
import netcracker.unc.annotation.TIgnore;
import netcracker.unc.annotation.TMarker;
import netcracker.unc.logic.Null;
import netcracker.unc.logic.TestAnnotation;

public class TestAnnotationAnalyzer {
    /*
     * Indicates the test passed or not.
     */
    private static boolean statusTest = false;

    /*
     * Indicates the presence of Asserts test method.
     */
    private static boolean notAssert = true;

    public void parse(Class<?> clazz) throws Exception {
        Method[] methods = clazz.getMethods();
        int pass = 0;
        int fail = 0;

        for (Method method : methods) {
            if (method.isAnnotationPresent(MyTest.class)) {
                try {
                    method.invoke(null);
                    pass++;
                } catch (Exception e) {
                    fail++;
                }
            }
        }
    }

    public void analyz(Class<?> clazz) throws Exception {
        Method[] methods = clazz.getMethods();
        int pass = 0;
        int fail = 0;

        for (Method method : methods) {
            if (method.isAnnotationPresent(MyTest.class)) {
                // Получаем доступ к атрибутам
                MyTest test = method.getAnnotation(MyTest.class);
                Class expected = test.expected();
                try {
                    method.invoke(null);
                    pass++;
                } catch (Exception e) {
                    if (Exception.class != expected) {
                        fail++;
                    } else {
                        pass++;
                    }
                }
            }
        }
    }

    public void processAnnotation() {
        /*
         * Successful passage of the test.
         */
        int passed = 0;

        /*
         * The failure of the test.
         */
        int failed = 0;

        /*
         * An error occurred during the test.
         */
        int error  = 0;

        /*
         * The status of the test.
         */
        boolean ok = true;

        /*
         * Time of the test.
         */
        double time = 0;
        double newTime = 0;

        /*
         * Test results stored in the file.
         */
        File fileInfoTest = new File("src/main/resources/MyJUnitInfo.dat");  // /media/92DF-E892/Intellij IDEA/workspace/task4/src/
        try {
            fileInfoTest.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        FileWriter wFile = null;
        try {
            wFile = new FileWriter(fileInfoTest);
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedWriter buffWriteFile = new BufferedWriter(wFile);

        /*
         * The cycle performs a pass on all annotation methods.
         */
        for(Method m : TestAnnotation.class.getMethods()){
            if(m.isAnnotationPresent(TMarker.class)){
                TMarker an = m.getAnnotation(TMarker.class);
                try{
                    time = System.nanoTime();
                    m.invoke(new TestAnnotation());
                    newTime = System.nanoTime() - time;
                    time = newTime/1000000;
                    if(!an.expected().equals(Null.class)){
                        buffWriteFile.write("\r\nFor the method (" + m.getName() + ") does not meet the exception -> " + an.expected());
                    }else{
                        buffWriteFile.write("\r\nMETHOD " + m.getName() + "()");
                        buffWriteFile.write("\r\nRun-time: " + time + " ms");
                        if(statusTest){
                            passed++;
                            if(!notAssert){
                                buffWriteFile.write("\r\nTest: PASSED\r\n");
                                notAssert = true;
                            }
                            statusTest = false;
                        }else{
                            failed++;
                            if(!notAssert){
                                buffWriteFile.write("\r\nTest: FAILED\r\n");
                                notAssert = true;
                            }
                        }
                    }
                } catch (Throwable e){
                    if(an.expected().equals(e.getCause().getClass())){
                        try {
                            buffWriteFile.write("\r\nMETHOD " + m.getName() + "()");
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                        try {
                            buffWriteFile.write("\r\nRun-time: " + time + " ms");
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                        if(statusTest){
                            passed++;
                            try {
                                buffWriteFile.write("\r\nTest: PASSED\r\n");
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                        }else{
                            failed++;
                            try {
                                buffWriteFile.write("\r\nTest: FAILED\r\n");
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                            statusTest = false;
                        }

                    } else {
                        try {
                            buffWriteFile.write("\r\nTest "+m+" Error: "+e.getCause());
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                        System.err.printf("\r\nTest %s Error: %s %n", m, e.getCause());
                        error++;
                        ok = false;
                    }
                }
            }else if(m.isAnnotationPresent(TIgnore.class)){}
        }


        try {
            buffWriteFile.write("\r\n------------------------------");
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            buffWriteFile.write("\r\nPassed: " + passed + " Failed " + failed + " Error " + error);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            buffWriteFile.write("\r\nThe test is completed: " + (ok ? "OK" : "Failed"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            buffWriteFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        FileReader readFile = null;
        try {
            readFile = new FileReader(fileInfoTest);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BufferedReader readInBufferFile = new BufferedReader(readFile);

        String st;
        try {
            while ((st = readInBufferFile.readLine()) != null){
                System.out.println(st);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
    * The set of methods that claim that two objects are equal.
    */
    public static boolean myAssertEquals(Object obj1, Object obj2){
        boolean result = obj1.equals(obj2);
        if(result){
            statusTest = true;
        }
        notAssert = false;
        return result;
    }

    public static boolean myAssertEquals(int a, int b){
        boolean result = a==b;
        if(result){
            statusTest = true;
        }
        notAssert = false;
        return result;
    }

    public static boolean myAssertEquals(boolean result, boolean to){
        boolean resultTo = result==to;
        if(result){
            statusTest = true;
        }
        notAssert = false;
        return resultTo;
    }

    public static boolean myAssertEquals(String st1, String st2){
        boolean result = st1.equals(st2);
        if(result){
            statusTest = true;
        }
        notAssert = false;
        return result;
    }
}
