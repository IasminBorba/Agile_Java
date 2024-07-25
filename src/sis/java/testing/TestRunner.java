package testing;

import java.util.*;
import java.lang.reflect.*;

public class TestRunner {
    private Class testClass;
    private int failed = 0;
    private Set<Method> testMethods = null;

    public static void main(String[] args) throws Exception {
        TestRunner runner = new TestRunner(args[0]);
        runner.run();

        System.out.println("passed: " + runner.passed() + " failed: " + runner.failed());

        if(runner.failed() > 0)
            System.exit(1);
    }

    public TestRunner(Class testClass) {
        this.testClass = testClass;
    }
    public TestRunner(String className) throws Exception {
        this(Class.forName(className));
    }

    public Set<Method> getTestMethods() {
        if(testMethods == null)
            loadTestMethods();
        return testMethods;
    }

    private void loadTestMethods() {
        testMethods = new HashSet<>();
        for(Method method: testClass.getDeclaredMethods())
            testMethods.add(method);
    }

    public void run() {
        for (Method method: getTestMethods())
            run(method);
    }
    public void run(Method method) {
        try {
            Object testObject = testClass.newInstance();
            method.invoke(testObject, new Object[]{});
        } catch (InvocationTargetException e) {
            Throwable cause = e.getCause();
            if (cause instanceof AssertionError)
                System.out.println(cause.getMessage());
            else
                e.printStackTrace();
            failed++;
        } catch (Throwable t) {
            t.printStackTrace();
            failed++;
        }
    }

    public int passed() {
        return testMethods.size() - failed;
    }

    public int failed() {
        return failed;
    }
}
