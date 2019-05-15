package cn.com.almostlover.annotation;


import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface TestField {
    String value();
    int id();
}
