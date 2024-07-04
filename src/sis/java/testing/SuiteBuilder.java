package testing;

import java.lang.reflect.Modifier;
import java.util.*;
import junit.runner.*;
import junit.framework.*;

public class SuiteBuilder {
    public List<String> gatherTestClassNames(){
        TestCollector collector = new ClassPathTestCollector(){
            public boolean isTestClass(String classFileName){
                if(!super.isTestClass(classFileName))
                    return false;
                String className = classNameFromFile(classFileName);
                Class klass = createClass(className);
                return TestCase.class.isAssignableFrom(klass) && isConcrete(klass);
            }
        };
        return Collections.list(collector.collectTests());
    }

    private boolean isConcrete(Class klass){
        if(klass.isInterface())
            return false;
        int modifiers = klass.getModifiers();
        return !Modifier.isAbstract(modifiers);
    }

    private Class createClass(String name){
        try {
            return Class.forName(name);
        } catch (ClassNotFoundException e){
            return null;
        }
    }

    public TestSuite suite() {
        List<String> testClassNames = gatherTestClassNames();
        TestSuite suite = new TestSuite();

        for(String className: testClassNames){
            try{
                Class klass = Class.forName(className);
                suite.addTestSuite(klass);
            } catch (ClassNotFoundException e){
                e.printStackTrace();
            }
        }
        return suite;
    }
}
