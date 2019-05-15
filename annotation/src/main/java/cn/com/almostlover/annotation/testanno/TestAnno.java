package cn.com.almostlover.annotation.testanno;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TestAnno {

    public void testBug() {
        NoBug noBug = new NoBug();


        Method[] declaredMethods = noBug.getClass().getDeclaredMethods();

        for (Method item : declaredMethods) {

            //被检查注解的了的方法
            if (item.isAnnotationPresent(JianCha.class)) {
                try {
                    item.setAccessible(true);
                    item.invoke(noBug, null);
                } catch (Exception e) {
                    System.out.println("has error");
                }

            }
            System.out.println("test finish");
        }

    }
}
