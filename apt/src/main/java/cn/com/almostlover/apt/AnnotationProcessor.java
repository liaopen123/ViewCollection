package cn.com.almostlover.apt;

import com.google.auto.service.AutoService;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import javax.tools.Diagnostic;
import java.util.Set;


@AutoService(Processor.class)
@SupportedSourceVersion(SourceVersion.RELEASE_8)
@SupportedAnnotationTypes({"cn.com.almostlover.annotation.TestAPT"}) //标注注解处理器支持的注解类型，就是我们刚才定义的接口TestAPT，可以写入多个注解类型。
public class AnnotationProcessor extends AbstractProcessor {


    private Elements elementUtils;
    private Types typeUtils;
    private Filer filer;
    private Messager messager;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
         elementUtils = processingEnv.getElementUtils();//用来出来Element的工具类，element代表程序中的元素 就上面表所说的几个类型
         typeUtils = processingEnv.getTypeUtils();//用来处理typemirror相关的工具类
        filer = processingEnv.getFiler();//创建文件的相关类
         messager = processingEnv.getMessager();//打印日志的相关类

    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        messager.printMessage(Diagnostic.Kind.NOTE,"开始🌶开始🌶");
        MethodSpec main = MethodSpec.methodBuilder("main")//创建main方法
        .addModifiers(Modifier.PUBLIC,Modifier.STATIC)//定义修饰符
        .addJavadoc("@ 此类由apt生成")//在生成的代码前添加注释
        .returns(void.class)//设定返回值类型
        .addParameter(String[].class,"args")//设定参数值类型和变量名称
        .addStatement("$T.out.println($S)",System.class,"lph 真帅")//定义方法体
        .build();//进行构建


        TypeSpec helloWorldClass = TypeSpec.classBuilder("HelloWorld")//创建hello world类
                .addModifiers(Modifier.PUBLIC, Modifier.FINAL)
                .addMethod(main)
                .addJavadoc("@ 此类有apt自动生成")
                .build();


        JavaFile javaFile = JavaFile.builder("cn.com.almostlover.annotation", helloWorldClass).build();

        try {
            javaFile.writeTo(filer);
        } catch (Exception e) {
            e.printStackTrace();
        }


        return true;
    }
}


