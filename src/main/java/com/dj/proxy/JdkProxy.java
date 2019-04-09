package com.dj.proxy;

import javax.tools.JavaCompiler;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.FileWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * 1、拿到被代理对象的引用，并且获取到它的所有的接口，反射获取。
 * 2、JDK Proxy 类重新生成一个新的类、同时新的类要实现被代理类所有实现的所有的接
 * 口。
 * 3、动态生成 Java 代码，把新加的业务逻辑方法由一定的逻辑代码去调用（在代码中体
 * 现）。
 * 4、编译新生成的 Java 代码.class。
 * 5、再重新加载到 JVM 中运行。
 */
public class JdkProxy {
    private static final String LINE_END = "\r\n";
    public static Object newProxyInstance(JdkProxyClassLloader classLloader,Class<?>[] interfaces,JdkProxyHandle handle){
        try{
//            1.动态生成源代码
             String src = gengerateSrc(interfaces);
//            2.java文件保存
            String path = JdkProxy.class.getResource("").getPath();
            System.out.println("Java Path "+ path);
            File file = new File(path+"$Proxy0.java");
            if(file.exists()){
                if(! file.delete()){
                    throw new RuntimeException("已存在代理文件，请手动删除");
                }
            }
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(src);
            fileWriter.flush();
            fileWriter.close();
//            3.Java文件编译
            JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
            StandardJavaFileManager manager = compiler.getStandardFileManager(null,null,null);
            Iterable iterable = manager.getJavaFileObjects(file);
            JavaCompiler.CompilationTask task =
                    compiler.getTask(null,manager,null,null,null,iterable);
            task.call();
            manager.close();
//            4.class文件加载
            Class proxyClass = classLloader.findClass("$Proxy0");
            Constructor constructor = proxyClass.getConstructor(JdkProxyHandle.class);
            file.delete();
//            5.返回加载对象
            return constructor.newInstance(handle);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    private static String gengerateSrc(Class<?>[] interfaces) {
        StringBuffer src = new StringBuffer();
        src.append("package com.dj.proxy;" + LINE_END);
        src.append("import com.dj.proxy.Persion" + LINE_END);
        src.append("import java.lang.reflect.*;" + LINE_END);
        src.append("public final class $Proxy0 extends JdkProxy implements " + interfaces[0].getName());
        src.append("JdkProxyHandle handler;" + LINE_END);
        src.append("public $Proxy0(JdkProxyHandle handler)" + LINE_END +
                "{" + LINE_END +
                "this.handler = handler;" + LINE_END +
                "}" + LINE_END);

        for (Method method : interfaces[0].getMethods()) {
            src.append("public " + method.getReturnType().getName() + " " + method.getName() + "() {" +
                    LINE_END);
            src.append("try{" + LINE_END);
            src.append("Method m = " + interfaces[0].getName() + ".class.getMethod(\""
                    + method.getName() + "\",new Class[]{});" + LINE_END);
            src.append("this.handler.invoke(this,m,null);" + LINE_END);
            src.append("}catch(Throwable e){" + LINE_END);
            src.append("e.printStackTrace();" + LINE_END);
            src.append("}");
        }
        src.append("}" + LINE_END);
        return src.toString();
    }
}
