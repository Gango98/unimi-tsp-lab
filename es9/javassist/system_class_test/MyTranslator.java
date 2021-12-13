import javassist.*;

public class MyTranslator implements Translator {
    public void start(ClassPool pool)
            throws NotFoundException, CannotCompileException {
        System.out.println("START");
    }

    public void onLoad(ClassPool pool, String classname)
            throws NotFoundException, CannotCompileException {
        System.out.println("ON_LOAD: " + classname);
        
        //CtClass cc = pool.get(classname);
        //cc.setModifiers(Modifier.PUBLIC);

        // String builder
        /*
        CtClass cls = pool.get("java.lang.StringBuilder");
        CtClass param = pool.get("java.lang.CharSequence");

        CtMethod method = cls.getDeclaredMethod("append", new CtClass[] { param });
        System.out.println("Method found: " + method);

        method.insertBefore("{ System.out.println(\"modified\"); }");
        */
    }
}