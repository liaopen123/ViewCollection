package cn.com.almostlover.javassist;

import javassist.*;
import javassist.bytecode.AccessFlag;

import java.lang.reflect.Field;

public class JavassistDemo {


    public void test() {
        ClassPool pool = ClassPool.getDefault();
        CtClass ctClass = pool.makeClass("cn.com.almostlover.javassist.MakeClassTest");
        ctClass.setInterfaces(new CtClass[]{pool.makeInterface("java.lang.Cloneable")});//让其实现cloneable

        try {
            //添加名为id 的int类型字段
            CtField id = new CtField(CtClass.intType, "id", ctClass);//创建一个名为id 的int 的变量
            id.setModifiers(AccessFlag.PUBLIC);//将该字段设置为public
            ctClass.addField(id);

            //添加构造
            CtConstructor constructor = CtNewConstructor.make("public MakeClassTest(int pId){this.id=pId;}", ctClass);
            ctClass.addConstructor(constructor);

            //添加方法
            CtMethod method = CtNewMethod.make("public void hello(String des){ System.out.println(des);}", ctClass);
            ctClass.addMethod(method);

            //最后写入文件 生成.class文件保存到磁盘
            ctClass.writeFile();


            //进行验证看看 好不好使
            Field[] fields = ctClass.toClass().getFields();
            System.out.println("属性名称：" + fields[0].getName() + "  属性类型：" + fields[0].getType());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
