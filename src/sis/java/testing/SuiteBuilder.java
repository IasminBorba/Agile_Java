package testing;

import java.util.*;
import junit.runner.*;

public class SuiteBuilder {
    public List<String> gatherTestClassNames(){
        TestCollector collector = new ClassPathTestCollector(){
            public boolean isTestClass(String classFileName){
                return classFileName.endsWith(".class") && classFileName.indexOf('$') < 0 && classFileName.indexOf("Test") > 0;
            }
        };
        return Collections.list(collector.collectTests());
    }
}
