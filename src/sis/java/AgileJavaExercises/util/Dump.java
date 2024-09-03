package AgileJavaExercises.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Dump {
    int order() default Integer.MAX_VALUE;

    boolean quote() default false;

    String[] outputMethods() default {"toString"};
}