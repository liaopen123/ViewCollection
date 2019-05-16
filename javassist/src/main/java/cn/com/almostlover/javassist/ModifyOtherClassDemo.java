package cn.com.almostlover.javassist;

import javassist.*;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ModifyOtherClassDemo {


    public void test() {
        ClassPool pool = ClassPool.getDefault();
        try {
            CtClass ctClass = pool.getCtClass("cn.com.almostlover.javassist.MyClass");
            CtMethod method = ctClass.getDeclaredMethod("test");
            method.insertBefore("   System.out.println(\"gagagagagagagag\");");
            method.insertAfter("   System.out.println(\"hahhahahahahahahah\");");

            ctClass.writeFile();

            //通过反射

            Class<?> aClass = ctClass.toClass();
            Method mothod1 = aClass.getMethod("test", new Class[]{});
            Constructor<?> con = aClass.getConstructor(new Class[]{});
            mothod1.invoke(con.newInstance());

        } catch (NotFoundException e) {
            e.printStackTrace();
        } catch (CannotCompileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }
}
