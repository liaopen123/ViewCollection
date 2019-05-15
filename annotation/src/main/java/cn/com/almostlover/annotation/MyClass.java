package cn.com.almostlover.annotation;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class MyClass {


    public void testAnnotion(){
        Boolean annotation = TestAnn.class.isAnnotationPresent(TestClass.class);
        System.out.println("annotationannotationannotation"+annotation);
        if (annotation) {
            TestClass annotation1 = TestAnn.class.getAnnotation(TestClass.class);
            System.out.println(annotation1.value());
        }


        try {

            //获取field上面的注解值
            Field name = TestAnn.class.getDeclaredField("name");
            name.setAccessible(true);

            TestField testField = name.getAnnotation(TestField.class);
            if (testField != null) {
            System.out.println(testField.id());
            System.out.println(testField.value());
            }


            Method testMethod = TestAnn.class.getDeclaredMethod("testMethod");
            TestMethod testMethod1 = testMethod.getAnnotation(TestMethod.class);
            if (testMethod1 != null) {
                System.out.println(testMethod1.id());
                System.out.println(testMethod1.value());
            }


        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
}
